import com.google.common.base.Preconditions;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/14.
 */
public class CountMostImport {


    private HashMap<String, Integer> map = new HashMap<String, Integer>();


    public static void main(String[] args) {
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.println("please input the dir path (input q to quit): ");
            String dirPath = in.next();
            if(dirPath.equals("q")) break;
            try {
                CountMostImport cmi = new CountMostImport();
                cmi.getFile(dirPath);
                cmi.getTop10Import();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 根据目录获取文件
     * @param dirPath
     */
    public void getFile(String dirPath) {

        //检查目录路径字符串是否为空
        Preconditions.checkArgument(dirPath != null && !dirPath.equals(""),"please input an effective string:");
        File file = new File(dirPath);
        //检查文件是否存在
        Preconditions.checkArgument(file.exists(),"the file is not exist");

        Stack<File> stack = new Stack<File>();
        stack.push(file);
        while(!stack.isEmpty()) {
            file = stack.peek();
            stack.pop();
            if(file.isFile()) {
                countImport(file);
            } else if(file.isDirectory()) {
                File[] files = file.listFiles();
                for(File tmpFile: files){
                    stack.push(tmpFile);
                }
            }
        }

    }

    /**
     * 计算import数量
     * @param file
     */
    public void countImport(File file) {

        if (!file.getName().endsWith(".java"))
        {
            return;
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null) {
                //以public或class开头，结束循环
                if(Pattern.matches("^\\s*public.*", line) ||
                        Pattern.matches("^\\s*class.*", line)) {
                    break;
                }
                //以import开头，计数
                if(Pattern.matches("^\\s*import.*", line)) {
                    //提取被引用的类名
                    String className = line.trim().substring(6).replace(";","");
                    if(!map.containsKey(className)) {
                        map.put(className,1);
                    } else {
                        map.put(className,map.get(className)+1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null)
            {
                try {
                    reader.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取前十多的import类名
     */
    public void getTop10Import() {

        //初始化list为map.entrySet
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue()); //降序排序
            }
        });

        int i = 10;
        Iterator iter = list.iterator();
        //取出前10个key(引用类名)、value(被引次数)
        while (iter.hasNext() && i > 0) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String)entry.getKey();
            Integer value = (Integer)entry.getValue();
            System.out.println(key + ":" + value);
            i--;
        }
    }

}
