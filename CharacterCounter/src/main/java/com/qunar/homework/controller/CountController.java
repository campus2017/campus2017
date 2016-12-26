package com.qunar.homework.controller;

/**
 * Created by dayong.gao on 2016/12/12.
 */

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.qunar.homework.entity.CountInfo;
import com.qunar.homework.service.CountService;
import com.qunar.homework.service.impl.CountServiceImpl;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller @RequestMapping("/") public class CountController {

    private static final Logger logger = LoggerFactory.getLogger(CountController.class);
    CountService countService = new CountServiceImpl();

    @RequestMapping(value = "/getCount", method = { RequestMethod.GET })
    public @ResponseBody CountInfo getCount(String str)
            throws UnsupportedEncodingException {
        String nstr = new String(str.getBytes("iso8859-1"), "utf-8").replaceAll(" ", "");//转换编码方式为UTF-8,去除所有空格
        CountInfo resultInfo =countService.getCount(nstr);
        return resultInfo;
    }

    @RequestMapping(value = "/upLoadCount", method = { RequestMethod.POST })
    public @ResponseBody CountInfo doUploadCount(@RequestParam("file") MultipartFile file)
            throws IOException {
        if (!file.isEmpty()) {
            //logger.info("file:{}", file.getOriginalFilename());
            CountInfo resultinfo = countService.getCount(file.getInputStream());
            return resultinfo;
        }
        return null;
    }
}
