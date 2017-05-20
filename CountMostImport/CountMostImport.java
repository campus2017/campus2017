package CountMostImport;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 曹 on 2017/5/16.
 * 可以判断： import
 *                 java.io.*;
 *          import java.
 *                     io.*;
 * 等情况
 */

public class CountMostImport {
    //加入Map时的判断前缀
    private static String STATIC_PROCESS_STRING = "import ";
    private static int INITIALIZE_NUM = 1;

    //容纳目录下所有文件
    List<File> allFiles = new ArrayList<File>();
    //容纳所有文件内容，每个文件一个字符串（有时速度较慢，需要改进）
    List<String> content = new ArrayList<String>();
    //容纳导入类文件，String：类名，Integer:次数
    Map<String,Integer>  importNumMax = new TreeMap<String,Integer>();
    //获取路径下所有的文件
    /**
     * 获取所有目录下的文件，存入allFiles变量
     * path:项目目录
     * **/
    public void getFilesFromPath(String path) {
        File f = new File(path);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()&&files[i].getName().endsWith(".java")) {
                this.allFiles.add(files[i]);
            } else if(files[i].isDirectory()){
                getFilesFromPath(files[i].toString());
            }
        }
    }
    //根据allFiles返回所有文件内容到content
    public void getContentFromFile(List<File> listfiles) throws Exception {

        File singleFile;
        for (int i = 0; i < listfiles.size(); i++) {
            // System.out.println(listfiles.get(i));
            singleFile = listfiles.get(i);
            getContentFromFile(singleFile);
        }
    }
    //根据allFiles返回所有文件内容到content
    public void getContentFromFile(File file) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader br=null;
        try{
            //文件读入
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)
            ));
            String line = null;
            while((line = br.readLine())!=null){
                //追加
                stringBuffer.append(line);
            }
            content.add(String.valueOf(stringBuffer));
        }catch (IOException e){
            //流读取失败
            throw new RuntimeException("读取文件失败"+e);
        }finally {//finally除特殊情况一定会关闭，保证流关闭
            try{
                //关闭流
                br.close();
            }catch (IOException e){
                //流关闭失败
                throw new RuntimeException("关闭文件流失败");
            }
        }
    }
    /**
     * 将List转化为Array
     * **/
    public String[] StringListToArray(List<String> listStings) {
        String[] arrayFiles = new String[listStings.size()];
        for (int i = 0; i < listStings.size(); i++) {
            arrayFiles[i] = listStings.get(i);
        }
        return arrayFiles;
    }
    //将文件内容放入TreeMap中
    public void processContent(List<String> waitForProcessContent) {
        String[] record = StringListToArray(waitForProcessContent);
        for (int i = 0; i < record.length; i++) {
            //处理逻辑
            //将空格，回车，换行等空白符转换为空字符（去掉空白符）
            // record[i] = record[i].replaceAll("\\s","");
            String[] strings = record[i].split(";");
            for(int j=0;j<strings.length;j++){
                strings[j] = strings[j].trim();
                strings[j] = strings[j].replaceAll("[\\t\\n\\r]","");
                if(strings[j].startsWith(STATIC_PROCESS_STRING)){
                    strings[j] = strings[j].replaceAll("\\s","");
                    if (importNumMax.get(strings[j].substring(6)) == null) {
                        importNumMax.put(strings[j].substring(6), INITIALIZE_NUM);
                    } else {
                        int num = importNumMax.get(strings[j].substring(6)) + 1;
                        importNumMax.put(strings[j].substring(6), num);
                    }

                }
            }
        }

    }
    //排序取出前十名
    public void sort(Map<String, Integer> importNumMax){
        List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(importNumMax.entrySet());
        //通过比较器实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            //降序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        int num = 1;
        //将结果写入文件
        for(Map.Entry<String, Integer> map : list) {
            if(num <= 10) {
                // System.out.println("出现次数第" + num + "的单词为：" + map.getKey() + "，出现频率为" + map.getValue() + "次");
                System.out.println(map.getKey() + ":" + map.getValue());
                num++;
            }
            else break;
        }
    }
    //测试
    public static void main(String[] args) throws Exception {
        CountMostImport T = new CountMostImport();
        T.getFilesFromPath("./");
        T.getContentFromFile(T.allFiles);
        T.processContent(T.content);
        T.sort(T.importNumMax);
    }
}
