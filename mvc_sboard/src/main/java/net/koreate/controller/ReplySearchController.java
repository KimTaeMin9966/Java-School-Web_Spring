package net.koreate.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.koreate.service.BoardService;
import net.koreate.vo.ReplyBoardVo;

@Controller
@RequestMapping("/board/*")
public class ReplySearchController {
	private static final Logger logger = LoggerFactory.getLogger(ReplySearchController.class);
	
	@Inject
	BoardService service;

	@RequestMapping(value = "/listReply", method = RequestMethod.GET)
	public void replyListGET() {
		logger.info("ReplyListGET Called!!!");
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("RegisterGET Called!!!");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(ReplyBoardVo VO) throws Exception {
		logger.info("RegisterPOST Called!!!");
		System.out.println(VO);
		
		service.registReply(VO);
		return "redirect:/board/listReply";
	}

}
