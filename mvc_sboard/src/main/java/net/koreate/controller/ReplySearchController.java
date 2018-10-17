package net.koreate.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.BoardService;
import net.koreate.vo.PageMaker;
import net.koreate.vo.ReplyBoardVo;
import net.koreate.vo.SearchCriteria;

@Controller
@RequestMapping("/board/*")
public class ReplySearchController {
	private static final Logger logger = LoggerFactory.getLogger(ReplySearchController.class);
	
	@Inject
	BoardService service;

	@RequestMapping(value = "/listReply", method = RequestMethod.GET)
	public void replyListGET(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("ReplyListGET Called!!!");
		List<ReplyBoardVo> list = service.listReplyCriteria(cri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = service.getPageMaker(cri);
		model.addAttribute(pageMaker);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("RegisterGET Called!!!");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(ReplyBoardVo VO) throws Exception {
		logger.info("RegisterPOST Called!!!"); logger.info("VO : " + VO);
		
		service.registReply(VO);
		return "redirect:/board/listReply";
	}

	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPageGET(@RequestParam("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("ReadPageGET Called!!!");
		logger.info("updateCnt Called!!!"); service.updateCnt(bno);
		
		rttr.addAttribute("bno", bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/board/readDetail";
	}
	
	@RequestMapping(value = "/readDetail", method = RequestMethod.GET)
	public String readDetailGET(@ModelAttribute("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		logger.info("ReadDetailGET Called!!!");
		ReplyBoardVo VO = service.readReply(bno);
		
		model.addAttribute("boardVo", VO);
		return "/board/readPage";
	}

	@RequestMapping(value = "/replyRegister", method = RequestMethod.GET)
	public void replyRegisterGET(@ModelAttribute("bno") int bno,
			@ModelAttribute("cri") SearchCriteria cri,
			Model model) throws Exception {
		logger.info("ReplyRegisterGET Called!!!");
		model.addAttribute("boardVo", service.readReply(bno));
	}
	
	@RequestMapping(value = "/replyRegister", method = RequestMethod.POST)
	public String replyRegisterPOST(ReplyBoardVo VO,
			@ModelAttribute("cri") SearchCriteria cri,
			RedirectAttributes rttr) throws Exception {
		logger.info("ReplyRegisterPOST Called!!!"); logger.info("ReplyBoardVo : " + VO);
		service.replyRegister(VO);
		
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listReply";
	}

	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("RemovePOST Called!!!");
		service.remove(bno);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listReply";
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, @ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		logger.info("ModifyGET Called!!!");
		model.addAttribute("boardVo", service.readReply(bno));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(ReplyBoardVo VO, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("ModifyPOST Called!!!");
		service.modify(VO);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addFlashAttribute("results", "SUCCESS");
		return "redirect:/board/listReply";
	}
	
	@RequestMapping(value = "/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") int bno) throws Exception {
		logger.info("getAttach Called!!!");
		
		List<String> list = service.getAttach(bno);
		
		return list;
	}
}
















