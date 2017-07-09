package com.qunar.clp.controller;

import com.qunar.clp.common.FileUtils;
import com.qunar.clp.pojo.CountDto;
import com.qunar.clp.service.CountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by nipingchen on 16-12-20.
 */
@Controller
public class HomeController {
    private static final Logger logger= LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private CountsService countsService;

    @RequestMapping(value="/home")
    public String home(){
        logger.info("统计首页");
        return "home";
    }
    @RequestMapping(value="/addContextAndAnalysis",method =RequestMethod.POST,params = "inputText")
    public String analysisCharacter(@RequestParam("inputText") String str, Model model){
        logger.info("{}",str);
        CountDto countDto=countsService.count(str);
        model.addAttribute("countDto",countDto);
        return "home";
    }
    @RequestMapping(value="/uploadFileAndAnalysis",method =RequestMethod.POST)
    public String saveFile(Model model,HttpServletRequest servletRequest, @RequestParam(value = "inputFile") MultipartFile multipartFile){
        try{
            if(multipartFile!=null) {
                logger.info("文件{}", multipartFile.getOriginalFilename());
                InputStream inputStream=multipartFile.getInputStream();
                String str= FileUtils.readToString(inputStream);
                logger.info("{}",str);
                CountDto countDto=countsService.count(str);
                model.addAttribute("countDto",countDto);
                return "home";
            }
        }catch(IOException e){
            logger.error("{}",e);
        }
        return "home";
    }

}
