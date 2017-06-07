package qunar.pre.commission;

import com.google.common.base.Preconditions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Created by hughgilbert on 2017/5/12.
 */
public class Crawler {

    private CreateExcel excelFile = null;

    private static final String URL = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index";
    private static final int RATES_THRESHHOLD = 30;

    public Crawler(String filePath) throws IllegalArgumentException
    {
        Preconditions.checkArgument(filePath.endsWith(".xlsx"),"expect a filename with \".xlsx\"");
        this.excelFile = new CreateExcel(filePath);
    }

    public void createExcel() throws Exception
    {
        try {
            getUrl();
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    private void getUrl() throws Exception
    {
        ArrayList<String> urlList = new ArrayList<String>();
        try
        {
            for(int page = 1 ; urlList.size() < RATES_THRESHHOLD; page++) {
                Document doc = Jsoup.connect(URL + page + ".html").post();
                Elements links = doc.select("a[title$=公告]");
                //Elements links = doc.select("a[onclick=void(0)]");
                for (Element e : links) {
                    if(urlList.size() >= RATES_THRESHHOLD) break;
                    urlList.add("http://www.pbc.gov.cn" + e.attr("href"));
                }
            }

            for(String urlOfRate : urlList)
            {
                ArrayList<String> rates = getRate(urlOfRate);
                excelFile.write(rates);
            }
        }
        catch (Exception e)
        {
            throw e;
        }
        finally {

            excelFile.close();
        }
    }

    private ArrayList<String> getRate(String urlOfRate) throws IOException
    {

        try {
            String tempResult = "";
            Document doc = Jsoup.connect(urlOfRate).post();
            Elements content = doc.select("p:contains(美元对人民币)");
            for (Element e : content) {
                tempResult = e.text();
            }

            return strFilter(tempResult);
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    private ArrayList<String> strFilter(String str)
    {
        ArrayList<String>  resultOfList = new ArrayList<String>();
        String[] result = str.split("[,，。：]");
        for(String e : result)
        {
            if(e.contains("年"))
            {
                resultOfList.add(e.replaceAll("银行间外汇市场人民币汇率中间价为",""));
            }
            if(e.contains("美元") || e.contains("欧元")||e.contains("港元")){
                resultOfList.add(e.replaceAll("1[美欧港]元对人民币",""));
            }
        }
        return resultOfList;
    }

}
