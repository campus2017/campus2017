package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CountMostImports {
  
    public void change(String[] res,int[] count,int i)
    {
    	int j=i;
		while(j<5&&(count[j]>count[2*j+1]||count[j]>count[2*j+2]))
	    {
		 	if(count[2*j+2]<count[2*j+1])
		 	{
		 		String temp=res[2*j+2];
		 		res[2*j+2]=res[j];
		 		res[j]=temp;
		 		count[2*j+2]=count[2*j+2]^count[j];
		 		count[j]=count[2*j+2]^count[j];
		 		count[2*j+2]=count[2*j+2]^count[j];
		 		j=2*j+2;
		 	}
		 	else
		 	{
		 		String temp=res[2*j+1];
		 		res[2*j+1]=res[j];
		 		res[j]=temp;
		 		count[2*j+1]=count[2*j+1]^count[j];
		 		count[j]=count[2*j+1]^count[j];
		 		count[2*j+1]=count[2*j+1]^count[j];
		 		j=2*j+1;
		 	}
	    }
    }
 
    public String[] ClassTree(Map<String,Integer> ClassMap)
    {  
       String[] res=new String[11];
       int[] count=new int[11];
       Iterator<String> iter=ClassMap.keySet().iterator();
       int i=0;
       while(iter.hasNext())
       {
           String cur=iter.next();
    	   if(i<11)
    	   { res[i]=cur;
    	     count[i++]=ClassMap.get(cur);
    	    if(i==11) 
    	    {
    	    	for(int j=4;j>=0;--j) change(res,count,j);
    	    }
    	   }
    	   else if(ClassMap.get(cur)>count[0])
    	   {        res[0]=cur;
    			   count[0]=ClassMap.get(cur);
    			   change(res,count,0);
    		}
    }
       return res;	
    }
    public String[]  CountMostImport(String pathName) throws IOException//E:\\JAVA_file\\workspace\\test\\src\\test
    {  File file=new File(pathName);
	   File[] list=file.listFiles();
       
	   HashMap<String,Integer> ClassMap=new HashMap<String,Integer>();
	   for(File temp: list)
	   {   
		  Runnable r=new FileReadThread(pathName+"\\"+temp.getName());
		  Thread t=new Thread(r);
		  t.start();
		  
		}
	    while(Thread.activeCount()>1){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	   return ClassTree(FileReadThread.ClassMap);
    }
    public static void main(String[] args) throws IOException
    {  /* String[] res={"java.lang.annotation.ElementType","java.lang.annotation.Inherited",
    		          "java.util.ArrayList","java.util.Arrays","java.util.Calendar",
    		          "java.util.Collections","java.util.Comparator","java.util.Date",
    		          "java.util.HashMap","java.util.HashSet","java.util.ListIterator",
    		          "java.util.Map","java.util.Queue","java.util.Scanner","java.util.Set",
    		          "java.util.Stack","java.util.TreeSet","java.lang.reflect.Method"};
        
       HashMap<String,Integer> map=new HashMap<String,Integer>();
       for(String s:res)
       {
    	   int n=(int) (Math.random()*100);
    	   System.out.println(s+" "+n+"th");
    	   map.put(s,n);
       }
       res=new Work().ClassTree(map);
       for(String s:res)
       {
    	   System.out.println(s+" "+map.get(s));
       }*/
      String[] res=new CountMostImports().CountMostImport("E:\\test1");
      for(String s:res)
      {
   	   System.out.println(s+" "+FileReadThread.ClassMap.get(s));
      }
      
    }
}
class FileReadThread implements Runnable
{   public static HashMap<String,Integer> ClassMap=new HashMap<String,Integer>();
    private static Object lock=new Object();
    public String Filename;
    public int count=0;
    private FileInputStream input;
	private BufferedReader read;
    public FileReadThread(String Filename) throws FileNotFoundException 
    {
    	this.Filename=Filename;
    	input=new FileInputStream(Filename);
		 read=new BufferedReader(new InputStreamReader(input));}
	public void run()
	{  
	   boolean flag=false;
	   String column;
	   try {
		while((column=read.readLine())!=null)
		   {    System.out.println(Thread.currentThread().getName());
			   if(column.startsWith("import"))
			   {   ++count;
				   flag=true;
				   column=column.substring(7);
				   synchronized(lock)
				   {
					if(ClassMap.containsKey(column)) ClassMap.put(column,ClassMap.get(column)+1);
				    else ClassMap.put(column,1);
				   }
			   }
			   else if(flag) break; 
				   
		   }
	} catch (IOException e) {
		
	}
	 
	}
}