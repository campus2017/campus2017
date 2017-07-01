package com.cuihuaru.model;

/**
 * Created by Administrator on 2017/6/25.
 */
public class judgeCharIsChinese implements judgeCharacter {
  public  boolean judge(char c)
    {
        Character.UnicodeScript sc = Character.UnicodeScript.of(c);
        if (sc == Character.UnicodeScript.HAN) {
            return true;
        }
        return false;
    }

}
