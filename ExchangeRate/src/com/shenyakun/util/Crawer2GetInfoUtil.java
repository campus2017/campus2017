package com.shenyakun.util;

import com.shenyakun.pojo.*;

import java.util.*;

/**
 * 通过Jsoup爬虫获取信息
 * 
 * @author ShenYankun
 *
 */
public class Crawer2GetInfoUtil {

	/* 本页 */
	private static final String html = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html";
	/* 本页 对应的下一页 */
	private static final String nextHtml = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html";

	/**
	 * 主方法，插入数据到excel
	 * 
	 * @param excelLocal：excel的位置
	 */
	public static void crawerToGetInfo(String excelLocal) {
		List<String> titleKeyList = new ArrayList<String>();// title关键词的list（没必要全称）
		List<Info> infoList = new ArrayList<Info>();// 封装对象list
		Date curDate = new Date();
		// 1、拼接规则字符串
		for (int i = 0; i < 30; i++) {
			String[] myDate = DateUtils.getNextDate(curDate, -i, "yyyy-M-d").split("-");
			titleKeyList.add(myDate[0] + "年" + myDate[1] + "月" + myDate[2] + "日");
		}
		try {
			List<String> urlList = CrawerUtil.getUrlList(nextHtml,
					CrawerUtil.getUrlList(html, new ArrayList<String>(), titleKeyList), titleKeyList);
			for (String s : urlList) {
				String[] curInfoArry = CrawerUtil.getInfo(s);
				Info info = new Info();
				info.setDate(curInfoArry[1].substring(0, curInfoArry[1].indexOf("银")));
				String curStr = curInfoArry[1].replace(":", "：").split("：")[1];
				info.setMy(curStr.substring(curStr.indexOf("币") + 1, curStr.length() - 2));
				info.setOy(curInfoArry[2].substring(curInfoArry[2].indexOf("币") + 1, curInfoArry[2].length() - 2));
				info.setGy(curInfoArry[4].substring(curInfoArry[4].indexOf("币") + 1, curInfoArry[4].length() - 2));
				infoList.add(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ExcelUtil.insertData2Excel(infoList, excelLocal);
			System.out.println("==================写入数据成功....=========================");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==================写入数据失败....=========================");
		}

	}

}
