package com.qunar.vo;

/**
 * Created by DELL on 2017/5/4.
 * Description:该类封装了import的类名和出现的次数
 */
public class ImportClassVo {
	private String className; //类名
	private int num; //出现的次数

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
}
