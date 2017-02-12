package com.springmvc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class isRegExUtils {
    public static boolean isRegEx(String str,String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher isNum = pattern.matcher(str);
        return isNum.matches() ? true : false;
    }
}
