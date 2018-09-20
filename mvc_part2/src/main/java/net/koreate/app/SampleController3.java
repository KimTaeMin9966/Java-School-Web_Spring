package net.koreate.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.koreate.vo.ProductVo;

@Controller
public class SampleController3 {

	@RequestMapping("doE")
	public String doE(Model model) {
		ProductVo product = new ProductVo("Sample", 10000);
		System.out.println(product.toString());
		// model.addAttribute(product);
		model.addAttribute("product", product);
		return "productDetail";
	}

}
