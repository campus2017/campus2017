package com.springmvc.service;

import com.springmvc.CountBean.EveryCountBean;
import com.springmvc.dao.CountDao;

/**
 * Created by gcy0904 on 2017/1/24.
 */
public class CountService {
    public EveryCountBean getEveryCount(String string) {
        int ChinaChar = 0;
        int EnglishChar = 0;
        int Num = 0;
        int punctuation = 0;
        char[] chars = string.toCharArray();
        for (char ch : chars) {
            String s = String.valueOf(ch);
            if (CountDao.isNumeric(s)) {
                Num++;
            }
            if (CountDao.isChinaChar(s)) {
                ChinaChar++;
            }
            if (CountDao.isEnglishChar(s)) {
                EnglishChar++;
            }
            punctuation = chars.length - ChinaChar - EnglishChar - Num;
        }
        EveryCountBean everyCountBean = new EveryCountBean(String.valueOf(ChinaChar), String.valueOf(EnglishChar), String.valueOf(Num), String.valueOf(punctuation));
        return everyCountBean;
    }

}
