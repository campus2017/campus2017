package com.qunar.flight.service;

import com.google.common.collect.Maps;
import com.qunar.flight.model.TableModel;
import com.qunar.flight.util.CharUtil;
import com.qunar.flight.util.JsonUtil;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.callback.CallbackHandler;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by nostalie.zhang on 2017/2/3.
 */
@Service
public class CounterService {

    Logger logger = LoggerFactory.getLogger(CounterService.class);
    public static int THREE = 3;

    public TableModel contentCounter(String content){
        int number = 0;
        int EnglishCharacter= 0;
        int ChineseCharacter= 0;
        int punctuation= 0;
        Map<Character,Integer> counter = Maps.newHashMap();
        char[] charArray = content.toCharArray();
        for(int i=0;i<charArray.length;i++){
            if(CharUtil.ignore(charArray[i])){
                continue;
            }
            try {
                if(CharUtil.isNumber(charArray[i])){
                    number++;
                }else if (CharUtil.isEnglishCharacter(charArray[i])) {
                    EnglishCharacter++;
                }else if(CharUtil.isChineseCharacter(charArray[i])){
                    ChineseCharacter++;
                }else if(CharUtil.isChinesePunctuation(charArray[i]) || CharUtil.isEnglishPunctuation(charArray[i])){
                    punctuation++;
                }
                int count = counter.get(charArray[i]);
                counter.put(charArray[i],count+1);
            }catch (NullPointerException e){
                counter.put(charArray[i], 1);
            }catch (Exception e){
                logger.error("解析文本失败",e);
            }
        }
        //根据value进行排序，若value相同按照key的字典序排序
        ValueComparator valueComparator = new ValueComparator(counter);
        TreeMap<Character,Integer> finalCounter = Maps.newTreeMap(valueComparator);
        finalCounter.putAll(counter);
        logger.info("排序TreeMap is ：{}",finalCounter);
        //构造返回类
        TableModel result = new TableModel();
        result.setChineseCharacter(ChineseCharacter);
        result.setEnglishCharacter(EnglishCharacter);
        result.setNumber(number);
        result.setPunctuation(punctuation);
        TreeMap<Character,Integer> mostThree = Maps.newTreeMap(valueComparator);
        int count =0;
        for(Map.Entry<Character,Integer> entry : finalCounter.entrySet()){
            count ++;
            if(count<=THREE){
                mostThree.put(entry.getKey(),entry.getValue());
            }
        }
        result.setMostThree(mostThree);
        logger.info("返回结果类tableModel 是：{}",result);
        return result;
    }

    public TableModel fileCounter(MultipartFile file){
        TableModel result;
        String content = readFileContent(file);
        result = contentCounter(content);
        return result;
    }

    private String readFileContent(MultipartFile file){
        String result="";
        try {
            InputStream in = file.getInputStream();
            byte[] buffer = new byte[10240];
            int length=in.read(buffer);
            if(length<=buffer.length){
                byte[] re = new byte[length];
                for(int i=0;i<length;i++){
                    re[i]=buffer[i];
                }
                result= new String(re);
            }
        } catch (IOException e) {
            logger.error("解析文件出错",e);
        }
        logger.info("解析文件内容为: {}",result);
        return result;
    }
    class ValueComparator implements Comparator<Character>{
        Map<Character,Integer> base;
        public ValueComparator(Map<Character,Integer> base){
            this.base = base;
        }
        @Override
        public int compare(Character o1, Character o2) {
            if(base.get(o1) > base.get(o2)){
                return -1;
            }else if(base.get(o1) == base.get(o2)){
                if(o1 >= o2) {
                    return 1;
                }else{
                    return -1;
                }
            }else if (base.get(o1) < base.get(o2)){
                return 1;
            }
            return 0;
        }
    }
}
