/**
 * Created by dxq on 2017/6/20.
 */
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.Scanner;

public class EffectiveLines {

    /**
     * @param args
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
           //计算有效代码的行数
            int linesNum = 0;
            BufferedReader br = null;
            String lineBegin = "\\s*/\\*.*";   //多个空格加/*开头的行
            String lineEnd = ".*\\*/\\s*";     //多个空格加*/结尾的行
            String annotationLines = "//.*";   //  //注释的行
            String spaceLines = "\\s*";        //  空白行
            try {
                br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null)
                    //采用正则表达式判断是否为有效行
                    if (!(line.matches(lineBegin) && line.matches(lineEnd)) && !(line.matches(lineBegin)) && !(line.matches(lineEnd)) && !(line.matches(spaceLines)) && !(line.matches(annotationLines)))
                        ++linesNum;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("有效行数：" + linesNum);
        } else {
            System.out.println("文件不存在");
        }
        scanner.close();
    }


}