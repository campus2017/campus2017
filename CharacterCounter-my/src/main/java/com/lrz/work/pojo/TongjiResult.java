package com.lrz.work.pojo;
/**
 * 统计结果
 * @author Administrator
 *
 */
public class TongjiResult {

	//英文字母个数
	private long englishCount;
	//中文汉字个数
	private long chineseCount;
	//数字个数
	private long numberCount;
	//标点符号个数
	private long charCount;
	//error==200 结果正确
	private Character[] top3;
	private long[] value3;
	private int error;
	public long getEnglishCount() {
		return englishCount;
	}
	public TongjiResult setEnglishCount(long englishCount) {
		this.englishCount = englishCount;
		return this;
	}
	public long getChineseCount() {
		return chineseCount;
	}
	public TongjiResult setChineseCount(long chineseCount) {
		this.chineseCount = chineseCount;
		return this;
	}
	public long getNumberCount() {
		return numberCount;
	}
	public TongjiResult setNumberCount(long numberCount) {
		this.numberCount = numberCount;
		return this;
	}
	public long getCharCount() {
		return charCount;
	}
	public TongjiResult setCharCount(long charCount) {
		this.charCount = charCount;
		return this;
	}
	public int getError() {
		return error;
	}
	public TongjiResult setError(int error) {
		this.error = error;
		return this;
	}
	public Character[] getTop3() {
		return top3;
	}
	public TongjiResult setTop3(Character[] top3) {
		this.top3 = top3;
		return this;
	}
	public long[] getValue3() {
		return value3;
	}
	public TongjiResult setValue3(long[] value3) {
		this.value3 = value3;
		return this;
	}
	
	
}
