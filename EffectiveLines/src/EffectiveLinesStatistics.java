
import java.io.*;


/**
 * 统计一个java文件的有效行
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

    public static void statistics(File inFile)
            throws IOException {
        for (File file : inFile.listFiles(new JavaFileFilter()) ) {
            if (file.isFile()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(file),"utf-8"));
                String tmp;
                while ((tmp = br.readLine()) != null) {

                }
            } else if (file.isDirectory()) {
                statistics(file);
            }
        }
    }
}
