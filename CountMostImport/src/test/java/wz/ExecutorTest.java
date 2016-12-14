package wz;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * ExecutorTest
 *
 * @author wz
 * @date 16/11/16 20:39
 */
public class ExecutorTest {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Boolean finished = false;
//        Test1 test1 = new Test1();
//        new Thread(new Test1()).start();
//        new Thread(new Test2()).start();
//        executorService.execute(new Test1());
//        executorService.execute(new Test2());
        Future future1 = executorService.submit(new Test1());
//        System.out.println(future1);

        Future future2 = executorService.submit(new Test2());
//        System.out.println(future2);
        executorService.shutdown();
    }

}

class Test1 implements Runnable{

    private volatile Boolean finished = false;

//    public Test1(Boolean finished) {
//        this.finished = finished;
//    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread 1:"+i);
        }

    }
}

class Test2 implements Runnable{

    private volatile boolean finished = false;


    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("thread 222222222222222:"+i);
        }
    }
}