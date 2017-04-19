import java.io.*;
import java.util.*;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 17-4-8
 * Time: 上午10:50
 * To change this template use File | Settings | File Templates.
 */
public class Exam1 {
    public static void main(String[] args) throws IOException {
        String textPath;
        textPath = "D:\\unorderedmsg.txt";
        File file=new File(textPath);
        BufferedReader bf=null;
        bf=new BufferedReader(new FileReader(file));
        String lineStr="";
        ArrayList<String> arraylist=null;
        arraylist=new ArrayList<String>();
        int n=0;
        while((lineStr=bf.readLine())!=null)
        {
             if(lineStr.matches("(.*)\\【..\\】.*"))
             {
                 n++;
                 if(n==1)
                 {
                    lineStr=lineStr.substring(1);
                 }
                 arraylist.add(lineStr);
             }
        }
        Map<String,String> map = new HashMap<String,String>();
        for(String ele:arraylist)
        {
             //System.out.println(ele);
            String[] strings=ele.split(" ");
            System.out.println(strings[4]+" "+strings[5]);
            map.put(ele,strings[4]+" "+strings[5]);
        }
        Map<String,String> newMap = null;
        newMap=sortMap(map);
        String path="D:\\orderedmsg.txt" ;
        writeFile(newMap,path);
        countNumber(newMap);
//        for(String key:newMap.keySet())
//        {
//            System.out.println(key);
//        }
//        //System.out.println(" ");
//        //System.out.println(arraylist.toString());
//        System.out.println(n);
    }
    public static Map sortMap(Map oldMap) {
        ArrayList<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(oldMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {

            @Override
            public int compare(Map.Entry<String, String> arg0,
                               Map.Entry<String, String> arg1) {
                String[] s=arg0.getValue().split(" ");
                String[] date0=s[0].split("-");
                String[] time0=s[1].split(":");
                String[] s1=arg1.getValue().split(" ");
                String[] date1=s1[0].split("-");
                String[] time1=s1[1].split(":");
                if(!date0[0].equals(date1[0]))
                {
                    return Integer.valueOf(date0[0])-Integer.valueOf(date1[0]);
                }
                else if(!date0[1].equals(date1[1]))
                {
                    return Integer.valueOf(date0[1])-Integer.valueOf(date1[1]);
                }
                else if(!date0[2].equals(date1[2]))
                {
                    return Integer.valueOf(date0[2])-Integer.valueOf(date1[2]);
                }
                else if(!time0[0].equals(time1[0]))
                {
                    return Integer.valueOf(time0[0])-Integer.valueOf(time1[0]);
                }
                else if(!time0[1].equals(time1[1]))
                {
                    return Integer.valueOf(time0[1])-Integer.valueOf(time1[1]);
                }
                else if(!time0[2].equals(time1[2]))
                {
                    return Integer.valueOf(time0[2])-Integer.valueOf(time1[2]);
                }
                else
                {
                    String[] ss0=arg0.getKey().split(" ");
                    String[] ss1=arg1.getKey().split(" ");
                    return ss0[0].compareTo(ss1[0]);
                }
            }
        });
        Map newMap = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            newMap.put(list.get(i).getKey(), list.get(i).getValue());
        }
        return newMap;
    }

    public static void writeFile(Map<String,String> map,String path) throws IOException {
        FileWriter writer;
        try {
            writer=new FileWriter(path);
            for(String key:map.keySet())
            {
                writer.write(key.concat("\r\n"));
                //System.out.println();
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
    public static void countNumber(Map<String,String> map)
    {
        Map<String,Integer> m=new HashMap<String, Integer>();
        for(String key:map.keySet())
        {
            String[] s=key.split(" ");
            if(!m.containsKey(s[0]))
            {
                m.put(s[0],1);
            }
            else
            {
                Integer i=m.get(s[0]);
                m.remove(s[0]);
                m.put(s[0],++i);
            }
        }
        FileWriter writer;
        String path="D:\\count.txt";
        try {
            writer=new FileWriter(path);
            for(String key:m.keySet())
            {
                writer.write(key+"   "+m.get(key)+"\r\n");
                //System.out.println();
//                writer.write("    ");
//                writer.write(m.get(key));
//                writer.write("\r\n");
            }
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
