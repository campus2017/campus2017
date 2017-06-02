package com.qunar.amao.service;

import com.qunar.amao.pojo.CharacterCounter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Created by FGT on 2017/6/1.
 */
public class CounterService {
    /**
     * 通过文件路径提取文件内字符串信息并返回统计字符结果
     * @param path 文件路径
     * @return 字符统计结果
     * @throws Exception
     */
    public CharacterCounter FileResultCounter(String path) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader(new File(path)));
        String str="",temp;
        while ((temp=in.readLine()) != null)
        {
            str = str + temp;
        }
        in.close();
        CharacterCounter characterCounter = ResultCounter(str);
        return characterCounter;
    }

    /**
     * 通过字符串统计字符结果
     * @param str 文字字符串
     * @return
     */
    public CharacterCounter ResultCounter(String str){
        CharacterCounter characterCounter = new CharacterCounter();
        String text = str.replaceAll("\\s*","");//\s 可以匹配空格、制表符、换页符等空白字符的其中任意一个
        char temp;
        for(int i=0;i<text.length();i++){
            temp = text.charAt(i);
            characterCounter = SwitchCharacter(characterCounter,temp);
        }
        characterCounter.SortMap();
        return characterCounter;
    }

    /**
     * 判断具体每个字符类型
     * @param characterCounter 统计结果集
     * @param temp 判断字符临时变量
     * @return 字符结果集
     */
    public CharacterCounter SwitchCharacter(CharacterCounter characterCounter,char temp){
        if(temp >= '0' && temp <= '9'){
            characterCounter.addNumber();
        }else if((temp >= 'a' && temp <= 'z') || (temp >= 'A' && temp <= 'Z')){
            characterCounter.addEnglish();
        }else if(Character.toString(temp).matches("[\\u4E00-\\u9FA5]+")){//汉字字符匹配
            characterCounter.addChinese();
        }else if(IsCnSymbol(temp) || IsEngSymbol(temp)){//中英标点符号
            characterCounter.addSymbol();
        }
        characterCounter.CountCharacterMap(temp);//统计每一个字符的次数

        return characterCounter;
    }

    /**
     * 判断是否属于中文标点符号
     * @param temp 判断字符变量
     * @return
     */
    public boolean IsCnSymbol(char temp){
        if (0x3004 <= temp && temp <= 0x301C) return true;
        if (0x3020 <= temp && temp <= 0x303F) return true;
        return false;
    }

    /**
     * 判断是否属于英文标点符号
     * @param temp 判断字符变量
     * @return
     */
    public boolean IsEngSymbol(char temp){
        if (temp == 0x40) return true;
        if (temp == 0x2D || temp == 0x2F) return true;
        if (0x23 <= temp && temp <= 0x26) return true;
        if (0x28 <= temp && temp <= 0x2B) return true;
        if (0x3C <= temp && temp <= 0x3E) return true;
        if (0x5B <= temp && temp <= 0x60) return true;
        if (0x7B <= temp && temp <= 0x7E) return true;
        return false;
    }
}
