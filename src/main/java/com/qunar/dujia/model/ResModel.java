package com.qunar.dujia.model;

import java.util.List;

/**
 * Created by tianyiren on 16-12-22.
 */
public class ResModel {
    private TongJi tongJi;
    private List<MostNum> lisMostNum;

    public ResModel(TongJi tongJi,List<MostNum>lisMostNum){
        this.tongJi = tongJi;
        this.lisMostNum = lisMostNum;
    }

    public TongJi getTongJi() {
        return tongJi;
    }

    public void setTongJi(TongJi tongJi) {
        this.tongJi = tongJi;
    }

    public List<MostNum> getLisMostNum() {
        return lisMostNum;
    }

    public void setLisMostNum(List<MostNum> lisMostNum) {
        this.lisMostNum = lisMostNum;
    }
}
