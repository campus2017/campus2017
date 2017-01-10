import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLines {

    static int count=0;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("D:\\JAVA\\Homework\\EffectiveLines\\src\\EffectiveLines.java"));
            String line=null;
            while((line = in.readLine()) != null)
                pattern(line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("代码行： " + count);
    }

//    进行匹配
    private static void pattern(String line) {

        String regx = "//.*";
        String regxSpace = "\\s*";
        if(!(line.matches(regx) ||  line.matches(regxSpace))){
            ++count;
            return ;
        }
    }
}
