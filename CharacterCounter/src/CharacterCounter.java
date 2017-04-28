import com.google.common.collect.TreeMultiset;

import java.util.*;

/**
 * Created by wuyaqi on 17-4-16.
 */
public class CharacterCounter {

    private int englishCount = 0;
    private int digitalCount = 0;
    private int chineseCount = 0;
    private int punctuationCount = 0;

    private TreeMultiset<Character> characterCountSet = TreeMultiset.create();

    private char top1_ch,top2_ch,top3_ch;
    private int top1_total,top2_total,top3_total;

    public int getEnglishCount() {
        return englishCount;
    }

    public int getDigitalCount() {
        return digitalCount;
    }

    public int getChineseCount() {
        return chineseCount;
    }

    public int getPunctuationCount() {
        return punctuationCount;
    }
    //从str字符串中，将所有的字符保存起来。
    public void getCounter(String str)
    {
        String english_r = "[a-zA-Z]";//英文字符
        String digital_r = "[0-9]";//数字
        String chinese_r = "[\u4e00-\u9fa5]";//中文
        String space_r = " ";

        for(int i = 0;i<str.length();i++)
        {
            String word = String.valueOf(str.charAt(i));
            characterCountSet.add(str.charAt(i));
            if(word.matches(english_r))
            {
                englishCount++;
                //englishCountSet.add(word);
            }
            else if(word.matches(digital_r))
            {
                digitalCount++;
                //digitalCountSet.add(word);
            }
            else if(word.matches(chinese_r))
            {
                chineseCount++;
                //chineseCountSet.add(word);
            }
            else if(word.matches(space_r))
            {

            }
            else
            {
                punctuationCount++;
                //punctuationCountSet.add(word);
            }
        }
    }
    //得到前三个比较大的字符。
    public void getTop3Charater()
    {
        top1_total = 0;
        top2_total = 0;
        top3_total = 0;


        HashMap<Character,Integer> charMap = new HashMap<Character, Integer>();
        for(char ch : characterCountSet.elementSet())
        {
            int temp = characterCountSet.count(ch);
            charMap.put(ch,temp);
        }
        //以下代码为了使map按照Integer的value值进行排序。
        List<Map.Entry<Character,Integer>> charList = new ArrayList<Map.Entry<Character, Integer>>(charMap.entrySet());
        Collections.sort(charList, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                int temp_comp = 0;
                if(o2.getValue() > o1.getValue())
                    temp_comp = 1;
                else if(o2.getValue() < o1.getValue())
                    temp_comp = -1;
                else if(o2.getValue() == o1.getValue())//如果次数相同，按照字典序从小到大排序
                {
                    temp_comp = o1.getKey()-o2.getKey();
                }
                return temp_comp;
             }
            //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
        });

        //对charList进行排序后，就可以直接从前面去前三个。
        top1_ch = charList.get(0).getKey();
        top1_total = charList.get(0).getValue();

        top2_ch = charList.get(1).getKey();
        top2_total = charList.get(1).getValue();

        top3_ch = charList.get(2).getKey();
        top3_total = charList.get(2).getValue();
    }
    public void printstr()
    {
        System.out.println("英文字符个数：" + englishCount);
        System.out.println("中文字符个数：" + chineseCount);
        System.out.println("数字个数：" + digitalCount);
        System.out.println("标点符号个数: " + punctuationCount);

        System.out.println("出现次数最多的三个字符分别是：");

        System.out.println(  top1_ch +"出现次数 " + top1_total);
        System.out.println(  top2_ch +"出现次数 " + top2_total);
        System.out.println(  top3_ch +"出现次数 " + top3_total);

    }
    public static void main(String[] args) {
        CharacterCounter chcounter = new CharacterCounter();
        String str = "aabbcccd中国人！毋亚琦wuyaqi。122342123232424122";

        chcounter.getCounter(str);
        chcounter.getTop3Charater();
        chcounter.printstr();

    }
}
