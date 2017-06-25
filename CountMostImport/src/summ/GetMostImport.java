package summ;

import util.counter.ImportCounter;
import util.getter.FilePathGetter;
import util.reader.FileImportReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/23.
 * 总结类，运用util获得引用次数最多的前(<=10)个类。
 */
public class GetMostImport {

    public void getMostImport(String path){
        File file = new File(path);
        FilePathGetter getter = new FilePathGetter();
        FileImportReader reader = new FileImportReader();
        List<String> list = getter.getFilePath(file);

        List<String> allImport = new ArrayList<>();//将所有的被引用的类放入到allImport的List中，汇总。

        for (int i = 0; i < list.size(); i++) {
            String filepath = list.get(i);
            List<String> importList = reader.readImport(filepath);
            for (int j = 0; j < importList.size(); j++) {
                String imp = importList.get(j);
                allImport.add(imp.split("import")[1].trim());
            }
        }

        if (allImport == null || allImport.size() == 0){
            System.out.println("NO IMPORT CLASS!");//没有被引用的类
        }else {
            //有被引用的类，打印前(<=10)个
            ImportCounter importCounter = new ImportCounter();
            List<Map.Entry<String, Integer>> mostImportList = importCounter.countImport(allImport);
            int printNum = mostImportList.size() > 10 ? 10 : mostImportList.size();

            for (int i = 0; i < printNum; i++) {
                System.out.println(mostImportList.get(i));
            }
        }
    }
}
