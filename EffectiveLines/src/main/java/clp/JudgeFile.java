package clp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * Created by Asus on 2016/12/7.
 */
public class JudgeFile {
    public static boolean judgeFileName(String filenName){
        if(filenName.endsWith(".java")){
            return true;
        }
        return false;
    }
    public static int effectiveLines(String fileName)throws Exception{
        int lines=0;
        BufferedReader br=null;
        try{
            br=new BufferedReader(new FileReader(fileName));
            String line=null;
            while((line=br.readLine())!=null) {
                if (line.length() != 0 && !line.startsWith("//")) {
                    lines++;
                }
            }
        }catch(FileNotFoundException e){
            throw new FileNotFoundException();
        }catch(IOException e){
           throw new IOException();
        }
        return lines;
    }
}
