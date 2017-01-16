import java.io.*;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/1/7 0007.
 */
public class Main {
    public static void main(String[] args){
        String filePath = "";
        System.out.println("请输入要统计的文件路径");
        Scanner in = new Scanner(System.in);
        filePath = in.nextLine();
        //filePath = "E:/QunarTest/EffectiveLines/test/Calculate.java";
        File file = new File(filePath);
        if(file.exists()){
            try {

                System.out.println("该文件有效行数为：" + count(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("can't find the file!");
        }
    }
    //有效行计数
    private static int count(File file) throws IOException {
        int lineCount = 0;
        BufferedReader br = null;
        br = new BufferedReader(new FileReader(file));
        String line = null;
        try {
            while((line = br.readLine()) != null){
                if(isValid(line)){
                    lineCount++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return  lineCount;

    }
    //判断是否是有效行
    private static boolean isValid(String line){
        line = line.trim();
        if("".equals(line)||line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("*/"))){
            return false;
        } else {
            return true;
        }
    }
}
