import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/14.
 */
public class EnfectiveLines {
    private static  long effectiveLines=0;
    public static void main(String[] args) {

        System.out.println("请输入Java文件路径");
        Scanner sn = new Scanner(System.in);
        String filePath = sn.nextLine();
        File file = new File(filePath);
        try {
            effectiveLines = findLines(file);
        } catch (FileNotFoundException e) {
            System.out.println("出错了。");
            e.printStackTrace();
        }

        System.out.println(effectiveLines);
    }

    private static Long findLines(File file) throws FileNotFoundException {

        if (file == null || !file.exists())
            throw new FileNotFoundException(file + "文件不存在");
        BufferedReader bufr ;
        try {
            // 将指定路径的文件与字符流绑定
            bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(file + "，文件不存在！" + e);
        }

        // 定义匹配每一行的正则匹配器
        Pattern codeLinePattern = Pattern.compile("(?!import|package).+;\\s*(((//)|(/\\*+)).*)*",
                Pattern.MULTILINE + Pattern.DOTALL); // 代码行匹配器（以分号结束为一行有效语句,但不包括import和package语句）

        // 遍历文件中的每一行，并根据正则匹配的结果记录每一行匹配的结果
        String line;
        try {
            while ((line = bufr.readLine()) != null) {

                if (codeLinePattern.matcher(line).matches()) {
                    effectiveLines++;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("读取文件失败！" + e);
        } finally {
            try {
                bufr.close();   // 关闭文件输入流并释放系统资源
            } catch (IOException e) {
                throw new RuntimeException("关闭文件输入流失败！");
            }


            return effectiveLines;
        }
    }
}

