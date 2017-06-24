/**
 * Created by steamedfish on 17-6-22.
 */
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class EffectiveLines {
    static long codeLine = 0;
    static long annotationLine=0;
    static long blankLine=0;

    public static void  main(String[] args){
        System.out.println("Please Enter the name of java file or directory!");
        Scanner in=new Scanner(System.in);
        String filePath = in.nextLine();

        File file = new File(filePath);

        try {
            codeStat(file);
        }catch(FileNotFoundException e)
        {

        }

        System.out.println(file+" has effective lines "+codeLine);
    }

    private static void codeStat(File file)throws FileNotFoundException{
        if(file==null || !file.exists())
            throw new FileNotFoundException(file+",file not exists");
        if(file.isDirectory()){
            File[] files=file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".java")||pathname.isDirectory();
                }
            });
            //search all files
            for(File target : files){
                codeStat(target);
            }
        }
        else{
            BufferedReader buff = null;
            try{
                //bond the stream
                buff = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            }catch(FileNotFoundException e){
                throw new FileNotFoundException(file+"not exists!"+e);
            }
            //define regular
            Pattern annotationLinePattern = Pattern.compile("((//)|(/\\*+)|((^\\s)*\\*)|" +
                    "((^\\s)*\\*+/))+",Pattern.DOTALL);//pattern annotation
            Pattern blankLinePattern = Pattern.compile("^\\s*$");//pattern blanks;
            Pattern codeLinePattern = Pattern.compile("(?!import|package).+;" +
                            "\\s*(((//)|(/\\*+)).*)*",
                    Pattern.MULTILINE+Pattern.DOTALL);//pattern the ;except import;

            //traverse every line
            String line=null;
            try{
                while((line = buff.readLine())!=null){
                    if(annotationLinePattern.matcher(line).find()){
                        annotationLine++;
                    }
                    if(blankLinePattern.matcher(line).find()){
                        blankLine++;
                    }
                    if(codeLinePattern.matcher(line).find()){
                        codeLine++;
                    }
                }
            }catch (IOException e){
                throw new RuntimeException("failed read the file"+e);
            }finally {
                try{
                    buff.close();
                }catch (IOException e){
                    throw new RuntimeException("failed close the input stream!");
                }
            }

        }
    }
}
