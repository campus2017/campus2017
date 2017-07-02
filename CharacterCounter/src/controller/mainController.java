package controller;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import service.mainService;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2017/1/26.
 */
@Controller
@RequestMapping(value = "/CharacterCounter")
public class mainController {
    @Autowired
    private mainService mainService;

    //文本输入
    @RequestMapping(value = "/content", method = RequestMethod.POST)
    public void getContentData(@RequestBody String content, PrintWriter printWriter) {
        //按需求得到封装好的JSONObject
        JSONObject JSONResult = mainService.getJSONResult(content);
        printWriter.write(JSONResult.toString());
        printWriter.flush();
        printWriter.close();
    }

    //文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void getUploadData(@RequestParam(value = "upload") MultipartFile uploads, PrintWriter printWriter) throws IOException {
        if (uploads != null) {
            //得到上传文件的内容并转换成字符串
            String content = mainService.getFileContent(uploads);

            //按需求得到封装好的JSONObject
            JSONObject JSONResult = mainService.getJSONResult(content);
            printWriter.write(JSONResult.toString());
            printWriter.flush();
            printWriter.close();
        }
    }

}
