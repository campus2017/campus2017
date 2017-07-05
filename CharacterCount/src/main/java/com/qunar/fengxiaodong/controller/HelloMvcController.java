package com.qunar.fengxiaodong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloMvcController {
	
	@RequestMapping("/mvc")
	public String helloMvc() {
		
		//��ͼ��Ⱦ��/WEB-INF/jsps/home.jsp
		return "success";
	}
	
	@RequestMapping("/file")
	public String file() {
		
		//��ͼ��Ⱦ��/WEB-INF/jsps/home.jsp
		return "file";
	}

}
