package net.koreate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() { logger.info("RegisterGET Called!!!"); }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void registerPOST() {
		logger.info("RegisterPOST Called!!!");
	}
	
}
