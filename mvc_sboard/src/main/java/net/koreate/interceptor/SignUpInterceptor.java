package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import net.koreate.service.UserService;
import net.koreate.vo.UserVo;

public class SignUpInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	UserService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uid = request.getParameter("uid"); System.out.println("uid : " + uid);
		
		UserVo vo = service.getUserByID(uid);
		
		if(vo != null) {
			System.out.println("Interceptor VO : " + vo);
		
			RequestDispatcher rd = request.getRequestDispatcher("/user/signIn");
			request.setAttribute("message", uid + "는 이미 존제하는 아이디 입니다.");
			rd.forward(request, response);
			return false;
		}
		return true;
	}
	
}
