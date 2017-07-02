package service;

import interFace.wordImp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/1/28.
 */
@Service("mainService")
public class mainService implements wordImp {
    //得到上传文件的内容并转换成字符串
    public String getFileContent(MultipartFile file) throws IOException {
        InputStream input = file.getInputStream();
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader);
        String temp = "";
        String content = "";
        while ((temp = buffer.readLine()) != null) {
            content += temp;
        }
        return content;
    }

    //按需求对数据进行处理并进行Json封装
    public JSONObject getJSONResult(String content) {
        //统计每个字符出现的次数 并把结果按降序排列 封装成json对象
        HashMap<Character, Integer> result = analysisWord(content);
        List list = sortMap(result, "DESC");
        JSONArray resultArray = JSONArray.fromObject(list);

        //统计英文字母，中文字母，中英文标点符号的个数 封装成json对象
        HashMap<String, Integer> result_statistics = statistcsWord(result);

        //最终封装成JSONObject
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sortWord", resultArray);
        jsonObject.put("statisticsWord", result_statistics);
        return jsonObject;
    }

    //统计该字符串中每个字符出现的次数
    public HashMap<Character, Integer> analysisWord(String content) {
        HashMap<Character, Integer> result = new HashMap<Character, Integer>();
        for (int i = 0; i < content.length(); i++) {
            if (!result.containsKey(content.charAt(i))) {
                result.put(content.charAt(i), 1);
            } else {
                int tempValue = result.get(content.charAt(i));
                result.replace(content.charAt(i), tempValue + 1);
            }
        }
        return result;
    }

    //按MAP的value进行排序，默认升序排列
    public List sortMap(HashMap<Character, Integer> map, String sortWay) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<Map.Entry<Character, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                if (sortWay != null && sortWay.equals("DESC")) {
                    return o2.getValue().compareTo(o1.getValue());
                } else {
                    return o1.getValue().compareTo(o2.getValue());
                }
            }
        });
        return list;
    }

    //判断字符属于英文字母-0、数字-1、中文汉字-2、中英文标点符号-3
    public int judgeWord(Character word) {
        int flag = -1;
        String wordStr = word.toString();
        String[] reg = {"[a-zA-Z]", "[\\pN]", "[\\u4e00-\\u9fa5]", "[\\pP]"};
        Pattern pattern;
        Matcher matcher;
        for (int i = 0; i < reg.length; i++) {
            pattern = Pattern.compile(reg[i]);
            matcher = pattern.matcher(wordStr);
            if (matcher.matches()) {
                flag = i;
                break;
            }
        }
        return flag;
    }

    //统计英文字母，中文字母，中英文标点符号的个数
    public HashMap<String, Integer> statistcsWord(HashMap<Character, Integer> content) {
        //初始化HashMap
        HashMap<String, Integer> result = new HashMap<String, Integer>();
        String[] keyArr = {"us", "number", "cn", "sign"};
        for (int i = 0; i < keyArr.length; i++) {
            result.put(keyArr[i], 0);
        }

        //计算个数
        Iterator iter = content.keySet().iterator();
        while (iter.hasNext()) {
            Character key = (Character) iter.next();
            int judgeNum = judgeWord(key);
            if (judgeNum != -1) {
                int count = result.get(keyArr[judgeNum]);
                int value = content.get(key);
                result.replace(keyArr[judgeNum], count + value);
            }
        }
        return result;
    }
}
