package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by chenli on 2017/1/2.
 */
public class FileCount {
    String fileName = null;
    int count = 0;
    public Boolean fileExist  = true;
    public FileCount(String fileName){
        this.fileName = fileName;
        count = 0;
    }
    //判断文件中每一行是否有效
    public Boolean effOneline(String line){
        if(line == null || line.trim().length() <= 0){
            return false;
        }
        line = line.trim();
        if(line.charAt(0) == '/' && line.charAt(1) == '/'){
            return false;
        }
        return true;
    }
    //fileName文件有效行
    public int readByLine(){
        File file = new File(fileName);
        //文件是否存在
        if( !file.exists() ){
            System.out.println("输入文件路径无效.");
            fileExist = false;
            return -1;
        }
        BufferedReader bufferReader = null;
        try{
            bufferReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bufferReader.readLine()) != null ){
                if(effOneline(line)){
                    count++;
                }
            }
            bufferReader.close();
        }catch(IOException e){
            e.printStackTrace();
            return -1;
        }finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                }
            }
        }
        return count;
    }
}
