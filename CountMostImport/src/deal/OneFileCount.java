package deal;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenli on 2017/6/28.
 */
public class OneFileCount {
    //BufferedReader bufferReader = null;
    File file = null;
    Pattern pattern = Pattern.compile("import\\s+(.*?);");



    public OneFileCount(File file){
        this.file = file;
    }

    //得到一个文件的所有内容
    //http://blog.csdn.net/fireson/article/details/6697606
    public String getFileContent(){
        Long fileLength = file.length();
        String encoding = "UTF-8";
        byte[] allContent = new byte[fileLength.intValue()];
        try{
            FileInputStream inputStream = new FileInputStream(file);
            inputStream.read(allContent);
            inputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            return new String(allContent, encoding);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //统计一个文件中import个数
    public void getOneFileCount(HashMap<String, Integer> allImport){
        String fileContent = getFileContent();
        //System.out.println(fileContent);
        Matcher matcher = pattern.matcher(fileContent);
        //ArrayList<String> allImport = new ArrayList<String>();
        while(matcher.find()){
            String oneImport = matcher.group(1);
            if(allImport.containsKey(oneImport)){
                allImport.put(oneImport, allImport.get(oneImport)+1);
            }else{
                allImport.put(oneImport, 1);
            }
        }
    }
}
