package net.koreate.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.koreate.service.MemberService;
import net.koreate.vo.MemberVo;

@Controller
public class testController {
	
	@Inject
	MemberService service;

	@RequestMapping(value = "/member", method = RequestMethod.GET)
	public void home() {
		
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public void insert(MemberVo VO) throws Exception {
		VO.setUserid("user01");
		VO.setUserpw("user01");
		VO.setUsername("USER01");
		VO.setEmail("user00@aaa.com");
		service.insert(VO);
	}
	
	
}
