package com.qunar.flight.zechuan_guo.charactercount.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zechuan.guo on 2016/12/12.
 */
public class PatternTest {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher m = pattern.matcher("123");
        String regex = "[a-zA-Z]";
        String input = " yt,,,rytr,,, ";
        int count = input.split(regex).length-1;
        System.out.println(count);

    }
}
