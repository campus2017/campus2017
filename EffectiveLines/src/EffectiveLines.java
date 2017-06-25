import java.io.*;

/**
 * Created by Lee on 2017/6/25.
 */
public class EffectiveLines {

    static int cntCode=0, cntNode=0, cntSpace=0;
    static boolean flagNode = false;


    public static void main(String[] args){
        File directory = new File("");//设定为当前文件夹
        BufferedReader br = null;
        try {
            System.out.println(directory.getCanonicalPath());
            br = new BufferedReader(new FileReader(directory.getCanonicalPath()+"\\EffectiveLines\\src\\EffectiveLines.java"));
            String line=null;
            while((line = br.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("有效代码行数： " + cntCode);
    }
    private static void pattern(String line) {
        String regxNodeBegin = "\\s*/\\*.*";
        String regxNodeEnd = ".*\\*/\\s*";
        String regx = "//.*";
        String regxSpace = "\\s*";
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
        } else if(line.matches(regxSpace))
            ++cntSpace;
        else if(line.matches(regx))
            ++cntNode;
        else if(flagNode)
            ++cntNode;
        else ++cntCode;
    }


}
