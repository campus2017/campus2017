package sei.tool;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class WordsTopCalculator {
	private PriorityQueue<WordCountBean> heap;
	private Map<String,Integer> map ;
	
	public class WordCountBean  {
		String val;
		Integer count;
		public WordCountBean(String val, Integer count){
			this.val=val;
			this.count=count;
		}

		public String getVal() {
			return val;
		}

		public void setVal(String val) {
			this.val = val;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
	}
	public WordsTopCalculator(){
		heap = new PriorityQueue<>(cmp);
		map=new HashMap<String,Integer>();
	}
	
	/**
	 * @param val
	 */
	public void addElement(String val){
		if(map.containsKey(val)){
			map.put(val, map.get(val)+1);
		}else{
			map.put(val, 1);
		}
	}
	
	/**
	 * @Description 得到前n个
	 * @param num
	 * @return
	 */

	public List<WordCountBean> getCurrentTop(int num){
		List<WordCountBean> resList = new ArrayList<>();
		//建堆
		for(Map.Entry<String, Integer> entry:map.entrySet()){
			heap.add(new WordCountBean(entry.getKey(), entry.getValue()));
		}
		for(int i=0;i<num && i< heap.size();i++){
			resList.add(heap.poll());
		}
		return resList;
	}
	
	/**
	 * 比较器
	 */
	private  Comparator<WordCountBean> cmp = new Comparator<WordCountBean>() {
		@Override
		public int compare(WordCountBean o1, WordCountBean o2) {
			if(o1.count>o2.count) return -1;
			if(o1.count<o2.count) return 1;
			return 0;
		}
	};
}
