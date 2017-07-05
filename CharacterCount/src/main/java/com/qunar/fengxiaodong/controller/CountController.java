package com.qunar.fengxiaodong.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qunar.fengxiaodong.model.MostNum;
import com.qunar.fengxiaodong.model.Result;
import com.qunar.fengxiaodong.service.CountService;


@Controller
@RequestMapping("/Count")
// /courses/**
public class CountController {
	
	private static Logger log = LoggerFactory.getLogger(CountController.class);

	private CountService countService;

	@Autowired
	public void setCountService(CountService countService) {
		this.countService = countService;
	}

	
	@RequestMapping(value="/text", method = RequestMethod.POST)
	public String  doCountString(@RequestParam("text") String text, Model model){		
		
		log.debug("Info of text:", text);
		//text = "AAB1D2CA汉字大大大.///./";
		Result result = countService.statisticCharacterCountByString(text);
		if(result != null)
		{
		List<MostNum> listMostNum = result.getLisMostNum();
		
		model.addAttribute(result);
		log.debug("RES 为：", result.toString());
		model.addAttribute("statistics", result.getSt());
		return "success";
		}
		
		return "error";
	}
	
	
	@RequestMapping(value="/doUpload", method=RequestMethod.POST)
	public String doCountFile(@RequestParam("file") MultipartFile file, Model model) throws IOException{
		
		if(!file.isEmpty()){
			log.debug("Process file: {}", file.getOriginalFilename());
			Result result = countService.statisticCharacterCountByFile(file);
			model.addAttribute(result);
			model.addAttribute("statistics", result.getSt());
			return "success";
		}
		
		return "error_file_upload";
	}
	
	

	
	
}
