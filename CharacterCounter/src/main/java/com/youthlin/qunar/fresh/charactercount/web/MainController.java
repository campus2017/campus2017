package com.youthlin.qunar.fresh.charactercount.web;

import com.youthlin.qunar.fresh.charactercount.service.CharacterCounterService;
import com.youthlin.qunar.fresh.charactercount.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by youthlin.chen on 2016-12-9 009.
 * MainController
 */
@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    @Resource
    private CharacterCounterService service;

    @RequestMapping(value = {"/index"}, method = {RequestMethod.GET})
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/upload"}, method = {RequestMethod.POST})
    @ResponseBody
    public Result upload(@RequestParam(value = "file-input") MultipartFile file, HttpServletRequest request) {
        Result result = new Result();
        if (file == null || file.isEmpty()) {
            return result.setCode(1).setMsg("请选择文件上传");
        }
        String contentType = file.getContentType();
        //log.debug("contentType = {}", contentType);
        if (!contentType.contains("text")) {
            return result.setCode(2).setMsg("请上传文本文件");
        }
        Scanner scanner;
        try {
            scanner = new Scanner(file.getInputStream());
        } catch (IOException e) {
            log.warn("get file input stream error.", e);
            return result.setCode(3).setMsg("处理失败(获取文件流出错)");
        }
        return result.setData(service.process(scanner));
    }

    @RequestMapping(value = {"/text"}, method = {RequestMethod.POST})
    @ResponseBody
    private Result text(@RequestParam("text-input") String text) {
        return new Result().setData(service.process(new Scanner(text)));
    }

}
