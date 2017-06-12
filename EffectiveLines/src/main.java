import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by apple on 17/6/8.
 */
public class main{
public static void main(String args[])
{
    String fileName=null;
    int result=0;
    Scanner  input=new Scanner(System.in);
    fileName=input.nextLine();
    try {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        fr = new FileReader(fileName);
        br = new BufferedReader(fr);

        String s=br.readLine();
        while (s!=null)
        {
            if(!s.equals(""))
                result++;
            s=br.readLine();

        }
        System.out.println("file "+fileName+" EffectiveLines are ："+result);

    } catch (FileNotFoundException e) {
        System.out.println("cannot open "+fileName);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}
class readFile
{

    public  readFile(String fileName)
    {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            fr = new FileReader(fileName);
            br = new BufferedReader(fr);

            String s=br.readLine();
            while (s!=null)
            {
                s=br.readLine();

            }

        } catch (FileNotFoundException e) {
            System.out.println("cannot open"+fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}