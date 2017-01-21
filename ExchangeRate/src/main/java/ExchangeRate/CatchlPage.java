package ExchangeRate;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 功能：通过url获取响应的html文件
 * Created by yangyening on 2016/12/27.
 *
 */
public class CatchlPage {
    public static String getHtml(String url){
         String html="";
        try {
            HttpGet httpGet=new HttpGet(url);//get请求
            HttpClient httpClient=new DefaultHttpClient();
            HttpResponse httpResponse=httpClient.execute(httpGet);//发送请求
            int  statusCode=httpResponse.getStatusLine().getStatusCode();//获取状态响应码
            if(200==statusCode){
                html=EntityUtils.toString(httpResponse.getEntity());//获取html实体内容
                EntityUtils.consume(httpResponse.getEntity());//消耗掉实体
            }else{
                EntityUtils.consume(httpResponse.getEntity());//消耗掉实体
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  html;
    }
}
