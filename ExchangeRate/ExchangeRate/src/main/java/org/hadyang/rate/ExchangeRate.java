package org.hadyang.rate;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AstaYang on 2016/11/13.
 */
public class ExchangeRate {
    public static final String BASE_URL =
            "http://www.chinamoney.com.cn/fe/static/html/column/basecurve/rmbparity/";

    private CloseableHttpClient httpClient;
    private String[] xmls = {"dataUSD.xml", "dataHKD.xml", "dataEUR.xml"};

    {
        httpClient = HttpClientBuilder.create().build();
    }

    public List<List<Rate>> getRate() {
        List<List<Rate>> rates = new ArrayList<>(3);

        for (int i = 0; i < xmls.length; i++) {
            ArrayList<Rate> list = new ArrayList<>(30);
            rates.add(list);
            HttpGet get = new HttpGet(BASE_URL + xmls[i]);
            try {
                HttpResponse response = httpClient.execute(get);
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                        .parse(response.getEntity().getContent());

                Element rootElement = doc.getDocumentElement();
                NodeList dates = rootElement.getElementsByTagName("xaxis").item(0).getChildNodes();
                NodeList values = rootElement.getElementsByTagName("graph").item(0).getChildNodes();

                int length = dates.getLength();
                for (int j = 0; j < length && j < 30; j++) {
                    Node date = dates.item(length - j - 1);
                    Node value = values.item(length - j - 1);

                    list.add(new Rate(date.getFirstChild().getNodeValue(),
                            value.getFirstChild().getNodeValue()));
                }
            } catch (IOException | ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }

        return rates;
    }

}
