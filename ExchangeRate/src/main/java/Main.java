import java.io.IOException;

/**
 * Created by asus on 2016/12/30.
 */
public class Main {
    public static void main(String[]args) throws IOException{
        RateData rateData=new RateData();
        ExcelUtils excelUtils = new ExcelUtils();
//        文件输出到D盘根目录，人民币中间价.xls
        excelUtils.exportexcel(rateData.getdata());
    }
}
