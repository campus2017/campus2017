package util.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23.
 */
public class FileImportReader {

    /**
     * 获取每一个.java文件中所引用的类，并存储
     *
     * @param path .java文件的路径
     * @return 包含此.java文件中的所有的引用类的List
     */
    public List<String> readImport(String path){
        List<String> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String s = null;
            while ((s = br.readLine()) != null){
                if (s.contains("import ") && s.contains(";")){
                    list.add(s.trim());
                }else if (s.contains("{")){
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

}
