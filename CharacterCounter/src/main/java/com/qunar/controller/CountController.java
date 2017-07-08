package com.qunar.controller;

import com.qunar.model.Count;
import com.qunar.pojo.CountResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by ZC on 2017/6/19.
 */
@Controller
public class CountController {
    Count c = new Count();

    @RequestMapping("/aaa.do")
    @ResponseBody
    public CountResult textCount(String text){
        CountResult countResult = c.textCount(text);
        return countResult;
    }

    @RequestMapping("/bbb.do")
    @ResponseBody
    public CountResult fileCount(MultipartFile myfile,HttpServletRequest request){
        long size = myfile.getSize();
        System.out.println(size);
        if(size>1024*1024*100){
            CountResult cr = new CountResult();
            cr.setStatus("文件不能大于100M");
            return cr;
        }
        //创建你要保存的文件的路径
        String path = request.getSession().getServletContext().getRealPath("/upload");
        //获取该文件的文件名
        String uuidName = UUID.randomUUID().toString();
        String fileName = myfile.getOriginalFilename();
        //保存文件
        File targetFile = new File(path, fileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            myfile.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            CountResult cr = new CountResult();
            cr.setStatus("文件保存失败");
            return cr;
        }
        CountResult countResult= c.fileCount(targetFile);
        if(countResult.getStatus()==null){
            countResult.setStatus("true");
        }
        return countResult;
    }

}
