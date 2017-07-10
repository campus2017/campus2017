
/**
 * Created by qiongwei_ren.
 */

import java.io.* ;
import java.lang.String ;
import static java.lang.System.out;
import java.io.BufferedInputStream;


public class MainTest {


    public static void main(String[] args) throws IOException {

        String projectPath = "./src/main/java";
        int selectNum = 10;
        MostImportClassCount MICC =  new MostImportClassCount(projectPath, selectNum);
        MICC.run();

    }



}

