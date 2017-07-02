package util;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;


import java.util.Map;

public class HttpUtil {
    public static String getRequest(String url, Map<String,String> header){
        HttpClient client = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(10000).build();

        String res = "";
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);

        for(Map.Entry<String, String> entry:header.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            request.setHeader(key, value);
        }
        try {
            HttpResponse response = client.execute(request);
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                res = EntityUtils.toString(response.getEntity());
            }else{
                res = EntityUtils.toString(response.getEntity());
            }
        }catch (Exception ex){
        }
        return res;

    }
}
