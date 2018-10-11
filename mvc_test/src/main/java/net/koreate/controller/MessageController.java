package net.koreate.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.koreate.service.MessageService;
import net.koreate.vo.MessageVo;

@RestController
@RequestMapping("/messages/*")
public class MessageController {
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@Inject
	MessageService service;
	
	//@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> addMessagePOST(@RequestBody MessageVo vo) throws Exception {
		logger.info("AddMessagePOST Called!!!"); ResponseEntity<String> entity = null;
		
		try { service.addMessage(vo); entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK); }
		catch (Exception e) { entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }
		
		return entity;
	}
	
	@RequestMapping(value = "/read/{mno}", method = RequestMethod.POST)
	public ResponseEntity<Object> readPOST(@RequestBody String uid, @PathVariable("mno") int mno) throws Exception {
		logger.info("ReadPOST Called!!!"); ResponseEntity<Object> entity = null;
		
		try { MessageVo vo = service.readMessage(uid, mno); entity = new ResponseEntity<>(vo, HttpStatus.OK); }
		catch (Exception e) { entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); }
		
		return entity;
	}
}
