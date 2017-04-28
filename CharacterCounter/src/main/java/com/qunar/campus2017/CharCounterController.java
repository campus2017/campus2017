package com.qunar.campus2017;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Leviathan on 2017/4/26.
 */
@Controller
public class CharCounterController {

    @Value("${top_n}")
    private int topN;

    @GetMapping(value = "/")
    public String hello(ModelMap modelMap) {
        return "index";
    }


    @PostMapping(value = "/", params = {"submitText"})
    public String parseText(@RequestParam("text") String text, ModelMap modelMap) {

        WordCountService wc = new WordCountService(text);
        HashMap<String, Integer> res = wc.countChar();
        ArrayList<CharCountEntry> topNChar = wc.getTopNChars(topN);
        modelMap.put("inputText", text);
        modelMap.putAll(res);
        for (int i = 0; i < topNChar.size(); ++i) {
            modelMap.put("top" + (i + 1) + "Char", topNChar.get(i).getmChar());
            modelMap.put("top" + (i + 1) + "Count", topNChar.get(i).getmCount());
        }
        return "index";
    }


    @PostMapping(value = "/", params = {"submitFile"})
    public String parseFile(@RequestParam("file") MultipartFile file, ModelMap modelMap) {

        StringBuffer sb = new StringBuffer();
        if (file.isEmpty()) {
            return "index";
        }

        try (InputStreamReader isr = new InputStreamReader(file.getInputStream());
             BufferedReader br = new BufferedReader(isr)) {
            String line = null;
            while((line = br.readLine())!= null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        WordCountService wc = new WordCountService(sb.toString());
        HashMap<String, Integer> res = wc.countChar();
        ArrayList<CharCountEntry> topNChar = wc.getTopNChars(topN);
        modelMap.put("inputFile", file);
        modelMap.putAll(res);
        for (int i = 0; i < topNChar.size(); ++i) {
            modelMap.put("top" + (i + 1) + "Char", topNChar.get(i).getmChar());
            modelMap.put("top" + (i + 1) + "Count", topNChar.get(i).getmCount());
        }

        return "index";
    }

}
