package com.campus.homework.controller;

import com.campus.homework.service.CounterService;
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
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;


/**
 * Created by Administrator on 2017/6/29.
 */
@Controller
@RequestMapping("/")
public class CharacterCounterController {
    public static Logger log = LoggerFactory.getLogger(CharacterCounterController.class);
    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/view")
    public String view(){
        return "charactercounter";
    }

    @RequestMapping(value="/doUpFile",method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file, Model model){
        try {
            if (!file.isEmpty()) {
                //将字符流以缓存的形式一行一行输出
                BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
                Map countMap = counterService.countCharacter(br);
                Map sortMap = counterService.SortCharacter();
                sendParam(countMap, sortMap, model);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "charactercounter";
    }

    @RequestMapping(value="/doUpText",method = RequestMethod.POST)
    public String doUploadText(HttpServletRequest request,Model model){
        String str=request.getParameter("text");
        if(!str.equals("") && str!=null) {
            log.debug("Text up load success!\n" + str);
            Map countMap = counterService.countCharacter(str);
            Map sortMap = counterService.SortCharacter();
            sendParam(countMap, sortMap, model);
        }
        return "charactercounter";
    }

    public void sendParam(Map countMap, Map sortMap, Model model){
        for (Object entry : countMap.entrySet())
            log.debug("Key = " + ((Map.Entry) entry).getKey() + ",value=" + ((Map.Entry) entry).getValue());
        model.addAttribute("letter", countMap.get("letters"));
        model.addAttribute("number", countMap.get("numbers"));
        model.addAttribute("chinese", countMap.get("chineses"));
        model.addAttribute("punctuation", countMap.get("punctuations"));
        Iterator it = sortMap.entrySet().iterator();
        Map.Entry e = null;
        for (int i = 0; i < 3; i++) {
            if(it.hasNext()){
                switch (i){
                    case 0:
                        e = (Map.Entry)it.next();
                        model.addAttribute("firstChar", e.getKey());
                        model.addAttribute("firstNum", e.getValue());
                        break;
                    case 1:
                        e = (Map.Entry)it.next();
                        model.addAttribute("secondChar", e.getKey());
                        model.addAttribute("secondNum", e.getValue());
                        break;
                    case 2:
                        e = (Map.Entry)it.next();
                        model.addAttribute("thirdChar", e.getKey());
                        model.addAttribute("thirdNum", e.getValue());
                        break;
                    default:
                        log.debug("wrrong param!");
                        break;
                }
            }
            else
                break;
        }
    }

}
