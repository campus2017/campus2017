package org.qunar.campus.util;

import net.htmlparser.jericho.Attributes;
import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.apache.commons.lang3.StringUtils;
import org.qunar.campus.entity.RateBean;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhanghe on 2017/7/4.
 */
public class HtmlParseTool {
    /**
     * 获取a标签上的href
     * @param htmlText
     * @return 请求集合   http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/3337942/index.html
     * @throws IOException
     */
    public static List<String> htmlText4reqUrl(String htmlText, String httpText) throws IOException {
        Source source = new Source(htmlText);
        Element bodyElement=source.getFirstElement(HTMLElementName.BODY);
        List<Element> allElements = bodyElement.getAllElements("table");
        Element element2 = allElements.get(23);
        List<Element> elements = element2.getAllElements("a");
        List<String> listUrl = new ArrayList<String>();
        for (Element element : elements) {
            Attributes attrs = element.getAttributes();
            String value = attrs.getValue("href");
            if(!StringUtils.isEmpty(value)){
                listUrl.add(httpText+value);
            }
        }
        return listUrl;
    }


    /**
     * 根据请求获取二级页面的文本内容集合
     * @param listReq
     * @return
     */
    public static List<RateBean> reqUtl4Content(List<String> listReq) throws ParseException {
        List<String> listContent = new ArrayList<>();
        List<RateBean> rateBeanlist = new ArrayList<>();
        double usa = 0;
        double eur = 0;
        double hkd = 0;
        for (String string : listReq) {
            String respText = HttpClientTool.doPost(string, null);
            Source source = new Source(respText);
            Element bodyElement=source.getFirstElement(HTMLElementName.BODY);

            List<Element> allElements = bodyElement.getAllElements("p");

            for (Element element : allElements) {
                String str = element.getContent().toString();


                if(str.startsWith("中国人民银行")){
                    int startIndex = str.indexOf("：");
                    String time = str.split("，")[1];


                    int timeEndIndex = time.indexOf("日");

                    time = time.substring(0,timeEndIndex+1);

                    if (!isFromNowThirtyDays(time)){
                        break;
                    }
                    String rateStr = str.substring(startIndex+1);
                    String[] tempArr = rateStr.split("，");

                    String usatemp = tempArr[0];
                    usa =  usa
                            + Double.parseDouble(usatemp.substring(7,
                            usatemp.length() - 1));
                    String eurtemp = tempArr[1];
                    eur =  eur
                            + Double.parseDouble(eurtemp.substring(7,
                            eurtemp.length() - 1));
                    String hkdtemp = tempArr[3];
                    hkd =  hkd
                            + Double.parseDouble(hkdtemp.substring(7,
                            hkdtemp.length() - 1));
                    listContent.add(str);
                }
            }
        }
        RateBean usaRate = new RateBean("美元", 100/(usa/listContent.size()));
        RateBean eurRate = new RateBean("欧元", 100/(eur/listContent.size()));
        RateBean hkdRate = new RateBean("港元", 100/(hkd/listContent.size()));
        rateBeanlist.add(usaRate);
        rateBeanlist.add(eurRate);
        rateBeanlist.add(hkdRate);
        return rateBeanlist;
    }

    /**
     * 判断汇率日期是否是当前时间的最近三十天
     * @param rateDate
     * @return
     * @throws ParseException
     */
    private static Boolean isFromNowThirtyDays(String rateDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = format.parse(rateDate.substring(0,rateDate.indexOf("日")+1));

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -30);

        if (date.after(calendar.getTime())){
            return true;
        }
        return false;
    }

}
