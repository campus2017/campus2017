package com.wuyaqi.controller;

import com.wuyaqi.model.CountResult;
import com.wuyaqi.service.CountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

/**
 * Created by wuyaqi on 17-4-29.
 */
@Controller
@RequestMapping("/count")//这个名字一定要和文件夹的名字相同
public class CountController {

    CountService countService = new CountService();

    //这个是主页，
    @RequestMapping(value = "counter",method = RequestMethod.GET)
    public String home_page(Model model)
    {
        model.addAttribute("checked1","checked") ;//设置radio中的checked属性
        model.addAttribute("checked0","") ;

        return "/count/charCounter";
    }

    //当用户输入内容，点击统计时，跳转到这里，
    @RequestMapping(value = "/input",method = RequestMethod.POST)
    public String input_txt(HttpServletRequest req,Model model)
    {
        System.out.println("进入函数");
        Enumeration paramNames = req.getParameterNames();
        System.out.println(paramNames);
        if(paramNames==null)
        {
            //没有参数
            return "/count/charCounter";
        }
        //得到文本信息
        String str = req.getParameter("inputtxt");
       /* System.out.println("deda input text");*/
        //调用countService中的方法，对这段文字进行处理，得到结果
        CountResult countResult = countService.getCountResult(str);
        /*System.out.println("chuxian wnti");
        System.out.println(countResult);*/
        //往model中添加属性，以便在页面中显示相应信息。
        model.addAttribute("result", countResult);
        model.addAttribute("txtstr",str);
        model.addAttribute("checked0","") ;
        model.addAttribute("checked1","checked") ;

        return "/count/charCounter";

    }
    //当用户选择文件，点击统计时，跳转到这里
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public String uplod_file(MultipartFile file,HttpServletRequest req,Model model) throws IOException {

        System.out.println(file.getName()+","+file.getSize()+","+file.getContentType());
        //从文件中得到字符流，保存在StringBuilder中。
        String content = "";
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(file.getInputStream()));
            while(content != null){
                content = bf.readLine();
                if(content == null){
                    break;
                }
                sb.append(content.trim());
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //调用主函数，问文件内容进行处理，得到结果。
        CountResult countResult = countService.getCountResult(sb.toString());

        System.out.println(countResult);
        model.addAttribute("result",countResult);
        model.addAttribute("filename",file.getOriginalFilename());
        model.addAttribute("checked0","checked") ;
        model.addAttribute("checked1","") ;

        return "/count/charCounter";


    }
}

