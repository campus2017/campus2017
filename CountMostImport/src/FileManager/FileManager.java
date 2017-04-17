package FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/2/18.
 */
public class FileManager {
    public enum  Method{
        HashMap,
        File
    }
    private static Method StorageMean = Method.HashMap;//class name storage mean HashMap or File
    private static File[] FileList = null;

    public static Boolean IsLegalDir(String path){
        boolean ret= false;
        if(path == null || path.trim().length() ==0){
            return ret;
        }

        File dir = new File(path);

        if (dir.exists()) {
            if (dir.isDirectory()) {
                ret = true;
            } else {
               ret = false;
            }
        }
        else {
            ret = false;
        }

        dir = null;
        return ret;
    }

    public static ArrayList<File> GetFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles();
        ArrayList<File> fileList = new ArrayList<File>();

        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) {
                    ArrayList<File> tmp = GetFileList(files[i].getAbsolutePath());
                    if(tmp != null && tmp.size() > 0){
                        fileList.addAll(tmp);
                    }
                } else if (fileName.endsWith("java")) {
                    fileList.add(files[i]);
                } else {
                    continue;
                }
            }
        }

        return fileList;
    }

    public static void GetClassList(File file, HashMap<String,Integer> classList)throws IOException {
        ArrayList<String> list = AnalysisFile(file);

        int listLen = list.size();
        int len = classList.size() + listLen;
        String tmp = "";

        if(len > 10000){
            try {
                if(StorageMean == Method.HashMap){
                    StorageMean = Method.File;
                    InitFile();
                }

                int index = 0;
                String key = "";

                for(Map.Entry<String, Integer> entry : classList.entrySet()){
                    key = entry.getKey();
                    index = GetFileIndex(key);
                    WriteByFile("classname_" + index + ".txt",key);
                }
            }
            catch (Exception e){

            }
        }
        else{
            for(int i = 0;i < listLen;i++){
                tmp = list.get(i);
                if(classList.containsKey(tmp)){
                    classList.put(tmp,classList.get(tmp) + 1);
                }
                else{
                    classList.put(tmp,1);
                }
            }
        }
    }

    public static Method GetStorageMean()
    {
        return StorageMean;
    }

    public static File[] GetFileList() {
        return FileList;
    }

    public static HashMap<String,Integer> GetDataFromFile(File file) {
        HashMap<String,Integer> map = new HashMap<String,Integer>();

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;

            while ((temp = reader.readLine()) != null) {
                if(map.containsKey(temp)){
                    map.put(temp,map.get(temp) +1);
                }
                else{
                    map.put(temp,1);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            map = null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return map;
    }

    private static void InitFile() throws IOException {
        String filePath = "classname_";
        FileList = new File[26];

        for(int i = 0;i < 26;i++){
            File file = new File(filePath + i + ".txt");
            try {
                if(file.exists()){
                   file.delete();
                }
                file.createNewFile();
                FileList[i] = file;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void WriteByFile(String path, String content) throws IOException {
        FileWriter fw = null;

        try {
            fw = new FileWriter(path);
            fw.append(content + "\n");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (fw != null) {
                fw.close();
                fw = null;
            }
        }
    }

    private static int GetFileIndex(String className){
        //return 0-25
        int len = className.length();
        int ret = 0;
        char tmp = ' ';

        for(int i = 0;i < len;i++){
            tmp = className.charAt(i);

            if(tmp == ' ' || tmp == '.'){
                ret += tmp;
            }
        }

        return tmp % 26;
    }

    private static ArrayList<String> AnalysisFile(File file){
        BufferedReader reader = null;
        ArrayList<String> list = new ArrayList<String>();

        try {
            reader = new BufferedReader(new FileReader(file));
            String temp = null;

            while ((temp = reader.readLine()) != null) {
                if(IsClassLines(temp)) {
                    ArrayList<String> tmpList = GetClassName(temp.trim());

                    if(tmpList != null){
                        int len = tmpList.size();

                        for(int i = 0;i < len;i++){
                            list.add(tmpList.get(i));
                        }
                    }
                }
                else{
                    if(temp.indexOf(" class ")>0){
                        break;
                    }
                }
            }

            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return list;
    }

    private static boolean IsClassLines(String line){
        if(line != null && line.trim().startsWith("import ")){
            return  true;
        }

        return false;
    }

    private static ArrayList<String> GetClassName(String line){
        String[] classList = null;
        ArrayList<String> retList = new ArrayList<String>();

        classList = line.split(";");

        if(classList == null || classList.length == 0){
            return null;
        }

        int len = classList.length;
        String className = "";

        for(int i = 0; i < len;i++){
            className = classList[i].trim();

            if(className.startsWith("import")){
                className = className.substring(6);
            }
            else{
                continue;
            }

            className = className.trim();

            if(className.startsWith("static")){
                className = className.substring(6);
            }

            retList.add(className.trim());
        }

        return retList;
    }
}
