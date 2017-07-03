package deal;

import java.io.File;
import java.util.*;

/**
 * Created by chenli on 2017/6/28.
 */
public class AllFileRead {
    String dirPath = null;
    ArrayList<String> allFilePath = new ArrayList<String>();
    OneFileCount oneFileCount = null;
    HashMap<String, Integer> allImport = new HashMap<String, Integer>();
    ArrayList<String> mostTenImport = new ArrayList<String>();

    public AllFileRead(String dirPath){
        this.dirPath = dirPath;
    }

    //得到所有文件路径
    public void traverseFolder(String dirPath){
        File file = new File(dirPath);
        if(!file.exists()){
            System.out.println("文件夹不存在");
            return;
        }
        File[] files = file.listFiles();
        if(files.length == 0){
            System.out.println("文件夹为空");
        }else{
            for(File tmpFile: files){
                if(tmpFile.isDirectory()){
                    traverseFolder(tmpFile.getAbsolutePath());
                }else{
                    allFilePath.add(tmpFile.getAbsolutePath());
                }
            }
        }
    }
    //得到所有import
    public void getAllCount(){
        traverseFolder(dirPath);
        for(String path: allFilePath){
            System.out.println(path);
            try {
                oneFileCount = new OneFileCount(new File(path));
                oneFileCount.getOneFileCount(allImport);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    //对所有import排序
    public ArrayList<String> getMostTenImport(){
        List<Map.Entry<String, Integer>> importList = new ArrayList<>(allImport.entrySet());
        Collections.sort(importList, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                return (o2.getValue().toString().compareTo(o1.getValue().toString()));
            }
        });

        for(int i=0; i<importList.size() && i<10; i++){
            Map.Entry<String, Integer> tmpImport = importList.get(i);
            mostTenImport.add(tmpImport.getKey());
        }
        return mostTenImport;
    }
}
//http://www.cnblogs.com/lovebread/archive/2009/11/23/1609122.html  文件读取
//http://www.cnblogs.com/jxgxy/archive/2012/07/30/2615997.html      正则匹配
//http://www.cnblogs.com/azhqiang/p/4596793.html