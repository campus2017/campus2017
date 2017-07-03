package com.main;
import com.main.bean.*;
import java.io.File;
import java.util.Date;
import java.util.List;

public class ExchangeRate {
	    private Date startDate = null;//起始时间，默认是30天之前
	    private Date endDate = null; //结束时间，默认是今天
	    private String dirPath = "./output"; //文件目录的路径信息,默认在"./output"

	    public ExchangeRate(){}

	    public ExchangeRate(String path){
	        dirPath = path;
	    }

	    public ExchangeRate(Date startDate, Date endDate, String dirPath){
	        this.startDate = startDate;
	        this.endDate = endDate;
	        this.dirPath = dirPath;
	    }

	    public String createRateData(){
	        List<data> data = netObtain.getRateData(startDate, endDate);
	        if (data == null) {
	            return "数据爬取失败";
	        }
	        File fileDir = new File(dirPath);
	        if (!fileDir.exists() || !fileDir.isDirectory()){
	            return "文件目录设置出错";
	        }

	        String path = dirPath + "/人民币汇率.xls";
	        if (!createExcel.createExcelFile(path, data)){
	            return "文件生成失败";
	        }

	        return "文件生成路径为：" + path;
	    }




	    public static void main(String[] args) {
	        //long start = System.currentTimeMillis();
	        System.out.println(new ExchangeRate().createRateData());
	        //System.out.println(System.currentTimeMillis()-start);
	    }
}
