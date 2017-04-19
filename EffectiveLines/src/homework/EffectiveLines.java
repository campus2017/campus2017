package homework;

/**
 * Created by jiye on 2017/4/12.
 */

import java.io.*;
import java.util.Scanner;

public class EffectiveLines {


    static int codeNum = 0, nodeNum = 0, spaceNum = 0;
    static boolean flagNode = false;

    private static void countFun(String line) {

        String regxNodeBegin = "\\s*/\\*.*";
        String regxNodeEnd = ".*\\*/\\s*";
        String regx = "//.*";
        String regxSpace = "\\s*";
        if (line.matches(regxNodeBegin) && line.matches(regxNodeEnd)) {
            ++nodeNum;
            return;
        }
        if (line.matches(regxNodeBegin)) {
            ++nodeNum;
            flagNode = true;
        } else if (line.matches(regxNodeEnd)) {
            ++nodeNum;
            flagNode = false;
        } else if (line.matches(regxSpace))
            ++spaceNum;
        else if (line.matches(regx))
            ++nodeNum;
        else if (flagNode)
            ++nodeNum;
        else ++codeNum;
    }

    public static void main(String[] args) {
        System.out.println("请输入要统计的java文件路径（不考虑路径名称的大小写）：");
        System.out.println("222");
        Scanner scanner = new Scanner(System.in);
        File file = new File(scanner.nextLine());
        while (!file.exists() || !file.getName().endsWith(".java")) {
            System.out.println("您输入的文件路径错误！");
            System.out.println("请重新输入文件路径：");
            file = new File(scanner.nextLine());
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                countFun(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
        System.out.println("代码有效行数为： " + codeNum + "行");
    }


}