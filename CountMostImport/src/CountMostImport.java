import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Administrator on 2017/6/17.
 */
public class CountMostImport {
    private static Map<String, Integer> statisticalMap = new HashMap<String, Integer>();
    public static void main(String[] args) {

        System.out.println("请输入需要统计的目录");
        Scanner sn = new Scanner(System.in);
        String filePath = sn.next();
        File file = new File(filePath);
        getAllFiles(file);
        String result= getMostImportFile();
        System.out.println("import 最多的类是:"+result);

    }

    private static void getAllFiles(File file) {
//        File file = new File(filePath );
        if(file.isDirectory()){
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    getAllFiles(f);
                }
            }
        } else {// 是文件
            try {
                findImporetnumber(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        return null;


    }

    private static void findImporetnumber(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String str = "";
        while ((str = reader.readLine()) != null) {
            if(str.startsWith("package")||"".equals(str)){
                continue;
            }
            if (str.startsWith("import")) {
                str = str.replaceAll("\\s*", "");
                str = str.substring(6);
                Integer value = statisticalMap.get(str);
                if(value==null){
                    statisticalMap.put(str, 1);
                }else{
                    statisticalMap.put(str, value+1);
                }
            } else {
                break;
            }
        }
        reader.close();


    }

    private static String getMostImportFile(){
        Iterator<String> iter = statisticalMap.keySet().iterator();
        String result = "";
        int max = 0;
        while(iter.hasNext()){
            String  key = iter.next();

            if(statisticalMap.get(key)>max){
                max = statisticalMap.get(key);
                result = key;
            }
        }
        return result;
    }

}
