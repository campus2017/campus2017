package mostimport;


import com.google.common.base.Charsets;
import com.google.common.collect.*;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CountMostImport {

    public static List<File> allFiles = new ArrayList<File>();
    public static List<String> allClasses = new ArrayList<String>();


    public static void main(String[] args) {

        File root = new File("源码目录路径");
        getAllFiles(root);
        getImportClasses(allFiles);
        Multiset<String> multiset = HashMultiset.create();
        multiset.addAll(allClasses);
        Set<Multiset.Entry<String>> sets = multiset.entrySet();
        List<Multiset.Entry<String>> result = Lists.newArrayList(sets);
        Collections.sort(result, new Comparator<Multiset.Entry<String>>() {
            public int compare(Multiset.Entry<String> obj1, Multiset.Entry<String> obj2) {
                return obj2.getCount() - obj1.getCount();
            }
        });
        System.out.println("该目录下被import最多的10个类为：");
        for (int i = 0; i < 10; i++) {
            System.out.println(result.get(i).getElement());
        }

    }

    /**
     * 递归添加所有文件
     * @param root 文件路径
     */

    public static void getAllFiles(File root) {
        if (!root.isDirectory()) {
            allFiles.add(root);
        } else {
            File[] files = root.listFiles();
            for (File file : files) {
                getAllFiles(file);
            }
        }
    }

    /**
     * 获取所有源码文件中被import的类名
     * @param files
     */

    public static void getImportClasses(List<File> files) {
        for (File file : files) {
            try {
                ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
                boolean inImportCode = false;
                for (String line : lines) {
                    line.trim();
                    if (line.startsWith("import static") && line.endsWith(";") && !line.endsWith("*;")) {
                        String[] strings = line.split("[\\s]+");
                        allClasses.add(strings[2].substring(0, strings[2].length() - 1));
                    } else if (line.startsWith("import") && line.endsWith(";") && !line.endsWith("*;")) {
                        String[] strings = line.split("[\\s]+");
                        allClasses.add(strings[1].substring(0, strings[1].length() - 1));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }


}
