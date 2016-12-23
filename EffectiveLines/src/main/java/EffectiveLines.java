import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by DW on 2016/12/13.
 */
public class EffectiveLines {
    //获取有效行数
    private static int getEffectiveLines(String path){
        if(path==null||path.equals("")){
            return 0;
        }
        File file = new File(path);     //根据路径取得文件对象
        int count = 0;      //有效行数
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while(line!=null){
                if(line.trim().equals("")||line.trim().matches("^//.*")){
                    line = br.readLine();
                }
                else {
                    ++count;
                    line = br.readLine();
                }
            }
        } catch (IOException e) {
            System.out.println("输入路径有误！");
            System.exit(-1);
        }
        return count;
    }

    public static void main(String[] args){
        System.out.println("请输入java文件路径：");
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        if(path==null||path.trim().equals("")){
            System.out.println("输入有误！");
            System.exit(-1);
        }
        int count = getEffectiveLines(path);
        System.out.println("有效行数为"+count+"行");
    }
}
