package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.UserService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("userInfo") == null) {
			String uri = request.getRequestURI();
			String query = request.getQueryString();
			
			if (query == null || query.equals("null")) { query = ""; }
			else { query = "?" + query; }
			
			if(request.getMethod().equals("GET")) { System.out.println("요청 : " + (uri + query)); session.setAttribute("dest", (uri + query)); }
			
			response.sendRedirect("/user/signIn");
			return false;
		}
		return true;
	}
	
}
