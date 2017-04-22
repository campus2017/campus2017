import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by wuyaqi on 17-4-6.
 */
public class EffectiveLines {

    // 记录文档中独自一行的单行注释行数
    static long annotationLine = 0;

    // 记录空白行数
    static long blankLine = 0;

    // 记录有效代码的行数
    static long codeLine = 0;

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("请输入要统计有效行数的java文件目录：");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();
        //F://QUNAR//2017应届生培训前自学内容//自学作业//EffectiveLines//src//HelloWord.java
        File file = new File(filePath);
        // 根据用户输入的文件目录执行代码量统计
        codeStat(file);

        System.out.println("－－－－－－－－－－统计结果－－－－－－－－－");

        System.out.println("代码有效行数：" + codeLine);
        System.out.println("单独一行的单行注释行数：" + annotationLine);

        System.out.println("空白行数：" + blankLine);
        System.out.println("总行数：" + (codeLine+annotationLine+blankLine));
    }

    private static void codeStat(File file) throws FileNotFoundException {
        if (file == null || !file.exists())
            throw new FileNotFoundException(file + "，文件不存在！");

        BufferedReader bufr = null;
        try {
            // 将指定路径的文件与字符流绑定
            bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(file + "，文件不存在！" + e);
        }

        // 定义匹配每一行的正则匹配器

        Pattern annotationLinePattern = Pattern.compile("(^(//))|(^(/\\*).*?((\\*/)$))",
                Pattern.MULTILINE + Pattern.DOTALL);
        // 匹配单独一行的单行注释://,/**/，匹配单独一行的多行及单行注释："(^(//)|^(/\\*+)|(\\*)|(\\*+/)$)"，
        // 匹配所有注释行（包括与代码同一行的）："((//)|(/\\*+)|((^\\s)*\\*)|((^\\s)*\\*+/))+"

        Pattern blankLinePattern = Pattern.compile("^\\s*$");   // 空白行匹配器（匹配回车、tab键、空格）

        // 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
        String line = null;
        try {
            while ((line = bufr.readLine()) != null) {

                if (annotationLinePattern.matcher(line.trim()).find())
                {
                    annotationLine++;
                    //continue;
                }
                else if (blankLinePattern.matcher(line).find())
                {
                    blankLine++;
                    //continue;
                }
                else        codeLine++;
            }

        } catch (IOException e) {
            throw new RuntimeException("读取文件失败！" + e);
        } finally {
            try {
                bufr.close();   // 关闭文件输入流并释放系统资源
            } catch (IOException e) {
                throw new RuntimeException("关闭文件输入流失败！");
            }
        }

    }
}
