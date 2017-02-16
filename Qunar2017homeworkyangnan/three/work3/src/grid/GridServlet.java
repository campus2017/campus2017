package grid;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GridServlet extends HttpServlet{
	
	
	data data0=new data(0,"2014-10-28 15:37:21","手机50元话费充值",1,190,0,"充值失败，E币已退回。");
	data data1=new data(1,"2014-10-23 21:36:44","手机50元话费充值",1,190,0,"充值失败，E币已退回。");
	data data2=new data(2,"2014-09-24 11:34:59","博洋梦澜棉被",1,1049,1,"您兑换的商品申请处理成功。");
	data data3=new data(3,"2014-08-29 18:24:37","米奇真空古堡杯",1,323,0,"充值失败，E币已退回。");
	data data4=new data(4,"2014-12-15 15:37:21","博洋梦澜棉被",1,1049,0,"充值失败，E币已退回。");
	data[] datas=new data[5];
		
	public GridServlet(){
		super();
		datas[0]=data0;
		datas[1]=data1;
		datas[2]=data2;
		datas[3]=data3;
		datas[4]=data4;
	}
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String status=request.getParameter("status");
		System.out.println("status:"+status);
		String str="";
		int len=datas.length;
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		PrintWriter out =response.getWriter();

		if(status.equals("all")){
			for(int i=0;i<len;i++){
				str+="{\"id\":"+datas[i].id+",\"time\":\""+datas[i].time+"\",\"name\":\""+datas[i].name+"\",\"num\":"
						+datas[i].num+",\"ecoin\":"+datas[i].ecoin+",\"status\":"+datas[i].status
						+",\"info\":\""+datas[i].info+"\"}";
				str+="&";
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}else{
				str="0";
			}
		}else if(status.equals("success")){
			for(int i=0;i<len;i++){
				if(datas[i].status==1){
					str+="{\"id\":"+datas[i].id+",\"time\":\""+datas[i].time+"\",\"name\":\""+datas[i].name+"\",\"num\":"
							+datas[i].num+",\"ecoin\":"+datas[i].ecoin+",\"status\":"+datas[i].status
							+",\"info\":\""+datas[i].info+"\"}";
					str+="&";
					
				}else{
					continue;
				}
				
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}else{
				str="0";
			}
			
		}else{
			for(int i=0;i<len;i++){
				if(datas[i].status==0){
					str+="{\"id\":"+datas[i].id+",\"time\":\""+datas[i].time+"\",\"name\":\""+datas[i].name+"\",\"num\":"
							+datas[i].num+",\"ecoin\":"+datas[i].ecoin+",\"status\":"+datas[i].status
							+",\"info\":\""+datas[i].info+"\"}";
					str+="&";
					
				}else{
					continue;
				}
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}else{
				str="0";
			}
		}
		System.out.println(str);
		out.write(str);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{	
		String status=request.getParameter("status");
		System.out.println("status:"+status);
		String str="";
		int len=datas.length;
		response.setContentType("text/html;charset=GBK");//解决中文乱码
		PrintWriter out =response.getWriter();

		if(status.equals("all")){
			for(int i=0;i<len;i++){
				str+="{\"id\":"+datas[i].id+",\"time\":\""+datas[i].time+"\",\"name\":\""+datas[i].name+"\",\"num\":"
						+datas[i].num+",\"ecoin\":"+datas[i].ecoin+",\"status\":"+datas[i].status
						+",\"info\":\""+datas[i].info+"\"}";
				str+="&";
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}else{
				str="0";
			}
		}else if(status.equals("success")){
			for(int i=0;i<len;i++){
				if(datas[i].status==1){
					str+="{\"id\":"+datas[i].id+",\"time\":\""+datas[i].time+"\",\"name\":\""+datas[i].name+"\",\"num\":"
							+datas[i].num+",\"ecoin\":"+datas[i].ecoin+",\"status\":"+datas[i].status
							+",\"info\":\""+datas[i].info+"\"}";
					str+="&";
					
				}else{
					continue;
				}
				
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}else{
				str="0";
			}
		}else{
			for(int i=0;i<len;i++){
				if(datas[i].status==0){
					str+="{\"id\":"+datas[i].id+",\"time\":\""+datas[i].time+"\",\"name\":\""+datas[i].name+"\",\"num\":"
							+datas[i].num+",\"ecoin\":"+datas[i].ecoin+",\"status\":"+datas[i].status
							+",\"info\":\""+datas[i].info+"\"}";
					str+="&";
					
				}else{
					continue;
				}
			}
			if(str.length()>0){
				str=str.substring(0,str.length()-1);
			}else{
				str="0";
			}
		}
		System.out.println(str);
		out.write(str);
	}
}
