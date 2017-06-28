package utils;

import domain.Context;
import domain.Statistics;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by luotuomianyang on 2017/6/29.
 */
@Service
public class ContextServiceImplements implements ContextService{
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

    @Override
    public Statistics statFile(MultipartFile file){
        String text = "";
        BufferedReader reader;
        StringBuilder builder = new StringBuilder();
        try {

            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
        text = builder.toString();
        int number = countNumber(text);
        int letter = countLetter(text);
        int chinese = countChinese(text);
        int punctuation = countPunctuation(text);

        Map<String, Integer> top3 = getTop3(text);

        stat = new Statistics(letter, number, chinese, punctuation, top3);
        return stat;
    }

    /**
     * 按行读取文件，
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try{
           BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
           while ((line = reader.readLine()) != null) {
               sb.append(line + "\n");
           }
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 正则表达式判断有多少数字
     * @param str
     * @return
     */
    public static int countNumber(String str){
        int count = 0;
        Pattern p = Pattern.compile("\\d");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    };

    /**
     * 判断有多少字母
     * @param str
     * @return
     */
    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    /**
     * 判断有多少中文
     * @param str
     * @return
     */
    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    /**
     * 判断标点
     * @param str
     * @return
     */
    public static int countPunctuation(String str){
        int count = 0;
        Pattern p = Pattern.compile("\\pP");
        Matcher m = p.matcher(str);
        while (m.find()){
            count++;
        }
        return count;
    }

    /**
     * 出现最多的三个中文
     * @param text
     * @return
     */
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

}

