package util.getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22.
 */
public class FilePathGetter {

    /**
     *
     * @param file 可以看作为根目录，java项目中的源文件
     * @return 包含根目录下所有.java文件的路径的List
     */
    public List<String> getFilePath(File file){
        List<String> list = new ArrayList<>();
        addFile(file,list);
        return list;
    }

    /**
     * 递归遍历每个文件夹下的.java文件，并存储在List中
     *
     * @param file 根目录文件
     * @param list 存放.java文件路径的List
     */
    private void addFile(File file, List<String> list) {
        if (file.isDirectory()){
            File[] files = file.listFiles();

            for (File fileIndex : files) {
                if (fileIndex.isDirectory()){
                    addFile(fileIndex,list);
                }else {
                    String path = fileIndex.getPath();
                    if (path.contains(".java")){
                        list.add(path);
                    }
                }
            }
        }
    }

}
