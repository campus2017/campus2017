import java.io.IOException;
import java.util.Scanner;

/**
 * Created by asus on 2017/1/17.
 */
public class Main {
    public static void main(String []args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入项目目录(统计import前10的类):");
        String filepath =scanner.nextLine();

        FindFile demo = new FindFile();
        demo.findFile(filepath);

        Top10Sort tp = new Top10Sort();
        tp.sort(demo.fileList);
    }
}

