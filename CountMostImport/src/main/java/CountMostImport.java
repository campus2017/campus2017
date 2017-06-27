package main.java;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CountMostImport {

    private static HashMap<String, Integer> CountMap = new HashMap<>();

    public static void  main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String path = in.nextLine();
        CountTop10Class(path);
    }
    private static void CountTop10Class(String Path)
    {
        File root = new File(Path);
        breadthFirstSearch(root);
        List<Map.Entry<String, Integer>> list=new ArrayList<>();
        list.addAll(CountMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        for (int i=0; i<10; i++)
        {
            Map.Entry entry = list.get(i);
            System.out.println(entry.getKey() + "   :    " + entry.getValue()+"次");

        }
    }
    private static void breadthFirstSearch(File root)
    {
        LinkedList<File> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty())
        {
            File parent = queue.pop();
            File[] filelist = parent.listFiles();

            for (int i = 0; i < filelist.length; i++) {
                if (filelist[i].isDirectory()) {
                    queue.add(filelist[i]);
                } else if ((filelist[i].isFile())) {
                    CountImportClassOneFile(filelist[i]);
                }
            }
        }
    }

    private static void CountImportClassOneFile(File file)
    {
        try {
            String filename = file.getName();
            String extensionFileName = getExtensionName(filename);
            if (extensionFileName.equals("java"))
            {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = null;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("import")) {
                        String ImportClassString = line.replace("import", "").trim();
                        storeImportClass(ImportClassString);
                    }
                    else if (line.startsWith("public") || line.startsWith("class"))
                    {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    private static void storeImportClass(String ImportClassString)
    {
        if (CountMap.get(ImportClassString) == null)
        {
            CountMap.put(ImportClassString, 1);
        }
        else
        {
            int value = CountMap.get(ImportClassString);
            CountMap.put(ImportClassString, value+1);
        }
    }


}
