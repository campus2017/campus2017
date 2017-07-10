package com.qunar.hello;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qunar.hello.CountItemModel;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.servlet.view.script.ScriptTemplateConfig;

/**
 * Created by qiongweiren.
 */
@Controller
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public class HelloController {

    @RequestMapping(value = "/getStasticData", method = RequestMethod.POST)
    public String printGetStasticDataPage(ModelMap model) {
         return "FunctionAndLayout";
    }

    @RequestMapping(value = "/getStasticDataValue", method = RequestMethod.POST)
    public String printGetStasticDataValuePage(ModelMap model, HttpServletRequest request) {

        String EG = "EnglishCount";
        String CH = "ChineseCount";
        String NM = "NumberCount";
        String DT = "DotCount";
        List<String> tagItem = new ArrayList<>();
        List<String> tagItemCount = new ArrayList<>();
        tagItem.add("ItemFirst");
        tagItemCount.add("ItemFirstCount");
        tagItem.add("ItemSecond");
        tagItemCount.add("ItemSecondCount");
        tagItem.add("ItemThird");
        tagItemCount.add("ItemThirdCount");

        String inputWord  =request.getParameter("inputWord");
        //System.out.println(inputWord);

        HashMap<String, Integer> itemList= new HashMap<>();

        CountItemModel CIM = new CountItemModel(inputWord);
        itemList = CIM.getItemList();
        if(itemList==null)
        {
            return "FunctionAndLayout";
        }
        List<Map.Entry<String, Integer>> list = CIM.getSortWordList();

        model.addAttribute(EG, itemList.get(EG));
        model.addAttribute(CH, itemList.get(CH));
        model.addAttribute(NM, itemList.get(NM));
        model.addAttribute(DT, itemList.get(DT));
        model.addAttribute("textArea", inputWord);


        List<String> topWord = new ArrayList<>();
        List<Integer> topWordNum = new ArrayList<>();
        int wNum=0;
        for(Map.Entry<String , Integer> word:list)
        {
            if(wNum>=3)
                break;
            topWord.add(word.getKey());
            topWordNum.add(word.getValue());
            model.addAttribute(tagItem.get(wNum), topWord.get(wNum));
            model.addAttribute(tagItemCount.get(wNum), topWordNum.get(wNum));
            wNum++;
        }

        return "FunctionAndLayout";

    }

}
