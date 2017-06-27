import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 输入文本名称
        Scanner input = new Scanner(System.in);
        String fileName = input.nextLine();

        // 构建文本统计对象
        MyText text = new MyText(fileName);
        System.out.println(text._textLine);
    }
}
