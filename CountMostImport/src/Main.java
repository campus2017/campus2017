
import leeyang.practice.qunar.MapClassFromFile;
import leeyang.practice.qunar.TopKItem;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


/**
 * 统计指定项目目录下import最多的类，前k个
 */

public class Main {

    public static void main(String[] args) {
        String path = getFilePath();
        int k = getK();
        File inFile = new File(path);
        try {
            MapClassFromFile.readFile(inFile);
            HashMap<String, Integer> mapClass = (HashMap<String, Integer>) MapClassFromFile.mapClass;
            Integer[] classCount = mapClass.values().toArray(new Integer[mapClass.size()]);
            TopKItem topK = new TopKItem(classCount,k);
            List<Integer> topData = topK.getResult();
            Map<String, Integer> topClassName = getTopClassName(mapClass, topData);
            System.out.println(inFile + " 目录下import最多的前" + k + "个类为:");
            printResult(topClassName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, Integer> getTopClassName(Map<String, Integer> mapData, List<Integer> topData) {
        Map<String, Integer> res = new HashMap<>();
        for (Map.Entry<String, Integer> entry : mapData.entrySet()) {
            if (topData.contains(entry.getValue())) {
                res.put(entry.getKey(), entry.getValue());
            }
        }
        return res;
    }

    private static void printResult(Map<String, Integer> mapResult) {

        for (Map.Entry<String, Integer> entry : mapResult.entrySet()) {
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }

    private static String getFilePath() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入文件:");
        return sc.nextLine();
    }

    private static int getK() {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入需要提取的前N个数:");
        int k = 1;
        try {
             k = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;
    }
}
