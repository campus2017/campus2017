package com.qunar.util;


import com.qunar.vo.ImportClassVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by DELL on 2017/4/19.
 * Description:求出出现次数最多的k个字符
 */
public class ImportClassTopK {

	ImportClassVo[] createHeap(ImportClassVo a[], int k) {
		ImportClassVo[] result = new ImportClassVo[k];
		for (int i = 0; i < k; i++) {
			result[i] = a[i];
		}
		for (int i = 1; i < k; i++) {
			int child = i;
			int parent = (i - 1) / 2;
			ImportClassVo temp = a[i];
			while (parent >= 0 &&child!=0&&result[parent].getNum() >temp.getNum()) {
				result[child] = result[parent];
				child = parent;
				parent = (parent - 1) / 2;
			}
			result[child] = temp;
		}
		return result;
	}

	void insert(ImportClassVo a[], ImportClassVo value) {
		a[0]=value;
		int parent=0;

		while(parent<a.length){
			int lchild=2*parent+1;
			int rchild=2*parent+2;
			int minIndex=parent;
			if(lchild<a.length&&a[parent].getNum()>a[lchild].getNum()){
				minIndex=lchild;
			}
			if(rchild<a.length&&a[minIndex].getNum()>a[rchild].getNum()){
				minIndex=rchild;
			}
			if(minIndex==parent){
				break;
			}else{
				ImportClassVo temp=a[parent];
				a[parent]=a[minIndex];
				a[minIndex]=temp;
				parent=minIndex;
			}
		}

	}

	ImportClassVo[] getTopKByHeap(ImportClassVo[] nodeArr, int k) {
		ImportClassVo heap[] = this.createHeap(nodeArr, k);
		for(int i=k;i<nodeArr.length;i++){
			if(nodeArr[i].getNum()>heap[0].getNum()){
				this.insert(heap, nodeArr[i]);
			}


		}
		return heap;

	}

	public List<ImportClassVo> topK(ImportClassVo[] arr, int k) {
		//ImportClassVo[] NodeArr = (ImportClassVo[]) list.toArray();
		//int a[] = { 4, 3, 5, 1, 2,8,9,10};
		if(k > arr.length){
			k = arr.length;
		}
		ImportClassVo[] result = new ImportClassTopK().getTopKByHeap(arr, k);
		List<ImportClassVo> list = new ArrayList<ImportClassVo>();
		for (ImportClassVo vo:result){
			list.add(vo);
		}
		Comparator<ImportClassVo> cmp = new ComparatorImportClass();
		Collections.sort(list,cmp);

		return list;
	}
}
	class ComparatorImportClass implements Comparator<ImportClassVo> {

		@Override
		public int compare(ImportClassVo o1, ImportClassVo o2) {
			int flag = Integer.compare(o2.getNum(),o1.getNum());
			return flag;
		}
	}





