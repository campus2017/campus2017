package qunar.pre.commission;

import java.util.Scanner;

/**
 * Created by hughgilbert on 2017/5/12.
 */
public class Main {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("please input the file with file Path (input quit if you want to stop): ");
        String fileName = in.next();
        try {
            Crawler spider = new Crawler(fileName);
            spider.createExcel();
        }
        catch(IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println("Cause :" +  e.getMessage());
        }
    }
}
