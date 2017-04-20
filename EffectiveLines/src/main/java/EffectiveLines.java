import java.io.*;

/**
 * Created by dang on 2017/4/19.
 * All right reserved.
 */
public class EffectiveLines {

    public static int countLinesByFilename(String filename) {
        File f = new File(filename);
        BufferedReader bufferedReader;
        int lines = 0;
        try {
            bufferedReader = new BufferedReader(new FileReader(f));
            String tempLine;
            while ((tempLine = bufferedReader.readLine()) != null) {
                if (isEffectiveLines(tempLine)) {
                    lines++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static boolean isEffectiveLines(String tempLine) {
        if (tempLine.equals("")) {
            return false;
        } else {
            String tempLineWithoutSpace = null;
            for (int i = 0; i < tempLine.length(); i++) {
                if (tempLine.charAt(i) != ' ') {
                    tempLineWithoutSpace = tempLine.substring(i, tempLine.length());
                    break;
                }
            }
            if (tempLineWithoutSpace == null) {
                return false;
            } else if (tempLineWithoutSpace.length() == 1) {
                return true;
            } else if (tempLineWithoutSpace.charAt(0) == '/' && tempLineWithoutSpace.charAt(1) == '/') {
//                System.out.println("注释行");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        EffectiveLines e = new EffectiveLines();
//        String test = "   1//";
//        System.out.println(e.isEffectiveLines(test));
        String fileName = "F:/IdeaProjects/EffectiveLines/src/main/java/EffectiveLines.java";
        System.out.println("文件："+fileName+" 的有效行数为："+EffectiveLines.countLinesByFilename(fileName));
    }
}
