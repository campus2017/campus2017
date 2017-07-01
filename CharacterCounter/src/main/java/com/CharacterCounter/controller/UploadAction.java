package com.CharacterCounter.controller;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import com.sun.tools.internal.ws.processor.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.CharacterCounter.service.*;
/**
 * Created by apple on 17/6/10.
 */
@Controller
public  class UploadAction
{
    private ServletContext servletContext;

    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public String upload( @RequestParam(value="startCount",required=true)String inputStr,   MultipartFile file,HttpServletRequest request)throws IOException  {

        System.out.print("run");
      //  for(MultipartFile file : myfiles)
        {
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if(file.isEmpty()){
                System.out.println("文件未上传!");
            }
            else{
                //得到上传的文件名
                String fileName = file.getOriginalFilename();
                //得到服务器项目发布运行所在地址
                String path1 = request.getSession().getServletContext().getRealPath("file")+File.separator;
                //  此处未使用UUID来生成唯一标识,用日期做为标识
                judgeDirExists(path1);
                String path = path1+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ fileName;
                //查看文件上传路径,方便查找
                System.out.println(path);
                //把文件上传至path的路径
                File localFile = new File(path);
                file.transferTo(localFile);
                String fileContent="";
                FileReader fr=new FileReader(path);
                BufferedReader br=new BufferedReader(fr);
                String str=br.readLine();
                while(str!=null)
                {
                    fileContent+=str;
                    fileContent+="\n";
                    str=br.readLine();
                }
                fr.close();
                br.close();
                HttpSession session=request.getSession();
                System.out.println("字符总数"+fileContent.replace("\n","").length());
                session.setAttribute("content",fileContent.replace("\n",""));
                wordCount wc=new wordCount(fileContent.replace("\n",""));

               // session.setAttribute("count",wc);
                session.setAttribute("en_ch_count",wc.data.en_ch_count);
                session.setAttribute("chinese_ch_count",wc.data.chinese_ch_count);
                session.setAttribute("number_count",wc.data.number_count);
                session.setAttribute("punctuation_count",wc.data.punctuation_count);
                session.setAttribute("topThree",wc.data.top_tree);
            }
        }
        return "index";
    }
    public static void judgeDirExists(String dirPath) {
        File file= new File(dirPath);
               if (file.exists()) {
                        return ;
                    } else {

                         file.mkdir();
                     }

            }
    @RequestMapping(value = "textInput",method = RequestMethod.POST)
    public String countWord( @RequestParam(value="WordCount",required=true)String inputStr, HttpServletRequest request, @RequestParam("textBox")String Content)throws IOException  {

        System.out.print("run");
        //  for(MultipartFile file : myfiles)
        {
            //此处MultipartFile[]表明是多文件,如果是单文件MultipartFile就行了
            if(Content.length()==0){
                System.out.println("未输入文本!");
            }
            else{

                HttpSession session=request.getSession();
                System.out.println("字符总数"+Content.replace("\n","").length());
                session.setAttribute("content",Content.replace("\n",""));
                wordCount wc=new wordCount(Content.replace("\n",""));

                // session.setAttribute("count",wc);
                session.setAttribute("en_ch_count",wc.data.en_ch_count);
                session.setAttribute("chinese_ch_count",wc.data.chinese_ch_count);
                session.setAttribute("number_count",wc.data.number_count);
                session.setAttribute("punctuation_count",wc.data.punctuation_count);
                session.setAttribute("topThree",wc.data.top_tree);
            }
        }
        return "index";
    }

}
