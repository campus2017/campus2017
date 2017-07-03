import java.io.BufferedReader;
import java.io.FileReader;


/**
 * Created by liuliling on 2016/11/12.
 */
public class EffectiveLines {
    public static void main(String[] args)
    {
        try {
            int lineNumber =0;
            BufferedReader in = new BufferedReader(new FileReader("./src/EffectiveLines.java"));//读取文件
            String str;
            while((str=in.readLine())!=null)
            {
                if(!str.trim().equals("")&&!str.trim().matches("^//.*|^/*.*$*/"))//不考虑空格和单行注释
                {
                    lineNumber++;
                }
            }
            System.out.println("文件的有效行数为"+lineNumber);
        } catch (java.io.IOException e) {
            System.out.println("发生错误，错误信息为："+e.toString().split(":")[1]);
        }
    }
}



//输出结果为：文件的有效行数为24