package com.qunar.flight.controller;

import com.qunar.flight.model.TableModel;
import com.qunar.flight.service.CounterService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

/**
 * Created by nostalie.zhang on 2017/2/3.
 */
@org.springframework.stereotype.Controller
public class Controller {
    @Resource
    private CounterService counterService;

    Logger logger = LoggerFactory.getLogger(Controller.class);
    @RequestMapping("/contentCounter.do")
    @ResponseBody
    public TableModel contentCounter(String content){
        logger.info("content is : {}",content);
        TableModel result;
        try {
            content = new String(content.getBytes("iso-8859-1"),"UTF-8");
            result = counterService.contentCounter(content);
        } catch (UnsupportedEncodingException e) {
            logger.error("编码转换失败", e);
            return null;
        }
        logger.info("返回结果：{}",result);
        return result;
    }

    @RequestMapping("/fileCounter.do")
    @ResponseBody
    public String fileCounter(@RequestParam(value = "file", required = false) MultipartFile file){
        JSONObject json = new JSONObject();
        try {
            if(!file.getContentType().contains("text")){
                json.put("type",false);
                return json.toString();
            }
            TableModel result = counterService.fileCounter(file);
            JSONObject content = new JSONObject(result);
            json.put("content",content);
            logger.info("处理结果为: {}",result);
            json.put("flag",true);
        } catch (Exception e) {
            json.put("flag",false);
            logger.error("文件处理失败，",e);
        }
        return json.toString();
    }
}
