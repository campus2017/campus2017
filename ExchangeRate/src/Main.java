import com.qunar.amao.service.CrawlReferenceRate;
import com.qunar.amao.service.PrintExcelService;

import com.qunar.amao.pojo.ReferenceRate;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try{
            //打印汇率中间价信息↓
            CrawlReferenceRate crr = new CrawlReferenceRate();

            ArrayList<ReferenceRate> list = crr.CrawlReferenceData("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html");

            OutputStream out = new FileOutputStream("./excel/ReferenceRate.xls");
            String[] headers = {"日期","1美元对人民币汇率（元）","1欧元对人民币汇率（元）","1港元对人民币汇率（元）"};
            PrintExcelService ex = new PrintExcelService();
            ex.PrintExcel("人民币汇率中间价",headers,list,out,"yyyy-MM-dd");
            out.close();

            System.out.println("success");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}

