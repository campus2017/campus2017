package wz;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * FileTest
 *
 * @author wz
 * @date 16/11/17 13:11
 */
public class FileTest {

    public static void main(String[] args) {
        File directory = new File("E:\\");
        List<File> dirs = new LinkedList<File>();
        dirs.add(directory);
        File dir;
        File[] subFiles;
        LinkedList<File> javaFiles = new LinkedList<File>();

        while (dirs.size() > 0) {
            dir = dirs.remove(0);
            subFiles = dir.listFiles();
            if (subFiles == null) {
//                System.out.println(dir);
                System.out.println("没有访问权限");
                continue;
            }
            for (File subFile : subFiles) {
                if (subFile.isFile()){
                    javaFiles.add(subFile);
                    System.out.println(subFile.getAbsolutePath());
                }
                else
                    dirs.add(subFile);
            }
        }
        System.out.println(javaFiles.size());
//        for (File file : javaFiles) {
//            System.out.println(file.getAbsolutePath());
//        }
//        File[] subFiles = directory.listFiles();
//        System.out.println(directory.isFile());
//        System.out.println(subFiles.length);
//        for (File file : subFiles) {
//            System.out.println(file.getName());
//        }
    }

}
