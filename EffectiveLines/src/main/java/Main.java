import java.io.*;
import java.util.Scanner;

/**
 * Created by 志雄 on 2017/1/18.
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Java文件地址：");
        Scanner in = new Scanner(System.in);
        String dir = in.nextLine();
        if(!isEndWithSuffix(dir, "java")){
            System.out.println("it's not a java file");
            return;
        }
        File file = new File(dir);
        if(!isExist(file)){
            System.out.println("not exist");
            return;
        }
        long startTime = System.currentTimeMillis();
        System.out.println(countlines(file));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");
        in.close();
    }

    // 判断文件是否存在
    public static boolean isExist(File file){
        if(file==null)
            return false;
        return file.exists();
    }

    // 判断后缀名
    public static boolean isEndWithSuffix(String filename, String suffix){
        if(filename == null || suffix == null)
            return false;
        return filename.endsWith(suffix);
    }

    // 清点行数
    public static int countlines(File file){
        if(file == null)
            return 0;
        int count = 0;
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                while((line = br.readLine()) != null){
                    if(isValidLine(line))
                        ++count;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }
    // 有效行判断
    public static boolean isValidLine(String line){
        line.trim();
        if(line.equals("")||line.startsWith("//")||line.startsWith("/*"))
            return false;
        return true;
    }

}
