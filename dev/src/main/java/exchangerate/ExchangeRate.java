package exchangerate;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExchangeRate {
    public static List<String> results = new ArrayList<String>();
    public static final String REGEX = ".*href=\"([\\S]*)\".*<span class=\"hui12\">([\\S]*)</span></td>";
    public static final String RMBREGEX = "<p>.*1美元对人民币([\\S]*?)元.*1欧元对人民币([\\S]*?)元.*1港元对人民币([\\S]*?)元.*</p>";
    public static Map<String, String> urlMaps = new HashMap<String, String>();
    public static List<RMBInfo> rbmInfoLists = new ArrayList<RMBInfo>();
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("中国人民银行公布的人民币汇率中间价正在获取中，请耐心稍等(受网速影响,5-60s)....");
        //爬取两页数据，一页是20条。
        getPageContent("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html");
        getPageContent("http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html");
        for (Map.Entry<String, String> entry : urlMaps.entrySet()) {
            getRMBRate(entry.getKey(), entry.getValue());
        }
        //生成EXCEL
        System.out.println("生成EXCEL表中，请稍等....");
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("中国人民银行公布的人民币汇率中间价");
        sheet.setDefaultColumnWidth(20);
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell dateCell = titleRow.createCell(0);
        dateCell.setCellValue("发布日期");
        HSSFCell dollarCell = titleRow.createCell(1);
        dollarCell.setCellValue("1人民币对美元汇率");
        HSSFCell euroCell = titleRow.createCell(2);
        euroCell.setCellValue("1人民币对欧元汇率");
        HSSFCell hkDollarCell = titleRow.createCell(3);
        hkDollarCell.setCellValue("1人民币对港元汇率");
        Collections.sort(rbmInfoLists, new Comparator<RMBInfo>() {
            public int compare(RMBInfo o1, RMBInfo o2) {
                return (int) (o2.getTime().getTime() - o1.getTime().getTime());
            }
        });
        for (int i = 0; i < rbmInfoLists.size(); i++) {
            RMBInfo rmbInfo = rbmInfoLists.get(i);
            HSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(dateFormat.format(rmbInfo.getTime()));
            row.createCell(1).setCellValue(rmbInfo.getDollarRate());
            row.createCell(2).setCellValue(rmbInfo.getEuroRate());
            row.createCell(3).setCellValue(rmbInfo.getHkDollarRate());
        }
        try {
            FileOutputStream outputStream = new FileOutputStream("E:/rmb.xls");
            hssfWorkbook.write(outputStream);
            hssfWorkbook.close();
            System.out.println("生成EXCEL表成功，存放在E:/rmb.xls");
            System.out.println("注意：30天内若有中国人民银行未公布汇率的日期，该数据不会生成在EXCEL中！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void getPageContent(String pageUrl) {
        try {

            URL url = new URL(pageUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");//这里用POST才能正确获取内容，GET方式失败。
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                Pattern pattern = Pattern.compile(REGEX);
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    urlMaps.put(matcher.group(2), "http://www.pbc.gov.cn" + matcher.group(1));
                }
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void getRMBRate(String date, String url) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date rmbDate = simpleDateFormat.parse(date);
            Date now = new Date();
            long diff = now.getTime() - rmbDate.getTime();
            long days = diff / (1000 * 60 * 60 * 24);
            if (days <= 30) {
                URL RMBPageUrl = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) RMBPageUrl.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod("POST");
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    line = line.trim();
                    Pattern pattern = Pattern.compile(RMBREGEX);
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        RMBInfo rmbInfo = new RMBInfo();
                        rmbInfo.setTime(rmbDate);
                        rmbInfo.setDollarRate(matcher.group(1));
                        rmbInfo.setEuroRate(matcher.group(2));
                        rmbInfo.setHkDollarRate(matcher.group(3));
                        rbmInfoLists.add(rmbInfo);
                        return;
                    }
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
