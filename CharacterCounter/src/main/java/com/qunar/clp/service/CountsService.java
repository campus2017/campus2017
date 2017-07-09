package com.qunar.clp.service;

import com.qunar.clp.pojo.CharacterAndCount;
import com.qunar.clp.pojo.CountDto;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by nipingchen on 16-12-14.
 */
@Service
public class CountsService {

    private final int NEED_CHAR_COUNT=3;
    /**
     * 統計字符串中的英文，中文，數字，符號的個數以及出現頻率最高的三個字符
     * @param str
     * @return CountDto
     * @throws Exception
     */
    public CountDto count(String str) {
        Map<Character, Integer> characterCountMap = new HashMap<>();
        int needCharCount =0;
        CountDto countDto = new CountDto(0,0,0,0);
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        for (int i = 0; i < str.length(); i++) {
            Character character = str.charAt(i);
            if(character.equals(' ')){
                continue;
            }
            if (!characterCountMap.containsKey(character)) {
                characterCountMap.put(character, 1);
            } else {
                int lastCount = characterCountMap.get(character);
                characterCountMap.put(character, ++lastCount);
            }
            if ((character >= 'A' && character <= 'Z') || (character >= 'a' && character <= 'z')) {
                countDto.enCounts++;
            }else if((character>='0')&&(character<='9')){
                countDto.numCounts++;
            }else if(isChinese(character)){
                countDto.chCounts++;
            }else{
                countDto.symbolCounts++;
            }
        }
        //根据字符的个数进行排序, 如果个数相同按照字典顺序排序
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(characterCountMap.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<Character, Integer>>() {

            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if(o2.getValue()<o1.getValue()){
                    return -1;
                }else if(o2.getValue()>o1.getValue()){
                    return 1;
                }else{
                    if(o2.getKey()<o1.getKey()){
                        return 1;
                    }else{
                        return -1;
                    }
                }
            }
        });

        if(list.size()<NEED_CHAR_COUNT){
            needCharCount=list.size();
        }else{
            needCharCount=NEED_CHAR_COUNT;
        }
        for(int i=0;i<needCharCount;i++){
            CharacterAndCount characterAndCount=new CharacterAndCount(list.get(i).getKey(),list.get(i).getValue());
            countDto.maxNumThreeCharacter.add(characterAndCount);
        }
        return countDto;
    }
    //判断字符是否是中文
    private boolean isChinese(char ch){
        Character.UnicodeBlock ub= Character.UnicodeBlock.of(ch);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B ) {
            return true;
        }
        return false;
    }
}
