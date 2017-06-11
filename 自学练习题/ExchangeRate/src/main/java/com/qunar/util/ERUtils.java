package com.qunar.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 工具类
 * Created by bmi-xiaoyu on 2017/6/9.
 */
public class ERUtils {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前日期，并格式化
     * @return 格式化的日期
     */
    public static String getToday() {
        Date today = new Date();
        String todayString = formatter.format(today);
        return todayString;
    }

    /**
     * 获取30天以前的日期，并格式化
     * @return 格式化的日期
     */
    public static String getDayBefore30() {
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(cal.DATE, -30);
        String dayBefore30 = formatter.format(cal.getTime());
        return dayBefore30;
    }
}
