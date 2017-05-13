package com.qunar.vo;

/**
 * Created by DELL on 2017/5/3.
 * Description:该类集成了每个文件以及它所对应的各种类型的代码行数
 */
public class FileCodeLineVo {
	private String filename; //文件名
	private int validcodeline; //有效行数
	private int blankline; //空行数
	private int annotationline; //注释行数
	private int totalline; //代码总行数

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getValidcodeline() {
		return validcodeline;
	}

	public void setValidcodeline(int validcodeline) {
		this.validcodeline = validcodeline;
	}

	public int getBlankline() {
		return blankline;
	}

	public void setBlankline(int blankline) {
		this.blankline = blankline;
	}

	public int getAnnotationline() {
		return annotationline;
	}

	public void setAnnotationline(int annotationline) {
		this.annotationline = annotationline;
	}

	public int getTotalline() {
		return totalline;
	}

	public void setTotalline(int totalline) {
		this.totalline = totalline;
	}
}
