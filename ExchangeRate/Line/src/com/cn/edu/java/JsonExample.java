package com.cn.edu.java;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;
/**
 * Created by ASUS on 2017/2/3.
 */
public class JsonExample {
   public static void JsonObjectSample(){
       JSONObject wangxiaoer = new JSONObject();
       wangxiaoer.put("name","王小二");
       wangxiaoer.put("age",25.2);
       wangxiaoer.put("birthday","1995-01-01");
       System.out.println(wangxiaoer.toString());
   }
    public static void createJsonByMap(){
        Map<String,Object> wangxiaoer = new HashMap<String,Object>();
        wangxiaoer.put("name","王小二");
        wangxiaoer.put("age",25.2);
        wangxiaoer.put("birthday","1995-01-01");
        System.out.println(new JSONObject(wangxiaoer).toString());
    }
    public static void createJsonByBean(){
        JavaBeanExample wangxiaoer = new JavaBeanExample();
        wangxiaoer.SetName("王小二");
        wangxiaoer.SetAge(25.2);
        wangxiaoer.SetBirthday("1995-01-01");
        System.out.println(new JSONObject(wangxiaoer));
    }

    public static void main(String[] args){
        createJsonByBean();
    }
}
