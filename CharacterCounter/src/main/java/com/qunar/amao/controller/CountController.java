package com.qunar.amao.controller;

import com.qunar.amao.pojo.CharacterCounter;
import com.qunar.amao.service.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.util.Iterator;

/**
 * 首次加载，显示主页信息的控制器
 * Created by FGT on 2017/5/30.
 */
@Controller
public class CountController extends HttpServlet {

    /**
     * 上传文件控制器
     * @param req 上传参数
     * @return 视图结果
     * @throws Exception
     */
    @RequestMapping(value = "/uploadFile.do",method= RequestMethod.POST)
    public ModelAndView UpFileController(HttpServletRequest req) throws Exception{
        ModelAndView modelAndView = new ModelAndView();

        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver( req.getSession().getServletContext());
        multipartResolver.setDefaultEncoding("UTF-8");
        MultipartHttpServletRequest multipartRequest = multipartResolver.resolveMultipart(req);
        Iterator iter = multipartRequest.getFileNames();
        CharacterCounter characterCounter = new CharacterCounter();
        while(iter.hasNext())
        {
            //一次遍历所有文件
            MultipartFile file=multipartRequest.getFile(iter.next().toString());
            if(file!=null)
            {
                //String path="C:/"+file.getOriginalFilename();
                String classpath = this.getClass().getResource("/").getPath().replaceFirst("/", "");
                String webappRoot = classpath.replaceAll("WEB-INF/classes/", "upload/");
                //没有没录就创建目录
                File dir = new File(webappRoot);
                if(!dir.isDirectory()){
                    dir.mkdirs();
                }
                String path = webappRoot+file.getOriginalFilename();
                //上传
                file.transferTo(new File(path));
                CounterService counterService = new CounterService();
                characterCounter = counterService.FileResultCounter(path);
            }
        }

        modelAndView.addObject("CharacterCounter",characterCounter);
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }

    /**
     * 上传文字控制器
     * @param req 上传参数
     * @return 视图结果
     * @throws Exception
     */
    @RequestMapping(value="/uploadText.do",method=RequestMethod.POST)
    public ModelAndView UpTextController(HttpServletRequest req)throws Exception{
        ModelAndView modelAndView = new ModelAndView();
        String text = req.getParameter("text");

        CounterService counterService = new CounterService();
        CharacterCounter characterCounter = counterService.ResultCounter(text);

        modelAndView.addObject("CharacterCounter",characterCounter);
        modelAndView.setViewName("/index.jsp");
        return modelAndView;
    }
}
