import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Created by Administrator on 2017/4/7.
 * 题目一、统计一个Java文件的有效行数。（作业命名：Main）
 1、有效不包括空行
 2、不考虑代码见有多行注释的情况
 */
/*
分析：
1、读入待处理的java文件
（1）、保证读入的文件路径正确
（2）、保证读入文件为.java的后缀
2、判断Java文件的有效行数
（1）、不包括空行
（2）、不考虑代码见有多行注释的情况（
 */
public class Main {
    public static void main(String[] args)
    {
        int lineNum = 0;
        System.out.println("Please input a file path ：");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        String fileName = file.getName();
        if (!file.exists())
        {
            System.out.println("The file is not exists!");
        }
        else if(fileName.substring(fileName.length()-5).equals(".java"))
        {
            lineNum = effectiveLineNum(filePath);
            System.out.println("The effective number of lines in "+fileName+" : "+lineNum);
        }
        else{
            System.out.println("The file is not Java file!");
        }

    }
    public static Integer effectiveLineNum(String filePath)
    {   int lineNum = 0;//记录有效行数
        String line = null;//读入每行的字符串
        try {
        BufferedReader br = new BufferedReader(new FileReader(filePath));

        while ((line = br.readLine()) != null)
        {
            line = line.trim();
            if (line.equals(""))
            {
                continue;
            }
            else if (line.startsWith("/*") && line.endsWith("*/"))
            {
                continue;

            }
            else if (line.indexOf("/*") != -1)
            {

                while (line.indexOf("*/") == -1)
                {
                    line = br.readLine();
                }
            }
            else {
               lineNum++;
            }
        }

    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
        return lineNum;
    }


}
