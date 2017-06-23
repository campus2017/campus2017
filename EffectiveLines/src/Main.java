//有效行即为除去空行以及注释行的代码行，在本次作业中不考虑多行注释问题
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String fileName = ".\\src\\Main.java";
        readFile(fileName);
    }

    //按行读取文件
    private static void readFile(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String context = null;
            int line = 0;
            while ((context = reader.readLine()) != null) {
                if (isEffective(context)) {
                    line++;
                }
            }
            System.out.println("There is " + line + " effective lines.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
        }
    }

    //判断改行是否为有效行
    private static boolean isEffective(String context) {
        context = context.trim();//去除空格
        if (context.length() == 0) {//空行
            return false;
        } else if (context.length() > 0 && (context.charAt(0) == '/' && (context.charAt(1) == '/' || context.charAt(1) == '*'))) { //注释行
            return false;
        } else {
            return true;
        }
    }
}