package net.koreate.controller;

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
import net.koreate.vo.Criteria;
import net.koreate.vo.PageMaker;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;

	// /WEB-INF/views/board/register.jsp
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("RegisterGET Called!!!");
	}

	/*@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerPOST(BoardVo boardVo,Model model) throws Exception {
		logger.info("RegisterPOST");
		service.regist(boardVo);
		model.addAttribute("result","SUCCESS");
		return "board/success";
	}*/

	// 2
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVo boardVo, RedirectAttributes rttr) throws Exception {
		logger.info("RegisterPOST Called!!!");
		System.out.println(boardVo);
		
		service.regist(boardVo);
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("ReadGET Called!!!");
		model.addAttribute("boardVo", service.read(bno));
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String listAll(Model model) throws Exception {
		logger.info("ListAll GET Called!!!");
		model.addAttribute("list", service.listAll());
		return "/board/listAll";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("ModifyGET Called!!!");
		model.addAttribute("boardVo", service.read(bno));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(BoardVo boardVo, RedirectAttributes rttr) throws Exception {
		logger.info("ModifyPOST Called!!!");
		System.out.println(boardVo);
		service.modify(boardVo);
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("RemovePOST Called!!!");
		service.remove(bno);
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listAll";
	}

	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri, Model model) throws Exception {
		logger.info("ListCri GET Called!!!");
		model.addAttribute("list", service.listCriteria(cri));
	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") Criteria cri,Model model) throws Exception {
		logger.info("ListPage GET Called!!!");
		model.addAttribute("list", service.listCriteria(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriteria());
		model.addAttribute(pageMaker);
		System.out.println(pageMaker);
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public String readPage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("ReadPage GET Called!!!");
		service.updateViewCnt(bno);
		rttr.addAttribute("bno", bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		return "redirect:/board/readDetail";
	}
	
	@RequestMapping(value = "/readDetail", method = RequestMethod.GET)
	public String readDetail(@ModelAttribute("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("ReadDetail GET Called!!!"); logger.debug("bno : " + bno);
		model.addAttribute("boardVo", service.read(bno));
		return "/board/readPage";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("ModifyPageGET Called!!!");
		model.addAttribute("boardVo", service.read(bno));
	}

	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVo boardVo, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("ModifyPagePOST Called!!!");
		System.out.println(boardVo);
		service.modify(boardVo);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(@RequestParam("bno") int bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) throws Exception {
		logger.info("RemovePagePOST Called!!!");
		service.remove(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/board/listPage";
	}
	
}
