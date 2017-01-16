import java.io.*;
import java.util.*;

public class EffectiveLines {
    static int codeLines = 0;
    static int whiteLines = 0;
    static int commentLines = 0;
    static int tatolLines = 0;
    static boolean bComment = false;

    public static void main(String[] args) {
        System.out.println("请输入要统计的文件路径");
        Scanner in = new Scanner(System.in);
        String filePath = in.nextLine();
        //filePath = "src/EffectiveLines.java";
        computeLines(filePath);
        System.out.println("该文件有效行数为： "
                + (codeLines = tatolLines - commentLines - whiteLines));
    }

    public static void computeLines(String path) {
        File file = new File(path);
        BufferedReader bf = null;

        try {
            bf = new BufferedReader(new FileReader(file));
            String lineStr = "";
            while ((lineStr = bf.readLine()) != null) {
                // 总行数
                tatolLines++;
                // 计算空行
                if (lineStr.matches("^[\\s&&[^\\n]]*$")) {
                    whiteLines++;
                }
                // 统计注释行
                if (lineStr.matches("\\s*/\\*{1,}.*\\*/.*")
                        || lineStr.matches("\\s*//.*")) {
                    commentLines++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("文件没有找到");
        } catch (IOException ee) {
            System.out.println("I/O异常");
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                    bf = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}