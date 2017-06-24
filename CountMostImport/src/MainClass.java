
import java.util.HashSet;
public class MainClass {


    public  static  void main(String args[])
    {

        CountImport cnImport = new CountImport();
        cnImport.processFiles("./src");
        cnImport.outputMap();

    }

}

