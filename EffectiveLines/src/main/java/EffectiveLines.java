package main.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created by fxd on 2017/6/11.
 */
public class EffectiveLines {
    private static final String REGEX = "^//.*";

    private static int getEffectiveLines(File file)
    {
        int count = 0;      //有效行数
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line != null){
                if(!line.trim().equals("") && !line.trim().matches(REGEX))
                { //有效的代码
                    ++count;
                }
                line = br.readLine();
            }
        } catch (IOException e)
        {
            System.out.println("输入路径有误！");
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println("请输入java文件路径：");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        // String path = "G:\\campus2017\\EffectiveLines\\src\\main\\test\\Main.java";
        if (path == null || path.equals(""))
        {
            System.out.println("文件的路径输入错误！");
            return;
        }
        File file =new File(path);
        int count = 0;
        if (file.exists())
        {
            System.out.print(file.getAbsolutePath());
            count = getEffectiveLines(file);
        }
        else
        {
            System.out.println("文件不存在");
            return;
        }
        System.out.println("有效行数为"+count+"行");
    }
}