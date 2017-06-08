package com.charactercounter.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.charactercounter.service.FileUploadService;
/**
 * 
 * @author wangyuquan
 *
 */
@Controller
public class CharacterCounterByFileUploadController {
	/**
	 * @see 采用spring提供的上传文件的方法上传文件到本地
	 * @param file
	 * @param request
	 * @return 返回带有参数的ModelAndView实例
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("fileUpload.do")
    public ModelAndView fileUpload(@RequestParam(value="uploadFile", required=false) MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException
    {
		request.setCharacterEncoding("utf-8");
		//将文件上传存到本地
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		File targetFile = new File(uploadPath + "/upload/" + file.getOriginalFilename());
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdir();
		}
		
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//读取文件并统计单词数
		int num[] = FileUploadService.readAndCount(targetFile.getAbsolutePath());
		request.setAttribute("num0", num[0]);
		request.setAttribute("num1", num[1]);
		request.setAttribute("num2", num[2]);
		request.setAttribute("num3", num[3]);
		
		Map<Character, Integer> maxCCMap = FileUploadService.maxCharacterCounterByFile(targetFile.getAbsolutePath());
		
		return new ModelAndView("CharacterCounter", "maxResult", maxCCMap);
    }
	
}
