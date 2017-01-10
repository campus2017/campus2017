import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/10.
 */
public class DirectoryTool {
//    返回给定路径下的目录文件
    static List<File> recurseDirs(File startDir, String regex){
        List<File> files = new ArrayList<File>();

        for(File item:startDir.listFiles()){
            if (item.isDirectory()){
                files.addAll(recurseDirs(item,regex));
            }else{
                if(item.getName().matches(regex))
                    files.add(item);
            }
        }
        return files;
    }
}
