//操作excel 工具包
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//网页解析工具包
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args){

        String path = null;
        System.out.print("请输入打印路径:");
        Scanner in = new Scanner(System.in);
        path = in.nextLine();

        try {
            List<RateBean> result  = getRate();
            printRate(result, path);
            System.out.println("打印成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<RateBean> getRate() throws IOException {
        List<RateBean> list = new ArrayList<RateBean>();
        /**
         * 将网页中的表格拆分 拿到数据
         */
        Document rDocument = Jsoup.connect("http://www.chinamoney.com.cn/fe-c/historyParity.do").post();
        Element rBody = rDocument.body();
        Element rTable = rBody.getElementsByTag("table").last();
        Elements rRow = rTable.getElementsByTag("tr");

        for(int i = 1; i < rRow.size(); i++ ){
            Elements rTd = rRow.get(i).getElementsByTag("td");
            String date = rTd.get(0).getElementsByTag("div").get(0).text();
            list.add(new RateBean(date,Double.parseDouble(rTd.get(1).text())));
            list.add(new RateBean(date,Double.parseDouble(rTd.get(2).text())));
            list.add(new RateBean(date,Double.parseDouble(rTd.get(4).text())));
        }

        System.out.println("网页读取成功");
        return list;

    }

    /**
     * 按照表格形式 输出数据
     * @param list 汇率类集合
     * @param path 路径
     * @throws IOException
     */
    public static void printRate(List<RateBean> list, String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }

        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet hSheet=hwb.createSheet("人民币汇率中间价");
        HSSFRow row1=hSheet.createRow(0);

        row1.createCell(0).setCellValue("日期");
        row1.createCell(1).setCellValue("美元汇率");
        row1.createCell(2).setCellValue("欧元汇率");
        row1.createCell(3).setCellValue("港币汇率");


        for(int row=0;row<list.size();row+=3){
            HSSFRow row2=hSheet.createRow(row/3+1);
            row2.createCell(0).setCellValue(list.get(row).getDate());
            row2.createCell(1).setCellValue(list.get(row).getPrice());
            row2.createCell(2).setCellValue(list.get(row+1).getPrice());
            row2.createCell(3).setCellValue(list.get(row+2).getPrice());
        }
        hSheet.setColumnWidth(0,15*256);


        FileOutputStream fos = new FileOutputStream(path + "\\ExchangeRate.xls");
        hwb.write(fos);
        fos.close();
    }
}
