import java.io.*;
import java.util.*;

/**
 * Created by dang on 2017/4/20.
 * All right reserved.
 */
public class CountMostImport {

    private Map<String, Integer> unsortedRecords;
    private String dirname;
    private TreeMap<String, Integer> sortedRecords;

    public CountMostImport(String dirname) {
        this.dirname = dirname;
        this.unsortedRecords = new HashMap<String, Integer>();
    }

    //    内部类实现比较器
    public class IntegerComparator implements Comparator<String> {
        Map<String, Integer> base;

        IntegerComparator(Map<String, Integer> base) {
            this.base = base;
        }

        public int compare(String o1, String o2) {
            if (base.get(o1) >= base.get(o2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public void sortMap() {
        IntegerComparator integerComparator = new IntegerComparator(unsortedRecords);
        sortedRecords = new TreeMap<String, Integer>(integerComparator);
        sortedRecords.putAll(unsortedRecords);
    }

    //统计计数，排列输出
    public void doCount() {
        File file = new File(dirname);
        countClassImports(file);
        sortMap();
    }

    public void countClassImports(File file) {
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            for (File f : fileList) {
                countClassImports(f);
            }
        } else {
            processFile(file);
        }
    }

    //  导入次数统计函数
    private void processFile(File file) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String tempLine;
            while ((tempLine = bufferedReader.readLine()) != null) {
//                只统计到类定义的位置
                if (tempLine.trim().startsWith("public") || tempLine.trim().startsWith("class")) {
                    break;
                }
                if (tempLine.trim().startsWith("import")) {
                    String strings[] = tempLine.substring("import".length()).trim().split(";");
                    String className = strings[0];
                    Integer value = unsortedRecords.get(className);
                    if (value == null) {
                        unsortedRecords.put(className, 1);
                    } else {
                        unsortedRecords.put(className, value + 1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMaxNImport(int n) {
//        按导入次数输出所有
//        System.out.println(sortedRecords);
        Iterator iterator = sortedRecords.keySet().iterator();
        int i = 0;
        while (iterator.hasNext() && i++ < n) {
            System.out.println(iterator.next());
        }
    }

    public static void main(String[] args) {

        System.out.println("请输入待统计的java源文件路径：");
        Scanner sc = new Scanner(System.in);
        String dir = sc.next();
//        String dir = "F:\\IdeaProjects\\BlogAPP2\\src";
        CountMostImport countMostImport = new CountMostImport(dir);
        countMostImport.doCount();
        countMostImport.printMaxNImport(10);
    }
}


