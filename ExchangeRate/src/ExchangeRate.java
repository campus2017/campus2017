/**
 * Created by steamedfish on 17-6-24.
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ExchangeRate {

    //get the data from url China people bank
    public static Document getDigitFromJsoup(String url){
        Document doc = null;
        try{
                doc = Jsoup.connect(url).timeout(1000).get();
            String title = doc.body().toString();
        }catch(SocketTimeoutException e){
            System.out.println("Socket is time out");
        }catch (IOException e){
            e.printStackTrace();
        }
        return doc;
    }

    //analyze the thirty days' exchange rate
    public static void getThirtyExchangeRate(){
        double dollar;
        double euro;
        double hkdollar;
        List<String> list = new ArrayList<String>();
        Document doc = ExchangeRate.getDigitFromJsoup("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action");
        System.out.println(doc);
    }
    public  static void main(String[] args){
       ExchangeRate.getThirtyExchangeRate();
    }

}
