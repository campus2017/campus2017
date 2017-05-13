package com.qunar.util;

import java.util.Calendar;

/**
 * Created by DELL on 2017/5/1.
 * Description:日期类
 * 项目总功能是实现：从今天开始过去30天内，中国人民银行公布的人民币汇率中间价
 * 该类功能就是获得网站中要输入的今天的日期和30天前的日期
 */
public class DateUtil {
	static Calendar ca = Calendar.getInstance();
	static int year = ca.get(Calendar.YEAR);
	static int month = ca.get(Calendar.MONTH) + 1;
	static int day = ca.get(Calendar.DATE);

	/**
	 * 返回当前日期
	 */
	public static String getCurrentDate() {
		if(month < 10 && day < 10){
			return  year + "-0" + month + "-0" + day;
		}else if(month < 10){
			return  year + "-0" + month + "-" + day;
		}else if (day < 10){
			return  year + "-" + month + "-0" +day;
		}else {
			return  year + "-" + month + "-" + day;
		}
	}

	public static String getMonthAgoDate() {
		if(month < 10 && day < 10){
			return  year + "-0" + (month-1) + "-0" + day;
		}else if(month < 10){
			return  year + "-0" + (month-1) + "-" + day;
		}else if (day < 10){
			return  year + "-" + (month-1) + "-0" +day;
		}else {
			return  year + "-" + (month-1) + "-" + day;
		}
	}
}
