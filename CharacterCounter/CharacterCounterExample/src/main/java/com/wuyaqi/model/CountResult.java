package com.wuyaqi.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuyaqi on 17-4-29.
 */
public class CountResult {
    private int engCount = 0;
    private int numCount = 0;
    private int chnCount = 0;
    private int punCount = 0;

    private List<Map.Entry<Character,Integer>> resultTop3 = new ArrayList<Map.Entry<Character, Integer>>();

    public CountResult() {
    }

    public CountResult(int engCount, int numCount, int chnCount, int punCount, File file, List<Map.Entry<Character,Integer>> resultTop3) {
        this.engCount = engCount;
        this.numCount = numCount;
        this.chnCount = chnCount;
        this.punCount = punCount;

        this.resultTop3 = resultTop3;
    }



    public void setEngCount(int engCount) {
        this.engCount = engCount;
    }

    public void setNumCount(int numCount) {
        this.numCount = numCount;
    }

    public void setChnCount(int chnCount) {
        this.chnCount = chnCount;
    }

    public void setPunCount(int punCount) {
        this.punCount = punCount;
    }

    public void setResultTop3(List<Map.Entry<Character,Integer>> resultTop3) {
        this.resultTop3 = resultTop3;
    }



    public int getEngCount() {
        return engCount;
    }

    public int getNumCount() {
        return numCount;
    }

    public int getChnCount() {
        return chnCount;
    }

    public int getPunCount() {
        return punCount;
    }

    public List<Map.Entry<Character,Integer>> getResultTop3() {
        return resultTop3;
    }

    @Override
    public String toString() {
        return "CountResult{" +
                "engCount=" + engCount +
                ", numCount=" + numCount +
                ", chnCount=" + chnCount +
                ", punCount=" + punCount +
                ", resultTop3=" + resultTop3 +
                '}';
    }
}
