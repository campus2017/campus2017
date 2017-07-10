

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by qunar on 17-7-6.
 */


import java.io.*;

public class MainTest {

    public static void  main(String[] args) throws IOException
    {

        // create handle calss.
        EffectiveLineCount ELC = new EffectiveLineCount();

        // run the main function .
        int effectiveLineNum = ELC.run();

        //
        System.out.print(effectiveLineNum);

    };


}
