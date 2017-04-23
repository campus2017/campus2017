package com.qunar.muhongfen.util;

import java.io.*;


/**
 * Created by muhongfen on 17/1/8.
 */
public class FileUtil {
    //上传文件
    public static void upFile(InputStream is, String fileName, String filePath) {

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }
        File f = new File(filePath + "/" + fileName);
        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            byte[] bt = new byte[4096];
            int len;
            while ((len = bis.read(bt)) > 0) {
                bos.write(bt, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != bos) {
                    bos.close();
                    bos = null;
                }
                if (null != fos) {
                    fos.close();
                    fos = null;
                }
                if (null != is) {
                    is.close();
                    is = null;
                }
                if (null != bis) {
                    bis.close();
                    bis = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //读取文件字符串
    public static String getFileString(String fileName){
        BufferedReader reader = null;
        String str = "";
        try{
            File file = new File(fileName);
            reader = new BufferedReader(new FileReader(file));
            String tmp = "";
            while((tmp = reader.readLine())!=null){
                str += tmp.trim();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
