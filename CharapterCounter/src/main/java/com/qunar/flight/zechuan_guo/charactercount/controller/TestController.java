package com.qunar.flight.zechuan_guo.charactercount.controller;

import com.qunar.flight.zechuan_guo.charactercount.service.ITestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by zechuan.guo on 2016/12/9.
 */
@Controller
public class TestController  {
    /**
    持有Service
     */
    @Resource
    private ITestService TestService;

    @RequestMapping("/hello")
    public @ResponseBody
    Map sayHello(@RequestParam String text)  {
        System.out.println("Controller: "+text);
        Map map = TestService.countLength(text);
        return map;
    }
    @RequestMapping(value = "/upload",method = {RequestMethod.POST})
    @ResponseBody
    public Map upLoad(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request){
        Map map = new HashMap();
        if(file == null||file.isEmpty()){
            map.put("errorCode",1);
            map.put("errorMsg","空文件异常，请检查网络");
            return map;
        }
        try {
            Scanner scanner = new Scanner(file.getInputStream());
            Map map1 = TestService.countLengthByFile(scanner);
            return map1;
        } catch (IOException e) {
            map.put("errorCode",3);
            map.put("errorMsg","系统IO异常 请联系管理员");
            return map;

        }

    }
    @RequestMapping(value = {"/upload2"}, method = {RequestMethod.POST})
    @ResponseBody
    public String upload(@RequestParam(value = "file2") MultipartFile file, HttpServletRequest request) {
        System.out.println("test true");
        return "true";
    }
    public ITestService getTestService() {
        return TestService;
    }

    public void setTestService(ITestService testService) {
        TestService = testService;
    }
}
