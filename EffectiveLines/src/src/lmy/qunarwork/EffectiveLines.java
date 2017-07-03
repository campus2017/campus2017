package src.lmy.qunarwork;


import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;


/**
 * Created by Administrator on 2017-01-05.
 */


public class EffectiveLines {

    static long annotationLine = 0;
    static long blankLine = 0;
    static long codeLine = 0;
    static long totalLine = 0;
    static long fileCount = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("请输入要统计的java文件:"); //输入要统计行数的java文件
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();

        File file = new File(filePath); //统计输入java文件的有效代码行数
        codeStat(file);

        System.out.println("该java文件的有效行数是：" + codeLine);    //输出该文件的有效行数
    }

    private static void codeStat(File file) throws FileNotFoundException {
        if (file == null || !file.exists())
            throw new FileNotFoundException(file + "，文件不存在！");

        fileCount ++;

        if (file.isDirectory()) {
            File[] files = file.listFiles(new FileFilter() {

                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".java") || pathname.isDirectory();
                }
            });

            for (File target : files) {
                codeStat(target);
            }
        } else {
            BufferedReader bufr = null;
            try {
                bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } catch (FileNotFoundException e) {
                throw new FileNotFoundException(file + "，文件不存在！" + e);
            }

            // 匹配每一行的正则匹配
            Pattern annotationLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+",
                    Pattern.MULTILINE + Pattern.DOTALL);

            Pattern blankLinePattern = Pattern.compile("^\\s*$");

            Pattern codeLinePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
                    Pattern.MULTILINE + Pattern.DOTALL);


            String line = null;
            try {
                while((line = bufr.readLine()) != null) {
                    if (annotationLinePattern.matcher(line).find()) {
                        annotationLine ++;
                    }

                    if (blankLinePattern.matcher(line).find()) {
                        blankLine ++;
                    }

                    if (codeLinePattern.matcher(line).matches()) {
                        codeLine ++;
                    }

                    totalLine ++;
                }

            } catch (IOException e) {
                throw new RuntimeException("读取文件失败！" + e);
            } finally {
                try {
                    bufr.close();
                } catch (IOException e) {
                    throw new RuntimeException("关闭文件输入流失败！");
                }
            }
        }
    }
}
