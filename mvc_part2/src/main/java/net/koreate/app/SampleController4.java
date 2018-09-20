package net.koreate.app;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.koreate.vo.ProductVo;

@Controller
public class SampleController4 {

	@RequestMapping("/doJson")
	public @ResponseBody ProductVo doJSON() {
		ProductVo product = new ProductVo("sample2", 10000);
		return product;
	}

	@RequestMapping("/doJsonList")
	public @ResponseBody ArrayList<ProductVo> doJsonList() {
		ArrayList<ProductVo> list = new ArrayList<>();

		list.add(new ProductVo("sample1", 10000));
		list.add(new ProductVo("sample2", 20000));
		list.add(new ProductVo("sample3", 30000));

		return list;
	}

}
