package homework.EffectiveLines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class EffectiveLines {

    public static void main(String[] args) {
        FileReader fileReader = null;
        BufferedReader reader = null;
        int count = 0;
        try {
            fileReader = new FileReader(args[0]);
            reader = new BufferedReader(fileReader);
            while (true){
                String line = reader.readLine();
                if(line == null){
                    break;
                }
                if("".equals(line.trim()) || line.startsWith("//")){
                    continue;
                }
                count++;

            }
        } catch (Exception e) {
            System.out.println("文件读取错误");
            e.printStackTrace();
        }  finally {
            try {
                fileReader.close();
                reader.close();
            } catch (IOException e) {
                System.out.println("关闭输入流出错");
                e.printStackTrace();
            }
        }

        System.out.println(count);
    }
}
