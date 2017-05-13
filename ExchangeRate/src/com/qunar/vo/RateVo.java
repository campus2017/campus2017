package com.qunar.vo;

/**
 * Created by DELL on 2017/5/1.
 * Description:汇率实体：日期，美元，欧元，港币
 */
public class RateVo {
	private  String date;
	private  String dollar;
	private  String euro;
	private  String hkdollar;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDollar() {
		return dollar;
	}

	public void setDollar(String dollar) {
		this.dollar = dollar;
	}

	public String getEuro() {
		return euro;
	}

	public void setEuro(String euro) {
		this.euro = euro;
	}

	public String getHkdollar() {
		return hkdollar;
	}

	public void setHkdollar(String hkdollar) {
		this.hkdollar = hkdollar;
	}
}
