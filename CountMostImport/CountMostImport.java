import java.io.*;
import java.util.*;

/**
 * 根据指定项目目录下(可以认为是 java 源文件目录)中,统计被 import 最多的类,前十个是什么
 *
 * 使用方法:
 * 1.以指定目录为参数,调用CountMostImport类构造方法创建对象,指定目录为绝对路径
 * 2.调用getMostImportedClass(int count)方法,得到import次数最多的count个类,这里调用时count=10
 *
 * 注意:
 * 1.有 * 的也当做一个类看待,例如 java.io.*;
 * 2.将 static 静态导入也算作导入一个类
 */
public class CountMostImport {

    private Map<String, Integer> importClass = new HashMap<>();     // 使用Map存储每个类被import的个数

    private Queue<String> queue = new ArrayDeque<>();               // 广度优先遍历中使用的队列

    public CountMostImport(String filepath) {
        this.queue.offer(filepath);
    }

    private void classCountSingleFile(String filepath) {
        File file = new File(filepath);
        String line;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("import")) {
                    String[] str = line.split(" ");
                    // 将static静态导入也算作导入一个类
                    String className = str[str.length - 1].substring(0, str[str.length - 1].length() - 1);
                    if (this.importClass.containsKey(className)) {
                        this.importClass.put(className, this.importClass.get(className) + 1);
                    } else {
                        importClass.put(className, 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 广度优先遍历,计算每个文件中import类的个数
     */
    private void solve() {
        if (this.queue.isEmpty())
            return;
        String path = this.queue.poll();                   // Queue的poll方法在队列为空时返回null,这是个坑
        File file = new File(path);

        if (file.isFile() && file.getName().endsWith(".java")) {    // 如果是java文件,则更新import类的个数
            classCountSingleFile(file.getAbsolutePath());
        } else if (file.isDirectory()) {
            String absolutePath = file.getAbsolutePath();
            String[] subPath = file.list();
            if (subPath != null) {
                for (String s : subPath) {
                    this.queue.offer(absolutePath + "/" + s);
                }
            }
        }
        while (!this.queue.isEmpty()) {
            solve();
        }
    }

    /**
     * 工具方法,对Map中的value进行排序,返回一个排好序的LinkedHashMap
     *
     * @param oldMap 准备进行排序的Map
     * @return 排好序的LinkedHashMap
     */
    private Map<String, Integer> sortMapValue(Map<String, Integer> oldMap) {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(oldMap.entrySet());
        list.sort((arg0, arg1) -> arg1.getValue() - arg0.getValue());
        Map<String, Integer> newMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aList : list) {
            newMap.put(aList.getKey(), aList.getValue());
        }
        return newMap;
    }

    /**
     * 对import的类按次数多少排序
     *
     * @return 以Map的形式返回
     */
    private Map<String, Integer> getSortedImportClass() {
        this.solve();
        return this.sortMapValue(this.importClass);
    }

    /**
     * 返回import最多的类
     * @param count 返回类的个数
     * @return 类名列表
     */
    public List<String> getMostImportedClass(int count) {
        Map<String, Integer> sortedClass = this.getSortedImportClass();
        if (sortedClass.size() < count) {
            return null;
        }
        List<String> result = new ArrayList<>();
        Set<String> keySet = sortedClass.keySet();
        int i = 0;
        for (String s : keySet) {
            if (i < keySet.size() && i < count) {
                result.add(s);
                i++;
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * 返回 import 最多的10个类
     * @return 类名列表
     */
    public List<String> get10MostImportedClass() {
        return this.getMostImportedClass(10);
    }

    public static void main(String[] args) {
        CountMostImport countMostImport = new CountMostImport("/Users/pluto/Desktop/workspace/campus2017");
        System.out.println(countMostImport.get10MostImportedClass());
    }
}
