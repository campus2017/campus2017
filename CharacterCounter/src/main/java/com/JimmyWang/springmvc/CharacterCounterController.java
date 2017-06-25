package com.JimmyWang.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/")
public class CharacterCounterController {
    @Autowired
    private CharacterCounterService service;
    @RequestMapping("CharacterCounter.html")
    public String page() {
        return "CharacterCounter";
    }

	@RequestMapping("TextCounter")
	public ModelAndView textCounter(String text) {
        ModelAndView mv = new ModelAndView("CharacterCounter");
        Word word = service.Count(text);
        List<Word> list = service.getTop3(text);
        mv.addObject("word",word);
        mv.addObject("top",list);
        return mv;
	}


	@RequestMapping("FileCounter")
	public ModelAndView fileCounter(@RequestParam("file")MultipartFile file) {
        ModelAndView mv = new ModelAndView("CharacterCounter");
        StringBuilder sb = new StringBuilder();
        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ( (line = reader.readLine())!=null){
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String text = sb.toString();
        Word word = service.Count(text);
        List<Word> list = service.getTop3(text);
        mv.addObject("word",word);
        mv.addObject("top",list);

        return mv;
	}
}
