
import java.io.File;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * 统计java文件的有效行
 *  1.有效行不包括空行
 *  2.不考虑代码间有多行注释的情况
 */

public class EffectiveLinesStatistics {
    //总有效代码行数
    private static int effectiveLines = 0;
    //统计的Java文件数
    private static int javaFileCount = 0;


    /**
     *
     * 统计Java源文件数和有效行数
     */
    public static void statistics(File file) {
        if (file.isFile())
    }

}
