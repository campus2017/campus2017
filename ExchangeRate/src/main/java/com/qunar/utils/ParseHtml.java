package com.qunar.utils;

import com.qunar.entity.Rate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by isc on 2017/1/10.
 */
public class ParseHtml {

    /**
     * 根据输入地址获取需要的url
     * @param document
     * @return
     */
    public  List<String> getAllParseList(Document document){

        List<String> list = new ArrayList<String>();//存放全部的链接
        Date thirty = getThirtyDaysDate();
        System.out.println(thirty);

        Elements elements = document.getElementsByTag("font");
        for (int i = 0; i <elements.size()-1; i++) {
            try {
                String href = (elements.get(i).getElementsByTag("a")).get(0).attr("href");
                String title = (elements.get(i).getElementsByTag("a")).get(0).attr("title");
                DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
                Date date = format.parse(title.substring(0,title.indexOf("日")+1));
                System.out.println(date);
                if(date.after(thirty)) {
                    list.add(href);
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 30天的截止日期
     * @return
     */
    private Date getThirtyDaysDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30); //得到前一天
        return calendar.getTime();
    }

    /**
     * 获取人民币与其他币种关系
     * @param document
     * @return
     */
    public Optional<Rate> getRmbData(Document document){
        Rate rate = new Rate();
        Date date = null;
        Elements elements = document.getElementById("zoom").getElementsByTag("p");
        Element element=null;
        if(elements.size()==7){
            element = elements.get(elements.size()-2);
        }else
            element = elements.get(elements.size()-1);

        String dateStr = element.html().trim();
        try {
            dateStr = dateStr.substring(dateStr.indexOf("2"));//得到日期
            date = new SimpleDateFormat("yyyy年mm月dd日").parse((dateStr));
            rate.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s = elements.get(0).html().trim();
        String[] strings = s.substring(s.indexOf("：") + 1).split("，");
        double meiyuan =Double.parseDouble(strings[0].substring(strings[0].indexOf("币")+1,strings[0].lastIndexOf("元")));
        double ouyuan = Double.parseDouble(strings[1].substring(strings[1].indexOf("币")+1,strings[1].lastIndexOf("元")));
        double gangyuan = Double.parseDouble(strings[3].substring(strings[3].indexOf("币")+1,strings[3].lastIndexOf("元")));

        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("美元",100/meiyuan);
        map.put("欧元",100/ouyuan);
        map.put("港元",100/gangyuan);
        rate.setDate(date);
        rate.setRate(map);
        return  Optional.of(rate);
    }
}
