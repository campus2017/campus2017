package qunar.pre.commission;

import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by hughgilbert on 03/06/2017.
 */
public class ImportCount {

    public List<Pair> start(String directory) throws IOException
    {
        try {

            Multiset<String> importCount = HashMultiset.create();
            //获得目录中的java文件
            FluentIterable<File> javaFiles = fileTravers(directory);

            //遍历每一个java文件，统计java文件中的import
            countImportClass(javaFiles,importCount);

            //排序统计
            return sortClass(importCount);

        }
        catch (IOException e)
        {
            throw e;
        }

    }

    //遍历目录文件
    private FluentIterable<File> fileTravers(String directory)
    {
        return Files.fileTreeTraverser().
                breadthFirstTraversal(new File(directory)).
                filter(new Predicate<File>() {
            public boolean test(File input) {
                return apply(input);
            }

            public boolean apply(File input) {
                return input.isFile() && input.getName().endsWith("java");
            }
        });
    }

    //统计import
    private void countImportClass(FluentIterable<File> javaFiles, Multiset<String> importCount) throws IOException
    {
        try {
            for (File file : javaFiles) {
                List<String> content = getImportClassOfOneFile(Files.readLines(file, Charsets.UTF_8));

                importCount.addAll(content);
            }
        }
        catch (IOException e)
        {
            throw e;
        }
    }

    //获得类名
    private List<String> getImportClassOfOneFile(List<String> content)
    {
        return FluentIterable.from(content).filter(new Predicate<String>() {
            public boolean test(String input){
                return apply(input);
            }

            public boolean apply(String input) {
                return input.startsWith("import");
            }
        }).transform(new Function<String, String>() {
            public String apply(String input){
                //换成正则表达式
                return input.replaceAll("import ","").
                        replace(";","").replaceAll("static", "").trim();
            }
        }).toList();
    }

    //对类进行排序
    private List<Pair> sortClass(Multiset<String> importCount){

        List<Pair> list = new ArrayList<Pair>();
        for(String key : importCount.elementSet()){
            list.add(new Pair(key,importCount.count(key)));
        }

        Collections.sort(list);

        if(list.size() <= 10){
            return list;
        }
        else{
            return list.subList(0,10);
        }
    }
}
