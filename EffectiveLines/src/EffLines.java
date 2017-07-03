/**
 * Created by chenli on 2017/1/2.
 */
import java.util.Scanner;
import file.FileCount;

public class EffLines {
    public static void main(String[] args){
        Scanner cin = new Scanner(System.in);
        String filePath = cin.nextLine();
        if(filePath == null || filePath.trim().length() <=0 ){
            System.out.println("输入文件路径为空");
            return ;
        }
        FileCount fileCount = new FileCount(filePath);
        int result = fileCount.readByLine();
        if(result >= 0 && fileCount.fileExist) {
            System.out.println("EffectiveLines: " + result);
        }
    }
}
