package com.qunar.model;

import com.qunar.pojo.CountResult;

import java.io.*;
import java.util.*;

/**
 * Created by ZC on 2017/6/25.
 */
public class Count {
    //对文本进行统计
    public CountResult textCount(String text){
        //统计内容
        int length = text.length();
        int cnWord = 0;
        int enWord = 0;
        int digit = 0;
        int punctuation = 0;
        String reg1 = "[\u4e00-\u9fa5]";// 中文
        String reg2 = "[a-zA-Z]";// 英文
        String reg3 = "[0-9]";// 数字

        //将字符存入hashMap，并且统计上述内容
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        String temp;
        for (char c : text.toCharArray()) {
            temp = String.valueOf(c);
            if(temp.matches(reg1)){
                cnWord++;
            }else if(temp.matches(reg2)){
                enWord++;
            }else if(temp.matches(reg3)){
                digit++;
            }else{
                punctuation++;
            }
            if(map.containsKey(c)){
                Integer n = map.get(c);
                map.put(c, n+1);
            }else{
                map.put(c, 1);
            }
        }
        //将这些字符按出现次数排序
        Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            
            public int compare(Map.Entry<Character, Integer> e1, Map.Entry<Character, Integer> e2) {
                if(e1.getValue().equals(e2.getValue())){
                    return e1.getKey().compareTo(e2.getKey());
                    }
                else{
                    return e2.getValue()-e1.getValue();
                }
            }
        });
//        //测试使用
//        for (Map.Entry<Character, Integer> entry : list) {
//            System.out.println(entry.getKey()+":"+entry.getValue());
//        }
//        System.out.println("中文："+cnWord+"英文："+enWord+"数字："+digit+"其他："+punctuation);

        //封装数据
        CountResult cr = new CountResult();
        cr.setCnWord(cnWord);
        cr.setEnWord(enWord);
        cr.setDigit(digit);
        cr.setPunctuation(punctuation);
        if(list.size()>=1) {
            cr.setWord1(list.get(0).getKey().toString());
            cr.setTime1(list.get(0).getValue());
        }
        if(list.size()>=2){
        cr.setWord2(list.get(1).getKey().toString());
        cr.setTime2(list.get(1).getValue());
        }
        if(list.size()>=3) {
            cr.setWord3(list.get(2).getKey().toString());
            cr.setTime3(list.get(2).getValue());
        }
        return cr;
    }
    //统计file文件
    public CountResult fileCount(File file) {
        //获取编码
        String code = null;
        try {
            code = getCode(file);
        } catch (IOException e) {
            e.printStackTrace();
            CountResult cr = new CountResult();
            cr.setStatus("获取编码失败");
            return cr;
        }
        //读取文件
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), code));
            String line = null;
            while( ( line = br.readLine() ) != null ) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            CountResult cr = new CountResult();
            cr.setStatus("文件解析失败");
            return cr;
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String s = sb.toString();
        //计算
        CountResult cr = textCount(s);
        return cr;
    }
    //解析编码
    public String getCode(File file) throws IOException {
        BufferedInputStream bin = null;
        String code = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(file));
            int p = (bin.read() << 8) + bin.read();
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
        }finally{
            try {
                bin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return code;

    }
}
