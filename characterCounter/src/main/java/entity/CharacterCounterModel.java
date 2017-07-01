package entity;

import java.util.Map;
import java.util.HashMap;
/**
 * Created by zhanghe on 2017/6/30.
 */
public class CharacterCounterModel {
    private int englishCount;  //英文字母的个数
    private int numCount;   //数字的个数
    private int chineseCount;  //中文汉字的个数
    private int otherCount; //中英文标点符号的个数

    private Map<Character, Integer> topCharacter = new HashMap<Character, Integer>();//存放出现次数最多的字符以及出现次数

    public Map<Character, Integer> getTopCharacter() {
        return topCharacter;
    }
    public void setTopCharacter(Map<Character, Integer> topCharacter) {
        this.topCharacter = topCharacter;
    }
    public int getEnglishCount() {
        return englishCount;
    }
    public void setEnglishCount(int englishCount) {
        this.englishCount = englishCount;
    }
    public int getNumCount() {
        return numCount;
    }
    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }
    public int getChineseCount() {
        return chineseCount;
    }
    public void setChineseCount(int chineseCount) {
        this.chineseCount = chineseCount;
    }
    public int getOtherCount() {
        return otherCount;
    }
    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }
    public CharacterCounterModel() {
        super();

    }

}
