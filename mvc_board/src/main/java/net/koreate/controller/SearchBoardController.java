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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.vo.BoardVo;
import net.koreate.vo.PageMaker;
import net.koreate.vo.SearchCriteria;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);

	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void listGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("List GET Called!!!");
		List<BoardVo> list = service.listSearchCriteria(cri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("ReadPage GET Called!!!");
		service.updateViewCnt(bno);
		rttr.addAttribute("bno", bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/sboard/readDetail";
	}
	
	@RequestMapping(value = "/readDetail", method = RequestMethod.GET)
	public String readDetail(@ModelAttribute("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("ReadDetail GET Called!!!"); logger.debug("bno : " + bno);
		model.addAttribute("boardVo", service.read(bno));
		return "/sboard/readPage";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception {
		logger.info("Register GET Called!!!");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVo boardVo, Model model, RedirectAttributes rttr) throws Exception {
		logger.info("Register POST Called!!!");
		service.regist(boardVo);
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/sboard/list"; 
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("ModifyPage GET Called!!!");
		model.addAttribute("boardVo", service.read(bno));
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVo boardVo, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("ModifyPage GET Called!!!");
		service.modify(boardVo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePagePOST(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("RemovePage POST Called!!!");
		service.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/sboard/list";
	}
	
	
	
	
	
	
	
	
	
}
