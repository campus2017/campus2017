package bean;

import java.io.InterruptedIOException;
import java.io.Serializable;
import java.text.Collator;
import java.util.*;

/**
 * Created by libo on 2017/6/3.
 */
public class ResponseObj implements Serializable{
    private int letterNum = 0;
    private int digitNum = 0;
    private int chineseNum = 0;
    private int punctuationNum = 0;
    private Map<Character, Integer> frequMap = new HashMap<Character, Integer>();


    public int  clacLetterNum(){
        return ++letterNum;
    }

    public int  clacDigitNum(){
        return ++digitNum;
    }

    public int  clacChineseNum(){
        return ++chineseNum;
    }

    public int  clacPunctuationNum(){
        return ++punctuationNum;
    }


    /**
     * 统计字符出现的频率
     * @param c
     */
    public void calcFrequence(char c){
        if (frequMap.containsKey(c)){
            frequMap.put(c, frequMap.get(c)+1);
        }
        else{
            frequMap.put(c, 1);
        }
    }

    /**
     * 排序获取频率最高的几个字符     val表示获取前几个值
     *
     * @return
     */
    public ResponseObj getMaxFrequence(int val){
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(frequMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int diff = o2.getValue() - o1.getValue();

                Comparator<Object> com= Collator.getInstance(java.util.Locale.CHINA);
                return diff == 0 ? com.compare(String.valueOf(o1.getKey()), String.valueOf(o2.getKey())) : diff;
            }
        });

        Map<Character, Integer> newMap = new LinkedHashMap<Character, Integer>();
        for (int i=0; i<list.size() && i<val; ++i){
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        this.setFreMap(newMap);

        return this;
    }

    public int getLetterNum() {
        return letterNum;
    }

    public void setLetterNum(int letterNum) {
        this.letterNum = letterNum;
    }

    public int getDigitNum() {
        return digitNum;
    }

    public void setDigitNum(int digitNum) {
        this.digitNum = digitNum;
    }

    public int getChineseNum() {
        return chineseNum;
    }

    public void setChineseNum(int chineseNum) {
        this.chineseNum = chineseNum;
    }

    public int getPunctuationNum() {
        return punctuationNum;
    }

    public void setPunctuationNum(int punctuationNum) {
        this.punctuationNum = punctuationNum;
    }

    public Map<Character, Integer> getFreMap() {
        return frequMap;
    }

    public void setFreMap(Map<Character, Integer> frequMap) {
        this.frequMap = frequMap;
    }


    @Override
    public String toString() {
        return "ResponseObj{" +
                "letterNum=" + letterNum +
                ", digitNum=" + digitNum +
                ", chineseNum=" + chineseNum +
                ", punctuationNum=" + punctuationNum +
                ", frequMap=" + frequMap +
                '}';
    }
}
