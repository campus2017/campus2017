import org.junit.Test;

import java.io.*;

/**
 * ReaderTest
 *
 * @author wz
 * @date 16/12/23 18:21
 */
public class ReaderTest {


    @Test
    public void readerTest() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\Users\\wz\\Desktop\\HTML5-CSS.txt")));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line+"\n");
        }
        System.out.println(builder);
    }


    @Test
    public void charTest() {
        Character c1 = 'a';
        Character c2 = 'b';
        System.out.println(c1.compareTo(c2));
        c1 = '1';
        c2 = '2';
        System.out.println(c1.compareTo(c2));
    }

    @Test
    public void stringTest() {
        //language=RegExp
//        String regex = "[a-zA-Z]";
//        String s =" " + "ddddd" + " ";
//        String[] res = s.split(regex);
//        System.out.println(res.length);
//        regex = "[\u4e00-\u9fa5]";
//        s = " "+"哎呀汉a字啊一"+" ";
//        res = s.split(regex);
//        System.out.println(res.length);
//        regex = "[0-9]";
//        s = " "+"a12b4"+" ";
//        res = s.split(regex);
//        System.out.println(res);
        Character c = '\n';
        String s = "\n";
//        StringBuilder sb = new StringBuilder("\n");
        System.out.println(s.equals("\n"));
//        System.out.println(sb.equals("\n"));

//        System.out.println(c.toString());
    }
}
