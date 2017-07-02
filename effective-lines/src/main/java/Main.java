import java.io.*;
import java.util.Scanner;

/**
 * Created by kefa.zhang on 2017/6/12.
 */
public class Main {
    public static void main(String[] args) {
        int lines = 0;
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        File file = new File(path);
        if (!file.isFile()) {
            System.out.println("Wrong path!");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){ // try-with-resource
            String line = reader.readLine();
            while (line != null) {
                line = line.trim();
                if (line.length() > 0 && !line.startsWith("/*") && !line.startsWith("//")) { // 不考虑跨行注释的情况
                    lines++;
                }
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(lines);
    }
}
