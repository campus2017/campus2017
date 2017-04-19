import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tang_yi on 2017/2/8.
 */
public class CountMostImport {

    static Multiset<String> importCount= HashMultiset.create();

    public static void main(String [] args){
        try {
            openDirectory(new File(args[0]));
        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> result = getMostImport(10);
        for (String res : result){
            System.out.println(res);
        }
    }

    //递归打开文件目录
    public static void openDirectory(File file){
        if (file.isFile()){
            if (file.getAbsolutePath().endsWith(".java")){
                countFileImport(file);
            }
        }else if(file.isDirectory()){
            File [] children = file.listFiles();
            for (File child : children){
                openDirectory(child);
            }
        }
    }

    //计算一个文件所有import类的个数
    public static void countFileImport(File file){
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null){
                if (line.startsWith("public") || line.startsWith("class") || line.startsWith("private")){
                    break;
                }
                if (line.startsWith("import ")){
                    String importName = line.substring(7, line.length());
                    importCount.add(importName);
                }
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ie){
            ie.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //得到10个最多import的类
    public static List<String > getMostImport(int num){
        if (importCount.elementSet().size() < num)
            return new ArrayList<String>(importCount.elementSet());

        List<String> res = new ArrayList<String>();
        Multiset<String> tempImportCount = HashMultiset.create(importCount);
        String className = null;
        int max = Integer.MIN_VALUE;
        while (num > 0) {
            for (String ele : tempImportCount.elementSet()) {
                if (tempImportCount.count(ele) > max) {
                    max = tempImportCount.count(ele);
                    className = ele;
                }
            }
            System.out.println(className + " " + max);
            res.add(className);
            max = Integer.MIN_VALUE;
            tempImportCount.setCount(className,0);
            num--;
        }
        return res;
    }
}
