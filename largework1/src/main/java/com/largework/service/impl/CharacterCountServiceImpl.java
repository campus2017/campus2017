package com.largework.service.impl;

import com.largework.model.Count;
import com.largework.service.ICharacterCountService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import com.largework.model.UploadFile;

/**
 * Created by liudan on 2017/6/16.
 */

@Service
public class CharacterCountServiceImpl implements ICharacterCountService{

    public static Boolean regexFind(String pattern, String text){
        boolean isMatch = Pattern.matches(pattern, text);
        return isMatch;
    }

    public String jsonReturn(Count count){
        LinkedHashMap<String,Integer> map=count.getMap();
        String englishWordNum=count.getEnglishWordNum()+"";
        String chineseWordNum=count.getChineseWordNum()+"";
        String numberNum=count.getNumberNum()+"";
        String punctuationNum=count.getPunctuationNum()+"";
        JSONObject  jo = new JSONObject();
        jo.put("eW",englishWordNum);
        jo.put("cW",chineseWordNum);
        jo.put("nN",numberNum);
        jo.put("pN",punctuationNum);
        jo.put("map",map);
        return jo.toString();
    }

    public static LinkedHashMap<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<String,Integer>();
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(oriMap.entrySet());
            Collections.sort(entryList,
                    new Comparator<Map.Entry<String, Integer>>() {
                        public int compare(Map.Entry<String, Integer> entry1,
                                           Map.Entry<String, Integer> entry2) {
                            return entry2.getValue() - entry1.getValue();
                        }
                    });
            Iterator<Map.Entry<String, Integer>> iter = entryList.iterator();
            Map.Entry<String, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }

    public Count characterCount(String txt){
        Count count=new Count();
        Map<String,Integer> map=new HashMap<String,Integer>();
        LinkedHashMap<String,Integer> sortMapAll;
        LinkedHashMap<String,Integer> mapThree=new LinkedHashMap<String,Integer>();
        int ew=0;//英文
        int cw=0;//中文
        int nu=0;//数字
        int bd=0;//标点符号
        String ewp="[a-zA-Z]+";
        String nup="\\d+";
        String cwp="[\\u4E00-\\u9FA5]+";
        String temp="";
        for (int i=0;i<txt.length();i++) {
            temp=txt.substring(i,i+1);
            if (regexFind(ewp,temp)==true){
                ew++;
            }else if (regexFind(cwp,temp)==true){
                cw++;
                map.put(temp, (map.get(temp)==null?1:map.get(temp)+1));
            }else if (regexFind(nup,temp)==true){
                nu++;
            }
        }
        sortMapAll=sortMapByValue(map);
        Iterator<Map.Entry<String,Integer>> iterator= sortMapAll.entrySet().iterator();
        int j=0;
        while(iterator.hasNext())
        {
            Map.Entry<String,Integer> entry = iterator.next();
            if (j<3){
                mapThree.put(entry.getKey(),entry.getValue());
                j++;
            }
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

        bd=txt.length()-ew-cw-nu;
        count.setChineseWordNum(cw);
        count.setEnglishWordNum(ew);
        count.setNumberNum(nu);
        count.setPunctuationNum(bd);
        count.setMap(mapThree);
        return count;
    }

    public UploadFile uploadFile(HttpServletRequest request){
        String text="";
        UploadFile file=new UploadFile();
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        factory.setSizeThreshold(1024 * 500);//设置内存的临界值为500K
        upload.setSizeMax(1024 * 1024 * 5);//设置上传的文件总的大小不能超过5M
        try {
            // 1. 得到 FileItem 的集合 items
            List<FileItem> items = upload.parseRequest(request);
            String savePath = request.getServletContext().getRealPath("/WEB-INF/upload/");
            System.out.println("savePath"+savePath);
            // 2. 遍历 items:
            StringBuilder sb=new StringBuilder();
            String lastFileName="";
            for (FileItem item : items) {
                // 若是文件域则把文件保存到 e:\\files 目录下.
                    String fileName = item.getName();
                    long sizeInBytes = item.getSize();
                    lastFileName=fileName;
                    System.out.println("fileName： "+fileName);
                    System.out.println("fileSize： "+sizeInBytes);
                    InputStream in = item.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    fileName = savePath + fileName;//文件最终上传的位置
                    System.out.println("uploadLocation： "+fileName);
                    OutputStream out = new FileOutputStream(fileName);
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                        sb.append(new String(buffer, 0, len));
                    }
                    out.close();
                    in.close();
                }
            text=sb.toString();
            file.setFilename(lastFileName);
            file.setText(text);
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  file;
    }
}
