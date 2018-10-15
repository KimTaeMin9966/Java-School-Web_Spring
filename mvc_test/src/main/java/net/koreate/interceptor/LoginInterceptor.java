package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.dto.LoginDto;
import net.koreate.service.UserService;
import net.koreate.vo.UserVo;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	private String login = "login";
	
	@Inject
	UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Login Pre Handle");
		
		HttpSession session = request.getSession();
		if(session.getAttribute(login) != null) {
			System.out.println("DATA Clear");
			session.removeAttribute(login);
			//session.invalidate();
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("Login Post Handle");

		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		LoginDto loginDto = (LoginDto)modelMap.get("loginDTO");
		
		UserVo vo = service.login(loginDto);
		System.out.println("postHandle : " + vo);
		
		if (vo != null) {
			session.setAttribute("login", vo);
			
			if(loginDto.isUseCookie()) {
				System.out.println("Cookie CREATE");
				
				Cookie cookie = new Cookie("loginCookie", vo.getUid());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);
				
				response.addCookie(cookie);
			}
		} else {
			response.sendRedirect("/user/login");
		}
		
		Object dest = session.getAttribute("dest");
		response.sendRedirect((dest != null) ? (String)dest : "/");
		
		/*Object userVo = modelMap.get("userVo");
		System.out.println(userVo);
		
		if (userVo != null) {
			session.setAttribute(login, userVo);
			
			if(request.getParameter("useCookie") != null) {
				System.out.println("Cookie CREATE");
				
				Cookie cookie = new Cookie("loginCookie", session.getId());
				cookie.setPath("/");
				cookie.setMaxAge(60 * 60 * 24 * 7);
				
				response.addCookie(cookie);
			}
		}*/
	}
	
}
