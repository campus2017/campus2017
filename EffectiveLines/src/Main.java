import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        while (true) {
            //循环扫描控制台
            System.out.print("请输入.java文件的相对路径或绝对路径：");
            Scanner scan = new Scanner(System.in);
            if (scan.hasNextLine()) {
                int legalLine = 0;
                String filePath = scan.nextLine();
                File file = new File(filePath);
                if (!file.exists()) {
                    System.out.print("该路径不存在，请重新输入路径：");
                } else {
                    if (file.isFile()) {
                        String name = file.getName();
                        int index = name.lastIndexOf(".");
                        String suffix = name.substring(index + 1, name.length());
                        if (!suffix.equals("java")) {
                            System.out.println("该路径不是java文件，请重新输入路径：");
                        } else {
                            BufferedReader reader = null;
                            try {
                                reader = new BufferedReader(new FileReader(file));
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            String lineContent;
                            try {
                                while ((lineContent = reader.readLine()) != null) {
                                    lineContent = lineContent.trim();
                                    if (!lineContent.isEmpty() && !lineContent.matches("^//(.*)")) {
                                        legalLine++;
                                    }
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        System.out.print("该路径不是文件，请重新输入路径：");
                    }
                }
                System.out.println("该文件的有效行数为：" + legalLine + "行！");
            }
        }
    }
}
