import com.countMostImport.CountMostImport;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by canda on 6/29/17.
 * 测试CountMostImport
 */
public class testCountMostImport {
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("请输入被统计文件或目录");
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        CountMostImport countMostImport = new CountMostImport(fileName);
        for (String str : countMostImport.importClassMap.elementSet()){
            System.out.println(str+":"+countMostImport.importClassMap.count(str));
        }
    }
}
