package com.cuihuaru.controller;
import com.cuihuaru.model.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
@Controller
public class checkController{
    @RequestMapping(value = "/index.html")
    public String index() {
        return "index";
    }
    @RequestMapping(value = "/CharacterCount.html")
    public ModelAndView loginCheck(HttpServletRequest request) {

        String input = request.getParameter("input");
        judgeCharacter[] judge = new judgeCharacter[4];
        judge[2]=new judgeCharIsChinese();
        judge[1]=new judgeCharIsDigit();
        judge[0]=new judgeCharIsLetter();
        judge[3]=new judgeCharIsPunctuation();
        result r=new result();
        characterJudge(input,judge,r);
        r.setInput(input);
        request.getSession().setAttribute("r",r);
        return new ModelAndView("index");
    }
        public void characterJudge(String input,judgeCharacter[] judge,result r) {
            int length=judge.length;
            int[] number=new int[length];
            HashMap<Character,Integer> h=new HashMap<>();
            for (int i = 0; i < input.length(); ++i) {
                if(h.containsKey(input.charAt(i))==false) {
                    h.put(input.charAt(i),1);
                }else
                {
                    int k=h.get(input.charAt(i));
                    ++k;
                    h.remove(input.charAt(i));
                    h.put(input.charAt(i),k);
                }
                for (int j = 0; j < length; ++j) {
                    if (judge[j].judge(input.charAt(i))) {
                        number[j]++;
                        break;
                    }
                }

            }
            TopK k=new TopK();
            k.setMap(h);
            k.getTopk1(3);
            char[] c=new char[4];
            int[] charnumber=new int[4];

            for( int index=0;index<3;++index)
            {
               c[index]=k.getList().get(index).getKey();
               charnumber[index]=k.getList().get(index).getValue();

            }
            r.setCharNumber(charnumber);
            r.setMostTimesChar(c);
            r.setNumber(number);
        }
    }