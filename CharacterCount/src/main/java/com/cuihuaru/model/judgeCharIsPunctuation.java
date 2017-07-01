package com.cuihuaru.model;

/**
 * Created by Administrator on 2017/6/25.
 */
public class judgeCharIsPunctuation implements judgeCharacter {
    public  boolean judge(char c)
    {
        judgeCharIsChinese j=new judgeCharIsChinese();
        if(j.judge(c)||Character.isDigit(c)||Character.isLetter(c))
            return false;
        else
            return true;
    }
}
