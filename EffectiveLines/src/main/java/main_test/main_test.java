package main_test;

import java.io.IOException;

import static com.qunar.common.EffectiveLines.getSimpleCommentLines;

/**
 * Created by gcy0904 on 2017/1/20.
 */
public class main_test {
    public static void main(String[] args) {
        String FilePath = "E:\\qunar_test\\EffectiveLines\\src\\main\\resources\\main.java";
        int line = 0;
        try {
            line = getSimpleCommentLines(FilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println();
        System.out.println(FilePath + "  has " + line + " lines ");
    }
}
