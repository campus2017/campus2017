/**
 * Created by Administrator on 2017/02/21.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class effectiveLines {
    static int cntLines=0;
   // static int positon=0;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("Main.java"));
            String line=null;
            while((line = br.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("代码有效行： " + cntLines);
    }

   ////////******* 注释 *********//////////
    /////////////注释
    private static void pattern(String line) {
        // TODO Auto-generated method stub
        String regxNodeBegin = "\\s*//*\\*.*"; //  \s表示空格等      \\* 表示字符*   .表示任意字符
        String regxNodeEnd = ".*\\*//*\\s*";
        String regx = "\\s*///*.*";
        String regxSpace = "\\s*";
        positon++;
        if(line.matches(regxNodeBegin) && line.matches(regxNodeEnd))  //主要表示 /*  */这种注释
            return ;

        if(line.matches(regx))   //主要表示 // 这种注释
            return;

        if(line.matches(regxSpace))  //表示空行
            return;
       // System.out.println("代码有效行位置： " + positon);
        cntLines++;
    }
}
