package com.qunar.dujia.utils;

import com.qunar.dujia.model.MostNum;
import com.qunar.dujia.model.TongJi;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tianyiren on 16-12-22.
 */
public class modelUtils {

    public static TongJi init(TongJi t){
        t.setNumber(0);
        t.setEnglish(0);
        t.setChinese(0);
        t.setBiaodian(0);
        return t;
    }

    public static TongJi addOpt(TongJi a,TongJi b){
        TongJi sum = new TongJi();
        sum.setBiaodian(a.getBiaodian()+b.getBiaodian());
        sum.setChinese(a.getChinese()+b.getChinese());
        sum.setEnglish(a.getEnglish()+b.getEnglish());
        sum.setNumber(a.getNumber()+b.getNumber());
        return sum;
    }

    public static List<MostNum> readFileChines(String fileName) {

        File file = new File(fileName);
        List<MostNum> li = new ArrayList<MostNum>();
        Map<String,Integer>map = new TreeMap<String, Integer>();
        BufferedReader reader = null;
        try {
            //System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                tempString = tempString.replaceAll(" ","");
                tempString = tempString.replaceAll("\n","");
                tempString = tempString.replaceAll("\t","");
                tempString = getChinese(tempString);
                for (int i=0;i<tempString.length();i++) {
                    // 用每一个字符作为键，在TreeMap中查找
                    Integer val = map.get(""+tempString.charAt(i));
                    if (val == null) {
                        // 返回null，则不存在，存储1
                        map.put(""+tempString.charAt(i), 1);
                    } else {
                        // 返回非null，则把值加1，重新存储
                        val++;
                        map.put(""+tempString.charAt(i), val);
                    }
                }
                line++;
            }

            List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

            //然后通过比较器来实现排序

            Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {

                //降序排序

                public int compare(Map.Entry<String, Integer> o1,

                                   Map.Entry<String, Integer> o2) {
                    if(o1.getValue().compareTo(o2.getValue())==0){
                        return o2.getKey().compareTo(o1.getKey());
                    }
                    return o2.getValue().compareTo(o1.getValue());

                }



            });


            int flag = 0;

            for(Map.Entry<String,Integer> mapping:list){

                if(flag>=3){
                    break;
                }
                //System.out.println(mapping.getKey()+":"+mapping.getValue());

                MostNum num = new MostNum();

                num.setName(mapping.getKey());

                num.setNum(mapping.getValue());

                li.add(num);
                flag++;
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return li;
    }

    public static TongJi readFileByLines(String fileName) {
        TongJi tongJi = new TongJi();
        TongJi resTongJi = new TongJi();
        init(tongJi);
        init(resTongJi);

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                tempString = tempString.replaceAll(" ","");
                tempString = tempString.replaceAll("\n","");
                tempString = tempString.replaceAll("\t","");
                resTongJi = addOpt(resTongJi,getObject(tempString));
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return resTongJi;
    }

    public static String saveFileToServer(MultipartFile multifile, String path)
            throws IOException {
        // 创建目录
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        // 读取文件流并保持在指定路径
        InputStream inputStream = multifile.getInputStream();
        OutputStream outputStream = new FileOutputStream(path
                + multifile.getOriginalFilename());
        byte[] buffer = multifile.getBytes();
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = inputStream.read(buffer)) != -1) {
            bytesum += byteread;
            outputStream.write(buffer, 0, byteread);
            outputStream.flush();
        }
        outputStream.close();
        inputStream.close();

        return path + multifile.getOriginalFilename();
    }

//    public static TongJi getFileObject(MultipartFile multipartFile){
//        try {
//            InputStream inputStream = multipartFile.getInputStream();
//            byte[] buffer = multipartFile.getBytes();
//            int bytesum = 0;
//            int byteread = 0;
//            while ((byteread = inputStream.read(buffer)) != -1) {
//                bytesum += byteread;
//
//            }
//            inputStream.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }

    public static TongJi getObject(String text){
        TongJi myobj = new TongJi();
        Integer yingwen = 0, shuzi = 0, hanzi = 0, biaodian = 0;
        String reg_chi = "^[\u4e00-\u9fa5]$";
        String reg_number="[0-9]";
        String reg_eng = "[A-Z]|[a-z]";
        for(int i=0;i<text.length();i++)
        {
            if(Pattern.matches("^[\u4E00-\u9FA5]{0,}$",""+text.charAt(i))){
                hanzi++;
            }else if(Pattern.matches("^[0-9]{0,}$",""+text.charAt(i))){
                shuzi++;
            }else if(Pattern.matches("^([A-Z]|[a-z]){0,}$",""+text.charAt(i))){
                yingwen++;
            }else {
                biaodian++;
            }
        }
        myobj.setBiaodian(biaodian);
        myobj.setChinese(hanzi);
        myobj.setEnglish(yingwen);
        myobj.setNumber(shuzi);
        return myobj;
//    public static boolean matches(String regex, CharSequence input)：
    }

    public static String getChinese(String paramValue) {
        String regex = "(([\u4e00-\u9fa5]|[A-Z]|[a-z])+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str+= matcher.group(0);
        }
        return str;
    }


    public static List<MostNum> getFinalObj(String text){
        String valueChinese = modelUtils.getChinese(text);

        Map<String, Integer> map = new TreeMap<String, Integer>();
        // 遍历字符数组，获取到每一个字符
        for (int i=0;i<valueChinese.length();i++) {
            // 用每一个字符作为键，在TreeMap中查找
            Integer val = map.get(""+valueChinese.charAt(i));
            if (val == null) {
                // 返回null，则不存在，存储1
                map.put(""+valueChinese.charAt(i), 1);
            } else {
                // 返回非null，则把值加1，重新存储
                val++;
                map.put(""+valueChinese.charAt(i), val);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());

        //然后通过比较器来实现排序

        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {

            //降序排序

            public int compare(Map.Entry<String, Integer> o1,

                               Map.Entry<String, Integer> o2) {

                if(o2.getValue().compareTo(o1.getValue())==0){
                    return o2.getKey().compareTo(o1.getKey());
                }

                return o2.getValue().compareTo(o1.getValue());

            }



        });

        List<MostNum> li = new ArrayList<MostNum>();

        int flag = 0;

        for(Map.Entry<String,Integer> mapping:list){

            if(flag>=3){
                break;
            }
            //System.out.println(mapping.getKey()+":"+mapping.getValue());

            MostNum num = new MostNum();

            num.setName(mapping.getKey());

            num.setNum(mapping.getValue());

            li.add(num);
            flag++;
        }


        return li;

    }

}
