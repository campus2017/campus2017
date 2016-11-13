package org.hadyang.impor;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("请输入文件夹路径：");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.next();

        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件夹不存在！");
            return;
        }

        CountMostImport countMostImport = new CountMostImport();
        List<CountResult> data = countMostImport.count(file);

        TopK<CountResult> topK = new TopK<>();
        List<CountResult> result = topK.getTopK(data, 10);

        System.out.println("该目录下被 Import 次数最多的类，前十分别是：");

        for (CountResult countResult : result) {
            System.out.println(String.format("%s:被 Import %d 次", countResult.name, countResult.count));
        }
    }
}
