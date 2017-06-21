package com.qunar.zhangguoqiang.controller;
import com.qunar.zhangguoqiang.characterCounter.Count;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@Controller
public class countController {
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "Count";
    }
    @RequestMapping(path="/inputtext",method = {RequestMethod.POST})
    @ResponseBody
    public Count getText(@RequestParam("inputText") String text) {
        return new Count(text);
    }
    @RequestMapping(path = "/uploadfile", method = RequestMethod.POST)
    @ResponseBody
    public Count getFile(@RequestParam("file") MultipartFile file) {
        BufferedReader br=null;
        StringBuilder sb = new StringBuilder();
        String line = null;
        if(file==null)
            return null;
        else
        {
            try {
                br=new BufferedReader(new InputStreamReader(file.getInputStream(),"UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while ((line =br.readLine()) != null)
                    sb.append(line + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Count(sb.toString());
    }
}