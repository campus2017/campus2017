package com.qunar.www;

import java.io.*;
import java.util.*;
/**
 * Created by  on 2017/7/3.ZC
 */
public class CountMostImport {
    public static Map<String,Integer> map = new HashMap<>();
    public static void main(String[] args) {
        if(args.length==0 || args[0].trim().equals("")){
            System.out.println("请输入项目目录");
            return;
        }
        File file = new File(args[0]);
        findImport(file);
        List<Map.Entry<String, Integer>> list = sort();
        int flag = 0;
        for (Map.Entry<String, Integer> en:list
             ) {
            if(flag<10) {
                System.out.println(en.getKey()+":"+en.getValue()+"次");
                flag++;
            }
        }
    }
    public static String getCode(File file) throws IOException {
        BufferedInputStream bin = null;
        String code = null;
        try {
            bin = new BufferedInputStream(new FileInputStream(file));
            int p = (bin.read() << 8) + bin.read();
            switch (p) {
                case 0xefbb:
                    code = "UTF-8";
                    break;
                case 0xfffe:
                    code = "Unicode";
                    break;
                case 0xfeff:
                    code = "UTF-16BE";
                    break;
                default:
                    code = "GBK";
            }
        }finally{
            try {
                bin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       // System.out.println(code);
        return code;

    }
    public static void findImport(File file) {
        File[] files = file.listFiles();
        if(files==null){
            throw new RuntimeException("请输入有效路径");
        }
        for (File f:files
             ) {
            if(f.isDirectory()){
                findImport(f);
            }else{
                String code = null;
                try {
                     code = getCode(f);
                } catch (IOException e) {
                    System.out.println(f.getAbsolutePath()+"文件获取编码失败");
                    System.out.println(e.getMessage().toString());
                    e.printStackTrace();
                }
                BufferedReader br = null;
                StringBuilder sb = new StringBuilder();
                try {
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(f),code));
                    String line=null;
                    while((line = br.readLine()) != null) {
                        String trim = line.replace(" ","");
                        //去除单行注释
                        int index = trim.indexOf("//");
                        if(index==-1){
                        sb.append(trim);
                        }else{
                            sb.append(trim.substring(0,index));
                        }
                    }
                    String content = sb.toString();
                    findImportFromString(content);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //从字符串中查找import的类
    private static void findImportFromString(String content){
        //替换所有多行注释
        String s = content.replaceAll("/\\*(.*?)\\*/", "");
        String[] split = s.split(";");
        for (String string: split
             ) {
            if (string.startsWith("import")) {
                String jarClass = string.substring(6, string.length());
                if (map.containsKey(jarClass)) {
                    map.put(jarClass, map.get(jarClass) + 1);
                } else {
                    map.put(jarClass, 1);
                }
            } else if (string.startsWith("publicclass") || string.startsWith("class")) {
                return;
            }
        }

    }
    //排序
    private static List<Map.Entry<String, Integer>> sort(){
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(entrySet);
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                if(e1.getValue().equals(e2.getValue())){
                    return e1.getKey().compareTo(e2.getKey());
                }
                else{
                    return e2.getValue()-e1.getValue();
                }
            }
        });
        return list;
    }
}
