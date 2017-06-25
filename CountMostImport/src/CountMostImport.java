import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMostImport {

    String path = new String();
    List<File> allFiles = new ArrayList<File>();
    private static String CUSTOM_PATH = "./";
    private static String STATIC_PROCESS_STRING = "import";
    private static int INITIALIZE_NUM = 1;
    private int IMPORT_MAXNUM = 1;
    private String maxImport = new String();
    List<String> content = new ArrayList<String>();
    List<String> list = new ArrayList<String>();
    Map<String, Integer> numMax = new HashMap<String, Integer>();

    /**
     * @param
     */
    public CountMostImport(String path) {
        this.path = path;
    }

    public CountMostImport() {
        this.path = CUSTOM_PATH;//默认当前文件夹
    }

    public void processContent(List<String> waitForProcessContent) {
        String[] record = StringListToArray(waitForProcessContent);
        Pattern p = Pattern.compile("(?<= ).*(?=;)");
        for (int i = 0; i < record.length; i++) {
            if (record[i].contains(STATIC_PROCESS_STRING)) {
                Matcher m = p.matcher(record[i]);
                while (m.find()) {
                  //  System.out.println(m.group());
                    if (numMax.get(m.group()) == null) {
                        numMax.put(m.group(), INITIALIZE_NUM);
                     } else {
                        int num = numMax.get(m.group()) + 1;
                        if (num > IMPORT_MAXNUM) {
                            IMPORT_MAXNUM= num;
                            maxImport = m.group();
                        }
                        numMax.put(m.group(), num);
                    }
            }
            }
        }
    }

    public void processContent() {
        processContent(this.content);

    }

    public void getContentFromFile(File... files) throws Exception {
        File singleFile;
        for (int i = 0; i < files.length; i++) {
            singleFile = files[i];
            getContentFromFile(singleFile);
        }
    }

    public void getContentFromFile(File file) throws Exception {
        InputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {// 从文本中读取文件
            this.content.add(line);
        }

        if (br != null) {
            br.close();
        }

    }

    public void getContentFromFile(List<File> listfiles) throws Exception {
        File singleFile;
        for (int i = 0; i < listfiles.size(); i++) {
            singleFile = listfiles.get(i);
            getContentFromFile(singleFile);
        }
    }

    public void getContentFromFile() throws Exception {
        getContentFromFile(this.allFiles);
    }

    public File[] fileListToArray(List<File> listFiles) {
        File[] arrayFiles = new File[listFiles.size()];
        for (int i = 0; i < listFiles.size(); i++) {
            arrayFiles[i] = listFiles.get(i);
        }
        return arrayFiles;
    }

    public String[] StringListToArray(List<String> listStings) {
        String[] arrayFiles = new String[listStings.size()];
        for (int i = 0; i < listStings.size(); i++) {
            arrayFiles[i] = listStings.get(i);
        }
        return arrayFiles;
    }

    public void getFilesFromPath(String path) {
        File f = new File(path);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {
                this.allFiles.add(files[i]);
            } else {
                getFilesFromPath(files[i].toString());
            }
        }
    }

    public void getFilesFromPath() {
        getFilesFromPath(this.path);
    }

    public void getTopTen(CountMostImport T,Map<String,Integer> numMax,int max,List<String> list){
        for(Map.Entry<String, Integer> key:numMax.entrySet()){
        if(key.getValue()==max&&!list.contains(key.getKey())) {
                list.add(key.getKey());
             }
        }
        if(max>0)
            T.getTopTen(T,numMax,max-1,list);
        //return list;
    }
    public static void main(String[] args) throws Exception {
// TODO Auto-generated method stub
        CountMostImport T = new CountMostImport();
        T.getFilesFromPath();
        T.getContentFromFile();
        T.processContent();
        T.getTopTen(T,T.numMax,T.IMPORT_MAXNUM,T.list);
        int e=0;
        if(T.list.size()>10)
            e=10;
        else
            e=T.list.size();
        for(int i=0;i<e;i++){
            System.out.println(T.list.get(i));
        }
    }
}
