import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by asus on 2017/1/17.
 */
public class Top10Sort {
    public void sort(ArrayList<String> fileList) throws IOException {
        int size = fileList.size();
//        所有Import类存入到multiSet
        Multiset<String> multiSet = HashMultiset.create();
        for (int i = 0; i < size; i++) {
            File file = new File(fileList.get(i));
            BufferedReader reader = null;
            reader = new BufferedReader(new FileReader(file));
            String linecontent = null;
                do {
                    linecontent = reader.readLine();
                    if(!linecontent.equals("")&&linecontent.startsWith("import")){
                        multiSet.add(linecontent.substring(6,linecontent.length()-1));
                    }
                }while (linecontent.equals("")||(!linecontent.startsWith("public") &&linecontent.startsWith("import")));
            reader.close();
        }
//        转为String数组便于操作 importclass
        Object[]  objects=  multiSet.elementSet().toArray();
        String[] clasz = new String[objects.length];
        for (int i =0;i < objects.length; i++){
            clasz[i] = objects[i].toString();
        }

        int length = clasz.length;
//        10次遍历找到top10
        for (int i=0;i<10;i++){
            for (int j=i+1;j<length;j++){
                if(multiSet.count(clasz[i])<multiSet.count(clasz[j])){
                    String temp = clasz[i];
                    clasz[i] = clasz[j];
                    clasz[j] = temp;
                }
            }
        }

        for(int i=0;i<10;i++){
            if (clasz.length<10){
                if (i>objects.length-1){
                    break;
                }
            }
            System.out.println("类名:"+clasz[i]+"------次数:"+multiSet.count(clasz[i]));
        }
    }
}
