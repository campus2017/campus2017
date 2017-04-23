import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dang on 2017/4/22.
 * All right reserved.
 */
public class Main {

    public static void main(String[] args){
        String path = "./result";
        List<RateBean> rateBeanList;
        try {
            rateBeanList = getRates();
            System.out.println("获取成功");
            printRate(rateBeanList,path);
            System.out.println("打印成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List<RateBean> getRates() throws IOException {
        List<RateBean> rateBeanList = new ArrayList<RateBean>();
        String url = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        Document doc = Jsoup.connect(url).post();
        Element body = doc.body();
        Element table = body.getElementsByTag("table").last();
        Elements rows = table.getElementsByTag("tr");
        for(int i = 1;i<rows.size();i++){
            Element iRow = rows.get(i);
            Elements iTds = iRow.getElementsByTag("td");
            String iDate = iTds.get(0).text();
            rateBeanList.add(new RateBean(iDate,Double.parseDouble(iTds.get(1).text())));
            rateBeanList.add(new RateBean(iDate,Double.parseDouble(iTds.get(2).text())));
            rateBeanList.add(new RateBean(iDate,Double.parseDouble(iTds.get(4).text())));
        }
        return  rateBeanList;
    }

    public static void printRate(List<RateBean> rateBeanList, String path) throws IOException {
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        HSSFWorkbook hwb = new HSSFWorkbook();
        HSSFSheet sheet = hwb.createSheet("人民币近30天汇率");
        HSSFRow row1 = sheet.createRow(0);

        row1.createCell(0).setCellValue("日期");
        row1.createCell(1).setCellValue("美元汇率");
        row1.createCell(2).setCellValue("欧元汇率");
        row1.createCell(3).setCellValue("港币汇率");

        for(int row = 0; row <rateBeanList.size();row+=3){
            HSSFRow row2 = sheet.createRow(row/3+1);
            row2.createCell(0).setCellValue(rateBeanList.get(row).getDate());
            row2.createCell(1).setCellValue(rateBeanList.get(row).getExchangeRate());
            row2.createCell(2).setCellValue(rateBeanList.get(row+1).getExchangeRate());
            row2.createCell(3).setCellValue(rateBeanList.get(row+2).getExchangeRate());
        }
        sheet.setColumnWidth(0,15*256);

        FileOutputStream fileOutputStream = new FileOutputStream(path+"\\ExchangeRate.xls");
        hwb.write(fileOutputStream);
        fileOutputStream.close();
    }

}
