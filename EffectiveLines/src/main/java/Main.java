import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import com.google.common.base.Strings;

/**
 * Created by Leviathan on 2017/4/20.
 */
public class Main {

    // match block comments /* */
    private static final String pattenMultiLine = "/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/";
    // match single line comments //
    private static final String pattenSingleLine = "//.*";

    private static int count(String filepath) throws Exception {

        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line = null;
            while((line = br.readLine()) != null) {
                if(isValidLine(line)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean isValidLine(String line) {
        String cleanLine = line.replaceAll(pattenMultiLine, "").replaceAll(pattenSingleLine, "").trim();
        return !Strings.isNullOrEmpty(cleanLine);
    }


    public static void main(String []args){

        System.out.println("Input file path:");
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        File file = new File(path);
        if (!file.exists()) {
            System.out.printf("Error: cannot find file \"%s\"", path);
        }else {
            try {
                System.out.println("Effective lines: " + count(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
