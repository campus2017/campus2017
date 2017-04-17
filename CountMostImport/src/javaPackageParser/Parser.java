package javaPackageParser;

import FileManager.*;

import java.io.File;
import java.util.*;

/**
 * Created by Administrator on 2017/2/18.
 */
public class Parser {
    //path
    private String path = "";
    private HashMap<String,Integer> classList = null;

    public Parser(String path){
        this.path = path;
    }

    public void Extract(){
        ArrayList<File> fileList = FileManager.GetFileList(path);

        int len = fileList.size();
        File tmp = null;
        classList = new HashMap<String,Integer>();

        try
        {
            for(int i = 0 ;i < len;i++){
                tmp = fileList.get(i);
                FileManager.GetClassList(tmp,classList);
            }
        }
        catch (Exception ex){
            return;
        }
    }

    public ArrayList<ClassEntity> CountTopTen(){
        if(classList == null){
            return null;
        }

        ArrayList<ClassEntity> list =  null;
        FileManager.Method method = FileManager.GetStorageMean();

        if(method == FileManager.Method.HashMap){
            list = CountByHashTable(classList);
        }
        else{
            list = CountByFile();
        }

        return list;
    }

    private ArrayList<ClassEntity> CountByHashTable(HashMap<String,Integer> classList){

        ArrayList<ClassEntity> list =  new ArrayList<ClassEntity>();
        MinHeap<ClassEntity> heap = new MinHeap<ClassEntity>();

        int i = 0;

        // create min heap contains ten values
        for(Map.Entry<String, Integer> entry : classList.entrySet()){
            ClassEntity classEntity = new ClassEntity();

            classEntity.className = entry.getKey();
            classEntity.count = entry.getValue();

            if(++i > 10){
                ClassEntity topEntity = heap.Top();

                if(topEntity.count < classEntity.count){
                    heap.poll();
                    heap.Add(classEntity);
                }
            }
            else {
                heap.Add(classEntity);
            }
        }

        GetListByMinHeap(heap,list);

        return list;
    }

    //get heap data by recursive
    private void GetListByMinHeap(MinHeap<ClassEntity> heap,ArrayList<ClassEntity> list){
        if(heap.Top() == null){
            return;
        }

        ClassEntity tmp = heap.poll();

        GetListByMinHeap(heap,list);

        list.add(tmp);
    }

    private ArrayList<ClassEntity> CountByFile(){
        ArrayList<ClassEntity> list =  new ArrayList<ClassEntity>();
        File[] fileList = FileManager.GetFileList();

        int len = fileList == null ? 0: fileList.length;
        HashMap<String,Integer> map = null;
        HashMap<String,Integer> mapList = new HashMap<String,Integer>();

        for(int i = 0;i < len;i++){
            map = FileManager.GetDataFromFile(fileList[i]);

            if(map != null){
                mapList.putAll(map);
            }
        }

        list = this.CountByHashTable(mapList);

        return list;
    }
}
