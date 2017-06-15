package com.qunar.amao.pojo;

import java.util.LinkedList;

/**
 * 队列用来保存将要访问的Url
 * Created by FGT on 2017/5/12.
 */
public class UrlQueue {
    //使用链表实现队列
    private LinkedList queue = new LinkedList();

    /**
     * 入队列
     * @param t
     */
    public void enQueue(Object t){
        queue.addLast(t);
    }

    /**
     * 出队列
     * @return
     */
    public Object deQueue(){
        return queue.removeFirst();
    }

    /**
     * 判断队列是否为空
     * @return
     */
    public boolean isQueueEmpty(){
        return queue.isEmpty();
    }

    /**
     * 判断队列是否包含t
     * @param t
     * @return
     */
    public boolean contains(Object t){
        return queue.contains(t);
    }

    public boolean empty(){
        return queue.isEmpty();
    }
}
