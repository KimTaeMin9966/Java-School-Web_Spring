package net.koreate.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import net.koreate.service.UserService;
import net.koreate.vo.UserVo;

public class CheckCookieInterceptor extends HandlerInterceptorAdapter {

	@Inject
	UserService service;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("userInfo") != null) {
			return true;
		}

		Cookie signInCookie = WebUtils.getCookie(request, "signInCookie");
		if (signInCookie != null) {
			int uno = Integer.parseInt(signInCookie.getValue());
			System.out.println("check Cookie uno : " + uno);
			UserVo vo = service.getUserByUNO(uno);
			if (vo != null) {
				session.setAttribute("userInfo", vo);
			}
		}
		return true;
	}

}
