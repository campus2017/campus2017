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
            System.out.println("File name is illegal");
            return;
        }
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("This file is not exist");
            return;
        }
        int count = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            do {
                String buff = bufferedReader.readLine();
                if (buff == null) {
                    break;
                }
                if (buff.startsWith("//")) {
                    continue;
                }
                if(buff.trim().isEmpty()){
                    continue;
                }
                if (buff.startsWith("/*")) {
                    do {
                        buff = bufferedReader.readLine();
                        if(buff.endsWith("*/")){
                            break;
                        }
                    }while (true);
                    continue;
                }
                count++;
                System.out.println(buff);
            } while (true);
            System.out.println(count);
        } catch (FileNotFoundException e) {

        } catch (IOException ioe) {

        }

    }

    public static boolean isLegal(String fileName) {
        if (!fileName.endsWith(".java")) {
            return false;
        }
        return true;
    }
}
