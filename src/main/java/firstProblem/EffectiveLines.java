package firstProblem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*
1 有效不包括空行
2 不考虑代码中有多行注释的情况
*/

public class EffectiveLines {

    /**
     *
     * @param str : input line
     * @return true: is effective line; false : is not effective line.
     */
    public boolean isEffective(String str){
        if(str==null || str.length()==0)
            return false;
        else if(str.startsWith("//") || str.startsWith("/*") && str.endsWith("*/"))
            return false;
        else
            return true;
    }

    /**
     *
     * @param filename: the file name string
     * @return the number of effective lines in the file
     */
    public int countEffectiveLines(String filename){
       int result = 0;
       try{
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String str = br.readLine();
            while(str != null){
                result += isEffective(str) ? 1 : 0;
                str = br.readLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args){
        EffectiveLines EL = new EffectiveLines();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            System.out.println("ddd");
            String filename = sc.nextLine();
            int result = EL.countEffectiveLines(filename);
            System.out.println("Effective lines is :" + result);
        }
    }
}
