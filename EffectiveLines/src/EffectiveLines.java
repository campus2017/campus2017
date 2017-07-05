import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by fmz on 2017/6/20.
 */
public class EffectiveLines {
    public static void main(String[] args) {
        System.out.println("Input a file name:");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();

        if(!isLegal(fileName)) {
            System.out.println("File name is ilegal!");
            return;
        }

        BufferedReader reader = null;
        String line = null;
        int count = 0;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            //统计有效行
            while((line = reader.readLine()) != null) {
                if(line.trim().isEmpty() || line==null) {
                    continue;
                }

                if(isAnnotation(line)) {
                    continue;
                }

                count++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        //输出结果
        System.out.println("Valid line: " + (count-1));
    }

    //判断是否是注释行
    public static boolean isAnnotation(String line) {
        String trimmed = line.trim();
        if(trimmed.startsWith("//") || trimmed.startsWith("/*") || trimmed.endsWith("*/")) {
            return true;
        }

        return false;
    }

    //判断文件名是否合法
    public static boolean isLegal(String fileName) {
        if(!fileName.endsWith(".java")) {
            return false;
        }

        return true;
    }
}
