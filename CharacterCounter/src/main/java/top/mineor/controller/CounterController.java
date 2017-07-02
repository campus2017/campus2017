package top.mineor.controller;

/**
 * Created by Mineor on 2017/6/26.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.mineor.service.CounterService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

@Controller
public class CounterController {

    @Autowired
    private CounterService counterService;

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }


    @ResponseBody
    @RequestMapping(value = "/parseText", method = RequestMethod.POST)
    public Map<String, Object> parseText(@RequestBody String text) {
        Map<String,Object> res = counterService.parseText(text);
        res.putAll(counterService.topKCharacter(text, 3));
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/parseFile", method = RequestMethod.POST)
    public Map parseFile(@RequestParam("textFile") MultipartFile textFile) {
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(textFile.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> res = counterService.parseText(builder.toString());
        res.putAll(counterService.topKCharacter(builder.toString(), 3));
        return res;
    }
}