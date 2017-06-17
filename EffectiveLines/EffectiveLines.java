import java.io.*;
import java.util.Scanner;

/**
 * 统计一个Java文件的有效行数
 * 先用checkFileExist()方法判断文件是否存在并为java源文件,然后调用calEffectiveLines()返回有效行数
 */
public class EffectiveLines {

    public EffectiveLines() {
    }

    /**
     * 检查指定位置的文件是否存在并是java源文件
     *
     * @param filepath 文件路径
     * @return 文件是否存在符合要求
     */
    public static boolean checkFileExist(String filepath) {
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            // 这里简单以文件名是否以.java结尾判断是否是合法的Java文件
            if (filepath.endsWith(".java")) {
                return true;
            } else {
                System.out.println("该文件不是Java文件");
                return false;
            }
        } else {
            System.out.println("文件不存在");
            return false;
        }
    }

    /**
     * 计算有效的行数
     *
     * @param filepath 文件路径
     * @return 有效行数
     */
    public static int calEffectiveLines(String filepath) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("没找到文件");
            return 0;
        }
        String line;
        int lineCount = 0;
        boolean isAnnotation = false;       // 表示是否处于注释中
        try {
            while ((line = bufferedReader.readLine()) != null) {    // 循环读取文件中的每一行
                line = line.trim();
                if (isAnnotation) {         // 若已处于注释状态,不计算行数,判断是否该行注释结束
                    if (line.endsWith("*/"))
                        isAnnotation = false;
                } else {                    // 处于非注释状态
                    if (line.startsWith("/*") || line.startsWith("/**")) {  // 若该行为多行注释开头
                        if (line.endsWith("*/"))    // 如果在该行中同时结束注释,开始下一轮循环
                            continue;
                        else {                      // 设置注释状态标志,开始下一轮循环
                            isAnnotation = true;
                            continue;
                        }
                    } else if (line.startsWith("//") || line.isEmpty()) {   // 改行为单行注释,开始下一轮循环
                        continue;
                    }
                    lineCount++;                     // 正常情况下,将总有效行数加1
                }
            }
        } catch (IOException e) {
            System.out.println("出现了IO问题");
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入文件路径:");
            String filepath = scanner.next();
            if (EffectiveLines.checkFileExist(filepath))
                System.out.println("该文件中的行数为: " + EffectiveLines.calEffectiveLines(filepath));
        }
    }

}