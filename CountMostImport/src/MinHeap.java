import java.util.ArrayList;
import java.util.List;

/**
 * Created by mml on 17-7-3.
 */


public class MinHeap {
    //最小堆数组
    public List<ClassAndValue> cvHeap=new ArrayList<ClassAndValue>();

    public void createLitteHeap(List<ClassAndValue> list, int k){

        if(list.size()<k){
            k = list.size();
        }
        for(int i=0;i<k;i++){
            cvHeap.add(list.get(i));
        }

        //调整成最小堆
        for (int i = 1; i < k; i++) {
            int child = i;
            int parent = (i - 1) / 2;
            ClassAndValue temp = cvHeap.get(i);
            while(parent>=0 && child !=0 && cvHeap.get(parent).times>temp.times){
                cvHeap.set(child,cvHeap.get(parent));
                child=parent;
                parent = (parent-1)/2;
            }
            cvHeap.set(child,temp);
        }
    }
    public void push(ClassAndValue tmp)
    {
        cvHeap.set(0,tmp);
        int parent=0;
        while(parent<cvHeap.size()){
            int lchild=2*parent+1;
            int rchild=2*parent+2;
            int minIndex=parent;

            if(lchild<cvHeap.size() && cvHeap.get(parent).times>cvHeap.get(lchild).times){
                minIndex=lchild;
            }
            if(rchild<cvHeap.size() && cvHeap.get(minIndex).times>cvHeap.get(rchild).times){
                minIndex=rchild;
            }
            if(minIndex==parent){
                break;
            }else{
                ClassAndValue temp=cvHeap.get(parent);
                cvHeap.set(parent,cvHeap.get(minIndex));
                cvHeap.set(minIndex,temp);
                parent=minIndex;
            }
        }

    }
}
