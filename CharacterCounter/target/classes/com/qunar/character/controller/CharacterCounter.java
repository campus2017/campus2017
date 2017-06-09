package com.qunar.character.controller;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import com.qunar.character.dto.CharacterDto;
import com.qunar.character.service.CharacterCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Controller
public class CharacterCounter {


    @Autowired
    public CharacterCounterService characterCounterService;

    @RequestMapping("/")
    public String home(Model model) {
        initModel(model, 0);
        return "home";
    }

    @RequestMapping(value = "/resolve/text", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "uploadfile", required = false) CommonsMultipartFile file, @RequestParam(value = "textarea", required = false) String textArea, HttpServletRequest request, HttpServletResponse httpServletResponse, Model model) {

        int tag = 0;
        List<String> words = Lists.newArrayList();
        StringBuffer content = new StringBuffer();
        if (file == null && textArea == null) {
            return "error";
        } else {
            if (textArea != null) {
                try {
                    String str = new String(textArea.getBytes("ISO-8859-1"), "utf-8");//解决乱码
                    content.append(str);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return "error";
                }
                tag = 1;
            } else {
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        content.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "error";
                }
            }
            CharacterDto characterDto = characterCounterService.countWords(content);
            final Multiset<String> multiset = HashMultiset.create();
            multiset.addAll(characterDto.getWordList());
            List<String> sortList = Lists.newArrayList();
            Set<String> set = multiset.elementSet();
            sortList.addAll(set);
            Collections.sort(sortList, new Comparator<String>() {
                public int compare(String o1, String o2) {
                    int n1 = multiset.count(o1);
                    int n2 = multiset.count(o2);
                    return n2 - n1;
                }
            });

            model.addAttribute("englishWord", characterDto.getEnglishWord());
            model.addAttribute("chineseWord", characterDto.getChineseWord());
            model.addAttribute("numberWord", characterDto.getNumberWord());
            model.addAttribute("punctuationWord", characterDto.getPunctuationWord());
            model.addAttribute("firstWord", sortList.size() > 0 ? sortList.get(0) : "");
            model.addAttribute("firstCount", sortList.size() > 0 ? multiset.count(sortList.get(0)) : "");
            model.addAttribute("secondWord", sortList.size() > 1 ? sortList.get(1) : "");
            model.addAttribute("secondCount", sortList.size() > 1 ? multiset.count(sortList.get(1)) : "");
            model.addAttribute("thirdWord", sortList.size() > 2 ? sortList.get(2) : "");
            model.addAttribute("thirdCount", sortList.size() > 2 ? multiset.count(sortList.get(2)) : "");
            if (tag == 0) {

                initModel(model, 1);
            } else {
                model.addAttribute("content", content);
                initModel(model, 2);
            }

            return "home";
        }


    }

    private void initModel(Model model, int tag) {
        if (tag == 0) {
            model.addAttribute("selectOne", "checked=\"checked\" ");
            model.addAttribute("selectTwo", "");
            model.addAttribute("textDisplay", "style=\" float: left; display: none;\"");
            model.addAttribute("fileDisplay", "");
        } else if (tag == 1) {
            model.addAttribute("selectOne", "checked=\"checked\" ");
            model.addAttribute("selectTwo", "");
            model.addAttribute("textDisplay", "style=\" float: left; display: none;\"");
            model.addAttribute("fileDisplay", "");
        } else if (tag == 2) {
            model.addAttribute("selectOne", "");
            model.addAttribute("selectTwo", "checked=\"checked\" ");
            model.addAttribute("textDisplay", "style=\" float: left;\"");
            model.addAttribute("fileDisplay", "style=\" display: none;\"");
        }

    }

}
