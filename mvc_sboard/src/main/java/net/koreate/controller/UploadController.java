package net.koreate.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.koreate.util.MediaUtils;
import net.koreate.util.UploadFileUtils;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name = "uploadPath")
	String uploadPath;

	@RequestMapping(value = "/uploadForm", method = RequestMethod.GET)
	public void uploadFormGET() {
		logger.info("UploadFormGET Called!!!");
	}
	
	@RequestMapping(value = "/uploadForm", method = RequestMethod.POST)
	public String uploadFormPOST(MultipartFile file, Model model) throws IOException {
		logger.info("UploadFormPOST Called!!!");
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		
		String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
		model.addAttribute("savedName", savedName);
		return "uploadResult";
	}
	
	public String uploadFile(String originalName, byte[] fileData) throws IOException {
		logger.info("uploadFile Called!!!");
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName;
		File file = new File(uploadPath, savedName);
		
		FileCopyUtils.copy(fileData, file);
		return savedName;
	}

	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public void uploadAjaxGET() {
		logger.info("UploadAjaxGET Called!!!");
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjaxPOST(MultipartFile file) throws IOException {
		logger.info("UploadAjaxPOST Called!!!");
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		return new ResponseEntity<String>(UploadFileUtils.uploadFile(file.getOriginalFilename(), uploadPath, file.getBytes()), HttpStatus.CREATED);
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		logger.info("displayFile GET OR POST Called!!!");
		InputStream in = null; ResponseEntity<byte[]> entity = null;
		
		System.out.println(fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			System.out.println(formatName);
			
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			if (mType != null) headers.setContentType(mType);
			else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-disposition", "attachment;fileName=\""
							+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1") + "\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}
		catch (Exception e) { e.printStackTrace(); }
		return entity;
	}
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFilePOST(String fileName) {
		logger.info("DeleteFilePOST Called!!!");
		ResponseEntity<String> entity = null;
		
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		MediaType mType = MediaUtils.getMediaType(formatName);
		
		if(mType != null) {
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front + end).replace('/', File.separatorChar)).delete();
		}

		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		
		entity = new ResponseEntity<String>("deleted", HttpStatus.OK);
		
		return entity;
	}
	
}
