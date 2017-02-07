import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/* 统计一个 Java 文件的有效行数 */
public class Main {
    public static void main(String[] args) {
        if(args == null){
            return;
        }
        System.out.println(getEffectiveLines(args[0]));
    }
    //得到有效行数
    public static int getEffectiveLines(String path){
        FileReader file_reader = null;
        BufferedReader buffer_reader = null;
        int count =0;
        try {
            file_reader = new FileReader(path);
            buffer_reader = new BufferedReader(file_reader);
            String line = null;
            while((line = buffer_reader.readLine())!=null){
                line = line.trim();
                if(!line.isEmpty()&&!isAnnotation(line)){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                buffer_reader.close();
                file_reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return count;
    }
    //判断该行是否是注释
    public static boolean isAnnotation(String line){
        if (line == null || line.length() == 0)
            return false;
        if (line.startsWith("@") || line.startsWith("//") || line.startsWith("/*") || line.endsWith("*/"))
            return true;
        return false;
    }

}
