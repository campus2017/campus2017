package leeyang.practice.qunar;

/**
 * 实现最小堆
 */

public class MinHeap {
    //采用数组方式存储堆
    private Integer[]  data;

    public MinHeap(Integer[] data) {
        this.data = data;
        buildHeap();
    }

    private void buildHeap() {
        for (int i = (data.length) / 2 - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    private void heapify(int index) {
        int l = ((index + 1) << 1) - 1;
        int r = (index + 1) << 1;
        int smallest = index;
        if (l < data.length && data[smallest] > data[l]) {
            smallest = l;
        }
        if (r < data.length && data[smallest] > data[r]) {
            smallest = r;
        }
        if (smallest == index) {
            return;
        }
        swap(index, smallest);

        heapify(smallest);
    }

    private void swap(int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public  int getRoot() {
        return data[0];
    }

    public void setRoot(int root) {
        data[0] = root;
        heapify(0);
    }
}
