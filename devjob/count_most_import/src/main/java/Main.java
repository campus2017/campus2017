import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by honglin.li on 2017/6/30.
 */
public class Main {
    public static void main(String[] args) throws IOException {

            String project_name = "D:\\java_work\\seccenter_coreinfo_web";
            ArrayList<ClassCountUnit> result = MaxClass.getMaxImportClass(project_name);

            for (ClassCountUnit unit : result) {
                System.out.println(unit);
            }
    }
}
