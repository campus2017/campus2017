package com.qunar.clp.pojo;

import java.util.ArrayList;

/**
 * Created by nipingchen on 16-12-14.
 */
public class CountDto {
    public int enCounts;//英文字符数
    public int chCounts;//中文字符数
    public int numCounts;//数字的个数
    public int symbolCounts;//符号的个数
    public ArrayList<CharacterAndCount> maxNumThreeCharacter=new ArrayList<>();//出现次数最多的三个字符以及个数
    public CountDto(){}
    public CountDto(int enCounts, int chCounts, int numCounts, int symbolCounts) {
        this.enCounts = enCounts;
        this.chCounts = chCounts;
        this.numCounts = numCounts;
        this.symbolCounts = symbolCounts;
    }

    public int getEnCounts() {
        return enCounts;
    }

    public void setEnCounts(int enCounts) {
        this.enCounts = enCounts;
    }

    public int getChCounts() {
        return chCounts;
    }

    public void setChCounts(int chCounts) {
        this.chCounts = chCounts;
    }

    public int getNumCounts() {
        return numCounts;
    }

    public void setNumCounts(int numCounts) {
        this.numCounts = numCounts;
    }

    public int getSymbolCounts() {
        return symbolCounts;
    }

    public void setSymbolCounts(int symbolCounts) {
        this.symbolCounts = symbolCounts;
    }

    public ArrayList<CharacterAndCount> getMaxNumThreeCharacter() {
        return maxNumThreeCharacter;
    }

    public void setMaxNumThreeCharacter(ArrayList<CharacterAndCount> maxNumThreeCharacter) {
        this.maxNumThreeCharacter = maxNumThreeCharacter;
    }
}
