import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by zdl on 2017/1/10.
 */
public class CountMostImport {
    public CountMostImport(String path) {
        dir = new File(path);
        classCount = new HashMap<String, Integer>();
    }
    public List<Map.Entry<String, Integer>> top10() {
        traverse(dir);
        LinkedList<Map.Entry<String, Integer>> res = new LinkedList<Map.Entry<String, Integer>>();
        PriorityQueue<Map.Entry<String, Integer>> kpq = new PriorityQueue<Map.Entry<String, Integer>>(10,
                new Comparator<Map.Entry<String, Integer>>() {
                    public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                        return o1.getValue().compareTo(o2.getValue());
                    }
                });
        /**
         * 维持优先队列大小为10
         * */
        for (Map.Entry<String, Integer> mapEntry : classCount.entrySet()) {
            if (kpq.size() < 10) {
                kpq.offer(mapEntry);
            } else{
                kpq.poll();
                kpq.offer(mapEntry);
            }
        }
        while(!kpq.isEmpty())
            res.add(kpq.poll());
        Collections.reverse(res);       //倒序输出
        return res;
    }
    private boolean isJavaFile(File file)
    {
        return file.getName().endsWith(".java");
    }
    /**
     * 遍历目录
     * */
    private void traverse(File dir) {
        if(null == dir) return ;
        if (dir.isFile()) {
            count(dir);
            return ;
        }
        if(dir.isDirectory()) {
            File[] sub = dir.listFiles();
            if (null != sub) {
                for (File file : sub) {
                    if (file.isFile()) {
                        count(file);
                    } else if (file.isDirectory()) {
                        traverse(file);
                    }
                }
            }
        }
    }
    /*
    * 统计类频率
    * */
    private void count(File file) {
        if (isJavaFile(file)) {
            try {
                Scanner scan = new Scanner(new FileReader(file));
                String line = null;
                while((line = scan.nextLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("import")) {
                        int start = line.lastIndexOf(".") + 1;
                        line = line.substring(start, line.length() - 1);
                        if (!line.equals("*")) {
                            if(classCount.containsKey(line)){
                                classCount.put(line, classCount.get(line) + 1);
                            } else{
                                classCount.put(line, 1);
                            }
                        }
                    } else
                        break;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private File dir;
    private HashMap<String, Integer> classCount;


    public static void main(String[] args) {
        System.out.println("please choose a directory:");
        String dir = null;
        Scanner scan = new Scanner(System.in);
        while ((dir = scan.nextLine()) != null) {
            if (!new File(dir).exists()) {
                System.out.println("file or directory not exists!");
                return;
            }
            CountMostImport cmi = new CountMostImport(dir);
            List<Map.Entry<String, Integer>> res = cmi.top10();
            System.out.println("top 10 class: ");
            if (null != res) {
                for (Map.Entry<String, Integer> c : res) {
                    System.out.println(c.getKey() + " " + c.getValue());
                }
            }
        }
    }
}
