import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
/**
 * Created by woo on 1/4.
 */
public class EffectiveLines {
    public static void getFileLines(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            int cnt = 0;
            while ((line = reader.readLine()) != null) {
                if(isValidLine(line)){
                    ++ cnt;
                }
            }
            System.out.println("The count is "+ cnt);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
}
    private static boolean isExist(File file){
        if(file.exists())
            return true;
        else
            return false;
    }
    private static boolean isValidLine(String line){
        line = line.trim();// Removes all leading and trailing white-space characters from the current String object.
        if("".equals(line)||line.startsWith("//")||(line.startsWith("/*")&&line.endsWith("*/")))
            return false;
        else
            return true;
    }
    public static void main(String[] args){
        System.out.println("Plz input the filename: ");
        String fileName = null;
        Scanner input = new Scanner(System.in);
        fileName = input.nextLine();
        File file = new File(fileName);
        boolean vaild = isExist(file);
        if(false == vaild){
            System.out.println(fileName + " is not exist!");
            return;
        }
        getFileLines(file);
    }
}
