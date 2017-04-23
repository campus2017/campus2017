package com.shenyakun.util;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.*;

/**
 * 爬虫工具类
 * @author 
 *
 */
public class CrawerUtil {
	/**
	 * 获取需要访问数据的url列表
	 * 
	 * @param titleKeyList
	 * @param urlList
	 * 
	 * @return
	 */
	public static List<String> getUrlList(String rootUrl, List<String> urlList, List<String> titleKeyList)
			throws Exception {

		Document document = Jsoup.connect(rootUrl).userAgent("Jsoup").timeout(3000).post();
		Elements docitem = document.getElementsByClass("newslist_style");
		// 经测试，有可能出现网络不良，使得访问出现404 的情况，建立长连接
		if (docitem == null || docitem.size() == 0) {
			return getUrlList(rootUrl, urlList, titleKeyList);
		}
		// 2、采集url
		for (String key : titleKeyList) {
			Elements link = docitem.select("a");
			for (Element element : link) {
				String title = element.text();
				if (null != title && title.contains(key)) {
					String absHref = element.attr("abs:href");
					urlList.add(absHref);
				}
			}
		}
		return urlList;
	}

	/**
	 * 获取每个url的信息，以分段数组方式返回，便于调试
	 * 
	 * @param rootUrl
	 * @return
	 * @throws Exception
	 */
	public static String[] getInfo(String rootUrl) throws Exception {
		Document document = Jsoup.connect(rootUrl).userAgent("Jsoup").timeout(3000).post();
		Elements docitem = document.getElementsByTag("p");

		// System.out.println(element.text());
		String curInfo = docitem.get(0).text();
		if (null != curInfo) {
			// 需求中只要求 美元、欧元，港币
			// 文本中显示：1美元对人民币6.8785元，1欧元对人民币7.3025元，1港元对人民币0.88485元
			String[] curInfoArry = curInfo.replace(",", "，").split("，");
			return curInfoArry;
		}
		return null;
	}
}
