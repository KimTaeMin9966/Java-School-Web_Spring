package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import net.koreate.service.UserService;
import net.koreate.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Inject
	UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("login") == null) {
			String uri = request.getRequestURI();
			System.out.println("URI : " + uri);
			
			String query = request.getQueryString();
			System.out.println("Query : " + query);
			
			if (query == null || query.equals("null")) { query = ""; }
			else { query = "?" + query; }
			
			if(request.getMethod().equals("GET")) { System.out.println("요청 : " + (uri + query)); session.setAttribute("dest", (uri + query)); }
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if (loginCookie != null) {
				String uid = loginCookie.getValue();
				UserVo vo = service.getUser(uid);
				
				if (vo != null) {
					session.setAttribute("login", vo);
					return true;
				}
			}
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
	}
	
}
