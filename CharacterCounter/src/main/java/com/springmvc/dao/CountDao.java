package com.springmvc.dao;

import com.springmvc.utils.isRegExUtils;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class CountDao {
    //判断数字
    public static boolean isNumeric(String str) {
        String regEx = "[0-9]";
        return isRegExUtils.isRegEx(str, regEx);
    }
    //判断是否为汉字
    public static boolean isChinaChar(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        return isRegExUtils.isRegEx(str,regEx);
    }
    //判断是否是英文
    public static boolean isEnglishChar(String str) {
        String regEx = "[a-zA-Z]";
        return isRegExUtils.isRegEx(str,regEx);
    }

}
