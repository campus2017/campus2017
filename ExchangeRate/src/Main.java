import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        //保存爬取到的货币中间价路径
        String filePath = "D://qunar//ExchangeRate//data";
        //构造货币中间价字典
        Map<String, String[]> ratesMap = getRatesMap(filePath);
        //将货币中间价字典保存成excel文件
        ExcelWrite.excelExp(".//result.xls", ratesMap);
    }

    public static Map<String, String[]> getRatesMap(String dirPath){
        Map<String, String[]> ratesMap = new HashMap<>();
        File file = new File(dirPath);
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<>();
            list.add(file);
            File temp_file;
            File[] files;
            while (!list.isEmpty()) {
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file2 : files) {
                    if (file2.isFile() && !file2.isHidden()) {
                        System.out.println("扫描文件:" + file2.getAbsolutePath());
                        phraseFile(file2, ratesMap);
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
        return ratesMap;
    }

    public static void phraseFile(File file, Map<String, String[]> ratesMap){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                //测试
                ExchangeRate rate = new ExchangeRate(file.getName().split("\\.")[0]);
                rate.setRateMap(tempString);
                ratesMap.put(rate.getDate(),rate.getRate());
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e1){
                    e1.printStackTrace();
                }
            }
        }
    }
}

class ExchangeRate{
    Map<String,String> rateMap;
    String date;

    public ExchangeRate(String date){
        this.date = date;
        this.rateMap = new HashMap<>();
    }

    public void setRateMap(String str){
        String[] tempArr = str.split("：")[1].split("，|,|。");
        for (String s:tempArr) {
            //System.out.println(s);
            if(s.contains("美元")){
                rateMap.put("美元",getRateByStr(s.split("对")[1]));
            }else if(s.contains("欧元")){
                rateMap.put("欧元",getRateByStr(s.split("对")[1]));
            }else if(s.contains("港元")){
                rateMap.put("港元",getRateByStr(s.split("对")[1]));
            }
        }
    }

    private String getRateByStr(String str){
            Pattern p = Pattern.compile("[\\u4E00-\\u9FA5]");
            Matcher m = p.matcher(str);
            return m.replaceAll("").trim();
    }

    public void printRate(){
        for(Map.Entry<String, String> entry : rateMap.entrySet()){
            System.out.println("key = "+entry.getKey() + ", value = "+entry.getValue());
        }
    }

    public String getDate(){
        return date;
    }

    public String[] getRate(){
        String[] rateList = new String[3];
        for(Map.Entry<String, String> entry : rateMap.entrySet()){
            if(entry.getKey() == "美元"){
                rateList[0] = entry.getValue();
            }else if(entry.getKey() == "欧元"){
                rateList[1] = entry.getValue();
            }else if(entry.getKey() == "港元"){
                rateList[2] = entry.getValue();
            }
        }
        return rateList;
    }
}
