import java.util.Scanner;

/**
 * Created by KevinZhang on 2017/6/26.
 */
public class TestCharRange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true){
            char c = sc.nextLine().toCharArray()[0];
            boolean b = c >= 19968 && c <= 40869;
            boolean f = c >= 0x4e00 && c <= 0x9fa5;
            System.out.println(f);
        }
    }
}
