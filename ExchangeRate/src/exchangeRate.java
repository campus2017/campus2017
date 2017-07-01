/**
 * Created by Administrator on 2017/02/24.
 */
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.URL;
import jxl.CellView;
import jxl.Range;
import jxl.Workbook;
import jxl.write.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class exchangeRate {

    private static int num=30; //需要输出的条数
    private static int pageSize=20;  //货币政策网每页放的记录条数
    private static String webSite="http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index1.html";

    public static void main(String []args)
    {
        List<Map<String, String>> urlList = requestList(webSite);
        List<Map<String, String>> tempUrlList;
        String siteTemp=webSite.substring(0,webSite.lastIndexOf('.'));
        int repeatNum=num/pageSize+1;
        for (int i = 2; i <= repeatNum; i++) { //方便设置大小
            tempUrlList = requestList(siteTemp+ i + ".html");
            urlList.addAll(tempUrlList);

        }

        List<Map<String, String>> exchangeRateList = requestExchangeRate(urlList);

        if(createExcel(exchangeRateList))
        {
            System.out.println("Success");
        }
        else
        {
            System.out.println("Failed");
        }
    }

    /**
     * function: create excel from list
     * @param exchangeRateList
     * @return true or false  —— echo success or fail
     */
    public static boolean createExcel(List<Map<String, String>> exchangeRateList)
    {
        try{
            //打开文件
            WritableWorkbook workbook = Workbook.createWorkbook(new File("exchangeRate.xls"));
            //生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = workbook.createSheet("Sheet1", 0);
            //在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
            //以及单元格内容为test
            Label label = new Label(0, 0, "Date");
            //将定义好的单元格添加到工作表中
            sheet.addCell(label);
            label = new Label(1, 0, "USD");
            sheet.addCell(label);
            label = new Label(2, 0, "Euro");
            sheet.addCell(label);
            label = new Label(3, 0, "Yen");
            sheet.addCell(label);

            Map<String, String> exchangeRateMap;
            for (int i = 1; i <= exchangeRateList.size(); i++)
            {
                exchangeRateMap = exchangeRateList.get(i - 1);
                label = new jxl.write.Label(0, i, exchangeRateMap.get("Date"));
                sheet.addCell(label);
                label = new jxl.write.Label(1, i, exchangeRateMap.get("USD"));
                sheet.addCell(label);
                label = new jxl.write.Label(2, i, exchangeRateMap.get("Euro"));
                sheet.addCell(label);
                label = new jxl.write.Label(3, i, exchangeRateMap.get("Yen"));
                sheet.addCell(label);
            }
            workbook.write();
            workbook.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * function: parse passageUrlList then return a exchangeRateList
     * @param passageUrlList
     * @return exchangeRateList
     */
    public static List<Map<String, String>> requestExchangeRate(List<Map<String, String>> passageUrlList)
    {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        int numTemp=0;
        for (Map<String, String> tempMap : passageUrlList)
        {
            String passageUrl = tempMap.get("PassageUrl");
            WebClient wc = new WebClient();
            wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
            wc.getOptions().setCssEnabled(false); //禁用css支持
            wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
            wc.getOptions().setTimeout(500000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待  10000
            HtmlPage page = null;
            try {
                page = wc.getPage(passageUrl);
                String pageXml = page.asXml(); //以xml的形式获取响应文本
                String domainUrl = new java.net.URL(passageUrl).getHost();
                /**jsoup解析文档*/
                Document doc = org.jsoup.Jsoup.parse(pageXml, domainUrl);
                String content = doc.select(".content").select("p").get(0).html();

                //Map<String, String> map = new HashMap<String, String>();


                Pattern p = Pattern.compile("美元对人民币(.*?)元");
                Matcher m = p.matcher(content);
                while(m.find()) {
                    tempMap.put("USD", m.group(1));
                }
                p = Pattern.compile("欧元对人民币(.*?)元");
                m = p.matcher(content);
                while(m.find()) {
                    tempMap.put("Euro", m.group(1));
                }
                p = Pattern.compile("日元对人民币(.*?)元");
                m = p.matcher(content);
                while(m.find()) {
                    tempMap.put("Yen", m.group(1));
                }
                list.add(tempMap);
                numTemp++;
                if(numTemp==num) break;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Just for test
        /*for (Map<String, String> temp : list)
        {
            System.out.println(temp.toString());
        }*/
        return list;
    }

    /**
     * function: parse listDomainUrl and return exchangeRate passageUrlList in everyday
     * @param listDomainUrl
     * @return passageUrlList
     */

    public static List<Map<String, String>> requestList(String listDomainUrl)
    {
        WebClient wc = new WebClient();
        wc.getOptions().setJavaScriptEnabled(true); //启用JS解释器，默认为true
        wc.getOptions().setCssEnabled(false); //禁用css支持
        wc.getOptions().setThrowExceptionOnScriptError(false); //js运行错误时，是否抛出异常
        wc.getOptions().setTimeout(10000); //设置连接超时时间 ，这里是10S。如果为0，则无限期等待
        HtmlPage page = null;
        try {
            page = wc.getPage(listDomainUrl);
            String pageXml = page.asXml(); //以xml的形式获取响应文本
            String domainUrl =  new java.net.URL(listDomainUrl).getProtocol() + "://" + new java.net.URL(listDomainUrl).getAuthority();
            /**jsoup解析文档*/
            Document doc = org.jsoup.Jsoup.parse(pageXml, domainUrl);
            Elements items = doc.select("div[opentype=page]").select("td[height=22]");
            Elements titles = items.select("a");
            Elements times = items.select(".hui12");

            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (int i = 0; i < items.size(); i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("Title", titles.get(i).text());
                map.put("PassageUrl", domainUrl + titles.get(i).attr("href"));
                map.put("Date", times.get(i).text());
                list.add(map);
            }
            return list;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
