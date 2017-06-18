package controller;

import bean.ResponseObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.CharacterCount;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by libo on 2017/6/4.
 */

@Controller
public class TextCount {

    @RequestMapping(value="textcount", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> testCount(@RequestParam("textarea") String textarea){
        Map<String, Object> res = new HashMap();

        String con = textarea.trim();
        if ("".equals(con)){
            res.put("code", 1);
            res.put("error", "文本内容不能为空");
        }
        else{
            res.put("code", 0);
            res.put("result", CharacterCount.textCount(con));
        }

        return res;
    }


    private boolean checkFile(MultipartFile file, Map<String, Object> res){
        if (file.isEmpty()){
            res.put("code", 1);
            res.put("error", "文件不能为空");
            return false;
        }
        if (file.getSize() > 20*1024*1024){
            res.put("code", 1);
            res.put("error", "文件大小不能超过20M");
            return false;
        }

        String type = file.getContentType();
        //支持文本文件  doc后缀的word文档   docx后缀的word文档
        if (!("text/plain".equals(type) || "application/msword".equals(type) ||
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(type))){
            res.put("code", 1);
            res.put("error", "不支持的文件类型");
            return false;
        }

        return true;
    }


    @RequestMapping(value="filecount", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> fileCount(@RequestParam("file") MultipartFile file){
        Map<String, Object> res = new HashMap();

        if (checkFile(file, res)){
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(fileName.lastIndexOf('.')+1);
            try {
                ResponseObj obj = null;
                if ("txt".equals(suffix)){
                    obj = CharacterCount.txtFileCount(file.getBytes());
                }
                else if ("doc".equals(suffix)){
                    obj = CharacterCount.docFileCount(file.getInputStream());
                }
                else if ("docx".equals(suffix)){
                    obj = CharacterCount.docxFileCount(file.getInputStream());
                }

                if (obj != null){
                    res.put("code", 0);
                    res.put("result", obj);
                }
            } catch (IOException e) {
                res.put("code", 1);
                res.put("error", "解析文件出现异常");
                e.printStackTrace();
            }
        }

        return res;
    }

}
