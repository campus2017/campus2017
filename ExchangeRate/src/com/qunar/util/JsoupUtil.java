package com.qunar.util;

import com.qunar.vo.RateVo;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 张瑞浩
 * @Date: 2017/5/2
 * @Description:
 */
public class JsoupUtil {
	String url = null;
	//static Logger logger = Logger.getLogger(JsoupUtil.class);
	public  JsoupUtil(String url) throws IOException{
		this.url = url;
		getElements(url);
	}

	/**
	 * 取得html后遍历节点元素输出到execl中
	 * @param url
	 * @throws IOException
	 */
	public void getElements(String url) throws IOException{
		//System.out.println("url:"+url);
		//logger.info("开始爬虫");
		List<RateVo> list = new ArrayList<RateVo>();
		Document document = null;
		document = Jsoup.connect(url).get();
		Element element_table = document.getElementsByTag("table").get(4);
		Elements element_tr = element_table.getElementsByTag("tr");
		for(int i = 1; i < element_tr.size();i++){
			//System.out.println();
			Elements element_td = new Elements();
			element_td = element_tr.get(i).getElementsByTag("td");
			/*for(int j = 0; j < element_td.size(); j++){
				//System.out.print(element_td.get(j).text()+" ");
			}*/
			RateVo rateVo = new RateVo();
			rateVo.setDate(element_td.get(0).text());
			rateVo.setDollar(element_td.get(1).text());
			rateVo.setEuro(element_td.get(2).text());
			rateVo.setHkdollar(element_td.get(4).text());

			list.add(rateVo);
		}
//		for(RateVo rv:list){
//			System.out.println(rv.getDate()+" "+rv.getDollar()+" "+rv.getEuro()+" "+rv.getHkdollar());
//		}
		//logger.info("爬虫完成");
		ExcelUtil.OutPutToExcel(list);

	}
}
