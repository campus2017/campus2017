package com.qunar.hello;

import java.util.*;

/**
 * Created by qiongweiren.
 */
public class CountItemModel {

    String inputString = null;
    HashMap<String, Integer> itemList = new HashMap<>();
    HashMap<String, Integer> wordSortList = new HashMap<>();
    public CountItemModel()
    {}
    public CountItemModel(String inputWord)
    {
        inputString  = inputWord;
    }


    public HashMap<String, Integer> getItemList()
    {

        HashMap<String ,Integer> englishList = new HashMap<>();
        HashMap<String ,Integer> numberList = new HashMap<>();
        HashMap<String ,Integer> chinessList = new HashMap<>();
        HashMap<String ,Integer> dotList = new HashMap<>();

        String EG = "EnglishCount";
        String CH = "ChineseCount";
        String NM = "NumberCount";
        String DT = "DotCount";

        itemList.put(EG, 0);
        itemList.put(CH, 0);
        itemList.put(NM, 0);
        itemList.put(DT, 0);


        if(inputString == null)
        {
            return null;
        }
        inputString = inputString.trim();
        String[] wordList = inputString.split("\\s+");

        for(String word:wordList)
        {
            addWordToList(wordSortList, word);
            if( isNumber(word) ) {
                addWordToList(numberList, word);
                itemList.put(NM, itemList.get(NM)+1);
                continue;
            }
            if( isDot(word) ) {
                addWordToList(dotList, word);
                itemList.put(DT, itemList.get(DT)+1);
                continue;
            }

            if( isEnglish(word) ) {
                addWordToList(englishList, word);
                itemList.put(EG, itemList.get(EG)+1);
                continue;
            }
            if( isChinese(word) ) {
                addWordToList(chinessList, word);
                itemList.put(CH, itemList.get(CH)+1);
                continue;
            }

        }

        return itemList;
    }


    public List<Map.Entry<String, Integer>> getSortWordList()

    {
        List<Map.Entry<String, Integer>> list =
                new ArrayList<Map.Entry<String, Integer>>(wordSortList.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        return list;
    }


    private boolean isNumber(String word)
    {
        if( word.matches("[0-9]+") )
            return true;
        return false;
    };

    private boolean isEnglish(String word)
    {
      if( word.matches("[a-zA-z0-9]+") )
          return true;
        return false;
    };

    private boolean isChinese(String word)
    {
        if( word.matches("[a-zA-z0-9]+") )
            return false;
        return false;
    };


    private boolean isDot(String word)
    {
        if( word.matches("[,.?:;`'\"\\[\\]{}+=-_)(*&^%$#@!]+") )
            return true;
        return false;
    };


    private void addWordToList(HashMap<String, Integer> List, String word)
    {
        if(List.containsKey(word))
            List.put(word, List.get(word)+1);
        else
            List.put(word,1);
    }

}
