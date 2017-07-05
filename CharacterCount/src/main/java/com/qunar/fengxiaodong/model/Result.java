package com.qunar.fengxiaodong.model;

import java.util.List;

public class Result {

	private Statistics st;
    private List<MostNum> lisMostNum;
	public Statistics getSt() {
		return st;
	}
	public void setSt(Statistics st) {
		this.st = st;
	}
	public List<MostNum> getLisMostNum() {
		return lisMostNum;
	}
	public void setLisMostNum(List<MostNum> lisMostNum) {
		this.lisMostNum = lisMostNum;
	}
	@Override
	public String toString() {
		List<MostNum> list = this.lisMostNum;
		StringBuilder sb =new StringBuilder();
		int i=0;
		for(MostNum mn : list)
		{
			sb.append("mostnum").append(": ").append(i++);
			sb.append(mn.toString());
			sb.append("\n");
		}
		System.out.print(sb.toString());
		System.out.println(this.st.toString());
		return st.toString();
	}
    
    
}
