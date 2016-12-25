import java.io.*;

/**
 * Created by zhuyin on 16-11-15.
 */
public class Main {
    public static void main(String args[]){
        String path = "EffectiveLines/resource/test.txt";
        File file = new File(path);
        boolean vaild = checkPath(file);
        if(!vaild)
            System.out.println("file is not exit");
        else
            //read
            System.out.println(countLines(file));
    }
    //check file
    private static boolean checkPath(File file){
        if(file.exists())
            return true;
        else
            return false;
    }

    //count lines
    private static int countLines(File file){
        BufferedReader reader = null;
        int lineNum = 0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            try {
                while((line=reader.readLine())!=null){
                    if(lineIsValid(line))
                        lineNum++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return lineNum;
    }

    private static boolean lineIsValid(String line){
        line = line.trim();
        if("".equals(line)||line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("/*")))
            return false;
        else
            return true;
    }
}
