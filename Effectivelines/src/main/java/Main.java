import java.io.IOException;
import java.util.Scanner;

/**
 * Created by hughgilbert on 2017/5/9.
 */
public class Main {
    public static void main(String[] args)
    {
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("please input the file (input quit if you want to stop): ");
            String fileName = in.next();

            if(fileName.equals("quit")) break;

            try {
                Effectivelines counter = new Effectivelines(fileName);
                int result = counter.getEffectiveLinesOftheFile();
                System.out.println("this file's effective lines is : " + result);
                counter.show();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
