package com.shenyakun.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateUtils  {

	/**
	 * 得到N天后的日期
	 * 
	 * @param num
	 * @return
	 */
	public static String getNextDate(Date date, int num, String pattern) {
		long time = System.currentTimeMillis() + (1000L * 60 * 60 * 24 * num);
		if (time > 0) {
			date.setTime(time);
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	/**
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {
		System.out.println(getNextDate(new Date(), 1, "yyyy-M-d"));
	}
}
