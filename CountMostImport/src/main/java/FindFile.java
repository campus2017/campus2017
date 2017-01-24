import java.io.File;
import java.util.ArrayList;

/**
 * Created by asus on 2017/1/17.
 */
public class FindFile {
//            存储java文件
    public static ArrayList<String> fileList = new ArrayList<String>();

    public void findFile(String filePath) {
        File root = new File(filePath);
        File[] files = root.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findFile(file.getAbsolutePath());
            } else {
                //判断是否为java文件
                if (file.getName().matches(".*\\.java")) {
                    fileList.add(file.getAbsolutePath());
                }
            }
        }
    }
}
