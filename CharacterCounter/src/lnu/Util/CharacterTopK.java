package lnu.Util;
import lnu.pojo.Character2;

/**
 * Created by DELL on 2017/4/19.
 * Description:求出出现次数最多的k个字符
 */
public class CharacterTopK {

	Character2[] createHeap(Character2 a[], int k) {
		Character2[] result = new Character2[k];
		for (int i = 0; i < k; i++) {
			result[i] = a[i];
		}
		for (int i = 1; i < k; i++) {
			int child = i;
			int parent = (i - 1) / 2;
			Character2 temp = a[i];
			while (parent >= 0 &&child!=0&&result[parent].getCount() >temp.getCount()) {
				result[child] = result[parent];
				child = parent;
				parent = (parent - 1) / 2;
			}
			result[child] = temp;
		}
		return result;
	}

	void insert(Character2 a[], Character2 value) {
		a[0]=value;
		int parent=0;

		while(parent<a.length){
			int lchild=2*parent+1;
			int rchild=2*parent+2;
			int minIndex=parent;
			if(lchild<a.length&&a[parent].getCount()>a[lchild].getCount()){
				minIndex=lchild;
			}
			if(rchild<a.length&&a[minIndex].getCount()>a[rchild].getCount()){
				minIndex=rchild;
			}
			if(minIndex==parent){
				break;
			}else{
				Character2 temp=a[parent];
				a[parent]=a[minIndex];
				a[minIndex]=temp;
				parent=minIndex;
			}
		}

	}

	Character2[] getTopKByHeap(Character2[] nodeArr, int k) {
		Character2 heap[] = this.createHeap(nodeArr, k);
		for(int i=k;i<nodeArr.length;i++){
			if(nodeArr[i].getCount()>heap[0].getCount()){
				this.insert(heap, nodeArr[i]);
			}


		}
		return heap;

	}

	public Character2[] topK(Character2[] arr, int k) {
		//Character2[] NodeArr = (Character2[]) list.toArray();
		//int a[] = { 4, 3, 5, 1, 2,8,9,10};
		Character2[] result = new CharacterTopK().getTopKByHeap(arr, k);
		/*for (Character2 temp : result) {
			System.out.println(temp.getData()+" 次数:"+temp.getCount());
		}*/
		return result;
	}
}





