/**
 * Created by MyPC on 2017/6/28.
 */
import java.io.*;
import java.util.*;
public class main {
    public static void main(String[] args) throws IOException {
        File directory=new File("");
        String path=directory.getCanonicalPath()+"/src/javaprogram/src";
        File file = new File(path);
        Map<String, Integer> Importmap = new HashMap<String, Integer>();
        if(file.isDirectory()){
            File[] dirFile = file.listFiles();
            for(File f:dirFile){
                if(f.isDirectory()){
                    ;
                }
                else{
                    if(f.getName().endsWith(".java")) {
                        String fpath = f.getAbsolutePath().toString();
                        BufferedReader buffer = null;
                        try {
                            buffer = new BufferedReader(new FileReader(fpath));
                            String line = "";
                            while ((line = buffer.readLine()) != null) {
                                if (line.startsWith("import")) {
                                    line = line.trim();
                                    line = line.substring(6);
                                    if (Importmap.get(line) == null) {
                                        Importmap.put(line, 1);
                                    } else
                                        Importmap.put(line, Importmap.get(line) + 1);
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        ArrayList<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String, Integer>>(Importmap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        Map sortedMap = new LinkedHashMap();
        for(int i = 0;i<list.size();i++){
            sortedMap.put(list.get(i).getKey(),list.get(i).getValue());
        }
        Iterator<Map.Entry<String,Integer>> iterator = sortedMap.entrySet().iterator();
        int i =0;
        while(iterator.hasNext()&&i<10){
            Map.Entry<String,Integer> entry = iterator.next();
            System.out.println("import次数第"+(i+1)+"名:"+entry.getKey()+"import次数："+entry.getValue());
            i++;
        }
    }
}
