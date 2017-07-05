package com.qunar.fengxiaodong.model;

public class Statistics {

	private int english;
    private int chinese;
    private int symbol;
    private int number;
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getChinese() {
		return chinese;
	}
	public void setChinese(int chinese) {
		this.chinese = chinese;
	}
	public int getSymbol() {
		return symbol;
	}
	public void setSymbol(int symbol) {
		this.symbol = symbol;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Statistics [english=" + english + ", chinese=" + chinese + ", symbol=" + symbol + ", number=" + number
				+ "]";
	}
    
    

}
