package cn.xuchunh.charcounter.controller;

import cn.xuchunh.charcounter.model.Result;
import cn.xuchunh.charcounter.model.TextStats;
import cn.xuchunh.charcounter.service.CharCounterService;
import cn.xuchunh.charcounter.service.FileService;
import cn.xuchunh.charcounter.util.ErrorCode;
import cn.xuchunh.charcounter.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created on 2017/6/12.
 *
 * @author XCH
 */
@Controller
@RequestMapping("/charcounter")
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Resource
    private CharCounterService textParseService;

    @Resource
    private FileService fileService;

    @RequestMapping(value = "/text", method = RequestMethod.POST)
    public @ResponseBody
    Result<TextStats> parseText(@RequestParam("text") String text) {
        try {
            return new Result<>(textParseService.parse(text));
        } catch (Exception e) {
            logger.error("text parse error", e);
            return new Result<>(ErrorCode.TEXT_PARSE_ERROR.getCode(),
                    ErrorCode.TEXT_PARSE_ERROR.getCause());
        }
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public @ResponseBody
    Result<TextStats> parseFile(@RequestParam("id") int id) {
        if (id > 0) {
            try {
                return new Result<>(textParseService.parse(fileService.getContentById(id)));
            } catch (Exception e) {
                logger.error("file parse error", e);
                return new Result<>(ErrorCode.FILE_PARSE_ERROR.getCode(),
                        ErrorCode.FILE_PARSE_ERROR.getCause());
            }
        } else {
            logger.error("file id is invalid {}", id);
            return new Result<>(ErrorCode.FILE_NOT_EXIST.getCode(),
                    ErrorCode.FILE_NOT_EXIST.getCause());
        }

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    Result<Integer> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            logger.error("upload file error: file is empty");
            return new Result<>(ErrorCode.FILE_IS_EMPTY.getCode(),
                    ErrorCode.FILE_IS_EMPTY.getCause());
        } else {
            try {
                String name = file.getOriginalFilename();
                String encoding = FileUtil.getFileEncode(file.getInputStream());
                String content = IOUtils.toString(file.getInputStream(), encoding);
                return new Result<>(fileService.insert(name, content));
            } catch (IOException e) {
                logger.error("file parse error", e);
                return new Result<>(ErrorCode.FILE_UPLOAD_ERROR.getCode(),
                        ErrorCode.FILE_PARSE_ERROR.getCause());
            }
        }

    }


}
