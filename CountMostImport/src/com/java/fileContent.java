package com.java;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 17/7/2.
 */
public class fileContent {
   private String fileName="";
   private List<String> content=null;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    public Map<String, Integer> getContent() {
//        return content;
//    }
//
//    public void setContent(Map<String, Integer> content) {
//        this.content = content;
//    }


    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }

    public fileContent(String name)
    {
        content=new ArrayList<>();
        fileName=name;
        try {
            FileReader fr=new FileReader(name);
            BufferedReader br=new BufferedReader(fr);
            String str=br.readLine();
            while(str!=null)
            {
                if(str.indexOf("import ")==0)
                content.add(str);
//                if(str.contains("import "))
//                {
//                    if(content.get(str)!=null)
//                    {
//                        int preNum=content.get(str);
//                        content.put(str,preNum+1);
//                    }
//                    else
//                        content.put(str,1);
//                }
                str=br.readLine();
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // content=Content;
    }
}
