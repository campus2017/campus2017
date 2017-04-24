import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
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
import java.util.*;

/**
 * Created by dang on 2017/4/22.
 * All right reserved.
 */
public class Main {

    public static void main(String[] args){
        String url = "http://www.chinamoney.com.cn/fe-c/historyParity.do";
        String encode = "utf-8";
        String path = "./result";
        Map<String, String> params = new HashMap<String, String>();
        params.put("startDate", "2017-02-25");
        params.put("endDate", "2017-04-24");
        List<RateBean> rateBeanList;
        try {
            String html = getHTTPResponse(url, params, encode);
            System.out.println("网页获取成功！");
            rateBeanList = getRates(html);
            System.out.println("网页数据抽取成功！");
            printRate(rateBeanList,path);
            System.out.println("数据处理成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getHTTPResponse(String url, Map<String, String> params, String charEncode) throws IOException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        List<BasicNameValuePair> nameValuePairs = new ArrayList();
        Set keySet = params.keySet();
        for (Object key : keySet) {
            nameValuePairs.add(new BasicNameValuePair((String) key, params.get(key)));
        }

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charEncode));
        HttpResponse httpResponse = httpclient.execute(httpPost);
        HttpEntity httpEntity = httpResponse.getEntity();
        String html = EntityUtils.toString(httpEntity, charEncode);
        return html;
    }

    public static List<RateBean> getRates(String html) throws IOException {
        List<RateBean> rateBeanList = new ArrayList<RateBean>();
        Document doc = Jsoup.parse(html);
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
            row2.createCell(1).setCellValue(rateBeanList.get(row).getRate());
            row2.createCell(2).setCellValue(rateBeanList.get(row+1).getRate());
            row2.createCell(3).setCellValue(rateBeanList.get(row+2).getRate());
        }
        sheet.setColumnWidth(0,15*256);

        FileOutputStream fileOutputStream = new FileOutputStream(path+"\\ExchangeRate.xls");
        hwb.write(fileOutputStream);
        fileOutputStream.close();
    }

}
