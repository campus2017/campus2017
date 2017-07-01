import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Administrator on 2017/02/21.
 */
public class countMostImport {
    List<File> allFiles = new ArrayList<File>(); //保存所有java文件路径
    List<String> content = new ArrayList<String>();
    //List<String>  specialContent= new ArrayList<String>();
    Map <String,Integer> specialContent=new HashMap<String,Integer>();
    Map <String,Integer> importNum=new HashMap<String,Integer>(); //记录不同的import的个数
    private static String keyWord="import";
    private static int initNum = 1;
    private static int Num = 10;

    public static void main(String[] args) throws Exception
    {
          String path="E:\\CountMostImport";
          countMostImport T=new countMostImport();
          T.getFilesFromPath(path);
          T.getContentFromFile();
          T.result();

    }

    public void getFilesFromPath(String path)
    {
        File f = new File(path);  //读取路径下的文件,包括文件夹
        File[] files = f.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (!files[i].isDirectory()) {   //isDirectory 判断是否为目录
                this.allFiles.add(files[i]);
            } else {
                getFilesFromPath(files[i].toString());
            }
        }
    }

    public void getContentFromFile() throws Exception
    {
        this.getContentFromFile(allFiles);
    }

    public void getContentFromFile(List<File> listfiles) throws Exception {
        File singleFile;
        for (int i = 0; i < listfiles.size(); i++) {
            singleFile = listfiles.get(i);
            getContentFromFile(singleFile);
        }
    }

    public void getContentFromFile(File file) throws Exception {
        InputStream in = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";

        //Pattern p=Pattern.compile("(?<= ).*(?=;)"); //定义规范

        Pattern p=Pattern.compile("\\s*import\\s\\s*.*;\\s*");

        while ((line = br.readLine()) != null) {  // 从文本中读取每行，并判断此行是否含有import
            if(line.contains(keyWord))
            {
                Matcher m=p.matcher(line);
                while(m.find())
                {
                    int lastIndex=m.group().indexOf(";");
                    int firstIndex=m.group().lastIndexOf(" ");
                    int midIndex=m.group().lastIndexOf(".");
                    String str1=m.group().substring(midIndex+1,lastIndex);
                    String str2=m.group().substring(firstIndex+1,lastIndex);
                    if(str1.equals("*")) {  //如果遇到 import java.io.* 则先保存起来，等到遍历完所有导入类再进行处理
                        if(specialContent.get(str2)==null)
                            specialContent.put(str2,initNum);
                        else
                        {
                            int num=specialContent.get(str2)+1;
                            specialContent.put(str2,num);
                        }
                        continue;
                    }

                    if(importNum.get(str2)==null)

                        importNum.put(str2,initNum);
                    else
                        {
                            int num=importNum.get(str2)+1;
                            importNum.put(str2,num);
                        }
                }
            }

        }
        if (br != null) {
            br.close();
        }

    }

    public void result() throws Exception
    {
        //对保存的特殊导入先处理，例如：import java.io.*
        for (String key : specialContent.keySet()) {
            //System.out.println("key= "+ key + " and value= " + map.get(key));
            int num=specialContent.get(key);
            int lastIndex=key.lastIndexOf("*");
            String str=key.substring(0,lastIndex);
               for(String classKey : importNum.keySet())
               {
                   if(classKey.contains(str)) {
                       importNum.put(classKey,num+importNum.get(classKey));
                   }

               }
        }

        //再进行排序
        List<Map.Entry<String, Integer>> infoIds =new ArrayList<Map.Entry<String, Integer>>(importNum.entrySet());
        Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String,Integer> oper1,Map.Entry<String,Integer> oper2)
            {
                return (oper2.getValue()-oper1.getValue());
            }
        });
        for(int i=0;i<infoIds.size() && i<Num;i++)
        {
            String temp = infoIds.get(i).toString();
            System.out.println(temp);
            System.out.print("\n");
        }
    }

}
