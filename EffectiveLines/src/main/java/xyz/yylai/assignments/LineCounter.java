package xyz.yylai.assignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LineCounter {
    private enum Status{BLANK, EFFECTIVE, SINGLECOMMENT, MULTICOMMENT}
    public static int countLines(InputStream is) throws IOException{

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        Status status = Status.BLANK;
        int currentCharacter = -1;
        int lastCharacter = -1;
        int lineCount = 0;
        while((currentCharacter = reader.read())!=-1){
            switch (currentCharacter){
                case ' ':
                    break;
                case '\n':
                    if(status!=Status.MULTICOMMENT){
                        status = Status.BLANK;
                    }
                    break;
                case '/':
                    if(status==Status.BLANK){
                        status = Status.EFFECTIVE;
                    }else if(status==Status.EFFECTIVE&&lastCharacter=='/'){
                        status = Status.SINGLECOMMENT;
                    }else if(status==Status.MULTICOMMENT&&lastCharacter=='*'){
                        status = Status.BLANK;
                    }
                    break;
                case '*':
                    if((status==Status.BLANK||status==Status.EFFECTIVE)&&lastCharacter=='/'){
                        status =  Status.MULTICOMMENT;
                    }else if(status==Status.BLANK){
                        status = Status.EFFECTIVE;
                        lineCount++;
                    }
                    break;
                default:
                    if(status==Status.BLANK){
                        status = Status.EFFECTIVE;
                        lineCount++;
                    }
                    break;
            }
            lastCharacter = currentCharacter;
        }
        if(status==Status.EFFECTIVE){
            lineCount++;
        }
        return lineCount;
    }
}
