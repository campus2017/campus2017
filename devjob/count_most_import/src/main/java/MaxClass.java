import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.io.*;
import java.util.*;

/**
 * Created by honglin.li on 2017/6/30.
 */
public class MaxClass {

    public static ArrayList<File> getAllJavaFileFromPath(String path) {

        ArrayList<File> result = new ArrayList<File>();
        File file = new File(path);

        if (file.exists()) {
            File[] files = file.listFiles();
            if (files.length == 0) {
                System.out.println("文件夹是空的!");
                return null;
            } else {
                for (File f : files) {
                    if (f.isDirectory()) {
                        ArrayList<File> tmpfilelist =  getAllJavaFileFromPath(f.getAbsolutePath());
                        if (tmpfilelist == null) continue;
                        for (File f1 : tmpfilelist) {
                            if (f1.getAbsoluteFile().toString().endsWith("java")) {
                                result.add(f1);
                            }
                        }
                    } else {
                        if (f.getAbsoluteFile().toString().endsWith("java")) {
                            result.add(f.getAbsoluteFile());
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }

        return result;
    }

    public static ArrayList<ClassCountUnit> getMaxImportClass(String project_path) throws IOException {

        ArrayList<File> allfile = new ArrayList<File>();

        SortedMap<String, Integer> class_map = new TreeMap<String, Integer>();
        ArrayList<ClassCountUnit> classinfo = new ArrayList<ClassCountUnit>();

        allfile = getAllJavaFileFromPath(project_path);

        for (File f : allfile) {
            BufferedReader reader = null;

            reader = new BufferedReader(new FileReader(f));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("import")) {
//                    System.out.println(line.split(" ")[1]);
                    String classname = line.split(" ")[1];
                    if (class_map.get(classname) == null) {
                        class_map.put(classname, 1);
                        continue;
                    }
                    int cnt = class_map.get(classname);
                    class_map.put(classname, cnt + 1);
                }
            }
        }

        Iterator it = class_map.keySet().iterator();

        while (it.hasNext()) {

            String key = (String)it.next();
//            System.out.println(key + " : " + class_map.get(key));
            ClassCountUnit c = new ClassCountUnit(key, class_map.get(key));

            classinfo.add(c);
        }

        Collections.sort(classinfo, new SortByCnt());

        int size = classinfo.size();
        for (int i = 10; i < classinfo.size(); ) {
           classinfo.remove(i);
        }


        return classinfo;
    }

    static class SortByCnt implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            ClassCountUnit u1 = (ClassCountUnit) o1;
            ClassCountUnit u2 = (ClassCountUnit) o2;

            return u2.getCnt() - u1.getCnt();
        }
    }
}





































