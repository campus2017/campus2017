package work;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ExchangeRate {
	public List<String> getExchangeInfo() 
	{   HttpURLConnection url=null;
	    List<String> infoList=new ArrayList<String>();
		try{
		String ccpassport=decodeJS.getCcpassport();
		System.out.println(ccpassport);
		String[] urlArray={"http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index2.html",
				"http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/index.html"};
		for(int i=0;i<2;++i)
		{  url=(HttpURLConnection)new URL(urlArray[i]).openConnection();
		   url.setRequestProperty("Cookie", ccpassport);
		   url.connect();
		   Pattern pattern=Pattern.compile("/zhengcehuobisi/125207/125217/125925/3\\d{6}/index.html");
		   BufferedReader buffer=new BufferedReader(new InputStreamReader(url.getInputStream(),"UTF-8"));
		   String line;
		   while((line=buffer.readLine())!=null)
		   {
			   Matcher matcher=pattern.matcher(line);
			   while(matcher.find())
			   {  
				  System.out.println(line.substring(matcher.start(),matcher.end()));
				  Thread thread=new Thread(new NetReadThread(line.substring(matcher.start(),matcher.end()),infoList,ccpassport));
				  thread.start();
			   }
		   }
		   
		}
		while(Thread.currentThread().activeCount()>1) Thread.sleep(1000);
		   return infoList;
	  }
	  catch(Exception e)
	  {
		e.printStackTrace();
		System.out.println(e.getMessage());
		return null;
	  }
	  finally
	  {
		  if(url!=null) url.disconnect();
	  }
	}
	public Set<ViewObject> getInfoDetail()
	{     Set<ViewObject> daySet=new TreeSet<ViewObject>();
		  List<String> list=getExchangeInfo();
		  for(String info:list)
		  { 
			int year=Integer.parseInt(info.substring(22,26));
			int monthKey=Character.isDigit(info.charAt(28))?29:28;
			int month=Integer.parseInt(info.substring(27,monthKey));
			int dayKey=monthKey+(Character.isDigit(info.charAt(monthKey+2))?3:2);
			int day=Integer.parseInt(info.substring(monthKey+1,dayKey));
			ViewObject vo=new ViewObject(year,month,day);
			info=info.substring(dayKey+18,info.length()-5);
			String[] CountryExchange=info.split("，|,");
			for(String exchange:CountryExchange)
			{
				int index=exchange.indexOf('对');
				String str1=exchange.substring(0,index).trim();
				String str2=exchange.substring(index+1).trim();
				if(str1.startsWith("人民币"))
				    vo.put(str2,str1);
				else vo.put(str1, str2);
			}
			daySet.add(vo);
		  }
		  return daySet;
	}
	public void writeToTxt() throws Exception
	{
		File file=new File("E://exchange.txt");
		file.createNewFile();
		Set<ViewObject> vos=getInfoDetail();
		BufferedWriter buffer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
	    for(ViewObject vo: vos)
	    {
	    	buffer.write(vo.getDate().toString());
	    	buffer.write("\r\n");
	    	Map<String,String> map=vo.getMap();
	        for(Entry<String,String> entry :map.entrySet())	
	        {
	        	buffer.write(entry.getKey());
	        	for(int i=0;i<14-entry.getKey().length();++i) buffer.write(" ");
	        	buffer.write(entry.getValue());
	            buffer.write("\r\n");
	        }
	        buffer.write("\r\n");
	        buffer.flush();
	    }
	    buffer.close();
	}
	public static void main(String[] args) throws Exception
    { 
         new ExchangeRate().writeToTxt();
    }
}
class ViewObject implements Comparable<ViewObject>
{   private PublishDate publishDay;
    private Map<String,String> exchangeMap=new LinkedHashMap<String,String>();
	public ViewObject(int year,int month,int day)
	{
		 publishDay=new PublishDate(year,month,day);
	}
	public PublishDate getDate() {return publishDay;}
    public void put(String key,String value)
	{
		exchangeMap.put(key, value);
	}
	public Map<String,String> getMap() {return exchangeMap;}
	public int compareTo(ViewObject vo)
	{
		return this.publishDay.compareTo(vo.publishDay);
	}
}
class PublishDate implements Comparable<PublishDate>
{
	int year;
	int month;
	int day;
	public PublishDate(int year,int month,int day)
	{
		this.year=year;
		this.month=month;
		this.day=day;
	}
	public int compareTo(PublishDate date)
	{
	   return 365*(year-date.year)+30*(month-date.month)+day-date.day;	
	}
	public String toString()
	{
		StringBuilder sb=new StringBuilder();
		sb.append(year).append("年");
		if(month<10) sb.append(0);
		sb.append(month).append("月");
		if(day<10) sb.append(0);
		sb.append(day).append("日");
		return sb.toString();
	}
	
}
class NetReadThread implements Runnable
{   private String net;
    private List<String> infoList;
    private String  ccpassport;
    public NetReadThread(String net,List<String> list,String  ccpassport)
    {
    	this.net=net;
    	this.ccpassport=ccpassport;
    	infoList=list;
    }
	public void run()
	{    HttpURLConnection tempUrl=null; 
	     BufferedReader tempBuffer=null;
		try{
		  tempUrl=(HttpURLConnection)new URL("http://www.pbc.gov.cn"+net).openConnection();
		  tempUrl.setRequestProperty("Cookie", ccpassport);
		  tempBuffer=new BufferedReader(new InputStreamReader(tempUrl.getInputStream(),"UTF-8"));
		   String tempLine;
		   while((tempLine=tempBuffer.readLine())!=null)
		   {   //System.out.println(tempLine.trim()); 
			   if(tempLine.trim().equals("<div id=\"zoom\">"))
				   break;
		   }
		   tempLine=tempBuffer.readLine();
		   tempBuffer.close();
		   infoList.add(tempLine.trim());
	     }
	    catch(Exception e)
	   {
	       e.printStackTrace();
	       System.out.println(e.getMessage());
	      
	   }
	   finally
	   {
		 if(tempUrl!=null)  tempUrl.disconnect();
		 if(tempBuffer!=null)
			try {
				tempBuffer.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	   }
	
	}
}
class decodeJS//模拟请求 得到ccport;
{
	String dynamicurl = "/";
	String wzwschallenge;
	String wzwschallengex;
	String encoderchars="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	int[] num;
	String label;
	public decodeJS(String wzwschallenge,String  wzwschallengex,int[] num,String label)
	{
		this.wzwschallenge=wzwschallenge;
		this. wzwschallengex=wzwschallengex;
		this.num=num;
		this.label=label;
	}
	private  String KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU(String str)
	{
		String out="";
		int i=0,len=str.length();
		int c1,c2,c3;
		while(i<len)
		{
			c1=str.charAt(i++)&0xff;
			if(i==len)
			{
				out += encoderchars.charAt(c1 >> 2);
				out += encoderchars.charAt((c1 & 0x3) << 4);
				out += "==";
				break;
			}
			c2 = str.charAt(i++);
			if (i == len) {
				out += encoderchars.charAt(c1 >> 2);
				out += encoderchars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xf0) >> 4));
				out += encoderchars.charAt((c2 & 0xf) << 2);
				out += "=";
				break;
			}
			c3 = str.charAt(i++);
			out += encoderchars.charAt(c1 >> 2);
			out += encoderchars.charAt(((c1 & 0x3) << 4) | ((c2 & 0xf0) >> 4));
			out += encoderchars.charAt(((c2 & 0xf) << 2) | ((c3 & 0xc0) >> 6));
			out += encoderchars.charAt(c3 & 0x3f);
			
		}
	    return out;
		
	}
	private String QWERTASDFGXYSF() {
		String tmp =wzwschallenge+wzwschallengex;
		int hash = 0;
		for (int i = 0; i < tmp.length(); i++) {
			hash += tmp.charAt(i);
		}
		hash *= num[1];
		hash += 111111;
		return label + hash;
	}
	private String[] decode() {
	        String cookieString1 = "wzwstemplate=" + KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU(String.valueOf(num[0])) + ";";
			String confirm = QWERTASDFGXYSF();
			String cookieString2 = " wzwschallenge=" + KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU(confirm) + ";";
			return new String[]{cookieString1,cookieString2};
		
	}
	public static String getCcpassport()
	{   HttpURLConnection url=null;
	    HttpURLConnection url1=null;
		try{
		url=(HttpURLConnection)(new URL("http://www.pbc.gov.cn").openConnection());
    	BufferedReader buffer=new BufferedReader(new InputStreamReader(url.getInputStream(),"UTF-8"));
    	String line;
    	while((line=buffer.readLine())!=null)
    	{
    		if(line.startsWith("eval")) break;
    	}
    	buffer.close();
    	int[] num=new int[2];
        String[] valueSet=line.substring(line.indexOf("0xff")+5).split("\\|");
        Pattern pattern=Pattern.compile("\\w \\*= (\\d|[abcd]);\\w \\+= \\d");
        Matcher m=pattern.matcher(line);
        while(m.find()) 
        {
        	char c=line.charAt(m.start()+5);
        	num[1]=Integer.parseInt(valueSet[c-(c>=97?94:55)]);
        }
         pattern=Pattern.compile("[de]\\+\\/=");
        m=pattern.matcher(line);
        while(m.find()) 
        {
        	char c=line.charAt(m.start()-10);
        	num[0]=Integer.parseInt(valueSet[c-(c>=97?94:55)]);
        }
        String wzwschallenge="";
		String wzwschallengex="";
		String label="";
	    for(int i=4;true;++i)
	    {
	    	if(valueSet[i].charAt(0)=='A')
	    	{
	    		wzwschallenge=valueSet[i+4];
	    		wzwschallengex=valueSet[i+7];
	    		label=valueSet[i+5];
	    		break;
	    	}
	    }
	    //_gscs_1042262807=82218119f2s7xr18|pv:3; _gscbrs_1042262807=1
	    decodeJS js=new decodeJS(wzwschallenge,wzwschallengex,num,label);
	    String decodeRes[]=js.decode();
		//System.out.println(js.decode()[0]+" "+js.decode()[1]);
		StringBuilder cookieBuilder=new StringBuilder();
		cookieBuilder.append(url.getHeaderField(6).substring(0,url.getHeaderField(6).indexOf('p')));
		cookieBuilder.append(url.getHeaderField(7).substring(0,url.getHeaderField(7).indexOf('p')));
		cookieBuilder.append(decodeRes[0]+" "+decodeRes[1]);
		//cookieBuilder.append(" _gscu_1042262807=82202708g6n5pr18;").append(" _gscs_1042262807=82218119f2s7xr18|pv:3;");
	    // Proxy proxy=new Proxy(Proxy.Type.HTTP,new InetSocketAddress("127.0.0.1",8888));
	     url1=(HttpURLConnection)(new URL("http://www.pbc.gov.cn/L2VuZ2xpc2gv").openConnection());
		 url1.addRequestProperty("Cookie",cookieBuilder.toString());
		 url1.connect();
         return url1.getHeaderField(6);
         
	}
	catch(Exception e)
	{
		e.printStackTrace();
		System.out.println(e.getMessage());
		return "";
	}
	finally
	{
		if(url1!=null)url1.disconnect();
		if(url!=null)url.disconnect();
		
	}

	}
}





