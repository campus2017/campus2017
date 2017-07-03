package per.asuka;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Created by asuka on 23/06/2017.
 */


public class TestCase {
    public static void EffectiveLinesTest() throws FileNotFoundException{
        EffectiveLines.countLines(new Scanner(System.in).nextLine());
    }

    public static void CountMostImportTest(){
        CountMostImport.countTop10(new Scanner(System.in).nextLine());
    }

    public static void main(String args[]){
        CountMostImportTest();
    }
}
