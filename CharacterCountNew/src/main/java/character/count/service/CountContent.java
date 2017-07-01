package character.count.service;

import character.count.beans.PairOfChinese;
import character.count.beans.ResultOfCount;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hughgilbert on 30/06/2017.
 */
public class CountContent implements Counter {
    /*
        统计文本内容
     */
    public ResultOfCount getResultOfText(String text){

        ResultOfCount result = new ResultOfCount();
        result.setChinese(countOfChineseWord(text));
        result.setLetters(countOfLetter(text));
        result.setNumber(countOfNumber(text));
        result.setPunctuation(countOfPunctuation(text));
        result.setChineseWordsList(sortChineseWord(textTransToChineseWordList(text)));
        return result;
    }

    /*
        统计文件内容
     */
    public ResultOfCount getResultOfFile(MultipartFile file){

        String text = transToString(file);
        ResultOfCount result = new ResultOfCount();
        result.setChinese(countOfChineseWord(text));
        result.setLetters(countOfLetter(text));
        result.setNumber(countOfNumber(text));
        result.setPunctuation(countOfPunctuation(text));
        result.setChineseWordsList(sortChineseWord(textTransToChineseWordList(text)));

        return result;
    }

    /*
        将multipartFile 转换成String
     */

    private String transToString(MultipartFile file)
    {
        String text = null;
        try {
            text = IOUtils.toString(file.getInputStream(), "UTF-8");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return text;
    }

    /*
        统计英文字母
     */
    private int countOfLetter(String text){
        int numberOfletter = 0;
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            numberOfletter++;
        }

        return numberOfletter;
    }

    /*
        统计数字
     */
    private int countOfNumber(String text){
        int numberOfNumber = 0;
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            numberOfNumber++;
        }
        return numberOfNumber;
    }

    /*
        统计中文
     */
    private int countOfChineseWord(String text){
        int numberOfChineseWord = 0;
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            numberOfChineseWord++;
        }
        return numberOfChineseWord;
    }

    /*
       统计标点符号
     */
    private int countOfPunctuation(String text){
        int numberOfPuctuation = 0;
        Pattern pattern = Pattern.compile("\\pP");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            numberOfPuctuation++;
        }

        return numberOfPuctuation;
    }

    /*
        将文本内容转换称单字列表
     */
    private List<String> textTransToChineseWordList(String text){
        List<String> chineseWords = new ArrayList<String>();
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            chineseWords.add(matcher.group());
        }
        return chineseWords;
    }

    /*
        统计前三的文字
     */
    private List<PairOfChinese> sortChineseWord(List<String> chineseWordsList){
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(chineseWordsList);

        List<PairOfChinese> top3OfChineseWord = new ArrayList<PairOfChinese>();

        for(String key : multiset.elementSet()){
            top3OfChineseWord.add(new PairOfChinese(key,multiset.count(key)));
        }

        Collections.sort(top3OfChineseWord);


        if(top3OfChineseWord.size() <= 3){
            return top3OfChineseWord;
        }
        else{
            return top3OfChineseWord.subList(0,3);
        }
    }
}
