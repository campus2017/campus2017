import java.io.*;

import static java.lang.System.*;

/**
 * Created by root on 6/7/17.
 */
public class EffectivLInes
{
    static public void main(String args[])
    {
        if(args.length != 1)
        {
            out.print("require path\r\n");
            return;
        }
        String fileName = args[0];
        try
        {
            int count = 0;
            String tmp = null;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader((new FileInputStream(fileName))));
            while((tmp = reader.readLine()) != null)
            {
                if(!tmp.equals(""))
                    count++;
            }
            out.print(count);
            out.print("\r\n");
        }
        catch (Exception e)
        {
            out.print("file not found");
        }

    }
}
