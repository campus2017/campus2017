package cn.itcast.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.itcast.service.CountChar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/count")
public class Count {
	@Autowired
	private CountChar countChar;
	
	@RequestMapping(value = "/fileCount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> textCount(
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String str = null;
		try {
			str = new String(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Integer> result=countChar.count(str);
		return result;
	}
	
	@RequestMapping(value = "/stringCount", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Integer> testCount(
			@RequestParam("string") String str, HttpServletRequest request) {
	
		Map<String, Integer> result=countChar.count(str);
		return result;
	}
}
