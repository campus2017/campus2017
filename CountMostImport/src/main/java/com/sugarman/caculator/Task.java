package com.sugarman.caculator;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by SugarMan on 2017/1/19.
 */
public class Task implements Runnable {

    public static int COUNT = 0;
    private BlockingQueue<File> queue;
    private CountDownLatch donwLatch;
    public TaskInterface taskInterface;

    public Task(BlockingQueue<File> queue,CountDownLatch downLatch) {
        this.queue = queue;
        this.donwLatch = downLatch;
    }

    public void run() {
        try {
            boolean done = false;
            while (!done) {
                // 取出队首元素，如果队列为空，则阻塞
                File file = queue.take();
                if (file == FileEnumerationTask.DUMMY) {
                    // 取出来后重新放入，好让其他线程读到它时也很快的结束
                    queue.put(file);
                    done = true;
                } else {
                    if (taskInterface == null) {
                        taskInterface = new TaskInterface() {
                            public void task(File file) {
                            }
                        };
                    }
                    System.out.println(Thread.currentThread().getName() + file.getName());
                    taskInterface.task(file);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            this.donwLatch.countDown();
            System.out.println("----------------------->"+Thread.currentThread().getName()+" finished");
        }
    }

    public interface TaskInterface {
        public void task(File file);
    }
}
