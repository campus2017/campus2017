package wz;

import java.io.*;
import java.util.Scanner;

/**
 * EffectiveLines 统计一个java文件的有效行数
 *
 * @author wz
 * @date 16/11/15 14:13
 */
public class EffectiveLines {


    /**
     * 统计java文件中的有效行(不计单行注释)
     * @param javaFile 被统计的文件
     * @return  有效行数
     */
    public int countEffectiveLines(File javaFile) {
        if (!javaFile.exists())
            return 0;
        int count = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(javaFile));
            String line,temp;
            while ((line = br.readLine()) != null) {
                temp = line.trim();
                if (temp.length()>0 && !temp.startsWith("//"))
                    count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filePath;
        while (scanner.hasNext()) {
            filePath = scanner.nextLine();
            File javaFile = new File(filePath);
            int res = new EffectiveLines().countEffectiveLines(javaFile);
            System.out.println("文件中有效行为"+res+"行");
        }
    }

}
