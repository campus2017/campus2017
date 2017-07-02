package top.mineor;

import java.util.Scanner;

/**
 * Created by Mineor on 2017/7/1.
 */
public class MainClass {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String dirName = input.nextLine();
        CountImport.processFiles(dirName);
        CountImport.countMostImportClass();
    }
}
