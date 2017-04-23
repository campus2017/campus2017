/**
 * Created by lhx on 17-4-23.
 */
/**
 *一、统计一个Java文件的有效行数。（作业命名：EffectiveLines）
 *    1、有效不包括空行
 *    2、不考虑代码见有多行注释的情况
 */
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        Integer LineCount=0;
        System.out.println("Please input a file path ：");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("The file is not exists!");
        }
        else{
            LineCount=CountEffLineNum(filePath);
            System.out.println("The effective number of lines in "+filePath+"/"+file.getName()+" : "+ LineCount);
        }
    }

    public static Integer CountEffLineNum(String filePath){
        int EffectNum = 0;//记录有效行数
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
                    EffectNum++;
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return EffectNum;
    }
    }



