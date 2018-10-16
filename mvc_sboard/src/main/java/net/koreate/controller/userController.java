package net.koreate.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.koreate.dto.LoginDto;
import net.koreate.service.UserService;
import net.koreate.vo.UserVo;

@Controller
@RequestMapping("/user/*")
public class userController {
	private static final Logger logger = LoggerFactory.getLogger(userController.class);
	
	@Inject
	UserService service;
	
	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public void signInGET() throws Exception {
		logger.info("SignInGET Called!!!");
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public void signUpGET() throws Exception {
		logger.info("SignUpGET Called!!!");
	}

	@RequestMapping(value = "/signInPost", method = RequestMethod.POST)
	public String signInPOST(LoginDto dto) throws Exception {
		logger.info("SignInPOST Called!!!"); logger.info("DTO : " + dto);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/signUpPost", method = RequestMethod.POST)
	public String signUpPOST(UserVo vo) throws Exception {
		logger.info("SignUpPOST Called!!!"); logger.info("VO : " + vo);
		
		service.signUp(vo);
		return "redirect:/user/signIn";
	}
	
}
