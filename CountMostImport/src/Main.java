import entity.ImportClassBean;

import java.io.*;
import java.util.*;

public class Main {
    //用于存放所有引用的ImportClassBean实体的集合
    private static List<ImportClassBean> importClassBeanList = new ArrayList<>();
    //用于存放key：className以及value：count的Map
    private static Map<String, Integer> importMap = new HashMap<>();

    public static void main(String[] args) {
      //读取指定java源文件目录
        String filePath="G:\\ideaworkspaces\\ExchangeRate\\src";
        File f = new File(filePath);

        importMap = doCountJavaFileFromSrc(f);
        //遍历map，将importClassBean实体放入list中
        for (Map.Entry<String,Integer> entry: importMap.entrySet()){
            ImportClassBean bean = new ImportClassBean(entry.getKey(), entry.getValue());
            importClassBeanList.add(bean);
        }
        //对importClassBeanList排序（自定义类ImportClassBean排序：实现Comparable接口，重写接口方法）
        Collections.sort(importClassBeanList);

        System.out.println("被import最多的类，前十个:");
       for (int i = 0; i<10; i++){
           System.out.println(importClassBeanList.get(i).getClassName()+"\t\t"+importClassBeanList.get(i).getCounts());
        }
    }

    /**
     * 遍历src目录下所有目录、文件
     * @param f
     * @return 返回所有import的className的Map
     */
    private static Map<String, Integer> doCountJavaFileFromSrc(File f) {

        if (f != null) {
            if (f.isDirectory()) {
                File[] fileArray = f.listFiles();
                if (fileArray != null) {
                    for (int i = 0; i < fileArray.length; i++) {
                        //递归调用
                        doCountJavaFileFromSrc(fileArray[i]);
                    }
                }
            }else{
                String fileName = f.getName();
                if (fileName.endsWith(".java")) {
                    System.out.println(f);
                    importMap = doCountImportFromJavaFile(f,importMap);
                }
            }
        }
        return importMap;
    }
    private static  Map<String, Integer> doCountImportFromJavaFile(File file, Map<String, Integer> importMap){

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while (null !=(line = br.readLine())){
                if (line.startsWith("import")) {
                    int lastIndex = line.lastIndexOf(".");
                    //获取class的名称
                    String className = line.substring(lastIndex+1, line.length()-1);

                    //如果是通配符*,跳过
                    if ("*".equals(className)){
                        continue;
                    }
                    //查找map中是否存放过该名称
                    if (importMap.containsKey(className)){
                       importMap.put(className, importMap.get(className)+1);
                    }else{
                        importMap.put(className, 1);
                    }

                }else{
                    continue;
                }

            }
        } catch (IOException e) {
                e.printStackTrace();

        }

        return importMap;
    }
}

