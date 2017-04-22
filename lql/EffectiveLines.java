package work;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class EffectiveLines {
	  public int EffectiveLines(String FileName) throws IOException
	    {
	    	FileInputStream File=new FileInputStream(new File(FileName));
	    	BufferedReader read=new BufferedReader(new InputStreamReader(File));
	    	String column;
	    	int columnnumber=0;
	    	while(( column=read.readLine())!=null) 
	        {    
	    		++columnnumber;
	            System.out.println(columnnumber+":"+column);
	        }
	    	return columnnumber;
	    }
}
