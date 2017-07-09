package controller;

import java.io.*;

/**
 * Created by Vsur-Pc on 2017/7/3.
 */
public class EffectiveLines {

    static int cntCode=0;
    static boolean flagNode = false;
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("TestFile.java"));
            String line=null;
            while((line = br.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("有效代码行： " + cntCode);

    }

    private static void pattern(String line) {
        // TODO Auto-generated method stub
        String regxNodeBegin = "\\s*/\\*.*";
        String regxNodeEnd = ".*\\*/\\s*";
        String regx = "//.*";
        String regxSpace = "\\s*";
        if((line.matches(regxNodeBegin) && line.matches(regxNodeEnd))||
                (line.matches(regx)) || line.matches(regxSpace)){
            return ;
        }
        else  ++cntCode;
    }
}