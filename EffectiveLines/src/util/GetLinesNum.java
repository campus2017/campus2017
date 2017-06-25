package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2016/11/14.
 */
public class GetLinesNum {

    public static int getLinesNum(String filename)  {
        int effLinesCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String s ;
            while ((s = br.readLine()) != null){
                if (IsBlockLine.isBlockLine(s)) {
                    effLinesCount++;
                    System.out.println(s);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return effLinesCount;
    }

}
