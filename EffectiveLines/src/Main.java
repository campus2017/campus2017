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
        FileReader fileReader = null;
        BufferedReader bufferReader = null;
        int count =0;
        try {
            fileReader = new FileReader(path);
            bufferReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferReader.readLine())!=null){
                line = line.trim();
                if(!line.isEmpty()&&!isAnnotation(line)){
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                bufferReader.close();
                fileReader.close();
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
