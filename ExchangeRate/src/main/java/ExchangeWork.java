
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


/**
 * Created by qunar qiongweiren.
 */
public class ExchangeWork {

    private int dayNum = 30;
    private String url = "http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html";
    private String fileName = "test.xls";

    public ExchangeWork()
    {

    }

    public ExchangeWork(int day_num)
    {
        dayNum = day_num;
    }

    public ExchangeWork(int day_num, String file_name)
    {
        dayNum = day_num;
        fileName = file_name;
    }

    public void run() throws IOException, ParseException, WriteException {

        List<Label> labelList = new ArrayList<Label>();

        List<String> dateLists = new ArrayList<String>();

        dateLists = getDateList();

        labelList  = getExchangeDataLists(dateLists);

        doWriteXls(labelList);

    }


    private List<String> getDateList() throws ParseException {

        List<String> dataLists = new ArrayList<String>();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        for(int i=0; i<dayNum ;i++)
        {
            date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
            Date endDate = dft.parse(dft.format(date.getTime()));
            String dateNowStr = dft.format(endDate);
            dataLists.add(dateNowStr);

        }
        return  dataLists;
    }


    private List<Label> getExchangeDataLists(List<String> dateLists) throws IOException {

        //   create title .  (cols, rows)
        Label date_ = new Label(0,0,"DATE");
        Label dollar = new Label(1,0,"Dollar");
        Label hongkong = new Label(2,0,"HongKong");
        Label euro = new Label(3,0,"Euro");

        List<Label> labelList = new ArrayList<Label>();
        labelList.add(date_);
        labelList.add(dollar);
        labelList.add(hongkong);
        labelList.add(euro);

        Label dataSheet;

        int item_num = 1;
        for(String date:dateLists)
        {

            Document doc = Jsoup.connect(url).data("querydate", date).get();
            String title = doc.title();
            Elements e1  = doc.getElementsByTag("tbody");

            String i1 = e1.text().substring(59,66).trim();
            String i2 = e1.text().substring(10,18).trim();
            String i3 = e1.text().substring(108,114).trim();


            dataSheet = new Label(0,item_num,date);
            labelList.add( dataSheet );

            dataSheet = new Label(1,item_num,i1);
            labelList.add( dataSheet );

            dataSheet = new Label(2,item_num,i2);
            labelList.add( dataSheet );

            dataSheet = new Label(3,item_num,i3);
            labelList.add( dataSheet );


            item_num++;
        }

        return labelList;
    }




    private void doWriteXls(List<Label> labelList) throws IOException, WriteException {
        File os = new File(fileName);

        WritableWorkbook workbook = Workbook.createWorkbook(os);
        WritableSheet sheet = workbook.createSheet("First Sheet",0);

        for(Label label:labelList)
        {
            sheet.addCell(label);
        }

        workbook.write();
        workbook.close();

    }



}
