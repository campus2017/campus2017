package leeyang.practice.qunar;

import java.util.List;
import java.util.Arrays;

/**
 * 提取topK数量多的项
 * 最小堆实现
 */


public class TopKItem {
    private Integer[] data;
    private int k;
    public TopKItem(Integer[] data, int k) {
        this.data = data;
        this.k = k;
    }

    public List<Integer> getResult() {
        if (k > data.length) {
            return Arrays.asList(data);
        }

        Integer[] heapData = new Integer[k];
        for (int i = 0; i < k; i++) {
            heapData[i] = data[i];
        }
        MinHeap heap = new MinHeap(heapData);

        for (int i = k; i < data.length; i++) {
            int rootData = heap.getRoot();
            if (data[i] > rootData) {
                heap.setRoot(data[i]);
            }
        }
       // List<Integer> list = new ArrayList<Integer>();
        return Arrays.asList(heapData);
    }
}
