
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

/**
 * 过滤java文件
 */


public class JavaFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name, String filter_str) {

        File file = new File(dir, name);
        if (file.getName().toLowerCase().endsWith(filter_str)) {
            return true;
        }
        if (file.isDirectory()) {
            return true;
        }
        return false;
    }
}
