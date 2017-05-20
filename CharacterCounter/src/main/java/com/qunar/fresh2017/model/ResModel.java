package com.qunar.fresh2017.model;

import java.util.List;

/**
 * Created by 曹 on 2017.5.17.
 */
public class ResModel {
    private Statics statics;
    private List<MostNum> lisMostNum;

    public ResModel(Statics statics, List<MostNum>lisMostNum){
        this.statics = statics;
        this.lisMostNum = lisMostNum;
    }

    public Statics getStatics() {
        return statics;
    }

    public void setStatics(Statics statics) {
        this.statics = statics;
    }

    public List<MostNum> getLisMostNum() {
        return lisMostNum;
    }

    public void setLisMostNum(List<MostNum> lisMostNum) {
        this.lisMostNum = lisMostNum;
    }
}
