import com.google.common.base.Splitter;
import summ.GetMostImport;

import java.util.Iterator;

/**
 * Created by Administrator on 2016/11/22.
 */
public class CountTest {
    public static void main(String[] args) {
        String path = "D:\\AndroidTest\\CountTime\\app\\src";

        GetMostImport getMostImport = new GetMostImport();
        getMostImport.getMostImport(path);
    }

}
