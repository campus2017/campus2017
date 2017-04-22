import java.io.*;
import java.util.*;

/**
 * Created by wuyaqi on 17-4-9.
 */
public class CountMostImport {
    private String dirName;
    private HashMap<String,Integer> importClassNum;

    public CountMostImport(String dir) {
        this.dirName = dir;
        this.importClassNum = new HashMap<String, Integer>();
        this.readAllFile(new File(dir));
    }
    public void readAllFile(File dirfile)
    {

        if(!dirfile.isDirectory())//如果不是目录，就对该文件进行处理
        {
            readFile(dirfile);
        }
        else //如果是目录，就自己递归调用
        {
            File[] filelist = dirfile.listFiles();
           for(File tempFile:filelist)
           {
               readAllFile(tempFile);
           }
        }
    }
    public void readFile(File file)//对文件进行读取，找出import之后的类。
    {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            String linetemp = null;
            while((linetemp = reader.readLine())!=null)
            {
                linetemp = linetemp.trim();//去掉前后空格
                if( linetemp.startsWith("public")|| linetemp.startsWith("class"))
                {
                    break;
                }
                if(linetemp.startsWith("import"))
                {
                    String tempclass = linetemp.substring(7,linetemp.length()-1);
                    Integer tempvalue = importClassNum.get(tempclass);
                    if(tempvalue != null)
                    {
                        tempvalue += +1;
                    }
                    else{
                        tempvalue = 1;
                    }
                    importClassNum.put(tempclass,tempvalue);
                }
            }
            reader.close();

        } catch (IOException e1){
            e1.printStackTrace();
        }

    }
    public List<Map.Entry<String,Integer>> getTop10Import()
    {
        int max = Integer.MIN_VALUE;

        List<Map.Entry<String,Integer> > listImport = new ArrayList<Map.Entry<String, Integer>>();

        for(Map.Entry<String,Integer> entry:importClassNum.entrySet())
        {
            listImport.add(entry);
        }
        if(listImport.size()<10)
            return listImport;
        //对list按照指定比较规则进行排序，
        Collections.sort(listImport,new Comparator<Map.Entry<String, Integer> >(){
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue()-o1.getValue();}
            //逆序（从大到小）排列，正序为“return o1.getValue()-o2.getValue”
        });
        List<Map.Entry<String,Integer> > top10list = new ArrayList<Map.Entry<String, Integer>>();

        for(int i = 0;i<10;i++)
        {
            top10list.add(listImport.get(i));
        }
        return top10list;

    }
    public void printImportcount()
    {
        for(Map.Entry<String,Integer> entry:importClassNum.entrySet())
        {
            System.out.println(entry.getKey() +" " + entry.getValue());
        }
    }
    public static void main(String[] args) {
        System.out.println("请输入java项目目录：");
        Scanner in = new Scanner(System.in);
        String dirname = in.nextLine();
        CountMostImport countimport = new CountMostImport(dirname);
        List<Map.Entry<String,Integer>> Top10Import = countimport.getTop10Import();
        //countimport.printImportcount();


        for(Map.Entry<String,Integer> entry:Top10Import)
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
