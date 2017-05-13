package lnu.controller;

import lnu.pojo.Character2;
import lnu.pojo.Counter;

import java.util.ArrayList;

/**
 * Created by DELL on 2017/4/19.
 * Description:字符类的扩展查询。
 * 1、包装contrller的查询需求
 */
public class CharacterExtendCompute {

	private Counter counter; //统计字符
	private ArrayList<Character2> list; //求出现次数最多的topk个字符

	public Counter getCounter() {
		return counter;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public ArrayList<Character2> getList() {
		return list;
	}

	public void setList(ArrayList<Character2> list) {
		this.list = list;
	}

}
