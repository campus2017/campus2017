import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by 娜娜 on 2017/6/26.
 */
public class EffectiveLines {

    static int countEffectiveLines = 0;
    static int countNotEffectiveLines = 0;

    private static void countEffectiveLines(String line) {
        line = line.trim();//删除首尾空行
        if (line.matches("^[\\s&&[^\\n]]*$") || line.startsWith("/*") || line.startsWith("//")) {
            countNotEffectiveLines++;
            //System.out.println("ccc"+countNotEffectiveLines);
        } else {
            countEffectiveLines++;
            //System.out.println("dddd"+countEffectiveLines);
        }

    }

    public static void main(String[] args) {
       /* String path = Test02.class.getResource("/").getFile();
        System.out.println(path);//获取当前类的绝对路径*/

        // String filePath = "D:\\IdeaProjects\\EffectiveLines\\src\\Test02.java";//绝对路径
        String filePath = "src/Test02.java";//相对路径
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                countEffectiveLines(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(countEffectiveLines);
    }
}
