import util.GetLinesNum;
import util.IsBlockLine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Administrator on 2016/11/14.
 */
public class LinesTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = scanner.next();

        int num = GetLinesNum.getLinesNum(filename);

        System.out.println("Effective Lines Num:" + num);
    }

}
