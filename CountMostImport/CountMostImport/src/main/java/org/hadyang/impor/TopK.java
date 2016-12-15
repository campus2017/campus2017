package org.hadyang.impor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AstaYang on 2016/11/12.
 */
public class TopK<T extends Comparable<T>> {

    /**
     * 获取最大的K个元素
     *
     * @param input
     * @param k
     * @return
     */
    public List<T> getTopK(List<T> input, int k) {
        List<T> heap = miniHeap(input, k);
        for (int i = k; i < input.size(); i++) {
            if (input.get(i).compareTo(heap.get(0)) > 0) {
                insert(heap, input.get(i));
            }
        }
        return heap;
    }

    private void insert(List<T> heap, T t) {
        heap.set(0, t);
        heap(heap, 0);
    }

    /**
     * 构造最小堆
     *
     * @param a
     * @param k
     * @return
     */
    private List<T> miniHeap(List<T> a, int k) {
        List<T> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(a.get(i));
        }

        for (int i = result.size() / 2 - 1; i >= 0; --i) {
            heap(result, i);
        }
        return result;
    }

    private void heap(List<T> result, int start) {
        int dad = start;
        int son = dad * 2 + 1;
        while (son < result.size()) {
            //选取子节点中较大的一个子节点
            if (son + 1 < result.size() &&
                    result.get(son).compareTo(result.get(son + 1)) > 0)
                son++;

            //父节点大于最大的子节点
            if (result.get(dad).compareTo(result.get(son)) < 0)
                break;
            else {
                T temp = result.get(son);
                result.set(son, result.get(dad));
                result.set(dad, temp);

                dad = son;
                son = dad * 2 + 1;
            }
        }
    }

}