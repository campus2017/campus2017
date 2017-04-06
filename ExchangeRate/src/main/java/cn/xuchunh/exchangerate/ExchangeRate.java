package cn.xuchunh.exchangerate;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.Number;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.TableColumn;
import org.htmlparser.tags.TableRow;
import org.htmlparser.tags.TableTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created on 2017/4/5.
 *
 * @author XCH
 */
public class ExchangeRate {

    // 数据源
    private static final String DATA_SOURCE_URL = "http://www.chinamoney.com.cn/fe-c/historyParity.do";

    private static RateData getRate() throws IOException, ParserException {

        RateData data = null;

        // 时间参数
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        long current = calendar.getTimeInMillis();
        calendar.add(Calendar.DAY_OF_YEAR, -30);
        long past = calendar.getTimeInMillis();

        // 拉取数据
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(DATA_SOURCE_URL);
        List<NameValuePair> nameValuePairs = Lists.newArrayList();
        nameValuePairs.add(new BasicNameValuePair("startDate", dateFormat.format(past)));
        nameValuePairs.add(new BasicNameValuePair("endDate", dateFormat.format(current)));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, Charsets.UTF_8);
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);

        // 解析html
        if (response != null && response.getStatusLine().getStatusCode() == 200) {
            String html = EntityUtils.toString(response.getEntity());
            Parser parser = Parser.createParser(html, Charsets.UTF_8.displayName());
            NodeList nodeList = parser.extractAllNodesThatMatch(new NodeFilter() {
                @Override
                public boolean accept(Node node) {
                    return node instanceof TableTag;
                }
            });
            TableTag dataTable = (TableTag) nodeList.elementAt(2);

            List<String> headers = Lists.newArrayList();
            TableColumn[] headerColumns = dataTable.getRow(0).getColumns();
            headers.add(((Div) headerColumns[0].getChild(1)).getStringText().trim());
            headers.add(headerColumns[1].getStringText().trim());
            headers.add(headerColumns[2].getStringText().trim());
            headers.add(headerColumns[4].getStringText().trim());

            int rowCount = dataTable.getRowCount();
            Multimap<String, Double> multimap = LinkedListMultimap.create();
            for (int i = 1; i < rowCount; i++) {
                TableRow row = dataTable.getRow(i);
                TableColumn[] columns = row.getColumns();
                String key = ((Div) columns[0].getChild(1)).getStringText().trim();
                multimap.put(key, Double.valueOf(columns[1].getStringText()));
                multimap.put(key, Double.valueOf(columns[2].getStringText()));
                multimap.put(key, Double.valueOf(columns[4].getStringText()));
            }

            data = new RateData(headers, multimap);
        }


        return data;
    }

    private static void save2Excel(String path, String name, RateData data) {
        Preconditions.checkArgument(data != null, "rate data is null");

        List<String> headers = data.getHeaders();
        Preconditions.checkArgument(headers != null && !headers.isEmpty(),
                "can't get headers");

        Multimap<String, Double> multimap = data.getData();
        Preconditions.checkArgument(multimap != null && !multimap.isEmpty(),
                "can't get data");

        if (Strings.isNullOrEmpty(path)) {
            path = "./";
        }
        if (Strings.isNullOrEmpty(name)) {
            name = "rate_data.xls";
        }

        try {
            WritableWorkbook workbook = Workbook.createWorkbook(new File(path, name));
            WritableSheet sheet = workbook.createSheet("sheet1", 0);

            for (int i = 0; i < headers.size(); i++) {
                sheet.addCell(new Label(i, 0, headers.get(i)));
            }

            int j;
            int i = 1;
            for (String date : multimap.keySet()) {
                sheet.addCell(new Label(0, i, date));
                Collection<Double> rates = multimap.get(date);
                j = 1;
                for (Double rate : rates) {
                    sheet.addCell(new Number(j, i, rate));
                    j++;
                }
                i++;
            }
            workbook.write();
            workbook.close();

        } catch (IOException | WriteException e) {
            e.printStackTrace();
        }

    }

    public static void exchangeRate() {
        exchangeRate(null, null);
    }

    /**
     * @param excel 保存的excel文件名，默认值为
     */
    public static void exchangeRate(String excel) {
        exchangeRate(null, excel);
    }

    /**
     * @param path  保存的路径
     * @param excel 保存的excel文件名
     */
    public static void exchangeRate(String path, String excel) {
        try {
            RateData data = getRate();
            save2Excel(path, excel, data);
        } catch (IOException e) {
            System.out.println("get data error");
            e.printStackTrace();
        } catch (ParserException e) {
            System.out.println("parse html error");
            e.printStackTrace();
        }

    }

}
