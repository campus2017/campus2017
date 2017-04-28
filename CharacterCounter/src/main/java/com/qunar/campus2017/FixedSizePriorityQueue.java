package com.qunar.campus2017;

import java.util.*;

/**
 * Created by Leviathan on 2017/4/28.
 */
public class FixedSizePriorityQueue<E extends Comparable> {

    private PriorityQueue<E> queue; //minHeap
    private final int maxSize;

    public FixedSizePriorityQueue(int maxSize) {

        if (maxSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxSize = maxSize;
        this.queue = new PriorityQueue<>(maxSize);
    }

    public void add(E e) {
        if(queue.size() < maxSize) {
            queue.add(e);
        } else {
            E peekElement = queue.peek();
            if (e.compareTo(peekElement) > 0) {
                queue.poll();
                queue.add(e);
            }
        }
    }

    // sort from Higher to lower.
    public ArrayList<E> sortList() {
        ArrayList<E> list = new ArrayList<E>(queue);
        Collections.sort(list, new Comparator<E>() {
            @Override
            public int compare(E o1, E o2) {
                return o2.compareTo(o1);
            }
        });
        return list;
    }

}
