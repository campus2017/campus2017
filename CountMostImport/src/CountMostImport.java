import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by fmz on 2017/6/25.
 */
public class CountMostImport {
    public static Map<String, CountClass> map = null;
    public static ArrayList<CountClass> result = null;

    public static void main(String[] args) {
        System.out.println("Input directory path:");
        Scanner scanner = new Scanner(System.in);
        String dirUrl = scanner.nextLine();

        if(dirUrl.trim().isEmpty() || dirUrl == null) {
            System.out.println("Directory is invalid!");
            return;
        }

        File dir = new File(dirUrl);
        if(!dir.exists()) {
            System.out.println("Directory is not exists!");
            return;
        }

        File[] fileList = dir.listFiles();
        map = new HashMap<String, CountClass>();



        for(File f : fileList) {
            if(f.isDirectory()) {
                continue;
            }

            count(f);
        }

        List<CountClass> arr = new ArrayList<CountClass>();
        for(String s : map.keySet()) {
            arr.add(map.get(s));
        }

        Collections.sort(arr);

        if(arr.size()<10) {
            for(int i=0; i<arr.size(); ++i) {
                System.out.println("Class name: " + arr.get(i).name + "  count: " + arr.get(i).count);
            }
        } else {
            for(int i=0; i<10; ++i) {
                System.out.println("Class name: " + arr.get(i).name + "  count: " + arr.get(i).count);
            }
        }
    }

    public static void count(File file) {
        if(!file.getName().endsWith(".java")) {
            return;
        }
        //System.out.println(file.getName());
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null) {
                if(!line.trim().startsWith("import")) {
                    continue;
                }
                //System.out.println(line);
                String[] splits = line.trim().split("\\.");
                //System.out.println(splits.length);
                if(splits.length==0 || splits[splits.length-1].trim().endsWith("*")) {
                    continue;
                }



                String className = splits[splits.length-1];
                if(className.charAt(0)>='A' && className.charAt(0)<='Z') {
                    String[] t = line.trim().split(" ");
                    className = t[t.length-1];
                    if(map.containsKey(className)) {
                        map.get(className).count += 1;
                    } else {
                        map.put(className, new CountClass(className, 1));
                    }
                }

            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }
    }
}




class CountClass implements Comparable<CountClass> {
    public String name;
    public int count;

    public CountClass(String s, int i) {
        name = s;
        count = i;
    }

    @Override
    public int compareTo(CountClass o) {
        return o.count - count;
    }
}