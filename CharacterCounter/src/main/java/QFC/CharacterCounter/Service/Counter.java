package QFC.CharacterCounter.Service;

import QFC.CharacterCounter.POJO.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by root on 6/29/17.
 */
public class Counter
{
    private boolean isAlpha(char c)
    {
        if( (c <= 'z' && c >= 'a') || (c <= 'Z' && c >= 'A') )
            return true;
        return false;
    }

    private boolean isNum(char c)
    {
        if( (c <= '9' && c >= '0') )
            return true;
        return false;
    }

    private boolean isCHN(char c)
    {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        //  GENERAL_PUNCTUATION 判断中文的“号
        //  CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号
        //  HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS ||
            ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ||
            ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A ||
            ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B)
        {
            return true;
        }
        return false;
    }

    public Map<Character,Integer> sortByValue(HashMap<Character,Integer> rec)
    {
        Map<Character,Integer> result = new LinkedHashMap<Character, Integer>();
        List<Map.Entry<Character,Integer>> list =
            new ArrayList<Map.Entry<Character,Integer>>(rec.entrySet());

        Collections.sort(
                list, new Comparator<Map.Entry
                        <Character, Integer>>()
        {
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2)
            {
                if(o1.getValue() != o2.getValue())
                    return o1.getValue() - o2.getValue();
                else
                    return o2.getKey() - o1.getKey();
            }
        });

        Iterator<Map.Entry<Character, Integer>> iter = list.iterator();
        Map.Entry<Character, Integer> tmpEntry = null;
        while (iter.hasNext())
        {
            tmpEntry = iter.next();
            result.put(tmpEntry.getKey(), tmpEntry.getValue());
        }

        return result;
    }

    public Result work(String text)
    {
        System.out.println("counter:"+text);
        System.out.println(text.charAt(1));
        Result result = new Result();
        if(text!=null && !text.equals(""))
        {
            HashMap<Character,Integer> rec = new HashMap();
            char tmp;
            for(int i = 0;i < text.length();i++)
            {
                tmp = text.charAt(i);
                if(isAlpha(tmp))
                    result.addAlp();
                else if(isCHN(tmp))
                    result.addCHN();
                else if(isNum(tmp))
                    result.addNum();
                else
                    result.addPun();
                if(rec.containsKey(tmp))
                    rec.put(tmp,rec.get(tmp)+1);
                else
                    rec.put(tmp,1);
            }

            int count = 1;
            LinkedHashMap<Character,Integer> sortedRec =
                    new LinkedHashMap<Character, Integer>(sortByValue(rec));
            ListIterator<Map.Entry<Character,Integer>> i =
                    new ArrayList<Map.Entry<Character,Integer>>(sortedRec.entrySet())
                            .listIterator(sortedRec.size());

            if(i.hasPrevious())
            {
                Map.Entry<Character, Integer> entry = i.previous();
                result.setChara1(entry.getKey());
                result.setNum1(entry.getValue());
            }
            if(i.hasPrevious())
            {
                Map.Entry<Character, Integer> entry = i.previous();
                result.setChara2(entry.getKey());
                result.setNum2(entry.getValue());
            }
            if(i.hasPrevious())
            {
                Map.Entry<Character, Integer> entry = i.previous();
                result.setChara3(entry.getKey());
                result.setNum3(entry.getValue());
            }

        }
        return result;
    }

    public Result readFile(MultipartFile file)
    {
        String text = "";
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(file.getInputStream()));
            String tmp_str;
            while((tmp_str = reader.readLine()) != null)
                sb.append(tmp_str);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        text = sb.toString();
        Result result = work(text);
        return result;
    }
}
