import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/**
 * function:counting the sum of effective rows.
 * editor:Wang Yishu
 * Created by Administrator on 2016/11/16.
 */
public class Main {
    static int codeLine = 0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.err.println("请输入要处理的java文件路径");
        Path path=Paths.get(sc.nextLine());
        if(!path.isAbsolute())
            path.toAbsolutePath();
        if (!Files.exists(path) || !path.toString().endsWith(".java")) {
            System.err.println("请输入正确的java文件路径");
            System.exit(-1);
        }
        countFileLine(path);//读取文件信息并进行计算有效行数
        System.out.println("文件"+path.getFileName()+"有效行数=" + codeLine);
    }

    public static void countFileLine(Path path) {
        try {
            FileReader fr = new FileReader(path.toString());
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\\s", "");// \\s表示 空格,回车,换行等空白符,
                // 将空白符替换为空字符""

                if ("".equals(line)
                        || line.startsWith("//")
                        || line.startsWith("/*")
                        || line.startsWith("/**")
                        || line.startsWith("*")) {//过滤掉注释
                } else {
                    ++codeLine;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

