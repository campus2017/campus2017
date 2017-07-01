package com.cuihuaru.model;

import  java.lang.String;
/**
 * Created by Administrator on 2017/6/25.
 */
public class result {
    private int[]  number;
    private int[]  charNumber;
    private char[]  mostTimesChar;
    public void setSize(int size)
    {
        charNumber =new int[size];
        mostTimesChar=new char[size];
    }
    private String input;
   public  void setInput(String s)
    {
        this.input=s;
    }
    public void setNumber(int[] number)
    {
        this.number=number;
    }
   public void setCharNumber(int[] i)
   {
       charNumber=i;
   }
   public int[] getCharNumber()
   {
       return  charNumber;
   }
   public  void setMostTimesChar(char[] c)
   {
       mostTimesChar=c;
   }
   public char[] getMostTimesChar()
   {
       return mostTimesChar;
   }
    public int[] getNumber()
    {
        return number;
    }
    public String getInput(){return input;}

}
