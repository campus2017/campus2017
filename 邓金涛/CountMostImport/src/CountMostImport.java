//dengjintao
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class CountMostImport{
    String path;//文件路径
    HashMap<String, Integer> count_Map;//统计过程中，以类名为key，以import数量为value

    public  CountMostImport(String dir){
        this.path = dir;
        count_Map = new HashMap<String, Integer>();    
    }

    //get()方法就是返回对应类名的import次数
    public int get(String class_Name){
        Integer value = count_Map.get(class_Name);
        if(value==null) {
        	return 0;
        }else{
            return value;	
        }
    }
    
    //process方法对文件进行统计import类的数量
    public void process(File file){
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null){
                line = line.trim();//去空格
                if(line.startsWith("public")||line.startsWith("class")){//如果到了Java代码部分直接忽略，因为代码部分没有再import类的语句了
                    break;
                }
                if(line.startsWith("import")){//统计import类放入map中
                    String className = line.substring(6, line.length()-1).trim();
                    Integer value = count_Map.get(className);
                    if(value==null){
                        count_Map.put(className, 1);
                    }else{
                        count_Map.put(className, value+1);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    //判断路径是文件夹还是文件，如果是文件直接用process方法统计，如果是文件夹进行递归
    public void if_It_Is_A_File(File file){
        if(!file.isDirectory()){
            process(file);
        }else{
            File [] files = file.listFiles();
            for(File f: files){
                if_It_Is_A_File(f);
            }
        }

    }

    //使用优先队列，统计前十个import最多的类
    public String ten_Most(){
    	
    	 Comparator<Entry<String,Integer>> compare;
		    compare = new Comparator<Entry<String,Integer>>() {//重写compare方法
		      public int compare(Entry<String,Integer> e1, Entry<String,Integer> e2) {
		        return e2.getValue() - e1.getValue();
		      }
		    };
        PriorityQueue<Entry<String,Integer>> pq=new
                PriorityQueue<Entry<String,Integer>>(compare);//优先队列中使用定义的compare对象，保证Entry中Integer值大的在队列顶端
        for(Entry<String,Integer> item: this.count_Map.entrySet()){
            pq.offer(item);
        }
        StringBuilder result=new StringBuilder();
        result.append("import前十名：\n");
        for(int i=0;i<10;i++){
            Entry<String,Integer>  big=pq.poll();
            if(big==null)
                break;
            result.append("import  ");
            result.append(big.getKey());
            result.append("的数量 ：");
            result.append(big.getValue());
            result.append("\n");
        }
        return result.toString();
    }
    public static void main(String[] args) {
        String p="D:\\eclipse\\eclipse-jee-mars\\workspace\\EffectiveLines\\src\\EffectiveLines.java";
        File directory = new File(p);
        CountMostImport count=new CountMostImport(p);
        count.if_It_Is_A_File(directory);
        System.out.println(count.ten_Most());
    }


 
}

