import util.SelectTopK;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by libo on 2017/6/6.
 *
 * 统计被 import 最多的类，前十个是什么
 * 不考虑源文件中的语法错误和不规范的语法使用情况（如import语句前后含有注释等）
 */
public class CountMostImport {
    private List<File> javaFile = new ArrayList<File>();                         //存放目录中所有的Java源文件
    private Map<String, Integer> countMap = new HashMap<String, Integer>();      //存放所有java文件中的统计信息

    /**
     * 验证参数是否合法
     * @return 返回null表示合法  否则返回的是错误信息
     */
    private String checkDirectory(String dirPath){
        String error = null;
        File dir = new File(dirPath);

        if (dirPath == null || "".equals(dirPath)){
            error = "参数不合法";
        }
        else if (!dir.exists()){
            error = "目录不存在";
        }
        else if (!dir.isDirectory()){
            error = "所给路径不是目录文件";
        }

        return error;
    }


    /**
     * 获取目录中所有的Java文件
     * @param dirFile
     * @return
     */
    private void getJavaFiles(File dirFile){
        File[] files = dirFile.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                if (pathname.isDirectory()){
                    return true;
                }
                if (pathname.getName().endsWith(".java")){
                    return true;
                }
                return false;
            }
        });
        if (files != null){
            for (File fileItem : files){
                if (fileItem.isDirectory()){
                    getJavaFiles(fileItem);
                }
                else{
                    javaFile.add(fileItem);
                }
            }
        }
    }


    private void addCountMap(String str){
        if (countMap.containsKey(str)){
            countMap.put(str, countMap.get(str)+1);
        }
        else{
            countMap.put(str, 1);
        }
    }

    /**
     * 对每一个文件统计import的class类
     * @param file
     * @throws IOException
     */
    private void countImport(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        String reg = "(?<=import)\\s+(.*?)\\s*(?=;)";
        Pattern p = Pattern.compile(reg);

        while((line=reader.readLine()) != null){
            line = line.trim();
            //这里找出import的类
            Matcher m = p.matcher(line);
            while (m.find()){
                addCountMap(m.group(1));
            }

            //import语句只允许在类定义的前面，所以当出现类定义的时候应该停止遍历文件
            if (line.matches("[\\w ]*?(?=class|interface).*?")){
                break;
            }
        }
    }

    /**
     * 统计被import最多的类
     * @param dirPath 传入的是目录的具体路径，不接受单个文件，因为统计没有意义
     * @return 如果ImportInfo中的info变量是null表示调用失败
     */
    public ImportInfo countMostImport(String dirPath){
        String checkMsg = checkDirectory(dirPath);
        if (checkMsg != null){
            return new ImportInfo(checkMsg);
        }
        getJavaFiles(new File(dirPath));
        if (javaFile.isEmpty()){
            return new ImportInfo("所给目录中没有Java文件");
        }

        try{
            for (File file : javaFile){
                countImport(file);
            }
        } catch(IOException e){
            e.printStackTrace();
            return new ImportInfo("文件解析出现异常");
        }

        if (countMap.isEmpty()){
            return new ImportInfo("所有文件中没有import语句");
        }

        //对获取的map数据取topk的值
        return new ImportInfo(SelectTopK.getMaxVal(countMap, 10));
    }
}


