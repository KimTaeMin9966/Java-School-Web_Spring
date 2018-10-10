package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.koreate.service.BoardService;
import net.koreate.vo.BoardVo;
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	BoardService service;

	@RequestMapping(value = "/register")
	public void register() { logger.info("Register Called!!!"); }
	
	/*@RequestMapping(value = "/registerBoard", method = RequestMethod.POST)
	public String registerBoardPOST(HttpServletRequest request) throws Exception {
		logger.info("RegisterBoardPOST Called!!!");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writer = request.getParameter("writer");

		System.out.println("title : " + title);
		System.out.println("content : " + content);
		System.out.println("writer : " + writer);
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		System.out.println(vo);
		
		return "/board/listPage";
	}*/
	
	/*@RequestMapping(value = "/registerBoard", method = RequestMethod.POST)
	public String registerBoardPOST(BoardVo vo, Model model) throws Exception {
		logger.info("RegisterBoardPOST Called!!!");
		System.out.println(vo);
		
		service.create(vo);
		//model.addAttribute("boardVo", vo);
		model.addAttribute(vo);
		
		return "/board/listPage";
	}*/
	
	@RequestMapping(value = "/registerBoard", method = RequestMethod.POST)
	public String registerBoardPOST(@ModelAttribute("boardVo") BoardVo vo) throws Exception {
		logger.info("RegisterBoardPOST Called!!!");
		System.out.println(vo);
		
		service.create(vo);
		
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPageGET(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("ListPageGET Called!!!");
		List<BoardVo> list = service.listCriteria(cri);
		//List<BoardVo> list = service.listAll();
		model.addAttribute("list", list);
		
		PageMaker pageMaker = service.getPageMaker(cri);
		System.out.println(pageMaker);
		model.addAttribute(pageMaker);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("ReadGET Called!!! " + bno);
		BoardVo vo = service.read(bno);
		model.addAttribute("boardVo", vo);
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("ModifyGET Called!!!");
		model.addAttribute("boardVo", service.read(bno));
	}
	
	@RequestMapping(value = "/modifyAccept", method = RequestMethod.POST)
	public String modifyAcceptPOST(BoardVo vo) throws Exception {
		logger.info("ModifyAcceptPOST Called!!!");
		System.out.println(vo);
		service.update(vo);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGET(@RequestParam("bno") int bno) throws Exception {
		logger.info("DeleteGET Called!!!");
		service.delete(bno);
		return "redirect:/board/listPage";
	}
}
