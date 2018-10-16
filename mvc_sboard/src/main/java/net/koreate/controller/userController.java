package net.koreate.controller;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import net.koreate.dto.LoginDto;
import net.koreate.service.UserService;
import net.koreate.vo.UserVo;

@Controller
@RequestMapping("/user/*")
public class userController {
	private static final Logger logger = LoggerFactory.getLogger(userController.class);
	
	@Inject
	UserService service;
	
	@RequestMapping(value = "/signIn")
	public void signInGET(@ModelAttribute("message") String message, Model model, HttpServletRequest request) throws Exception {
		logger.info("SignInGET Called!!!");
		
		String msg = (String)request.getAttribute("message");
		System.out.println(msg);
		
		if(msg != null) {
			model.addAttribute("message", msg);
		}
		
		logger.info("message : " + message);
	}
	
	@RequestMapping(value = "/signUp")
	public void signUpGET() throws Exception {
		logger.info("SignUpGET Called!!!");
	}

	@RequestMapping(value = "/signInPost", method = RequestMethod.POST)
	public String signInPOST(LoginDto dto, Model model) throws Exception {
		logger.info("SignInPOST Called!!!"); logger.info("DTO : " + dto);
		
		model.addAttribute("loginDto", dto);

		return "home";
	}
	
	@RequestMapping(value = "/signUpPost")
	public String signUpPOST(UserVo vo, Model model) throws Exception {
		logger.info("SignUpPOST Called!!!"); logger.info("VO : " + vo);
		
		service.signUp(vo);
		model.addAttribute("message", "회원가입에 성공 하였습니다.");
		return "redirect:/user/signIn";
	}

	@RequestMapping(value = "/signOut")
	public String signOutGET_POST(HttpServletRequest request, HttpSession session, HttpServletResponse res) throws Exception {
		logger.info("SignOutGET & POST Called!!!");
		Object obj = session.getAttribute("userInfo");
		
		if (obj != null) {
			session.removeAttribute("userInfo");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "signInCookie");
			
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				res.addCookie(loginCookie);
			}
		}
		return "redirect:/";
	}
}
