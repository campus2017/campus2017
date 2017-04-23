package com.shenyakun.main;

import com.shenyakun.util.Crawer2GetInfoUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Crawer2GetInfoUtil.crawerToGetInfo("F:\\银行间外汇市场人民币汇率中间价"+System.currentTimeMillis()+".xls");//将数据导入到
		} catch (Exception e) {
		}
	}

}
