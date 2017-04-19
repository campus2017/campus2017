import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-7
 * Time: 下午8:21
 * To change this template use File | Settings | File Templates.
 */
//import java.lang.StringBuffer;
public class CountMostImport {
    private static boolean bComment=false;
    public static void main(String[] args) throws IOException {
        String pathname= "D:\\javatest";
        File dir=new File(pathname);
        File[] files=dir.listFiles();
        Map<String,Integer> map = new HashMap<String,Integer>();
        if(dir.isDirectory())
        {
            for(int i=0;i<files.length;i++)
            {
                if(files[i].isFile()&&files[i].getName().matches("^[a-zA-Z[^0-9]]\\w*.java$"))
                {
                    countClass(map,files[i]);
                }
            }
        }
        System.out.println("test");
        Map<String,Integer> newmap=sortMap(map);
        int i=0;
        for(String key:newmap.keySet())
        {   System.out.println(key);
            if(i==9)
                break;
            else
            {
                i++;
            }
        }
        //System.out.println("Hello world!");
    }
    public static void countClass(Map map,File file) throws IOException {
        BufferedReader bf=null;
        bf=new BufferedReader(new FileReader(file));
        String lineStr="";
        while((lineStr=bf.readLine())!=null)
        {
            if(lineStr.matches("^[\\s&&[^\\n]]*$"))
               {
                   continue;
               }
            else if(lineStr.matches("\\s*/\\*{1,}.*(\\*/).*"))
               {
                   continue;
               }
            else if(lineStr.matches("\\s*/\\*{1,}.*[^\\*/].*"))
               {
                   bComment=true;
                   continue;
               }
            else if(true==bComment)
               {
                   if(lineStr.matches("\\s*[\\*/]+\\s*"))
                   {
                       bComment=false;
                   }
                   continue;
               }
            else if(lineStr.matches("^[\\s]*//.*"))
               {
                   continue;
               }
            else
               {
                   if(lineStr.matches("^[\\s]*import.*"))
                   {
                       int end=lineStr.indexOf(';');
                       int start=end;
                       while(lineStr.charAt(start)!='.')
                       {
                           start--;
                       }
                       String sub=lineStr.substring(++start,end);
                       if(!map.containsKey(sub))
                       {
                           map.put(sub,1);
                       }
                       else
                       {
                           Integer num= (Integer) map.get(sub);
                           num++;
                           map.remove(sub);
                           map.put(sub, num);
                       }
                   }
               }
        }
    }
    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Map.Entry<String, Integer> arg0,
                               Map.Entry<String, Integer> arg1) {
                return arg1.getValue() - arg0.getValue();
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }
}
