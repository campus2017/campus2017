package com.action;

import com.utils.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Lee on 2017/6/26.
 */
@Controller
public class CharacterCounterAction {

        @RequestMapping(value = "/result.do")
    public ModelAndView counter() {
         int ccount = 0; //汉字
         int ecount = 0; //英文字母
         int ncount = 0; //数字
         int bcount = 0; //标点符号
         String input =null;
        input = "你你是3谁我A3AB，;d13";
        HashMap<Character, Integer> num = new HashMap<>();
        char[] c = null;
        c = input.toCharArray();
        int l= c.length;
        //对文本进行分析统计
        for (int i = 0; i < l; i++) {
            if (Character.toString(c[i]).matches("[\\u4E00-\\u9FA5]+")) {
                ccount ++;

            }else if(Character.toString(c[i]).matches("[a-zA-Z]+")) {
                ecount ++;

            }else if(Character.toString(c[i]).matches("[0-9]+")) {
                ncount ++;

            }else
                bcount++;
            //统计各字符数
            if (num.containsKey(c[i]))
                num.put(c[i], num.get(c[i]) + 1);
            else {
                num.put(c[i], 1);
            }

        }
        //字符按频率排序
        Sort sort = new Sort();
       List<Object> list= sort.sortByValue(num);
       ModelAndView modelAndView = new ModelAndView();
       /******************各类型字符数****************/
        modelAndView.addObject("ccount", ccount);
        modelAndView.addObject("ecount", ecount);
        modelAndView.addObject("ncount", ncount);
        modelAndView.addObject("bcount", bcount);
        /******************频率最高的三个字****************/
        modelAndView.addObject("fname", list.get(1).toString());
        modelAndView.addObject("fcount", list.get(0).toString());
        modelAndView.addObject("sname", list.get(3).toString());
        modelAndView.addObject("scount", list.get(2).toString());
        modelAndView.addObject("tname", list.get(5).toString());
        modelAndView.addObject("tcount", list.get(4).toString());
        modelAndView.setViewName("result");
        return modelAndView;
    }


}
