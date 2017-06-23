import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2017/6/21.
 */
public class Main {
    public static void main(String[] args) throws Exception{
        String data[][] = new String[3][31];

        data[0][0] = "港币";
        data[1][0] = "美元";
        data[2][0] = "欧元";

        toSpider(data);
        toExcel(data);
    }
    private static void toSpider(String[][] data) throws Exception {
        for (int i = 0; i < 30; i++) {
            String dateStr = getYMD(i);
            String url = "http://www.kuaiyilicai.com/bank/rmbfx/b-safe.html?querydate="+dateStr;
            String str;

            WebClient webClient = new WebClient(BrowserVersion.CHROME);

            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setCssEnabled(false);

            HtmlPage page = webClient.getPage(url);

            str = page.asText();

            getData(data,i,str);
        }
    }

    private static String getYMD(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -i);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);

        return dateStr;
    }

    public static void toExcel(String[][] data) {
        String path=".\\Excel.xls";

        File excelFile=new File(path);
        try {
            excelFile.createNewFile();
            WritableWorkbook workbook= Workbook.createWorkbook(excelFile);

            WritableSheet sheet = workbook.createSheet("中间价", 0);

            for (int i=0 ; i<31; i++) {
                Label label1 =  new Label(0,i,data[0][i]);
                sheet.addCell(label1);
                Label label2 =  new Label(1,i,data[1][i]);
                sheet.addCell(label2);
                Label label3 =  new Label(2,i,data[2][i]);
                sheet.addCell(label3);
            }

            workbook.write();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[][] getData(String[][] data, int i, String str) {
        String[] lines = str.split("\n");
        boolean flag = false;
        int line = 0;

        for (int j = 0; j < lines.length; j++) {
            String tempString = lines[j];
            if (flag && line < 3) {
                String tempArray[] = tempString.split("\t");
                Double num = 0.0;

                for (int k = 0; k < tempArray.length; k++) {
                    try {
                        num = Double.parseDouble(tempArray[k]);
                        break;
                    } catch (Exception e) {
                    }
                }

                data[line][i + 1] = num + "";
                line++;
            }

            Pattern p = Pattern.compile("\\u53d1\\u5e03\\u65f6\\u95f4.*\\u5907\\u6ce8.*\\u8d70\\u52bf\\u56fe.*");
            Matcher m = p.matcher(tempString);
            if (m.find()) {
                flag = true;
            }
        }
        return data;
    }

}
