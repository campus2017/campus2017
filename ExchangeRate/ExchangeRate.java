import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分析从今天开始过去30天,中国人民银行公布的人民币汇率中间价,得到人民币对美元,欧元,港币的汇率,形成Excel文件输出
 */
public class ExchangeRate {

    private List<Double> dollar = new ArrayList<>();        // 美元,记录每次爬取的具体数值
    private List<Double> euro = new ArrayList<>();          // 欧元
    private List<Double> hk = new ArrayList<>();            // 港币

    private String dollarMean;                         // 三者的平均汇率,取5位小数之后转换为字符串
    private String euroMean;
    private String hkMean;

    /**
     * 爬取具体的每天的"中国外汇交易中心受权公布人民币汇率中间价公告"页面中的具体汇率值,具体页面见图2
     */
    private void crawsDetail() {
        Spider spider = new Spider();
        List<String> urls = spider.getCrawlUrls();
        String f = "[0-9]+\\.[0-9]{4}";     // 取出8.3431等浮点数
        Pattern p = Pattern.compile(f);
        Matcher m;
        double dollarTotal = 0;
        double euroTotal = 0;
        double hkTotal = 0;
        int dayCount = urls.size();
        for (String url : urls) {
            Document doc = Spider.jsoupCrawl(Spider.BANK_PREFIX + url);
            Element element = doc.getElementById("zoom");
            String text = element.child(0).text();
            m = p.matcher(text);
            for (int i = 0; i < 4; i++) {
                if (m.find()) {
                    int start = m.start();
                    int end = m.end();
                    double d = Double.parseDouble(text.substring(start, end));
                    if (i == 0) {           // 正则表达式匹配浮点数,第1,2,4个为美元,欧元和港币
                        dollar.add(d);
                        dollarTotal += d;
                    } else if (i == 1) {
                        euro.add(d);
                        euroTotal += d;
                    } else if (i == 3) {
                        hk.add(d);
                        hkTotal += d;
                    }
                }
            }
        }
        double tmpDollarMean = dollarTotal / dayCount;
        double tmpEuroMean = euroTotal / dayCount;
        double tmpHkMean = hkTotal / dayCount;
        this.dollarMean = String.format("%.5f", tmpDollarMean);     // 取结果中的5位小数转换为字符串存储
        this.euroMean = String.format("%.5f", tmpEuroMean);
        this.hkMean = String.format("%.5f", tmpHkMean);
    }

    /**
     * 将汇率信息写入 Excel 文件,位于ExchangeRate文件夹中,ExchangeRate.xls
     */
    private void transToExcel() {

        String[][] context = {{"币种", "近30天平均汇率"},
                {"美元", this.dollarMean},
                {"欧元", this.euroMean},
                {"港币", this.hkMean}};

        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File("ExchangeRate/ExchangeRate.xls"));
            WritableSheet sheet = workbook.createSheet("第一页", 0);

            for (int i = 0; i < 4; i++) {
                sheet.addCell(new Label(0, i, context[i][0]));
                sheet.addCell(new Label(1, i, context[i][1]));
            }
            workbook.write();
            workbook.close();
        } catch (WriteException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.crawsDetail();
        exchangeRate.transToExcel();
    }

}
