package campus.homework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/6/11.
 */
public class EffectiveLines {

    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("输入文件路径: (测试路径：test.java)");
        String filePath = sc.nextLine();
        int num = 0;
        try{
            num = countEffectiveLine(filePath);
        }catch(IOException e){      //IOException异常处理
            e.printStackTrace();
        }
        System.out.println("有效行数："+ num);
    }

    public static int countEffectiveLine(String file) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = null;
        int effectiveNum = 0,nodeNum = 0, blankNum = 0;
        boolean nodeFlag = false;

        while((line = br.readLine()) != null){
            if((line.matches("\\s*/\\*.*") && line.matches(".*\\*/\\s*")) || line.matches("\\s*//.*"))  //单行注释
                ++nodeNum;
            else if(line.matches("\\s*/\\*.*")){     //多行注释开始
                ++nodeNum;
                nodeFlag = true;
            }
            else if(line.matches(".*\\*/\\s*")){    //多行注释结束
                ++nodeNum;
                nodeFlag = false;
            }
            else if(line.matches("\\s*"))     //空行
                ++blankNum;
            else if(nodeFlag)  //多行注释中间行
                ++nodeNum;
            else
                ++effectiveNum;     //有效行
        }
        return effectiveNum;
    }
}
