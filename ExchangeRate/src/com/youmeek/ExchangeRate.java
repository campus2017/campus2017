package com.youmeek;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*** Created by thinkpad on 2017/7/4.
 */
public class ExchangeRate {
    //消除html标签
    private static String delHTMLTag(String htmlStr){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式
        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(htmlStr);
        htmlStr=m_script.replaceAll(""); //过滤script标签
        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(htmlStr);
        htmlStr=m_style.replaceAll(""); //过滤style标签
        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(htmlStr);
        htmlStr=m_html.replaceAll(""); //过滤html标签
        return htmlStr.trim(); //返回文本字符串
    }
    //消除回车符、 换行符、空格等字符串
    private static String replaceSpecialStr(String str) {
        String repl = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            repl = m.replaceAll("");
        }
        return repl;
    }

        // 获取中国人民银行汇率中间价
public static void getThirtyAvg(String[] addstr) throws IOException {
        List<String> list = new ArrayList<String>();
        Map<String,String> urlMap=new HashMap<String,String>();
        Document document =null;//文件首先为空
        Elements elements=null;
        int a_size=0;
        List<Elements> elements_list=new ArrayList<Elements>();
        for(int i=0;i<addstr.length;i++) {
            document= Jsoup.parse(addstr[i]);
            elements = document.select("a");
            elements_list.add(elements);
            a_size+=elements.size();
        }
        String [] dates=new String[a_size];
        String[] dolar=new String[a_size];
        String[] euro=new String[a_size];
        String[] hongkong=new String[a_size];
        int j=0;
        Element element=null;
        Elements elements1=null;
        String ahref=null;
        String pageContent=null;
        String date=null;
        String meiyuan=null;
        String meiyuan_=null;
        String ouyuan=null;
        String ouyuan_=null;
        String gangyuan=null;
        String gangyuan_=null;
        for(int i=0;i<elements_list.size();i++){
            elements1 =elements_list.get(i);
            for(int k=0;k<elements1.size();k++){
                element=elements1.get(k);
                ahref = element.attr("href");
                if (ahref.length()>4&&"html".equals(ahref.substring(ahref.length() - 4, ahref.length()))) {
                    if (urlMap.containsKey(ahref)) {
                    } else {
                        urlMap.put(ahref, ahref);
                        pageContent = replaceSpecialStr(delHTMLTag(getPageContent(element.attr("href"), "post", 100500)));
                        if (null != pageContent) {
                            //时间
                            date = pageContent.substring(pageContent.indexOf("中国人民银行授权中国外汇交易中心公布,") + 1, 9);
                            //美元汇率
                            meiyuan = pageContent.substring(pageContent.indexOf("1美元对人民币"), pageContent.indexOf("1美元对人民币") + 16);
                            meiyuan_ = meiyuan.substring(meiyuan.indexOf("币") + 1, meiyuan.lastIndexOf("元"));
                            //欧元汇率
                            ouyuan = pageContent.substring(pageContent.indexOf("1欧元对人民币"), pageContent.indexOf("1欧元对人民币") + 16);
                            ouyuan_ = ouyuan.substring(ouyuan.indexOf("币") + 1, ouyuan.lastIndexOf("元"));
                            //港元汇率
                            gangyuan = pageContent.substring(pageContent.indexOf("1港元对人民币"), pageContent.indexOf("1港元对人民币") + 16);
                            gangyuan_ = gangyuan.substring(gangyuan.indexOf("币") + 1, gangyuan.lastIndexOf("元"));
                            dates[j] = date;
                            dolar[j] = meiyuan_;
                            euro[j] = ouyuan_;
                            hongkong[j] = gangyuan_;
                            j++;
                            date=null;
                            meiyuan=null;
                            meiyuan_=null;
                            ouyuan=null;
                            ouyuan_=null;
                            gangyuan=null;
                            gangyuan_=null;
                            ahref=null;
                            element=null;
                        }

                    }
                }
            }

        }
        String[] sheetNames={"汇率"};
        String sheetName="汇率";
        String[][]headNames=new String[4][1];
        headNames[0][0]="日期";
        headNames[1][0]="美元";
        headNames[2][0]="欧元";
        headNames[3][0]="港币";
        String datas[][]=new String[4][a_size];
        datas[0]=dates;
        datas[1]=dolar;
        datas[2]=euro;
        datas[3]=hongkong;
        ExcelUtil.add_StringArray("E://中国人民银行人民币汇率中间价.xls", sheetNames, sheetName, headNames, datas);
    }
    /**
     * 这种方法是JAVA自带的URL来抓取网站内容
     * @param strUrl 网页地址
     * @param strPostRequest 请求
     * @param maxLength 最大长度
     * @return
     */
    private static String getPageContent(String strUrl, String strPostRequest, int maxLength) {
        // 读取结果网页
        StringBuffer buffer = new StringBuffer();
        System.setProperty("sun.net.client.defaultConnectTimeout", "5000");
        System.setProperty("sun.net.client.defaultReadTimeout", "5000");
        try {
            URL newUrl = new URL(strUrl);
            HttpURLConnection hConnect = (HttpURLConnection) newUrl
                    .openConnection();
            // POST方式的额外数据
            if (strPostRequest.length() > 0) {
                hConnect.setDoOutput(true);
                OutputStreamWriter out = new OutputStreamWriter(hConnect
                        .getOutputStream());
                out.write(strPostRequest);
                out.flush();
                out.close();
            }
            // 读取内容
            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    hConnect.getInputStream()));
            int ch;
            for (int length = 0; (ch = rd.read()) > -1
                    && (maxLength <= 0 || length < maxLength); length++)
                buffer.append((char) ch);
            String s = buffer.toString();
            s.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll("<[^>]*>", "");
            rd.close();
            hConnect.disconnect();
            return buffer.toString().trim();
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] stringHtml(){
        int page_size=5;
        String[]url=new String[page_size];
        String[] contentPages=new String[page_size];
        for(int i=0;i<url.length;i++){
            url[i]= "http://wzdig.pbc.gov.cn:8080/dig/ui/search.action?ty=&w=false&f=&dr=true&p="+String.valueOf(i)+"&sr=score+desc%2Cdatetime+desc&rp=&advtime=&advrange=&fq=&ext=&q=%E4%BA%BA%E6%B0%91%E5%B8%81%E6%B1%87%E7%8E%87%E4%B8%AD%E9%97%B4%E4%BB%B7+%E6%AC%A7%E5%85%83++%E7%BE%8E%E5%85%83+++%E6%B8%AF%E5%85%83";
        }
        for(int i=0;i<url.length;i++){
            contentPages[i]= getPageContent(url[i], "post", 100500);
        }
        return contentPages;
    }

    public static void main(String[] args) throws IOException {
        getThirtyAvg(stringHtml());
    }
}