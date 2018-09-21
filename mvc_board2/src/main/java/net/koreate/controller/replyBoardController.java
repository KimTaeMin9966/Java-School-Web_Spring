package net.koreate.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.koreate.service.replyBoardService;
import net.koreate.vo.replyBoardVo;

@Controller
@RequestMapping("/replyboard/*")
public class replyBoardController {
	private static final Logger logger = LoggerFactory.getLogger(replyBoardController.class);

	@Inject
	private replyBoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() {
		logger.info("Register GET Called!!!");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(replyBoardVo VO, RedirectAttributes rttr) throws Exception {
		logger.info("Register POST Called!!!");
		System.out.println(VO);
		
		service.regist(VO);
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/replyboard/listShow";
	}
	
	@RequestMapping(value = "/listShow", method = RequestMethod.GET)
	public void replyBoardListALLShow(Model model) throws Exception {
		logger.info("replyBoardListALLShow GET Called!!!");
		model.addAttribute("list", service.listAllShow());
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readGET(@RequestParam("bno") int bno, RedirectAttributes rttr, Model model) throws Exception {
		logger.info("ReadGET Called!!!");
		//service.updateViewCnt(bno);
		rttr.addAttribute("bno", bno);
		model.addAttribute("reBoardVO", service.read(bno));
	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removePOST(@RequestParam("bno") int bno, RedirectAttributes rttr) throws Exception {
		logger.info("RemovePOST Called!!!");
		service.remove(bno);
		
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/replyboard/listShow";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public void modifyGET(@RequestParam("bno") int bno, Model model) throws Exception {
		logger.info("ModifyGET Called!!!");
		model.addAttribute("reBoardVO", service.read(bno));
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyPOST(replyBoardVo VO, RedirectAttributes rttr) throws Exception {
		logger.info("ModifyPOST Called!!!");
		System.out.println(VO);
		service.modify(VO);
		
		rttr.addFlashAttribute("result", "SUCCESS");
		return "redirect:/replyboard/listShow";
	}
}
