package CountMostImport;

import java.io.*;
import java.util.*;


/**
 * Created by 朱潇翔 on 2017/1/22.
 */
public class CountMost {
    private static HashMap<String, Integer> map = new HashMap<String, Integer>();
    private File file;

    public CountMost(String filename) {
        this.file = new File(filename);

        porcessFile(this.file);
    }

    private static void porcessFile(File staticFile) {
        //判断传入的是否是个文件夹
        if(staticFile.isFile()) {
            if(staticFile.getName().matches(".+\\.java$")) {
                //System.out.println(staticFile.getName());
                try {
                    CountImport(staticFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return;
        } else {
            File[] t = staticFile.listFiles();
            for(int i = 0; i < t.length; ++i) {
                porcessFile(t[i]);
            }
        }

    }

    private static void CountImport(File file) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String strLine = null;
        try {
            while((strLine = bufferedReader.readLine()) != null) {
                strLine = strLine.trim(); //去除字符串首尾的空格
                if(strLine.startsWith("public") || strLine.startsWith("protected")
                || strLine.startsWith("private") || strLine.startsWith("class")) {
                    break;
                } else if(strLine.startsWith("import")) {
                    String className = strLine.substring(6, strLine.length() - 1).trim(); //去除import和;
                    Integer value = map.get(className);
                    if(value == null) {
                        //以前没有添加过
                        map.put(className, 1);
                    } else {
                        map.put(className, value + 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CoutTopTen() {
        String[] strImport = GetTopTen();
        for(int i = 0; i < strImport.length; ++i) {
            if(strImport[i] == null) {
                break;
            }
            System.out.println(strImport[i]);
        }
    }

    private static String[] GetTopTen() {
        //降序
        LinkedList list = new LinkedList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - (o1.getValue());
            }
        });
        //Map sortMap = new LinkedHashMap();
        String[] strImport = new String[10];
        int i = 0;
        for(Iterator it = list.iterator(); it.hasNext() && i < 10; ++i) {
            Map.Entry entry = (Map.Entry)it.next(); //第一次调用返回第一个
            //sortMap.put(entry.getKey(), entry.getValue());
            strImport[i] = entry.getKey().toString();
        }
        //return sortMap;

        return strImport;
    }

}
