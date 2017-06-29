package com.lrz.work.controller;

import com.lrz.work.pojo.TongjiResult;
import com.lrz.work.service.TongjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/work")
public class WorkController {

	
	@Autowired
	public TongjiService tongjiService;
	
	@RequestMapping("/index")
	public String index(){
		return "index";
	}
	@RequestMapping(value="/tongji",method=RequestMethod.POST)
	@ResponseBody
	public TongjiResult tongji(@RequestParam(defaultValue="")String content){
		return tongjiService.tongji(content);
	}
	
	@RequestMapping("/upload")
	@ResponseBody
    public TongjiResult upload(@RequestParam(value = "file", required = false) MultipartFile file) throws Exception{
		return tongjiService.tongji(file);
	}
}
