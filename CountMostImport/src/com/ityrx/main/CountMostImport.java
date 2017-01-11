package com.ityrx.main;

import java.io.*;  
import java.util.ArrayList;  
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;  
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;  
import java.util.regex.Pattern;  

  
  
public class CountMostImport {  
	
    String path = new String();  
    List<File> allFiles = new ArrayList<File>();  
    private static String CUSTOM_PATH = "E:\\学习\\核心编程\\WEB\\yrx-web笔记\\代码\\BookManager";  
    private static String STATIC_PROCESS_STRING = "import";  
    private static int INITIALIZE_NUM = 1;  
    //private static int IMPORT_MAXNUM = 1;  
    private String maxImport = new String();  
    List<String> content = new ArrayList<String>();  
    String importNumMax = new String();  
  
    public static void main(String[] args) throws Exception {
    	
    	CountMostImport T = new CountMostImport();  
 
        T.getFilesFromPath(); //将该路径下的文件全部报保存在链表中 
        T.getContentFromFile(); //获取文件内容
        T.processContent();  
        System.out.print(T.maxImport);  
    }
    
    public CountMostImport(String path) {  
        this.path = path;  
    }  
  
    public CountMostImport() {  
        this.path = CUSTOM_PATH;//默认当前文件夹  
    }  
    /**
     * 统计import类
     * @param waitForProcessContent
     */
    @SuppressWarnings("unchecked")
	public void processContent(List<String> waitForProcessContent) {  
       // record myRecord = new record(); //定义一个HashMap表 
    	final Map<String, Integer> map = new HashMap<String, Integer>();
        String[] record = StringListToArray(waitForProcessContent);  
 
        Pattern p = Pattern.compile("(?<= ).*(?=;)"); //意思前后匹配成对
        //统计并存入map表中
        for (int i = 0; i < record.length; i++) {  
            if (record[i].contains(STATIC_PROCESS_STRING))//将字符串和import关键字进行比较
            {  
                Matcher m = p.matcher(record[i]);  
                while (m.find())
                {  
                    if (map.get(m.group()) == null) {  
                        map.put(m.group(), INITIALIZE_NUM);  
                    } else {  
                        int num = map.get(m.group()) + 1;  
                        map.put(m.group(), num);  
                    }  
               }  
            }  
        }  
        //得到map表中前10个并输出
        
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());    
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()    
          {     
              public int compare(Map.Entry<String, Integer> v1, Map.Entry<String, Integer> v2)    
              {    
               if(v2.getValue()!=null&&v1.getValue()!=null&&v2.getValue().compareTo(v1.getValue())>0){    
                return 1;    
               }else{    
                return -1;    
               }    
                    
              }    
          });    
        for(int i=0;i<10;i++)
        {
        	System.out.println(list.get(i)); 
        }     
  }  

    
    public void processContent() {  
        processContent(this.content);  
  
    }  
  
  	/**
  	 * 获取所有文件内容
  	 * @param files
  	 * @throws Exception
  	 */
    public void getContentFromFile(File... files) throws Exception {  
        File singleFile;  
        for (int i = 0; i < files.length; i++) {  
            singleFile = files[i];  
            getContentFromFile(singleFile);  
        }  
    }  
    /**
     * 读取单个文件中的内容保存在content链表中
     * @param file
     * @throws Exception
     */
    public void getContentFromFile(File file) throws Exception {  
        InputStream in = new FileInputStream(file);  
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);  
 
        String line = "";  
        while ((line = br.readLine()) != null) {// 从文本中读取文件  
            this.content.add(line);  //将文本内容按行保存在content链表中
        }  
        if (br != null) {  
            br.close();  
        }  
    }  
    /**
     * 如果文件是一个目录，拆分目录
     * @param listfiles
     * @throws Exception
     */
    public void getContentFromFile(List<File> listfiles) throws Exception {  
        File singleFile;  
        for (int i = 0; i < listfiles.size(); i++) {  
            singleFile = listfiles.get(i);  
            getContentFromFile(singleFile);  
        }  
    }  
    
    public void getContentFromFile() throws Exception {  
 
        getContentFromFile(this.allFiles);  
    }  
  
    public File[] fileListToArray(List<File> listFiles) {  
  
        File[] arrayFiles = new File[listFiles.size()];  
        for (int i = 0; i < listFiles.size(); i++) {  
            arrayFiles[i] = listFiles.get(i);  
        }  
        return arrayFiles;  
    }  
    /**
     * 将链表转成字符串数组
     * @param listStings
     * @return
     */
    public String[] StringListToArray(List<String> listStings) {  
        String[] arrayFiles = new String[listStings.size()];  
        for (int i = 0; i < listStings.size(); i++) {  
            arrayFiles[i] = listStings.get(i);  
        }  
        return arrayFiles;  
    } 
    
    /**
     * 根据路径获得所有路径下的文件，将文件保存在链表中
     * @param path
     */
    public void getFilesFromPath(String path) {  
        File f = new File(path);  
        File[] files = f.listFiles();  

        for (int i = 0; i < files.length; i++) {
        	//判断是否为目录，如果是递归进去，不是将文件加入链表中
            if (!files[i].isDirectory()) {  
                this.allFiles.add(files[i]);  
            } else {  
                getFilesFromPath(files[i].toString());  
            }  
        }  
    }  
  
    public void getFilesFromPath() {  
        getFilesFromPath(this.path);  
    }  
      
}  








