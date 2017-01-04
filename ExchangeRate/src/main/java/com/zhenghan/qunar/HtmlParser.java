package com.zhenghan.qunar;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.base.Optional;
import com.zhenghan.qunar.bean.RateBean;
import com.zhenghan.qunar.util.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.*;


/**
 * Created by Administrator on 2016/11/14.
 */
public enum HtmlParser {
    INSTANCE;
    private Logger logger = LoggerFactory.getLogger(HtmlParser.class);

    /**
     * @param source
     * @return
     * @description 得到某一页的全部汇率超链接
     */
    public List<RateBean> pickUpDateLink(String source) {
        Document doc = Jsoup.parse(source);
        Elements elements = parserDocByDateLink(doc);
        List<RateBean> rateList = Lists.newLinkedList();
        for (Element table : elements) {
            String href = table.select("a").get(0).attr("href");
            String dateStr = table.select("span").text();
            try {
                RateBean bean = new RateBean(DateUtils.strToDate(dateStr), href, null);
                rateList.add(bean);
            } catch (ParseException e) {
                logger.debug(e.getMessage());
            }
        }
        return rateList;
    }

    /**
     * @param doc
     * @return
     */
    private Elements parserDocByDateLink(Document doc) {
        Element bodyElement = doc.body();
        Elements elements = bodyElement.select("div > table > tbody div#r_con > div.portlet");
        elements = elements.get(0).select("div > div > table");
        Elements elementsTables = elements.get(0).children().select("table");
        return elementsTables;
    }

    /**
     * 得到下一页的超链接
     *
     * @param source
     * @return 貌似只需要在index.html的url中加入
     * index2.html就是下一页所以这个方法也可以重写
     */
    public String nextPage(String source) {
        Document doc = Jsoup.parse(source);
        Element bodyElement = doc.body();
        Elements elements = bodyElement.select("div > table > tbody div#r_con > div.portlet");
        elements = elements.get(0).select("div > div > table");
        Element hrefA = elements.get(1).select("a").get(2);
        return hrefA.attr("tagname");
    }

    /**
     * 得到汇率超链接中的文章并分析注入到RateBean中
     */
    public RateBean parserArticle(String source, RateBean rateBean) {
        Document doc = Jsoup.parse(source);
        String article = doc.body().getElementById("zoom").select("p").get(0).text();
        return doParserArticle(article, rateBean);
    }

    /**
     * @param article
     * @param rateBean
     * @return 设置好sortedMap的rateBean
     */
    protected RateBean doParserArticle(String article, RateBean rateBean) {
        logger.debug(article);
        String articleKeys = Optional.fromNullable(article.split("：")[1]).or("");
        List<String> lists = Splitter.on(CharMatcher.anyOf("。，,"))
                .omitEmptyStrings()
                .trimResults()
                .splitToList(articleKeys);
        SortedMap<String, BigDecimal> map = new TreeMap<String, BigDecimal>();
        long start = System.currentTimeMillis();
        for (String mess : lists) {
            Map.Entry<String, BigDecimal> entry = parserMoney(mess.trim());
            if (entry != null)
                map.put(entry.getKey(), entry.getValue());
        }
        long end = System.currentTimeMillis();
        logger.debug("解析耗时:" + (start - end) + "ms");
        rateBean.setMap(map);
        return rateBean;
    }

    /**
     * @param mess
     * @return 一个国家, 汇率的Entry
     */
    private Map.Entry<String, BigDecimal> parserMoney(String mess) {
        MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
        List<String> moneys = Splitter.on("对").trimResults().omitEmptyStrings().splitToList(mess.replace("人民币", ""));
        TreeMap<String, BigDecimal> map = Maps.newTreeMap();
        if (moneys.size() == 2) {
            String rmbStr = moneys.get(1);
            String foreignStr = moneys.get(0);
            if (CharMatcher.anyOf("元.").or(CharMatcher.JAVA_DIGIT).matchesAllOf(moneys.get(0))) {
                rmbStr = moneys.get(0);
                foreignStr = moneys.get(1);
            }
            BigDecimal rmb = new BigDecimal(rmbStr.substring(0, rmbStr.length() - 1).trim(), mc);
            CharMatcher digiest = CharMatcher.is('.').or(CharMatcher.JAVA_DIGIT);
            BigDecimal foreign = new BigDecimal(digiest.negate().removeFrom(foreignStr), mc);
            String foreignCity = digiest.removeFrom(foreignStr);
            logger.debug(foreignCity + ":" + foreign.divide(rmb, mc));
            return new HashMap.SimpleEntry<String, BigDecimal>(foreignCity, foreign.divide(rmb, mc));
        }
        return null;
    }
}
