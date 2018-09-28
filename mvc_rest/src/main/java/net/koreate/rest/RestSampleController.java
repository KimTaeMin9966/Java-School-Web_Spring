package net.koreate.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.koreate.vo.SampleVO;

@RestController
@RequestMapping("/sample")
public class RestSampleController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World!!!";
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		SampleVO VO = new SampleVO();
		VO.setName("김태민");
		VO.setAge(20);
		return VO;
	}
	
	@RequestMapping("/sendList")
	public List<SampleVO> sendList() {
		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			SampleVO VO = new SampleVO();
			VO.setName("김태민" + i);
			VO.setAge(20);
			list.add(VO);
		}
		return list;
	}
	
	@RequestMapping("/sendMap")
	public Map<Integer, SampleVO> sendMap() {
		Map<Integer, SampleVO> maps = new HashMap<>();
		for (int i = 0; i < 50; i++) {
			SampleVO VO = new SampleVO();
			VO.setName("김태민" + i);
			VO.setAge(20);
			maps.put(i, VO);
		}
		return maps;
	}

	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendErrorAuth() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendErrorNot() {
		List<SampleVO> list = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			SampleVO VO = new SampleVO();
			VO.setName("김태민" + i);
			VO.setAge(20);
			list.add(VO);
		}
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}
	
}
