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

        result.forEach(System.out::println);
    }
}
