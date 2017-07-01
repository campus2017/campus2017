package com.example.demo.service;

import com.example.demo.domain.CountBean;
import com.example.demo.domain.TopBean;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import java.util.*;

/**
 * Created by WangWeiyi on 2017/5/26 0026.
 */
public class CountService {
    /*
    * 判断是否是中文
    */
    private static boolean isCh(char ch){
        if (Character.UnicodeScript.of(ch) == Character.UnicodeScript.HAN) {
            return true;
        } else {
            return false;
        }
    }

    /*
    * 判断是否是标点符号
    */
    private static boolean isPunc(char ch){
        if(Character.isDigit(ch)||Character.isLetter(ch)||isCh(ch)){
            return false;
        } else {
            return true;
        }

    }

    /*
    * 判断是否是数字
    */
    private static boolean isDidit(char ch){
        return Character.isDigit(ch);

    }

    /*
    * 判断是否是英文字母
    */
    private static boolean isEN(char ch){
        return Character.isLetter(ch);
    }

    /*
     * 统计所有字符的数量
     */
    private static CountBean countNum(String s){
        char[] chars = s.toCharArray();
        CountBean cb = new CountBean();
        for(char ch : chars){
            if(isPunc(ch)){
                cb.puncIncrement();
            } else if(isDidit(ch)){
                cb.digitIncrement();
            } else if(isCh(ch)){
                cb.cnIncrement();
            } else if(isEN(ch)){
                cb.enIncrement();
            } else {
                cb.otherIncrement();
            }

        }
        return cb;
    }

//    public static void getResult(String s){
//        CountBean cb = countNum(s);
//
//        System.out.println("中文:" + cb.getCnChar() + "标点：" + cb.getPuncChar() + "英文："+ cb.getEnChar()+"数字："+ cb.getDigitChar()+"其他："+cb.getOtherChar());
//    }



    /*
   * 统计前三的字符
   */
    public static List<TopBean> getTop(String s){
        HashMap<Character, Integer> map = new HashMap<>();
        //读取信息内容
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(map.get(c) == null){
                map.put(c, 1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        //PriorityQueue优先队列实现小根堆
        PriorityQueue<TopBean> queue = new PriorityQueue<>();
        for(Character c : map.keySet()){
            TopBean topBean = new TopBean();
            topBean.setCh(c);
            topBean.setNum(map.get(c));
            queue.add(topBean);
            if(queue.size() > 3){
                queue.poll();
            }
        }
        List<TopBean> list = new ArrayList<>();
        while(!queue.isEmpty()){
            list.add(queue.poll());
        }
        while(list.size() < 3){
            TopBean topBean = new TopBean();
            topBean.setCh('\0');
            topBean.setNum(0);
            list.add(topBean);
        }
        return list;

    }
    /*
    * 输出最终的结果
    * */
    public static Map<String, String> getResult(String s){
        HashMap<String, String> result = new HashMap<>();
        CountBean cb = countNum(s);

        result.put("EngChar",cb.getEnChar() > 0?cb.getEnChar() +"":"0");
        result.put("DigitChar",cb.getDigitChar() > 0?cb.getDigitChar() +"":"0");
        result.put("ChiChar",cb.getCnChar() > 0?cb.getCnChar() +"":"0");
        result.put("PunChar",cb.getPuncChar() > 0?cb.getPuncChar() +"":"0");

        List<TopBean> list  = getTop(s);
        result.put("Char1",list.get(2).getCh()+"" );
        result.put("Char2",list.get(1).getCh()+"" );
        result.put("Char3",list.get(0).getCh()+"" );

        result.put("Char1Num",list.get(2).getNum()+"");
        result.put("Char2Num",list.get(1).getNum()+"");
        result.put("Char3Num",list.get(0).getNum()+"");


        return result;

    }

    public static void main(String[] args){
        getResult("\"hello, 1341dasfajjj你玩把|||%!*&^^%%$8888&！````】【。。。\"");

    }
}