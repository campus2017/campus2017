package wz;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * DirectoryTraversal
 * 用于遍历给定目录
 *
 * @author wz
 * @date 16/11/16 20:50
 */
public class DirectoryTraversal implements Runnable {

    private File directory;
    private List<File> javaFiles;

    public DirectoryTraversal(String path, List<File> javaFiles) {
        this.directory = new File(path);
        this.javaFiles = javaFiles;
    }

    public void run() {
        if (!directory.exists())
            return;

        if (directory.isFile()) {
            if (directory.getName().endsWith(".java"))
                javaFiles.add(directory);
            return;
        }
        List<File> dirs = new LinkedList<File>();
        dirs.add(directory);
        File dir;
        File[] subFiles;
        while (dirs.size() > 0) {
            dir = dirs.remove(0);
            subFiles = dir.listFiles();
            //对dir没有访问权限，返回null
            if (subFiles == null)
                continue;
            synchronized (javaFiles) {
                // System.out.println("正在遍历"+dir);
                for (File subFile : subFiles) {
                    if (subFile.isFile()){
                        if (subFile.getName().endsWith(".java"))
                            javaFiles.add(subFile);
                    }
                    else
                        dirs.add(subFile);
                }
            }
        }
    }
}
