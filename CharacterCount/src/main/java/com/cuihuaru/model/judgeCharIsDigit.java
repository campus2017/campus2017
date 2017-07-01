package com.cuihuaru.model;

/**
 * Created by Administrator on 2017/6/25.
 */
public class judgeCharIsDigit implements  judgeCharacter{
    public boolean judge(char c)
    {
        return Character.isDigit(c);
    }
}
