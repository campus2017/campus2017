
/*
Description
一、统计一个 Java 文件的有效行数。（作业命名：EffectiveLines）
1、有效不包括空行  ：空行ignore
2、不考虑代码间有多行注释的情况 ：只考虑一行注释，注释也是有效行
 */


import java.util.Scanner;

public class mainClass {

    public static void main(String args[])
    {

        effectiveLines effectiveLines_class = new effectiveLines();

        Scanner input = new Scanner(System.in);

         String file_name = input.nextLine();

        effectiveLines_class.readFromFile(file_name);

    }
}
