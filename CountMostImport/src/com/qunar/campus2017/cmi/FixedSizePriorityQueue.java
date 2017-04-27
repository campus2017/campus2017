package com.qunar.campus2017.cmi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by Leon on 2017/4/24.
 */
public class FixedSizePriorityQueue<E extends  Comparable> {

        private PriorityQueue<E> queue; //use as a minHeap
        private final int maxSize;  // fixed heap size

        public FixedSizePriorityQueue(int maxSize) {
            if (maxSize <= 0)
                throw new IllegalArgumentException();
            this.maxSize = maxSize;
            this.queue = new PriorityQueue<>(maxSize);
        }

        public void add(E e) {
            if (queue.size() < maxSize) {
                queue.add(e);
            } else { // Queue full
                E peek = queue.peek();
                if (e.compareTo(peek) > 0) {
                    queue.poll();
                    queue.add(e);
                }
            }
        }

        public ArrayList<E> sortedList() {
            ArrayList<E> list = new ArrayList<E>(queue);
            Collections.sort(list, new Comparator<E>(){

                @Override
                public int compare(E o1, E o2) {
                    return o2.compareTo(o1);
                }

            }); // sort the topN list.
            return list;
        }
    }
