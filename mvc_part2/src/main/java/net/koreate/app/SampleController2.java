package net.koreate.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {

	@RequestMapping("doC")
	public String doC() {
		System.out.println("doC 호출");
		return "result";
	}

	@RequestMapping("doD")
	public String doD(@ModelAttribute("msg") String msg) {
		System.out.println("doD");
		return "result";
	}

}