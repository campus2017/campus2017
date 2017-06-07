package qunar.pre.commission;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hughgilbert on 03/06/2017.
 */
public class Main {
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        ImportCount counter = new ImportCount();
        while(true)
        {
            try {
                System.out.println("please input the file directory: ");
                String directory = userInput.next();
                List<Pair> result = counter.start(directory);
                System.out.println("the 10 top class or interface is: ");
                for(Pair pair : result){
                    System.out.println(pair);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
