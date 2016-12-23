package leeyang.practice.qunar;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 过滤java文件
 */


class JavaFileFilter implements FilenameFilter {

    private static boolean accept(File dir, String name, String filter_str) {
        File file = new File(dir, name);
        return file.isDirectory() || file.getName().toLowerCase().endsWith(filter_str);
    }

    @Override
    public boolean accept(File dir, String name) {
        String filter_str = "java";
        return accept(dir, name, filter_str);
    }
}
