package com.baobao.web;
import com.baobao.domain.Entry;
import com.baobao.service.CalculateString;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

// 注册为处理器或者控制器，在过滤.html的基础上，完成要处理的URL
@Controller
public class MyController {
    // http://192.168.1.100:8080/CharacterCounter/index.html
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    // 自动注入上传的文件，解析为xml格式的数据
    @RequestMapping(value="/fileUpload",method = RequestMethod.POST)
    public ModelAndView fileUpload(@RequestParam("docFile")MultipartFile file){
        System.out.println("fileUpload");
        ModelAndView mav = new ModelAndView();
        try {
            // windows下的文件由于不是utf-8编码，可能出错
            String text = new String(file.getBytes(), "utf-8");
            // 调用业务层处理方法
            List<Entry> list = CalculateString.calculate(text);
            // 待显示的xml对象列表
            mav.addObject("entryList", list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 视图逻辑名，被解析器映射为一个bean
        // 并将该页面的内容返回给客户端
        mav.setViewName("entryListXml");
        // 将模型list和视图逻辑名一并传递给视图解析器
        return mav;
    }
    // 处理输入的数据
    @RequestMapping("/contentUpload")
    public String contentUpload(@RequestParam("textArea")String text, ModelMap modelMap){
        System.out.println("contentUpload");
        List<Entry> list = CalculateString.calculate(text);
        modelMap.addAttribute("entryList", list);
        return "entryListXml";
    }
}
