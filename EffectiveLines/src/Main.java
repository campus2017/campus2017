
import leeyang.practice.qunar.EffectiveLinesStatistics;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;


/**
 * 统计有效的java代码行数总文件
 */

public class Main {
    public static void main(String[] args) {
        try {
            String path = getInput();
            File inFile = new File(path);
            EffectiveLinesStatistics.statistics(inFile);
            int totalFile = EffectiveLinesStatistics.javaFileCount;
            int effectiveLines = EffectiveLinesStatistics.effectiveLines;
            System.out.println("总文件数:\t" + totalFile);
            System.out.println("总有效代码行数:\t" + effectiveLines);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        String path;
        System.out.print("请输入要统计的文件:\n");
        path = sc.nextLine();
        return path;
    }
}
