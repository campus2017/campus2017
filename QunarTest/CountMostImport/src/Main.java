import java.io.*;
import java.util.*;

/**
 * Created by WangWeiyi on 2017/1/7 0007.
 */
public class Main {

    public static void main(String[] args){
        String path = "";
        System.out.println("请输入需要统计的目录");
        Scanner in = new Scanner(System.in);
        path = in.nextLine();
        //path = "C:\\\\Program Files\\\\Java\\\\jdk1.8.0_45\\\\src";

        List<File> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        list = getFiles(path, list);
        for(File f : list){
            try {
                map = getImport(f, map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map = sortImport(map);

        System.out.println("统计结果如下：");
        Iterator it = map.entrySet().iterator();
        int count=1;
        while(it.hasNext()){
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(count+":"+entry.getKey() + ":" + entry.getValue());
            count++;
            if(count >  10)
                break;
        }
    }
    public static Map<String, Integer> sortImport(Map<String, Integer> map){
        ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Map<String, Integer> result = new LinkedHashMap<String, Integer>();
        for(Map.Entry<String, Integer> entry : list){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }

    //读取文件中的引用信息
    public static Map<String, Integer> getImport(File file, Map<String, Integer> map) throws IOException {
        BufferedReader br = null;
        InputStreamReader sr = null;
        try {
            sr = new InputStreamReader(new FileInputStream(file));
            br = new BufferedReader(sr);
            String str = "";
            while((str = br.readLine())!= null){
                if(str.startsWith("import")){
                    str = str.replaceAll("\\s", "").substring(6);
                    if(!map.containsKey(str) ){
                        map.put(str, 1);
                    } else {
                        map.put(str, map.get(str)+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
            sr.close();
        }
        return map;
    }

    //深度优先遍历，获取list
    public static List<File> getFiles(String path, List<File> list){
        File file = new File(path);
        if(file.exists()){
            File[] files = file.listFiles();
            if(files.length > 0){
                for(File f : files){
                    if(f.isDirectory()){
                        getFiles(f.getAbsolutePath(),list);
                    } else {
                        list.add(f);
                    }
                }
            }

        }
        return list;
    }

}
