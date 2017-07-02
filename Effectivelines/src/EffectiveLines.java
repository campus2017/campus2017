import java.io.*;
/**
 * Created by 忘忧草 on 2017/7/2.
 */
public class EffectiveLines {
    public static void main(String args[]) throws IOException
    {
        String path = "E:\\java_project\\EffectiveLines\\src\\test.java" ;// 定义文件路径
        FileReader fr = new FileReader(path);   //获取文件指针
        BufferedReader br = new BufferedReader(fr);
        int x = 0;   // 用于统计行数，从0开始
        String lineInfo;
        while(br.ready()) //判断是否读取完成
        {
            lineInfo = br.readLine().trim();
            if(lineInfo.length()==0)
            {
                continue;
            }
            if (lineInfo.startsWith("//") || (lineInfo.startsWith("/*")&&lineInfo.endsWith("*/")))
            {
                continue;
            }

            x++;   // 每读一行，则变量x累加1
        }
        System.out.print(x);
    }
}
