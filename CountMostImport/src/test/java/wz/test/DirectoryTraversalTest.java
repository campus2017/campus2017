package wz.test;

import org.junit.Test;
import wz.DirectoryTraversal;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

/**
 * DirectoryTraversalTest
 *
 * @author wz
 * @date 16/11/17 13:51
 */
public class DirectoryTraversalTest {

    ExecutorService executorService;

    {
        executorService = Executors.newSingleThreadExecutor();
    }


    @Test
    public void run() throws Exception {
        List<File> javaFiles = new LinkedList<File>();
        Future future = executorService.submit(new DirectoryTraversal("E:\\IntelliJ Workspace\\campus2017\\CountMostImport\\src\\main\\java\\wz", javaFiles));
        while (true) {
            if (future.isDone()) {
                System.out.println(javaFiles.size());
                for (File file : javaFiles) {
                    System.out.println(file.getAbsolutePath());
                }
                return;
            }
        }
    }

    @Test
    public void stringTest() {
        System.out.println("abcd".compareTo("abed"));
    }

}