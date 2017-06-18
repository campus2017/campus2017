import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by xiazihao on 2017/5/22.
 */
public class Main {
    private Map<String, Integer> importMap = new HashMap<>();

    public static void main(String[] argv) {
        System.out.println("input path");
        Scanner scanner = new Scanner(System.in);

        String dirPath = scanner.nextLine();
        Main main = new Main();
        main.getFile(dirPath);
//        System.out.println(dirPath);
    }

    public void getFile(String dir) {
        if (dir == null) {
            return;
        }
        File file = new File(dir);
        if (!file.exists()) {
            System.out.println("File not found");
            return;
        }
        Stack<File> stack = new Stack<>();
        stack.push(file);
        while (!stack.empty()) {
            file = stack.peek();
            stack.pop();
            for (File fi : file.listFiles()) {
                if (fi.isFile()) {
                    count(fi);
                } else if (fi.isDirectory()) {
                    stack.push(fi);
                }

            }
        }
    }

    public void count(File file) {
        if (file.exists() && file.getName().endsWith(".java")) {
            System.out.println(file.getName());
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
