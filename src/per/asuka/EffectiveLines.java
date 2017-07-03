package per.asuka;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by asuka on 05/06/2017.
 */

//统计Java文件中的代码行数(分别统计空白行、注释行、代码行)
public class EffectiveLines {
    static int files = 0;
    static int blankLine = 0;
    static int commentLine = 0;
    static int codeLine = 0;
    static int totalLine = 0;

    private static void countSingleFile(File destFile) throws FileNotFoundException{
        files++;
        Scanner sc = new Scanner(destFile);
        String str;
        while (sc.hasNextLine()){
            totalLine++;
            str = sc.nextLine().trim();
            if (str.isEmpty()){
                blankLine++;
            }else if (str.startsWith("//") || str.startsWith("/*") || str.startsWith("/**")){
                commentLine++;
            }else{
                codeLine++;
            }
        }
    }

    private static void handleDirectory(File destDir) throws FileNotFoundException{
        for (File item: destDir.listFiles()){
            if (item.isDirectory()){
                handleDirectory(item);
            }else{
                Pattern pattern = Pattern.compile(".*\\.java$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(item.getName());
                if (matcher.matches()) {
                    countSingleFile(item);
                }
            }
        }
    }

    public static void countLines(String path) throws FileNotFoundException{
        File dest = new File(path);
        if (!dest.exists()){
            System.out.println(path + "不存在");
            return;
        }else{
            if (dest.isDirectory()){
                handleDirectory(dest);
            }else{
                countSingleFile(dest);
            }
        }
        System.out.println(String.format("文件总数:%d\n代码行数:%d\n注释行数:%d\n空行数:%d\n总行数:%d",
                files,codeLine, commentLine, blankLine, totalLine));
    }
}