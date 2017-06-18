package util;
import java.util.*;

/**
 * Created by libo on 2017/6/7.
 *
 * topk算法实现取最大的k个键值对数据
 * 算法的时间复杂度为 k*lgn    当排序的数量较多时会有性能的提升
 * 也可以使用冒泡排序，时间复杂度为 k*n
 */
public class SelectTopK {

    private static class EntryComparator implements Comparator<Map.Entry<String, Integer>>{
        //o1<o2返回正数   o1>o2返回负数
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            int diff = o2.getValue() - o1.getValue();                       //按照次数降序

            return diff == 0 ? o1.getKey().compareTo(o2.getKey()) : diff;    //按照字典升序
        }
    }


    /**
     * 当data的值小于等于k值时直接全部进行排序
     * @param data
     * @return
     */
    private static List<Map.Entry<String, Integer>> sortMap(Map<String, Integer> data){
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(data.entrySet());
        Collections.sort(list, new EntryComparator());

        return list;
    }


    //堆调整    对start下标到end下标的值进行调整
    private static void adjustMinHeap(List<Map.Entry<String, Integer>> minHeap, int start, int end){
        Map.Entry<String, Integer> item = minHeap.get(start);
        int parent = start;
        int leftChild = start*2 + 1;            //这个下标表示调整树中的左孩子的下标，也记录了左右孩子中较小值的下标
        EntryComparator comparator = new EntryComparator();

        for ( ; leftChild <= end; leftChild=leftChild*2+1){
            if (leftChild < end && comparator.compare(minHeap.get(leftChild),
                                                      minHeap.get(leftChild + 1)) < 0){
                ++leftChild;                        //比较左孩子和右孩子之间的大小，如果右孩子较小，则索引指向右孩子
            }
            if (comparator.compare(minHeap.get(leftChild), item) > 0){
                minHeap.set(parent, minHeap.get(leftChild));
                parent = leftChild;
            }
            else{
                break;
            }
        }
        minHeap.set(parent, item);
    }


    //初始化最小堆   从最后一个根元素(k-2)/2 开始到第一个元素0 进行调整最小堆
    private static void initMinHeap(List<Map.Entry<String, Integer>> minHeap, int k){
        int i = (k-2) / 2;

        for ( ; i>=0; --i){
            adjustMinHeap(minHeap, i, k-1);
        }
    }

    /**
     * 获取得到出现次数最多的k个值
     * @param data
     * @param k
     * @return
     */
    public static List<Map.Entry<String, Integer>> getMaxVal(Map<String, Integer> data, int k){
        if (data == null || k <= 0){
            return null;
        }
        if (k >= data.size()){
            return sortMap(data);
        }

        int index = 1;
        EntryComparator comparator = new EntryComparator();
        List<Map.Entry<String, Integer>> minHeap = new ArrayList<Map.Entry<String, Integer>>(k);
        for (Map.Entry<String, Integer> item : data.entrySet()){
            if (index < k){
                minHeap.add(item);
            }
            else if (index == k){
                minHeap.add(item);
                initMinHeap(minHeap, k);
            }
            else if (index > k && comparator.compare(item, minHeap.get(0)) < 0){
                minHeap.set(0, item);
                adjustMinHeap(minHeap, 0, k-1);
            }
            ++index;
        }
        ///以上过程筛选出了最大的k个值组成的小根堆  下面调整成升序的集合
        for (int j=0; j<k-1; ++j){
            Map.Entry<String, Integer> temp = minHeap.get(0);
            minHeap.set(0, minHeap.get(k-1-j));
            minHeap.set(k-1-j, temp);
            adjustMinHeap(minHeap, 0, k-2-j);
        }
        return minHeap;
    }
}




