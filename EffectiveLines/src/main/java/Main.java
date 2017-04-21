import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import com.google.common.base.Strings;
/**
 * Created by asus on 2017/4/21.
 */
public class Main {

    public static void main(String []args){
        System.out.println("请输入文件路径：");

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();

        File file = new File(path);
        if (!file.exists()) {
            System.out.println("文件不存在！");
            return;
        }else {
            try {
                System.out.println("该java文件有效代码行数:"+count(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static int count(String filepath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line = null;
        int count =0;
        while ((line=reader.readLine())!=null){
            line = line.trim();
            if (Strings.isNullOrEmpty(line)){
                continue;
            }else if((line.startsWith("/*")&&line.endsWith("*/"))){
                continue;
            }
            count++;
        }
        reader.close();
        return count;
    }

}
