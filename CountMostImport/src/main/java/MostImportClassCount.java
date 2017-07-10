import java.io.*;
import java.util.*;

import static java.lang.System.out;
import java.lang.String;
/**
 * Created by qiongweiren
 */
public class MostImportClassCount {

    private String path = "./src/main/java";
    private int SelectNum = 10;

    public MostImportClassCount()
    {};

    public MostImportClassCount(String my_path)
    {
        path = my_path;
    };

    public MostImportClassCount(String my_path, int num)
    {
        path = my_path;
        SelectNum = num;
    };


    public void run() throws IOException
    {

        List<String> fileAllPathLists = getAllFileName(path);

        HashMap<String, Integer> classHashMap = new HashMap<String, Integer>();   //  stastic;
        classHashMap = CountImportClassNum(fileAllPathLists);

        List<Map.Entry<String,Integer>> list = getSortedClass(classHashMap);

        // output result
        int itemNum = SelectNum < list.size() ? SelectNum:list.size() ;

        for(int i=0; i<itemNum; i++)
        {
            out.println(list.get(i).toString());
        }

    }

    // get all file path
    private List<String> getAllFileName(String path)
    {
        List<String> LIST = new ArrayList<String>();

        String rootPath = path;

        File file = new File(path);
        String[] fileNameLists = file.list();

        String[] tempList = fileNameLists.clone();

        List<String> fold2FIlePathList;

        int i=0;
        for(String fileName:fileNameLists)
        {
            //  FOLD  OR  FILE
            tempList[i] = rootPath +  "/" + fileName;
            file = new File(tempList[i]);

            if(file.isDirectory())
            {
                fold2FIlePathList = getAllFileName(tempList[i]);
                for(String fold2FilePath:fold2FIlePathList)
                {
                    LIST.add(fold2FilePath);
                }
            }
            else
            {
                fileNameLists[i] = rootPath +  "/" + fileName;
                LIST.add(fileNameLists[i]);
            }

            i++;
        }
        return LIST;
    }


    private HashMap<String, Integer> CountImportClassNum(List<String> fileAllPathList) throws IOException
    {

        File file ;

        HashMap<String, Integer> classHashMap = new HashMap<String, Integer>();   //  stastic ;

        for(String fileNameItem:fileAllPathList)
        {
            FileReader reader = new FileReader(fileNameItem);
            BufferedReader br = new BufferedReader(reader);
            String str = null;

            while((str = br.readLine()) != null) {
                str.trim();
                String pattern = "^import.*([a-zA-Z0-9]\\s*;)$";
                if (str.matches(pattern))
                {
                    String str2 = str.replaceAll("\\s*", "");

                    int firstPosi = str2.lastIndexOf(".");
                    int strLen  = str2.length();
                    String tmp = str2.substring(firstPosi+1, strLen-1);

                    if(classHashMap.containsKey(tmp))
                    {
                        classHashMap.put(tmp, classHashMap.get(tmp)+1);
                    }
                    else
                    {
                        classHashMap.put(tmp, 1);
                    }


                }
            }
            br.close();
            reader.close();
        }

        return classHashMap;
    }



    private List<Map.Entry<String,Integer>> getSortedClass(HashMap<String, Integer> classHashMap)
    {

        List<Map.Entry<String,Integer>> list =
                new ArrayList<Map.Entry<String,Integer>>(classHashMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue() - o1.getValue());
            }
        });
        return list;

    }


}


