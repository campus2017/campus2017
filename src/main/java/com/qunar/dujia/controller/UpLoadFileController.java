package com.qunar.dujia.controller;

import com.qunar.dujia.model.MostNum;
import com.qunar.dujia.model.ResModel;
import com.qunar.dujia.model.TongJi;
import com.qunar.dujia.utils.LIstFile;
import com.qunar.dujia.utils.modelUtils;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tianyiren on 16-12-23.
 */
@Controller
public class UpLoadFileController {

    @RequestMapping(value="/returnStart")
    @ResponseBody
    public ModelAndView returnStart(){
        return new ModelAndView("start");
    }

    @RequestMapping(value="/uploadFile")
    public String startUpload() {
        return "UpLoad";
    }

//    @ApiOperation(value = "上传文件", notes = "上传文件test", responseClass = "DataVo")
//    @RequestMapping(value = "/upload", produces = { "application/json" }, method =RequestMethod.POST )
//    public @ResponseBody DataVo upload(
//            @ApiParam(value = "logo", required = true) @RequestParam(value = "logo", required = true) MultipartFile logo,HttpServletRequest request){
//        //这里的logo就是接受的文件了
//        if(logo!=null){
//            //进行操作吧
//            System.out.println(logo.getOriginalFilename());
//        }
//    }


    @RequestMapping(value="/springUploadFile", method= RequestMethod.POST)
    @ResponseBody
    public ModelAndView addUser(@RequestBody MultipartFile file) throws IOException {
        System.out.println("controller is in");
        ModelAndView mad = new ModelAndView();
        MultipartFile myfile = file;
        if(myfile.isEmpty()){
            mad.setViewName("error_fileupload");
            String message = "上传信息为空！";
            mad.addObject("message",message);
            return mad;
        }
        try {
            System.out.println(myfile.isEmpty());
            System.out.println("文件长度: " + myfile.getSize());
            System.out.println("文件类型: " + myfile.getContentType());
            System.out.println("文件名称: " + myfile.getName());
            System.out.println("文件原名: " + myfile.getOriginalFilename());
            System.out.println("========================================");

            String paths = "/home/tianyiren/TestReceive/";

            String myDirect = modelUtils.saveFileToServer(myfile, "/home/tianyiren/TestReceive/");

            mad.setViewName("success");

            mad.addObject("name",myfile.getOriginalFilename());


            System.out.println("controller is in");

//             ResModel resModel = new ResModel(res, lis);

            String nameText = myfile.getOriginalFilename();

            System.out.println(nameText);

            LIstFile.AddFileName(nameText);

            return mad;

        }catch(Exception e){
            e.printStackTrace();
        }

        return mad;
    }

}

