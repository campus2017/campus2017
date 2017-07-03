import deal.AllFileRead;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chenli on 2017/6/28.
 */
public class CountMostTenImport {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        String dirPath = cin.nextLine();
        AllFileRead allFileRead = new AllFileRead(dirPath);
        ArrayList<String> mostTenImport = null;
        if(dirPath == null || dirPath.length() <=0 ){
            System.out.print("输入为无效目录");
            return;
        }

        allFileRead.getAllCount();
        mostTenImport = allFileRead.getMostTenImport();
        System.out.println("使用最多的十个import类：");
        for(String tmpImport: mostTenImport){
            System.out.println(tmpImport);
        }
    }
}
