import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 娜娜 on 2017/6/27.
 */
public class ExchangeRate {

    private static StringBuilder contentBuffer;
    private static int row;//行
    private static int column;//列

    private static void getExchangeRate(List<String> listurl) throws IOException, WriteException {
        //t.xls为要新建的文件名
        WritableWorkbook book= Workbook.createWorkbook(new File("ratetmp.xls"));
        //生成名为“第一页”的工作表，参数0表示这是第一页
        WritableSheet sheet=book.createSheet("人民币汇率",0);
        String title[]={"时间","美元","欧元","港币"};//标题行
        //写入内容
        for(int i=0;i<4;i++)  //title
            sheet.addCell(new Label(i,0,title[i]));

        for(String tmp:listurl) {

            Document document = Jsoup.parse(connectUrl(tmp));

            Elements trs = document.select("table").select("tr");
            //获得了网页上的表格中的行，返回的elements是该表格有多少行，如果是多个表格，那么select（）中的是该表格的标签，如它的class等属性，以确定你选择的是哪一个表格。
            row = trs.size();
            String[][] tmpContainer = new String[row][2];
            for (int i = 1; i < trs.size(); i++) {//获取每一行的列
                Elements tds = trs.get(i).select("td");
                //对每一行中的某些你需要的列进行处理
                for (int j = 0; j < tds.size(); j++) {
                    if (0 == j) {
                        String time = tds.get(j).text();//获取第i行第j列的值
                        tmpContainer[i - 1][j] = time;
                    } else if (1 == j) {
                        String exchangeRate = tds.get(j).text();
                        tmpContainer[i - 1][j] = exchangeRate;
                    }
                }
            }
            for(int i = 0; i < tmpContainer.length; i++)  //context
            {
                if (0 == column){
                    sheet.addCell(new Label(0,i+1,tmpContainer[i][0]));
                    sheet.addCell(new Label(1,i+1,tmpContainer[i][1]));
                   // System.out.println("aaaaaaa"+tmpContainer[i][1]);
                   // System.out.println("llllllllllllpppp"+column);
                }else{
                  //  System.out.println("ooooolllllllllpppp"+column);
                  //  System.out.println("bbbbbbbb"+tmpContainer[i][1]);
                    sheet.addCell(new Label(column,i+1,tmpContainer[i][1]));
                }
            }

            if (0 == column){
                column = column + 2;
            }else{
                column++;
            }
        }
        //写入数据
        book.write();
        //关闭文件
        book.close();
    }
    private static String connectUrl(String url) throws IOException {
        String tmp = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(
            new URL(url).openConnection().getInputStream(), "utf-8"));//GB2312可以根据需要替换成要读取网页的编码

        contentBuffer = new StringBuilder();
        while ((tmp = in.readLine()) != null) {
            contentBuffer.append(tmp);
        }
        return contentBuffer.toString();
    }

    public static void main(String[] args) throws IOException, WriteException {
        List<String> list = new ArrayList<String>();
        String usdurl = "http://www.kuaiyilicai.com/huilv/d-safe-usd.html";//美元
        String eururl = "http://www.kuaiyilicai.com/huilv/d-safe-eur.html";//欧元
        String hkdurl = "http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";//港币
        list.add(usdurl);
        list.add(eururl);
        list.add(hkdurl);

        ExchangeRate.getExchangeRate(list);
    }

}
