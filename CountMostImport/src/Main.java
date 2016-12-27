
import leeyang.practice.qunar.MapClassFromFile;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;

/**
 * 统计指定项目目录下import最多的类，前k个
 */

public class Main {
    public static void main(String[] args) {
        String path = getInput();
        File inFile = new File(path);
        try {
            MapClassFromFile.readFile(inFile);
            HashMap<String, Integer> mapClass = (HashMap<String, Integer>) MapClassFromFile.mapClass;
            Integer[] temp = mapClass.values().toArray(new Integer[mapClass.size()]);
            System.out.println(mapClass);
            for (int v : temp)
                System.out.println(v);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getInput() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入文件:");
        String path = sc.nextLine();
        return path;
    }
}
