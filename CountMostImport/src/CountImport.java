

import java.io.*;
import java.util.*;

public class CountImport {

    public static HashMap<String, Integer> importClass;

    public  CountImport()
    {
        importClass = new HashMap<>();
        importClass.clear();
    }

    public  void processFiles(String dirString)
    {
        File file = new File(dirString);

        if(!file.exists())
        {
            System.out.println("file not exists!");
            System.exit(1);
        }

        if(file.isFile()) //如果直接是文件
        {
            readFromFile(dirString);

        }
        else if(file.isDirectory()) //如果是目录
        {
            //列出目录下所有文件
           String [] fileList = file.list();
            for(int i = 0 ; i < fileList.length ; ++i)
            {
                String absolutePath = dirString+ "/"+ fileList[i];  // 构建新的路径
  //              System.out.println(absolutePath);
                processFiles(absolutePath);   // 递归处理
            }

        }

    }

    private void readFromFile(String fileName)
    {
        File file = new File(fileName);

        if(!file.isFile())
        {
            System.out.println("not file");
            System.exit(1);
        }

        try {
            BufferedReader bfReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bfReader.readLine())!=null)
            {
                if(line.trim().startsWith("import ")) // 如果以import开始, 处理了整个文件所有的行
                {
                    String splitResult []= line.trim().split(" ");

                    for(int i =1 ; i < splitResult.length ; ++i)
                    {
                        if(splitResult[i].length()<=1) // 空格
                        {
                            continue;
                        }
                        else
                        {
                           if(importClass.containsKey(splitResult[i]))
                           {
                               Integer val = importClass.get(splitResult[i]);
                               ++val;
                               importClass.replace(splitResult[i],val); // 替换
                           }else {

                               importClass.put(splitResult[i],1);
                           }

                        }

                    }

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void outputMap()
    {

        List<Map.Entry<String, Integer>> list = new ArrayList<>(importClass.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        if(list.size() <=10)
        System.out.println(list);
        if(list.size() >10)
        {
            for(int i=0 ;i < 10 ; ++i)
            System.out.println(list.get(i));
        }
    }

}
