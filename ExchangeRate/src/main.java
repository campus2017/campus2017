/**
 * Created by MyPC on 2017/6/27.
 */

import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import jxl.write.biff.RowsExceededException;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.jsoup.select.Elements;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {
        Calendar cal=Calendar.getInstance();
        cal.setTime(new Date());
        SimpleDateFormat date=new SimpleDateFormat("yyyy-MM-dd");
        String today=date.format(cal.getTime());
        cal.add(cal.DATE,-30);
        String datebegin=date.format(cal.getTime());
        Map<String,String> url=new TreeMap<String,String>();
        url.put("欧元汇率","http://www.kuaiyilicai.com/huilv/d-safe-eur.html");
        url.put("美金汇率","http://www.kuaiyilicai.com/huilv/d-safe-usd.html");
        url.put("港币汇率","http://www.kuaiyilicai.com/huilv/d-safe-hkd.html");
        Map<String,Map<String,Float>> rates=new TreeMap<String,Map<String,Float>>();
        for(Map.Entry<String,String> entry:url.entrySet()){
            try{
                Connection con=Jsoup.connect(entry.getValue());
                con.data("datebegin",datebegin);
                con.data("dateend",today);
                Document doc=con.post();
                Map<String,Float> rate =getRate(doc);
                rates.put(entry.getKey(),rate);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        File directory=new File("");
        saveinExcel(rates,directory.getCanonicalPath()+"/src/rate.xls");
    }

    public static Map<String,Float> getRate(Document doc){
        Map<String,Float> ratemap=new TreeMap<String,Float>();
        Elements days=doc.select(".table-responsive tr");
        if(days.size()>0){
            for(int i=1;i<days.size();i++){
                Document line=Jsoup.parse(days.get(i).toString());
                String[] temp=line.body().text().toString().split(" ");
                String date=temp[0];
                float rate=new Float(temp[1]);
                ratemap.put(date,rate);
            }
        }
        return ratemap;
    }
    public static void saveinExcel(Map<String,Map<String,Float>> rates,String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            WritableWorkbook excel = Workbook.createWorkbook(file);
            WritableSheet sheet = excel.createSheet("汇率", 0);
            Label date = new Label(0, 0, "日期");
            sheet.addCell(date);
            int column = 1;
            for (Map.Entry<String, Map<String, Float>> entry : rates.entrySet()) {
                String money = entry.getKey();
                Map<String, Float> data = entry.getValue();
                Label moneykind = new Label(column, 0, money);
                sheet.addCell(moneykind);
                int i = 0;
                for (Map.Entry<String, Float> entry2 : data.entrySet()) {
                    Label dateData = new Label(0, i + 1, entry2.getKey());
                    Label rate = new Label(column, i + 1, entry2.getValue().toString());
                    if (column == 1)
                        sheet.addCell(dateData);
                    sheet.addCell(rate);
                    i++;
                }
                column++;
            }
            excel.write();
            excel.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RowsExceededException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }

}
