package net.koreate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import net.koreate.service.CommentService;
import net.koreate.vo.CommentVo;
import net.koreate.vo.PageMaker;

@RestController
@RequestMapping("/comments")
public class CommentController {
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Inject
	CommentService service;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> registerPOST(@RequestBody CommentVo VO) {
		logger.info("registerPOST Called!!"); ResponseEntity<String> entity = null;
		
		try {
			service.addComment(VO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch (Exception e) { entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST); }
		return entity;
	}
	
	@RequestMapping(value = "/all/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<CommentVo>> listGET(@PathVariable("bno") int bno) {
		logger.info("listGET Called!!"); ResponseEntity<List<CommentVo>> entity = null;
		
		try {
			List<CommentVo> list = service.listComment(bno);
			entity = new ResponseEntity<List<CommentVo>>(list, HttpStatus.OK);
		}
		catch (Exception e) { e.getMessage(); entity = new ResponseEntity<List<CommentVo>>(HttpStatus.BAD_REQUEST); }
		return entity;
	}
	
	@RequestMapping(value = "/{cno}", method = {RequestMethod.PUT, RequestMethod.PATCH})
	public ResponseEntity<String> update(@PathVariable("cno")int cno, @RequestBody CommentVo VO) {
		logger.info("updatePUT OR PATCH Called!!"); ResponseEntity<String> entity = null;
		
		try {
			VO.setCno(cno); service.modifyComment(VO);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/{cno}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("cno") int cno) {
		logger.info("deleteDELETE Called!!"); ResponseEntity<String> entity = null;
		
		try {
			service.removeComment(cno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/{bno}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") int bno, @PathVariable("page") int page) {
		logger.info("listPageGET Called!!"); ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();

		try {
			PageMaker pageMaker = service.getPageMaker(page, bno);
			List<CommentVo> list = service.listCommentPage(bno, pageMaker.getCri());
			
			map.put("pageMaker", pageMaker); map.put("list", list);
			
			entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace(); entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
