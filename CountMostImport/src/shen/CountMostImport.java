package shen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;


public class CountMostImport {

    public static TreeMap<String,Integer> map = new TreeMap<>();
    //static int count = 0;
    public static List<String> list = new ArrayList<String>();


    public static void main(String args[]) {
        //输入java源文件目录的绝对地址
        List<File> filelist = getFileList("E:/EffectiveLines/src/shen");
        for (File path : filelist){
            CountFileImport(path,map);
        }
        Print();
    }

    /**
     * 统计 import 的类
     * @param file
     * @return
     */
    public static int CountFileImport(File file,TreeMap map){
        //List<String> list = new ArrayList<String>();
        try
        {
            String encoding = "GBK";
            //File file = new File(path);
            if (file.isFile() && file.exists())
            { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null)
                {
                    list.add(lineTxt);
                }
                bufferedReader.close();
                read.close();
            }
            else
            {
                System.out.println("找不到指定的文件");
            }
        }
        catch (Exception e)
        {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }

        //Map<String,Integer> map = new TreeMap<>();
        int count = 0;
        for (String  l : list ){
            //String[] key = new String[];
            if (l.contains("import")){
                //去除分号；
                l=l.substring(0,l.length()-1);
                String[] key1 = l.split(" ");
                //获取类名
                String[]  key = key1[1].split("\\.");
                if (!key[key.length-1].equals("*")) {
                if (key.length != 0){
                    if (map.containsKey(key[key.length-1])) {
                        map.put(key[key.length-1], count++);
                    } else {
                        map.put(key[key.length-1], 1);
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static void Print(){
        // 升序比较器
        Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                // TODO Auto-generated method stub
                return o2.getValue()-o1.getValue();
            }
        };

        // map转换成list进行排序
        List<Map.Entry<String, Integer>> list2 = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list2,valueComparator);
        int count = 3;
        for (Map.Entry<String, Integer> entry : list2) {
            //count --;
            if (count > 0)
                System.out.println(entry.getKey() + ":" + entry.getValue());
            else break;
            count --;
        }
    }


    /**
     * 遍历得到指定路径下的所有 .class 文件
     * @param strPath
     * @return
     */
    public static List<File> getFileList(String strPath) {
        List<File> filelist = new LinkedList<>();
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith("java")) { // 判断文件名是否以.avi结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }

        }
        return filelist;
    }
}
