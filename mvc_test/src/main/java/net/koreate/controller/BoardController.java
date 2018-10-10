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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() { logger.info("RegisterGET Called!!!"); }
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVo VO, RedirectAttributes rttr) throws Exception {
		logger.info("RegisterPOST Called!!!");
		System.out.println(VO);
		service.create(VO);
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPageGET(@ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("ListPageGET Called!!!");
		List<BoardVo> list = service.listCriteria(cri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = service.getPageMaker(cri);
		model.addAttribute(pageMaker);
	}
}
