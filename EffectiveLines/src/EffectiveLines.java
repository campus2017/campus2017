import java.io.*;

/**
 * Created by Administrator on 2017/5/30.
 */

public class EffectiveLines {
    public static void main(String[] args) {
        //根据自己需要改变测试文件的路径
        String fileName="F:\\去哪网作业\\CountMostImport\\src\\CountMostImport.java";
        EffectiveLines1 e1=new EffectiveLines1(fileName);
        e1.processFile(fileName);
    }
}
class EffectiveLines1{
    String fileName;
    static int cntCode=0, cntNode=0, cntSpace=0;
    static boolean flagNode = false;
    public EffectiveLines1(String fileName){
        this.fileName=fileName;
    }
    /*
    统计有效行数
     */
    public void lineCount(String line) {
        line=line.trim();
        String regxNodeBegin = "\\s*/\\*.*";
        String regxNodeEnd = ".*\\*/\\s*";
        String regx = "\\s*//.*";
        String regxSpace = "\\s*";
        if(line.matches(regxNodeBegin) && line.matches(regxNodeEnd)){
            ++cntNode;
        }
        if(line.matches(regxNodeBegin)){
            ++cntNode;
            flagNode = true;
        } else if(line.matches(regxNodeEnd)){
            ++cntNode;
            flagNode = false;
        }
        else if(line.matches(regx))
            ++cntNode;
        else if(flagNode)
            ++cntNode;
        else if(line.matches(regxSpace))
            ++cntSpace;
        else ++cntCode;
    }
    public  void processFile(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line=null;
            while((line = br.readLine()) != null)
                lineCount(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("代码有效行： " + cntCode);
    }

}



