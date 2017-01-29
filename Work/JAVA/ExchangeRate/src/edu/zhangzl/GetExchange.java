package edu.zhangzl;


import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017-01-26.
 */
public class GetExchange {





    public static void main(String[] args) {
          String [] time = getTime();
          //数据爬取地址，该地址返回页面HTML代码
          String address = "http://www.chinamoney.com.cn/fe-c/historyParity.do?startDate="+time[0]+"&endDate="+time[1];
          //将地址传给函数进行数据获取
          String data = getData(address);
          //对数据进行处理生成HTML本地页面
          File html_file = writeTable(data);
          //获取html页面的Document操作对象
          Document doc = getDoc(html_file);
          if(doc!=null){
              //将Doucument对象传给函数，生成Excel表格
              toExcel(doc);
          }else{
              System.out.println("未获取Document对象");
          }

    }
    //该函数获取当前时间和30天后的时间，并进行格式化，转化格式为：yyyy-MM-dd
    private static String [] getTime(){
        Calendar calender = Calendar.getInstance();
        calender.add(Calendar.DAY_OF_MONTH,-30);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String [] date =new String[2];
        //data[0]为当前时间，data[1]为30天后的时间
        date[0] = format.format(calender.getTime());
        date[1] = format.format(new Date(System.currentTimeMillis()));
        return  date;
    }
    //该方法生成EXCEL表格
    private static void toExcel(Document doc){
       //创建数据输出的EXCEL对象文件
        File file = new File(System.getProperty("user.dir")+"/src/edu/zhangzl/exchange.xls");
        WritableWorkbook book = null;
        try {
            //创建工作簿
            book = Workbook.createWorkbook(file);
            //创建第一章sheet，内容为30天的人民币中间价
            WritableSheet sheet_1 = book.createSheet("bankConversionPri",0);
            //得到html页面中的所有表格
            Elements tables = doc.getElementsByTag("table");
            //System.out.println(tables.size());
           //得到目标表格
            Element table_goal = tables.get(2);
            Elements trs = table_goal.getElementsByTag("tr");
            //遍历表格行
            for(int i=0;i<trs.size();i++){

                Elements tds = trs.get(i).getElementsByTag("td");
              //遍历表格当前行的列
                 for(int j=0;j<tds.size();j++){
                     if(j==1||j==2||j==4||j==0){
                         Label label = null;
                         Element td = tds.get(j);
                         if(j!=4) {
                             label = new Label(i, j, tds.get(j).text());
                         }else{
                             label = new Label(i,j-1,tds.get(j).text());
                         }
                         //添加单元格
                         sheet_1.addCell(label);
                    }
                }
            }
            //写入工作簿
            book.write();
        } catch (IOException e){
            e.printStackTrace();
        }catch (WriteException e){
            e.printStackTrace();
        }finally {
            try {
                book.close();
            }catch (WriteException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    //该方法得到数据，将数据转换成字符串进行返回
    private static String getData(final String address){
        HttpURLConnection connection = null;
        StringBuilder response = null;
        try{
            //根据地址生成URL
            URL url = new URL(address);
            //打开连接
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方式
            connection.setRequestMethod("GET");
            //设置请求超时时间
            connection.setConnectTimeout(20000);
            //设置数据读取时间
            connection.setReadTimeout(20000);
            //读取数据
            InputStream in = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            response = new StringBuilder();
            String line = null;
            while((line = reader.readLine() ) != null){
                response.append(line);
            }
            reader.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            {
                if (connection != null) {
                    connection.disconnect();
                }

            }
        }

        return response.toString();
    }

    //获取页面文件操作对象
    private static Document getDoc(File file){
        Document doc = null;
        try {
            //设置编码格式
            doc = Jsoup.parse(file, "UTF-8", "");
        }catch(IOException e){
            e.printStackTrace();
        }
        return doc;
    }
//该方法将得到的html字符串写入到本地生成本地html文件
//字符串为爬虫方法返回的字符串
    private static File writeTable(String data){
        String file_path = System.getProperty("user.dir")+"/src/edu/zhangzl/excel.html";
        File html_file = new File(file_path);
        FileWriter fw = null;
        try {
            fw = new FileWriter(html_file);
            fw.write(data);
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                fw.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return html_file ;
    }


}
