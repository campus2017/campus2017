import java.io.*;

/**
 * Created by yaoguoli on 2017/6/1.
 */
public class EffectiveLines {
    static int cntCode = 0;
    static boolean flagCode;

    public static void main(String[] args) throws IOException {

        //以字节读取文件，文件路径用File.separator适用多系统
        FileInputStream fis = new FileInputStream("E:" + File.separator
                + "QunarHomework" + File.separator + "EffctiveLines"
                + File.separator + "Test" + File.separator + "TestFile.java");

        //用带有缓冲的BufferedReader从字符输入流中读取文本（高效读取）
        //InputStreamReader将字节流转化为字符流
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (isEffective(line))
                cntCode++;
        }

        //关闭打开的文件流
        br.close();
        fis.close();
        System.out.println("有效代码行：" + cntCode);
    }

    //判断选取行line是否是代码行，返回判断结果
    private static boolean isEffective(String line) {
        flagCode = false;
        line = line.trim();    //去除每行起始处和结尾处的空格
        if (line.startsWith("//")   //以"//"开头的注释行
                || (line.startsWith("/*") && line.endsWith("*/"))    //用"/*  */"的注释行
                || line.equals(""))    //空格
            ;
        else
            flagCode = true;
        return flagCode;
    }
}
