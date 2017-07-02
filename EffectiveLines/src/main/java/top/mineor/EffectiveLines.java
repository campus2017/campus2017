package top.mineor;

import java.io.*;
import java.util.Scanner;

/**
 * 统计一个Java文件的有效行数
 * Created by Mineor on 2017/6/22.
 */

public class EffectiveLines {

    public static int countEffectiveLines(File objectFile) throws IOException{

        if(!objectFile.exists()){
            System.out.println("目标文件不存在");
            return 0;
        }
        int count = 0;
        BufferedReader reader;
        reader = new BufferedReader(new FileReader(objectFile));
        String line;
        while ((line = reader.readLine()) != null) {
            String temp = line.trim();
            if (temp.length()>0 && !temp.startsWith("//"))
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String fileName = scanner.nextLine();
            File objectFile = new File(fileName);
            try {
                int res = countEffectiveLines(objectFile);
                System.out.println("此文件有效行数为:"+res+"行");
            }catch (Exception e){
                return;
            }
        }
    }

}