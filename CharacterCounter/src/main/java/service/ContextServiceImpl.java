package service;

import domain.Context;
import domain.Statistics;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by luvslu on 2017/1/29.
 */
@Service
public class ContextServiceImpl implements ContextService{
    private Statistics stat;
    private static int maxCount = 3;

    @Override
    public Statistics statContext(Context context){
        String text = context.getText();
        int number = countNumber(text);
        int letter = countLetter(text);
        int chinese = countChinese(text);
        int punctuation = countPunctuation(text);

        Map<String, Integer> top3 = getTop3(text);

        stat = new Statistics(letter, number, chinese, punctuation, top3);
        return stat;
    }

    public static Map<String, Integer> getTop3(String text){
        Map<String, Integer> wordCount = new HashMap<>();
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(text);
        while(m.find()){
            String str = m.group();
            if(wordCount.containsKey(str)){
                wordCount.put(str, wordCount.get(str)+1);
            }else{
                wordCount.put(str, 1);
            }
        }

        Map<String,Integer> top3 = new LinkedHashMap<>();
        int count = 1;
        wordCount = sortByValue(wordCount);
        ListIterator<Map.Entry<String,Integer>> i=new ArrayList<>(wordCount.entrySet()).listIterator(wordCount.size());
        while(i.hasPrevious()) {
            Map.Entry<String, Integer> entry=i.previous();
            top3.put(entry.getKey(),entry.getValue());
            if(count >= maxCount){
                break;
            }else{
                count++;
            }
        }
        return top3;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> st = map.entrySet().stream();

        st.sorted(Comparator.comparing(e -> e.getValue())).forEach(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }


    public static int countNumber(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    public static int countPunctuation(String str){
        int count = 0;
        Pattern p = Pattern.compile("\\pP");
        Matcher m = p.matcher(str);
        while (m.find()){
            count++;
        }
        return count;
    }
}

