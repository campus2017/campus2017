package com.qunar.amao.pojo;

import java.util.Date;

/**
 * Created by FGT on 2017/5/10.
 */
public class ReferenceRate {
    private Date referenceDate;
    private float usDollar;
    private float euro;
    private float hkDollar;

    public ReferenceRate(Date referenceDate,float usDollar,float euro,float hkDollar){
        this.referenceDate=referenceDate;
        this.usDollar=usDollar;
        this.euro=euro;
        this.hkDollar=hkDollar;
    }

    public Date getReferenceDate() {
        return referenceDate;
    }

    public void setReferenceDate(Date referenceDate) {
        this.referenceDate = referenceDate;
    }

    public float getUsDollar() {
        return usDollar;
    }

    public void setUsDollar(float usDollar) {
        this.usDollar = usDollar;
    }

    public float getEuro() {
        return euro;
    }

    public void setEuro(float euro) {
        this.euro = euro;
    }

    public float getHkDollar() {
        return hkDollar;
    }

    public void setHkDollar(float hkDollar) {
        this.hkDollar = hkDollar;
    }
}
