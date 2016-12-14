package wz;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Main
 *
 * @author wz
 * @date 16/11/16 20:03
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("输入待解析目录：");
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        while (scanner.hasNext()) {
            List<File> javaFiles = new LinkedList<File>();
            Future visitor = executorService.submit(new DirectoryTraversal(scanner.nextLine(), javaFiles));
            CountImport countImport = new CountImport(javaFiles, 20);
            Future<List<ResultEntity>> counter = executorService.submit(countImport);
            while (true) {
                if (visitor.isDone()) {
                    countImport.stop();
                    break;
                }
                Thread.sleep(1000);
            }
            List<ResultEntity> results = counter.get();
            for (ResultEntity result : results)
                System.out.println(result.getClassName()+":出现"+result.getImportedCount()+"次");
            System.out.print("\n输入待解析目录：");

        }
        executorService.shutdown();
    }

}
