import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    static long codeLines = 0;
    static long commentLines = 0;
    static long blankLines = 0;


    public static void main(String[] args) {
        String path = "";
        System.out.println("请输入打印路径:");
        Scanner in = new Scanner(System.in);
        path = in.nextLine();
        File file = new File(path);
        count(file);

        System.out.println("有效行数" + codeLines);
        System.out.println("注释行数" + commentLines);
        System.out.println("空行数" + blankLines);
    }

    /**
     * 分别统计行数
     *
     * @param f
     */
    private static void count(File f) {
        BufferedReader br = null;
        boolean flag = false;
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.matches("^[ ]*$")) {
                    blankLines++;
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    flag = true;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (flag == true) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}