package com.qunar.muhongfen.controller;

import com.qunar.muhongfen.util.CharacterUtil;
import com.qunar.muhongfen.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


import com.qunar.muhongfen.util.FileUtil;



/**
 * Created by muhongfen on 17/1/6.
 */
@Controller
@RequestMapping(value = "/CharacterCounter")
public class CharacterCountController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    //文件上传的请求
    @RequestMapping(value="/uploadFile",method=RequestMethod.POST,produces="text/html;charset=utf-8")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, @RequestParam(value="" +
            "file", required=false) MultipartFile file) throws IOException{
        String filePath =request.getSession().getServletContext().getRealPath("/../../files/");
        String fileName = file.getOriginalFilename();
        String fileType = fileName.split("[.]")[1];
        File f = new File(filePath+fileName+"."+fileType);
        FileUtil.upFile(file.getInputStream(),fileName,filePath);
        return ResponseUtil.returnFileSuccess(fileName);
    }

    //处理文件
    @RequestMapping(value ="/fileCount",method=RequestMethod.GET,produces="text/html;charset=utf-8")
    @ResponseBody
    public String fileCount(HttpServletRequest request) throws UnsupportedEncodingException {
        String fileName = "";
        try {
            fileName = URLDecoder.decode(request.getParameter("filename"), "UTF-8");

        }catch (Exception e){
            System.out.println("参数解析错误,fileName:"+request.getParameter("fileName"));
            return ResponseUtil.returnFail("parameter error");
        }
        String filePath =request.getSession().getServletContext().getRealPath("/../../files/");

        String str = FileUtil.getFileString(filePath+'/'+fileName);
        System.out.println("+++++++++"+str);
        JSONObject countNumMap = CharacterUtil.CountNum(str);
        JSONObject maxThreeCountsList = CharacterUtil.maxThreeCounts(str);
        if(countNumMap == null){
            return ResponseUtil.returnFail("countnum is null");
        }
        if(maxThreeCountsList == null)
        {
            return ResponseUtil.returnFail("maxThreeCountsList is null");
        }
        List<JSONObject> result = new ArrayList<JSONObject>();
        result.add(countNumMap);
        result.add(maxThreeCountsList);
        return ResponseUtil.returnSuccess(result);
    }

    //字符串输入的请求
    @RequestMapping(value = "/stringCount",method = RequestMethod.POST,produces="text/html;charset=utf-8")
    @ResponseBody
    public String stringCount(@RequestParam(value = "str")String str){
        JSONObject countNumMap = CharacterUtil.CountNum(str);
        JSONObject maxThreeCountsList = CharacterUtil.maxThreeCounts(str);
        if(countNumMap == null){
            return ResponseUtil.returnFail("countnum is null");
        }
        if(maxThreeCountsList == null)
        {
            return ResponseUtil.returnFail("maxThreeCountsList is null");
        }
        List<JSONObject> result = new ArrayList<JSONObject>();
        result.add(countNumMap);
        result.add(maxThreeCountsList);
        return ResponseUtil.returnSuccess(result);
    }
}
