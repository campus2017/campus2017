package controller;

import constant.CharacterCounterConstant;
import entity.CharacterCounterModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by zhanghe on 2017/6/30.
 */

@Controller
@RequestMapping("/characterCounter")
public class CharacterCounterController {

    @RequestMapping("/showCharacterCounter")
    public String showCharacterCounter(){
        return "main";
    }

    @RequestMapping("/doCharacterCounter")
    public CharacterCounterModel doCharacterCounter(HttpServletRequest request){
        //获取请求参数
        String text = (String) request.getParameter("text");
        CharacterCounterModel model = characterCount(text);
        return model;
    }

    private CharacterCounterModel characterCount(String text){
        CharacterCounterModel model = new CharacterCounterModel();
        int englishCount = 0;  //英文字母的个数
        int numCount = 0;   //数字的个数
        int chineseCount = 0;  //中文汉字的个数
        int otherCount = 0; //中英文标点符号的个数

        //文章中出现频率最高的三个字符
        Map<Character, Integer> topCharacterMap = new HashMap<Character, Integer>();
        //统计文章中出现频率最高的三个字符
        Map<Character,Integer> characterAndIntegerMap = new HashMap<Character, Integer>();

        //将待统计字符串转为字符数组
        char[] charArray = text.toCharArray();

        //使用正则判断字符串类型
        Pattern englishPattern = Pattern.compile("[a-zA-Z]");
        Pattern numPattern = Pattern.compile("[0-9]");
        Pattern chinesePattern = Pattern.compile("[\u4e00-\u9fa5]");

        for (char ch:charArray){

            //将字符放入map中
            if(characterAndIntegerMap.containsKey(ch)){
                characterAndIntegerMap.put(ch, characterAndIntegerMap.get(ch)+1);
            }else{
                characterAndIntegerMap.put(ch, 1);
            }

            String temp = String.valueOf(ch);
            if(englishPattern.matcher(temp).matches()){
                englishCount++;
            }else if(numPattern.matcher(temp).matches()){
                numCount++;
            }else if(chinesePattern.matcher(temp).matches()){
                chineseCount++;
            }


        }
        otherCount = text.length() - (chineseCount + englishCount + numCount);

        //设置model参数，并返回
        model.setEnglishCount(englishCount);
        model.setNumCount(numCount);
        model.setChineseCount(chineseCount);
        model.setOtherCount(otherCount);
        //统计文章中出现频率最高的n个字符
        doTopCharacterCounter(characterAndIntegerMap, topCharacterMap);
        model.setTopCharacter(topCharacterMap);
        return model;
    }

    /**
     * 统计字符串中出现次数最多的三个字符
     * @param characterAndIntegerMap
     * @param topCharacterMap
     */
    private  void doTopCharacterCounter(Map<Character,Integer> characterAndIntegerMap, Map<Character,Integer> topCharacterMap){

        //得到所有值，值是可以重复，用集合存放
        Collection countValueCollection =  characterAndIntegerMap.values();
        List countValueList = new ArrayList<Integer>();
        countValueList.addAll(countValueCollection);
        //按照自然顺序的反序排
        Collections.sort(countValueList, Collections.reverseOrder());

        int maxCount;
        if(countValueList.size() > 0){
            maxCount = (int)Collections.max(countValueList);
        }else{
            return;
        }


        Set characterCountSet = characterAndIntegerMap.entrySet();
        Character topKey = null;
        Iterator is = characterCountSet.iterator();
        while(is.hasNext()){
            Map.Entry<Character, Integer> o = (Map.Entry<Character, Integer>) is.next();

            if(o.getValue() == maxCount){
                topCharacterMap.put(o.getKey(), o.getValue());
                topKey = o.getKey();
            }

            if(topCharacterMap.size() >= CharacterCounterConstant.TOPTHREECHARACTER){
                break;
            }
        }

        if(topCharacterMap.size() < CharacterCounterConstant.TOPTHREECHARACTER){
            characterAndIntegerMap.remove(topKey);
            doTopCharacterCounter(characterAndIntegerMap, topCharacterMap);
        }

    }

    @RequestMapping("/fileUploadCharacterCount")
    @ResponseBody
    public CharacterCounterModel doFileUpload(@RequestParam("file") CommonsMultipartFile file, HttpSession session) throws IOException {
        String fileName = file.getOriginalFilename();

        //当前项目在服务器上目录中的WEB-INF/upload 路径
        String uploadDir = session.getServletContext().getRealPath("/WEB-INF/upload");
        String fileDir = uploadDir + "/" + rename(fileName);

        file.transferTo(new File(fileDir));

        String str = fileToString(fileDir);
        CharacterCounterModel model = characterCount(str);

        return model;
    }

    /**
     * 读取文件以字符串的形式返回
     * @param pathname
     * @return
     */
    private String fileToString(String pathname){
        StringBuffer str=new StringBuffer("");

        try {

            FileReader fr=new FileReader(pathname);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();//按行读取
            while(line != null){
                line = br.readLine();
                str.append(line);
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return str.toString();

    }

    /**
     * 文件重命名
     * @param fileName
     * @return
     */
    private String rename(String fileName){
        int lastIndexOf = fileName.lastIndexOf(".");

        //取出后缀
        String suffix = fileName.substring(lastIndexOf);

        String name = fileName.substring(0, lastIndexOf);
        String newName = name + System.currentTimeMillis() + suffix;
        return newName;
    }
}
