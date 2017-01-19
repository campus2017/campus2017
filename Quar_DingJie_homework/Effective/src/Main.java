
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 统计一个java文件的有效行数
 */

public class Main {

    static int cntCode=0, cntNode=0, cntSpace=0;
    static boolean flagNode = false;

    public static void main(String[] args) {

        BufferedReader br = null;
        try {
            //br = new BufferedReader(new FileReader("TestFile.java"));
        	br=new BufferedReader(new FileReader("test.txt"));
        	// br=new BufferedReader(new FileReader("Calculate.java"));
            String line=null;
            while((line = br.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println("总行： " + (cntNode+cntSpace+cntCode));
        System.out.println("注释行： " + cntNode);
        System.out.println("空行： " + cntSpace);
        System.out.println("代码行： " + cntCode);


    }

    private static void pattern(String line) {

        String regxNodeBegin = "\\s*/\\*.*";   //正则表达式
        String regxNodeEnd = ".*\\*/\\s*";
        String regx = "//.*";
        String regxSpace = "\\s*";
        //统计以"/*"开头的注释行
        if(line.matches(regxNodeBegin) && line.matches(regxNodeEnd)){
            ++cntNode;
            return ;
        }
        if(line.matches(regxNodeBegin)){
            ++cntNode;
            flagNode = true;
        } else if(line.matches(regxNodeEnd)){
            ++cntNode;
            flagNode = false;
        } else if(line.matches(regxSpace))   //统计空行
            ++cntSpace;
        else if(line.matches(regx))      //统计以"//"开头的注释行
            ++cntNode;
        else if(flagNode)
            ++cntNode;
        else ++cntCode;               //统计代码行
    }
}
