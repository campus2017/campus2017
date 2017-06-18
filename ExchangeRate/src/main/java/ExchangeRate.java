import bean.RateData;
import utils.CreateExcelUtils;
import utils.WebParseUtils;

import java.io.File;
import java.util.Date;
import java.util.List;


/**
 * Created by libo on 2017/6/13.
 *
 * 爬取数据，生成excel文件
 */


public class ExchangeRate {
    private Date startDate = null;       //爬取汇率的起始时间，默认是30天之前
    private Date endDate = null;         //爬取汇率的结束时间，默认是今天
    private String dirPath = "./ExcelDir";            //给出保存的文件目录的路径信息,默认在"./ExcelDir"

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
        List<RateData> data = WebParseUtils.getRateData(startDate, endDate);
        if (data == null) {
            return "数据爬取失败";
        }
        File fileDir = new File(dirPath);
        if (!fileDir.exists() || !fileDir.isDirectory()){
            return "文件目录设置错误";
        }

        String path = dirPath + "/人民币汇率中间价.xls";
        if (!CreateExcelUtils.createExcelFile(path, data)){
            return "文件生成失败";
        }

        return "文件生成成功，所在路径为：" + path;
    }




    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new ExchangeRate().createRateData());
        System.out.println(System.currentTimeMillis()-start);
    }
}
