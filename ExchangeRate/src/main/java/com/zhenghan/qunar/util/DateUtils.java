package com.zhenghan.qunar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/11/14.
 * 普通的日期转换工具类
 * 直接用SimpleDateFormat
 */
public class DateUtils {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public  static String dateToString(Date date){
        return format.format(date);
    }
    public static  Date strToDate(String dateStr) throws ParseException {
        return format.parse(dateStr);
    }
    public static void setFormat(String format){
        DateUtils.format =  new SimpleDateFormat(format);
    }
}
