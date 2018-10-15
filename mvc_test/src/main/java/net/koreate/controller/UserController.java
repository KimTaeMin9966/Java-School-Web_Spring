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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import net.koreate.dto.LoginDto;
import net.koreate.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET() throws Exception {
		logger.info("LoginGET Called!!!");
	}

	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDto dto, Model model) throws Exception {
		logger.info("LoginPOST Called!!!"); logger.info("DTO : " + dto);
		model.addAttribute("loginDTO", dto);
		
		/*UserVo vo = service.login(dto); logger.info("VO : " + vo);
		
		if (vo != null) model.addAttribute("userVo", vo);*/
	}
	
	@RequestMapping(value = "/logOut", method = RequestMethod.GET)
	public String logOutGET(HttpServletRequest request,
			HttpSession session,
			HttpServletResponse response) throws Exception {
		logger.info("LogOutGET Called!!!");
		
		Object obj = session.getAttribute("login");
		
		if (obj != null) {
			session.removeAttribute("login");
			session.invalidate();
			
			/*Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("loginCookie")) {
					Cookie c = new Cookie("loginCookie", "");
					c.setPath("/");
					c.setMaxAge(0);
					response.addCookie(cookie);
				}
			}*/
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
			}
		}
		return "redirect:/";
	}
	
}
