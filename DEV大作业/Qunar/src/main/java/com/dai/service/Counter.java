package com.dai.service;

import com.dai.pojo.Words;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;

/**
 * 统计单词数
 */
@Service
public class Counter {
    /**
     * 字典顺序比较器
     */
    private Map<String,Integer> word = new TreeMap<>(new Comparator() {
        Collator collator = Collator.getInstance();

        public int compare(Object o1, Object o2) {
            //如果有空值，直接返回0
            if (o1 == null || o2 == null)
                return 0;

            String chi = "[\\u4e00-\\u9fa5]";
            if (((String)o1).matches(chi) && ((String)o2).matches(chi)){
                CollationKey key1 = collator.getCollationKey(o1.toString());
                CollationKey key2 = collator.getCollationKey(o2.toString());
                return key1.compareTo(key2);
            }
            else {

                return String.valueOf(o1).compareTo(String.valueOf(o2));
            }
        }
    });

    private Map<String,Integer> type = new HashMap<String,Integer>();

    public void clearRes(){
        word.clear();
        type.clear();
        type.put("alp",0);
        type.put("num", 0);
        type.put("chi", 0);
        type.put("other", 0);
    }

    /**
     * 读取文件
     * @param file 文件名
     * @param request
     */
    public void ReadFile(String file,HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload")+"/"+file;
        try {

            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(new File(realPath))));
            String temp;
            while ((temp = bf.readLine()) != null){
                CounterWord(temp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 对每行进行分割
     * @param line
     */
    public void CounterWord(String line){
        String temp;
        for (int i = 0;i < line.length();i++){
            temp = line.substring(i,i+1);
            if (!temp.equals(" ")) {
                addToMap(word, temp);
                CounterType(temp);
            }
        }

    }

    /**
     * 统计每个分类的单词数
     * @param word
     */
    private void CounterType(String word){
        String chi = "[\\u4e00-\\u9fa5]";
        String alp = "[a-zA-Z]";
        String num = "[0-9]";
        String other = "[\\p{P}]";
        if (word.matches(chi)){
            addToMap(type,"chi");
        }
        else if (word.matches(alp)){
            addToMap(type,"alp");
        }
        else if (word.matches(num)){
            addToMap(type,"num");
        }
        else if (word.matches(other)){
            addToMap(type,"other");
        }
    }

    /**
     * 向TreeMap增加键值对
     * @param map
     * @param key
     */
    private void addToMap(Map<String,Integer> map,String key){
        if (map.containsKey(key)){
            map.put(key,map.get(key) + 1);
        } else {
            map.put(key,1);
        }
    }

    /**
     * 获取前三的单词
     * @return
     */
    public Map<Integer,Words> getTop3(){
        Map<Integer,Words> mim = new HashMap<>();
        for (Map.Entry<String,Integer>entry:word.entrySet()){
            addToMIM(mim,entry.getKey(),entry.getValue());
        }
        return mim;
    }

    public Map<String,Integer> getWords(){
        return word;
    }

    public Map<String,Integer> getType(){
        return type;
    }

    /**
     * 向自定义Map中按照前三的规则添加元素
     * @param mim
     * @param key
     * @param val
     */
    private void addToMIM(Map<Integer,Words> mim,String key,Integer val){
        if (mim.size() < 3){
            mim.put(mim.size()+1,new Words(key,val));
            sortTop3(mim);
        }
        else {
            if (val > mim.get(3).getVal()){
                mim.put(3,new Words(key,val));
                sortTop3(mim);
            }
        }
    }

    /**
     * 将Top进行排序
     * @param mim
     */
    private void sortTop3(Map<Integer,Words> mim){
        Words temp;
        for (int i = mim.size();i > 1;i--){
            if (mim.get(i).getVal() > mim.get(i-1).getVal()){
                temp = mim.get(i-1);
                mim.put(i-1,mim.get(i));
                mim.put(i,temp);
            }
        }
    }



}
