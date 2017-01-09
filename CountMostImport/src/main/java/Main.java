/**
 * Created by woo on 1/9.
 */
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Plz input the filename: ");
        String fileName = null;
        Scanner input = new Scanner(System.in);
        fileName = input.nextLine();
        List<Map.Entry<String,Integer>> r = CountMostImport.topKImportClass(fileName,10);
        System.out.printf("======被import最多的是%d个类是=====\n",10);
        for(Map.Entry<String, Integer> ic: r){
            System.out.println(ic.getKey()+":"+ic.getValue());
        }

    }
}