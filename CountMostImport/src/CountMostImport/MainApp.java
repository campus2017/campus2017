package CountMostImport;

import java.util.Scanner;

/**
 * Created by 朱潇翔 on 2017/1/22.
 */
public class MainApp {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入文件路径：");
        String filename = input.next();

        CountMost countMost = new CountMost(filename);
        countMost.CoutTopTen();
    }

}
