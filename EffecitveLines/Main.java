import java.io.*;
import java.util.Scanner;

/**
 * Created by xiazihao on 2017/5/6.
 */
public class Main {
    public static void main(String[] argv) {
        System.out.println("Input file name");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        if (!isLegal(fileName)) {
            return;
        }
        File file = new File(fileName);
        if(!file.exists()){
            return;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            System.out.println(bufferedReader.readLine());
        }
        catch (FileNotFoundException e){

        }
        catch (IOException ioe){

        }

    }

    public static boolean isLegal(String fileName) {
        if (!fileName.endsWith(".java")) {
            return false;
        }
        return true;
    }
}
