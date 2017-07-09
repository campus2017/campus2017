package clp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Asus on 2016/12/7.
 */
public class Main {
    public static void main(String[]args)throws Exception{
        System.out.println("请输入文件路径,可多次输入：");
        Scanner sc=new Scanner(System.in);
        String file=null;
        while (sc.hasNext()){
            file=sc.nextLine();
            boolean flag=JudgeFile.judgeFileName(file);
            if(flag==false){
                System.out.println("不是java文件");
                return;
            }
            try{
                int effectiveLines=JudgeFile.effectiveLines(file);
                System.out.println("有效行数为："+effectiveLines);
            }catch (FileNotFoundException e){
                System.out.println("文件不存在");
            }catch(IOException e){
                System.out.println("IO出错");
            }
        }

    }
}
