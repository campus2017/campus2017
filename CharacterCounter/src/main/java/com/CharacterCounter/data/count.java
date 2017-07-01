package com.CharacterCounter.data;

/**
 * Created by apple on 17/6/24.
 */
public class count {



    public String word;




    public  int counts;
    public count(String w,int c)
    {
        word=w;
        counts=c;
    }
    public  String toString()
    {
        return word+" "+counts;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public int getCounts() {
        return counts;
    }
    public void setCounts(int counts) {
        this.counts = counts;
    }
}
