import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by dell--pc on 2016/11/16.
 */
public class FileUtilTest {
    public static void main(String[] args) throws IOException {
        File dir=new File("D:\\eclipse code\\Algo");
        FileUtil.scan(dir);
        /*File file=new File("D:\\eclipse code\\Algo\\src\\test\\algosort.java");
        Scanner sc=new Scanner(file);
        while(sc.hasNext()){
            while(sc.next().equals("import")){
                System.out.println(sc.next());
            }
        }*/

    }
}
