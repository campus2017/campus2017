/**
 * Created by muhongfen on 17/4/24.
 * 工具类，用来调用外部工具解析html文件和写入excel文件
 */
import jxl.write.biff.RowsExceededException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class Utils {
    //Jsoup解析document，获取汇率
    public static Map<String,Float> getRateFromDoc(Document doc){
        Map<String,Float> map = new TreeMap<String, Float>();
        Elements es = doc.select(".table-responsive tr");
        if(es.size()>0){
            for(int i=1;i<es.size();i++){
                Document d = Jsoup.parse(es.get(i).toString());
                String[] res = d.body().text().toString().split(" ");
                String date = res[0];
                float rate = new Float(res[1]);
                map.put(date,rate);
            }
        }
        return map;
    }
    //jxl写入excel
    public static void writeToExcel(Map<String,Map<String,Float>> result,String path){
        File file = new File(path);
            try {
                if(!file.exists()) {
                    file.createNewFile();
                }
                WritableWorkbook wbook = Workbook.createWorkbook(file);
                WritableSheet wsheet = wbook.createSheet("汇率",0);
                Label date = new Label(0,0,"Date");
                wsheet.addCell(date);
                int column = 1;
                for(Map.Entry<String,Map<String,Float>> entry:result.entrySet())
                {
                    String city = entry.getKey();
                    Map<String,Float>  data = entry.getValue();
                    Label c = new Label(column,0,city);
                    wsheet.addCell(c);
                    int i = 0;
                    for(Map.Entry<String,Float> entry2:data.entrySet())
                    {
                        Label dateData = new Label(0,i+1,entry2.getKey());
                        Label rateData = new Label(column,i+1,entry2.getValue().toString());
                        if(column == 1)
                            wsheet.addCell(dateData);
                        wsheet.addCell(rateData);
                        i++;
                    }
                    column++;
                }
                wbook.write();
                wbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }
        }
    }
