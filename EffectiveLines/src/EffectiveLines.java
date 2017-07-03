/**
 * Created by mml on 17-7-3.
 */
import java.io.*;

public class EffectiveLines{
    static int cntCode=0, cntNode=0, cntSpace=0;
    static boolean flagNode = false;
    static String regxNodeBegin = "\\s*/\\*.*";
    static String regxNodeEnd = ".*\\*/\\s*";
    static String regxNodeEndAndCode = ".*\\*/.*";
    static String regxNodeBeginAndEndAndCode = "\\s*/\\*.*\\*/.*";
    static String regxNodeBeginAndEndAndNode = "\\s*(/\\*.*\\*/)+//.*";
    static String regx = "\\s*//.*";//纯注释行
    static String regxSpace = "\\s*";//全空格

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("helloworld.java"));
            String line=null;
            while((line = br.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("有效行数： " + cntCode);


    }

    private static void pattern(String line) {
        if(flagNode)// 该行是否处于连续注释范围内
        {
            if (line.matches(regxNodeEnd)) flagNode = false;
            return;
        }
        if(line.matches(regx)||line.matches(regxSpace))return;
        //已经排除完所有的纯注释行或空白行

        if(line.matches(regxNodeBegin))//      /* 行或者*/行
        {
            flagNode = true;
            if (line.matches(regxNodeEnd)) flagNode = false;
            return;
        }
        ++cntCode;
    }

}




