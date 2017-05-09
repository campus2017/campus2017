import java.util.ArrayList;
import java.util.List;

/**
 * Created by isc on 2016/12/18.
 */
public class TopK {

    private static  List<MostImportClass> mHeap = new ArrayList<MostImportClass>();        // 存放堆的数组

    /**
     *  获取前k个最大值
     * @param input  输入数据源
     * @param k  k值
     * @return  list类型列表
     */
    public static  List<MostImportClass> getTopK(List<MostImportClass> input, int k){
        createLitteHeap(input,k);
        List<MostImportClass> a = new ArrayList<MostImportClass>();
        for(int i=k;i<input.size();i++){
            if(input.get(i).value>mHeap.get(0).value){
                insertMoreData(input.get(i));
            }
        }
        return mHeap;
    }

    /**
     * 构造容量为k的小根堆
     * @param list 值
     * @param k 容量
     */
   private static void createLitteHeap(List<MostImportClass> list, int k){

       if(list.size()<k){
           k = list.size();
       }
        for(int i=0;i<k;i++){
            mHeap.add(list.get(i));
        }

        //调整
        for (int i = 1; i < k; i++) {
            int child = i;
            int parent = (i - 1) / 2;
            MostImportClass temp = mHeap.get(i);
            while(parent>=0 && child !=0 && mHeap.get(parent).value>temp.value){
                mHeap.set(child,mHeap.get(parent));
                child=parent;
                parent = (parent-1)/2;
            }
            mHeap.set(child,temp);
        }
    }

    /**
     * 多余数据进行插入
     * @param im
     */
    private static void insertMoreData(MostImportClass im){
       mHeap.set(0,im);
        int parent = 0;
        while(parent<mHeap.size()){
            int lchild=2*parent+1;
            int rchild=2*parent+2;
            int minIndex=parent;

            if(lchild<mHeap.size() && mHeap.get(parent).value>mHeap.get(lchild).value){
                minIndex=lchild;
            }
            if(rchild<mHeap.size() && mHeap.get(minIndex).value>mHeap.get(rchild).value){
                minIndex=rchild;
            }
            if(minIndex==parent){
                break;
            }else{
                MostImportClass temp=mHeap.get(parent);
                mHeap.set(parent,mHeap.get(minIndex));
                mHeap.set(minIndex,temp);
                parent=minIndex;
            }
        }
    }
}
