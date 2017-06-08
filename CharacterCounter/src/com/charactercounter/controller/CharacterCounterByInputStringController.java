package com.charactercounter.controller;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.charactercounter.service.CharacterCounterService;

/**
 * 
 * @author wangyuquan
 *
 */

@Controller
public class CharacterCounterByInputStringController{
	
	/**
	 * @see 对通过文本输入的文字进行处理
	 * @param req
	 * @param resp
	 * @return 返回带有参数的ModelAndView实例
	 * @throws ServletException
	 * @throws IOException
	 */
	
	@RequestMapping("submitInputText.do")
	public static ModelAndView characterCounter(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {		
		req.setCharacterEncoding("utf-8");
		String str = req.getParameter("text");
		
//		System.out.println("str:" + str);
		
		CharacterCounterService ccs = new CharacterCounterService();
    	int[] num = ccs.count(str);

    	req.setAttribute("num0", num[0]);
    	req.setAttribute("num1", num[1]);
    	req.setAttribute("num2", num[2]);
    	req.setAttribute("num3", num[3]);
    	
//    	System.out.println("1:" + num[0] + " " + num[1] + " " + num[2] + " " + num[3]);
    	
    	Map<Character, Integer> map = ccs.characterCounter(str);
        Map<Character, Integer> maxCCMap = ccs.maxCharacterCounter(map);

        return new ModelAndView("CharacterCounter", "maxResult", maxCCMap);
	}
}
