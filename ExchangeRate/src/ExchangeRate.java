/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-21
 * Time: 下午5:14
 * To change this template use File | Settings | File Templates.
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.Number;
import jxl.write.biff.RowsExceededException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;



public class ExchangeRate {

    /**
     * @param args
     * @throws IOException
     * @throws WriteException
     * @throws RowsExceededException
     */
    public static void main(String[] args) throws IOException, RowsExceededException, WriteException {
        // TODO Auto-generated method stub
        double[] usd;
        double[] eur;
        double[] hkd;
        String[] date;
        OutputStream out;
        String url="http://www.chinamoney.com.cn/fe-c/historyParity.do";
        Document doc=Jsoup.connect(url).get();
        Elements list=doc.getElementsByAttributeValue("class", "dreport-title").get(0).getElementsByTag("tbody").get(0).children();
        usd = new double[31];
        eur = new double[31];
        hkd = new double[31];
        date = new String[31];
        for(int i=1;i<=21;i++)
        {
            date[i]=list.get(i).child(0).text();
            usd[i]=Double.valueOf(list.get(i).child(1).text());
            eur[i]=Double.valueOf(list.get(i).child(2).text());
            hkd[i]=Double.valueOf(list.get(i).child(4).text());
        }
        String path="D://exchangerate";
        File f=new File(path);
        if(!f.exists())
        {
            f.mkdir();
        }

        String excelPath=path+"//"+"exchangeRate.xls";
        File excelFile=new File(excelPath);
        if(!excelFile.exists())
        {
            if(!excelFile.createNewFile())
            {
                System.out.println("建立excel文件失败：建立excel文件发生异常");
                return;
            }
        }
        out = new FileOutputStream(excelFile);
        createExcel(date,usd,eur,hkd,out);
        System.out.println("Success!");
    }
    public static void createExcel(String[] s,double[] u,double[] e,double[] h,OutputStream os) throws IOException, RowsExceededException, WriteException
    {
        try {
            //创建工作薄
            WritableWorkbook workbook = Workbook.createWorkbook(os);
            //创建新的一页
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);
            Label date = new Label(0,0,"日期");
            sheet.addCell(date);
            Label usa = new Label(1,0,"美元");
            sheet.addCell(usa);
            Label eur = new Label(2,0,"欧元");
            sheet.addCell(eur);
            Label hk = new Label(3,0,"港币");
            sheet.addCell(hk);

            for(int i=1;i<=21;i++)
            {
                Label d = new Label(0,i,s[i]);
                sheet.addCell(d);

                Number n1=new Number(1,i,u[i]);
                sheet.addCell(n1);

                Number n2=new Number(2,i,e[i]);
                sheet.addCell(n2);

                Number n3=new Number(3,i,h[i]);
                sheet.addCell(n3);
            }
            workbook.write();
            workbook.close();
            os.close();
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
