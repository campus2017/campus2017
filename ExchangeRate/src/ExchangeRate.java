import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/6/14.
 */


public class ExchangeRate {

//   totalCount是当前获取到的记录条数
    private static int totalCount =0;
    //    requireRecord是需要的记录条数
    private static int requireRecords = 30;
    //    网站的网址
    private static String preUrl = "http://www.pbc.gov.cn/zhengcehuobisi/125207/125217/125925/17105/index";

    private  static ArrayList<Exchange> exchangeList = new ArrayList<Exchange>();
// private static String dbURL="jdbc:odbc:ExcelTest"; //数据源连接方式 DSN：ExcelTest


    public static void main(String[] args) {


        int firstPage =1;
//        满足不了需求继续循环
        while(totalCount<requireRecords){

            String url = preUrl+firstPage+".html";
            try {
                findPage(url);
            } catch (IOException e) {
                System.out.println("something wrong with computeRate");
                e.printStackTrace();
            }
            firstPage++;
//            System.out.println("while     ................................"+url);
        }

        System.out.println("一共有几条记录？ "+exchangeList.size());
        insert(exchangeList);

    }
    private static void findPage(String url) throws IOException {
//        System.out.println("进入第二层");
//        System.out.println("now the url is    "+url);
        WebClient webClient = new WebClient();
        //htmlunit 对css和javascript进行支持
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(true);
        //获取页面
        HtmlPage page = webClient.getPage(url);
        webClient.close();


        DomNodeList<DomNode> iList = page.querySelectorAll(".newslist_style");
// 遍历
        for (DomNode i : iList) {
            // 我们用 父元素 div.i 来继续获取 子元素 a 标签。
            HtmlAnchor a =  i.querySelector("a");
            // 获取 a 标签的属性 href ，就是帖子详情的地址啦！！
            String href = a.getAttribute("href");
//            System.out.println(href);
            computeRate("http://www.pbc.gov.cn/"+href);
            totalCount++;
            if (totalCount >= requireRecords) {
                break;
            }
        }


    }

    private static void computeRate(String s) throws IOException {

//        System.out.println("进入第二层");
//        System.out.println(s);
        WebClient webClient = new WebClient();
        //htmlunit 对css和javascript进行支持
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setCssEnabled(false);
        //获取页面
        HtmlPage page = webClient.getPage(s);
        webClient.close();

        Exchange exchange = new Exchange();

        String title = page.getTitleText();
//        System.out.println("get title ...."+title);

        String regEx1 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日";
//        String regEx1="....年.{1,2}.{1,2}日";
        Pattern p1 = Pattern.compile(regEx1);
        Matcher m1 = p1.matcher(title);
        if(m1.find()) {
          exchange.setDay(m1.group());
        }
        HtmlElement zoom = page.getHtmlElementById("zoom");
//        System.out.println("zoom content...................");
        String  text = zoom.getTextContent();
//        System.out.println(text);
//        写正则表达式
        String regEx = "[，,：](\\d*\\.?\\d*)([\\u0391-\\uFFE5]+)对人民币(\\d*\\.?\\d*)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);


        while(m.find()){

            exchange.setYuan(m.group(1));
            if(m.group(2).equals("美元")){


                exchange.setDollar(m.group(3));
            }else if(m.group(2).equals("欧元")){

//                exchange.setYuan(m.group(1));
                exchange.setPounds(m.group(3));

            }else if(m.group(2).equals("港元")){

//                exchange.setYuan(m.group(1));
                exchange.setHongKongDollar(m.group(3));

            }
        }


//            System.out.println(exchange);


        exchangeList.add(exchange);



    }

    static void insert(ArrayList<Exchange> exchangeList){



        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("exchangeRate.xls");
        HSSFRow row1 = sheet.createRow(0);
        HSSFCell cell1 = row1.createCell(0);
        cell1.setCellValue("日期");
        cell1 = row1.createCell(1);
        cell1.setCellValue("人民币");
        cell1 = row1.createCell(2);
        cell1.setCellValue("美元");
        cell1 = row1.createCell(3);
        cell1.setCellValue("英镑");
        cell1 = row1.createCell(4);
        cell1.setCellValue("港币");

        for(int i=0; i<exchangeList.size();i++) {
            //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
            HSSFRow row = sheet.createRow(i+1);
            //第四步，创建单元格，设置表头
            Exchange exchange = exchangeList.get(i);


            for (int j = 0; j < 4; j++) {

                HSSFCell cell = row.createCell(0);
                cell.setCellValue(exchange.getday());
                cell = row.createCell(1);
                cell.setCellValue(exchange.getYuan());
                cell = row.createCell(2);
                cell.setCellValue(exchange.getDollar());
                cell = row.createCell(3);
                cell.setCellValue(exchange.getPounds());
                cell = row.createCell(4);
                cell.setCellValue(exchange.getHongKongDollar());
            }

        }

        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream("E:\\exchangeRate.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}

class Exchange{


	private String day;
	private String yuan;

	String getYuan() {
		return yuan;
	}

	String getDollar() {
		return dollar;
	}

	String getPounds() {
		return pounds;
	}

	String getHongKongDollar() {
		return hongKongDollar;
	}

	private String dollar;

	void setYuan(String yuan) {
		this.yuan = yuan;
	}

	void setDollar(String dollar) {
		this.dollar = dollar;
	}

	void setPounds(String pounds) {
		this.pounds = pounds;
	}

	private String pounds;
	private String hongKongDollar;



	@Override
	public String toString() {
		return "Exchange{" +
				"day='" + day + '\'' +
				", yuan=" + yuan +
				", dollar=" + dollar +
				", pounds=" + pounds +
				", hongKongDollar=" + hongKongDollar +
				'}';
	}

	void setDay(String day) {
		this.day = day;
	}


	String getday() {
		return day;
	}

	void setHongKongDollar(String hongKongDollar) {
		this.hongKongDollar = hongKongDollar;
	}
}