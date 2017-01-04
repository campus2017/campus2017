package wz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wz.service.CounterService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * CounterController
 *
 * @author wz
 * @date 16/12/23 15:44
 */
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
//        try {
//            text = new String(text.getBytes("ISO-8859-1"), "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        Map<String,Object> res = counterService.parseText(text);
//        model.addAllAttributes(res);
        res.putAll(counterService.topKCharacter(text, 3));
//        List<Map.Entry<Character, Integer>> top3 = counterService.topKCharacter(text, 3);
//        model.addAttribute("top3", top3);

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
//        model.addAllAttributes(res);
//        List<Map.Entry<Character, Integer>> top3 = counterService.topKCharacter(builder.toString(), 3);
//        model.addAttribute("top3", top3);
        return res;
    }


}
