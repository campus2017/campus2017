package org.hadyang.impor;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
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

        if (!file.isDirectory()){
            System.out.println("该路径不是文件夹路径！");
            return;
        }

        CountMostImport countMostImport = new CountMostImport();
        List<CountResult> data = countMostImport.count(file);

        TopK<CountResult> topK = new TopK<>();
        int k = data.size() > 10 ? 10 : data.size();
        List<CountResult> result = topK.getTopK(data, k);
        Collections.sort(result, (o1, o2) -> o2.count - o1.count);
        System.out.println(String.format("该目录下被 Import 次数最多的类，前 %d 分别是：", k));

        for (CountResult countResult : result) {
            System.out.println(String.format("%s :被 Import %d 次", countResult.name, countResult.count));
        }
    }
}
