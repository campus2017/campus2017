package com.dj.rate;

/**
 * @author DJ
 * 创建Rate对象
 * 属性成员：date和money
 */

public class Rate {

	private String date;
	private double money;
	
	public Rate() {
		
	}	
	public Rate(String date, double money) {
		super();
		this.date = date;
		this.money = money;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String toString() {
		return "Rate [date=" + date + ", money=" + money + "]";
	}	
}
