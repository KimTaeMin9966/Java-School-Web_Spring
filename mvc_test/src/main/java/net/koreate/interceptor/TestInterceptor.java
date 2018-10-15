package net.koreate.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TestInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("pre Handler........................");
		
		HandlerMethod method = (HandlerMethod)handler;
		Method methodObj = method.getMethod();

		System.out.println("bean : " + method.getBean());
		System.out.println("Method : " + methodObj);
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("post Handler.......................");
		
		Object result = modelAndView.getModel().get("result");
		System.out.println("result : " + result);
		
		if(result != null) {
			request.getSession().setAttribute("result", result);
			/*modelAndView.addObject("result", result);*/
			response.sendRedirect("/");
		}
		
	}
}
