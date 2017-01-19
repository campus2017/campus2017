import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author DJ
 * 统计java的有效代码行数
 * 不考虑空格且不包括代码有多行注释的情况
 */

public class SumCode {
	static int spaceLines=0;//空行
	static int commentLines=0;//注释行数
	static int codeLine=0;//实际代码行数
	static boolean flag=false;
	
	public static void getResult(File file) throws IOException{		
		caclute(file);
		int sum=spaceLines+commentLines+codeLine;
		System.out.println("空行："+spaceLines);
		System.out.println("注释行数："+commentLines);
		System.out.println("实际代码行数："+codeLine);
		System.out.println("总代码行数："+sum);
	}
		
	public static void main(String[] args) throws IOException{
		System.out.println("输入文件的相对路径");
		Scanner scan=new Scanner(System.in);
		String filePath=scan.nextLine();//获取到文件的相对路径
		File file=new File(filePath);
		if(file.exists()){			
			getResult(file);//获取到结果信息
		}else{
			System.out.println("文件没有发现");
		}		
	}
	//对每一行的代码进行计算匹配
	public static void caclute(File file) throws IOException{
		FileReader fr = null;
		BufferedReader br=null;
		try {			
			fr = new FileReader(file);			
			br=	new BufferedReader(fr);
			String info=null;
			while((info=br.readLine())!=null){			
				cacluteResult(info);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(br!=null)
			br.close();
			if(fr!=null)
			fr.close();
		}			
	}

	private static void cacluteResult(String info) {
	    info=info.trim();
	    String regex="//.*";//匹配//
	    String regex_space="\\s*";//匹配空格
	    if(info.equals("")){
	    	spaceLines++;
	    }else if(info.startsWith("//")){
	    	commentLines++;
	    }else if(info.startsWith("/*")&&info.endsWith("*/")){
	    	commentLines++;
	    }else if(info.startsWith("/*")&&!info.endsWith("*/")){
	    	commentLines++;
	    	flag=true;
	    }else if(flag){
	    	commentLines++;
	    	if(info.endsWith("*/")){
	    		flag=false;
	    	}
	    }else{
	    	codeLine++;
	    }	    		
	}
}



