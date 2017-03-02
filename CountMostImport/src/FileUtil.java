import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by dell--pc on 2016/11/16.
 */
public class FileUtil {
    public static void scan(File dir)throws IOException{
        if(!dir.exists()){
            throw new IOException("不存在的目录");
        }
        if(!dir.isDirectory()){
            throw new IOException("非目录");
        }
        File[] files=dir.listFiles();
        Map<String,Integer> classNames=new HashMap<>();
        for (File file:files) {
            if (file.isDirectory()){
                FileUtil.scan(file);
                continue;
            }
            Scanner sc=new Scanner(file);
            while(sc.hasNext()){
                if(sc.next().equals("import")){
                    String str=sc.next();
                    System.out.println(str);
                    if(classNames.containsKey(str)){
                        classNames.put(str,classNames.get(str)+1);
                    }else{
                        classNames.put(str,1);
                    }
                }
            }
        }
        int max=0;
        String maxClassName="";
        for (Map.Entry<String,Integer> entry:classNames.entrySet()){
            if(entry.getValue()>max){
                maxClassName=entry.getKey();
                max=entry.getValue();
            }
        }

        if(max!=0){
            System.out.println("出现次数最多的类是"+maxClassName+"出现了"+max+"次");
        }



    }
}
