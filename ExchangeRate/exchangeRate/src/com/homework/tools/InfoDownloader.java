package com.homework.tools;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * Created by dell on 2016/11/17.
 */
public class InfoDownloader {
    private String url;
    private final static int CACHE=1024;
    private final static int DAY_NUM=-30;

    public static boolean downloadExcelFile(String url,String savedName){
        return sendPost(url,generateParams(),savedName);
    }

    /**
     * 生成post参数
     * @return 参数，格式name1=value1&name2=value2 的形式。
     */
    private static String generateParams(){
        String res;
        Date today =new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_YEAR,DAY_NUM);//日期减30
        Date startDay=calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        res="projectBean.startDate="+format.format(startDay)+"&projectBean.endDate="+format.format(today);
        return res;
    }
    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    private static boolean sendPost(String url,String param ,String savedFileName) {
        PrintWriter out;
       InputStream in;

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
             /* 直接读二进制数据*/
            in =  conn.getInputStream();
            FileOutputStream fileout = new FileOutputStream(savedFileName);
            byte[] buffer = new byte[CACHE];
            int byteRead;
            while ((byteRead = in.read(buffer)) != -1) {
                fileout.write(buffer,0,byteRead);
            }
            fileout.close();
            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

