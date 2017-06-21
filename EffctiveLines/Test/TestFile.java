import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

  /* Created by yaoguoli on 2017/3/1. */


public class TestFile {
    //以字节为单位读取文件，常用语读取二进制文件
    public static void readFileByBytes(String fileName) {      
        File file = new File(fileName);
        InputStream in = null;
        try {
            System.out.println("以字节为单位读取文件内容，一次读一个字节：");
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
}