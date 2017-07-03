
import com.raw.ExcelDeal;
import com.raw.JsoupDeal;
import com.raw.NationRate;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenli on 2017/1/2.
 */
//http://blog.csdn.net/chenleixing/article/details/47121679
public class ExRate {
    String url = "http://www.kuaiyilicai.com/huilv/d-safe-"; //usd.html
    String[] nations = {"美元", "欧元", "港币"};
    String[] tmpUrl = {"usd.html", "eur.html", "hkd.html"};
    HashMap<String, ArrayList<NationRate>> nationRates = new HashMap<String, ArrayList<NationRate>>();
    String excelName = "./30天中行人民币对美欧港汇率.xls";
    public ExRate(){
    }

    public static void main(String[] args){
        ExRate exRate = new ExRate();
        for(int i=0; i<exRate.nations.length; i++){     //3个国家
            JsoupDeal jDeal = new JsoupDeal(exRate.nations[i], exRate.url + exRate.tmpUrl[i]);
            exRate.nationRates.put(exRate.nations[i], jDeal.getOneMonth());
        }
        ExcelDeal excelDeal = new ExcelDeal(exRate.nationRates, exRate.excelName);
        excelDeal.readToExcel();
    }

}
