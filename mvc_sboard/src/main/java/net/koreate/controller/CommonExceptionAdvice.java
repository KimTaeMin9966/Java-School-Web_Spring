package net.koreate.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	private ModelAndView errorModelAndView(Exception e) {
		e.printStackTrace();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("error_common");
		mav.addObject("exception", e);
		return mav;
	}
}
