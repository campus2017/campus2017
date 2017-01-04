package com.zhenghan.qunar;

import com.google.common.io.ByteStreams;
import com.zhenghan.qunar.util.JsDecrpt;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/11.
 */
public class RateClient {
    private static Logger logger = LoggerFactory.getLogger(RateClient.class);
    private CloseableHttpClient client = null;
    private String reqCookie = null;
    private static  PoolingHttpClientConnectionManager manager;
    private String url;
    private CookieStore cookieStore;

    static{
        manager =new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(200);
    }
    public RateClient(){
        client=HttpClientBuilder.create().
                setConnectionManager(manager)
                .build();
    }

    /**
     * 解析动态js,通过js引擎。
     * 最后返回html页面
     * @return 得到最终静态的html页面
     * @param url 页面的url
     * @throws ClientProtocolException 如果抛出该异常证明可能需要为该客户端清除cookie重新请求
     * 请尝试3次如果还是失败那么记录日志
     */
    public String requestRateWeb(String url) throws ClientProtocolException{
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        CloseableHttpResponse responseSec = null;
        int redirectCount =0;
        try {
                //如果前面请求到了cookie那么该次请求直接进行
                if (reqCookie != null) {
                    get.setHeader("Cookie", reqCookie);
                }
                try {
                    response = client.execute(get);
                }catch (ClientProtocolException e){
                    System.out.println(url);
                    throw new ClientProtocolException();
                }
                int statusCode = response.getStatusLine().getStatusCode();
                logger.info(statusCode + "");
                if (statusCode == 200) {
                    //如果是第一次访问没有得到Cookie
                    if (reqCookie == null) {
                        HttpGet twoGet = requestWithCookie(response);
                        responseSec = client.execute(twoGet);
                        return new String(ByteStreams.toByteArray(responseSec.getEntity().getContent()), "utf-8");
                    }
                    //如果是后几次访问已经得到Cookie
                    return new String(ByteStreams.toByteArray(response.getEntity().getContent()), "utf-8");
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(response!=null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(responseSec!=null){
                try {
                    responseSec.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 组装Cookie
     * 建立第二次连接Get
     * @param response
     * @return
     * @throws IOException
     */
    public HttpGet requestWithCookie(CloseableHttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        byte[] buffer = ByteStreams.toByteArray(entity.getContent());
        String codeSource= new String(buffer, "utf-8");
        logger.debug(codeSource);
        Map<String,String> map = JsDecrpt.INSTANCE.cookieValue(parserDoc(codeSource));
        logger.info(map.toString());
        HttpGet twoGet =  new HttpGet("http://www.pbc.gov.cn"+map.get("dynamicurl"));
        StringBuilder builder = new StringBuilder("");
        for(Header header :response.getHeaders("Set-Cookie")){
            String temp = header.getValue();
            builder.append(temp.substring(0,temp.indexOf(";")+1)).append(" ");
        }
        builder.append("wzwstemplate="+map.get("wzwstemplate")+"; "+"wzwschallenge="+map.get("wzwschallenge")+";");
        logger.info(builder.toString());
        reqCookie = builder.toString();
        twoGet.setHeader("Cookie",reqCookie);
        return twoGet;
    }

    /**
     * 得到html中script部分
     * @param source
     * @return
     */
    private String parserDoc(String source){
        Document doc= Jsoup.parse(source);
        Element element = doc.getElementsByTag("script").get(0);
        return element.data();
    }
    /**
     * close客户端
     */
    public void shutdown() throws IOException {
        if(client!=null){
            client.close();
        }
    }

    /**
     * 关闭连接池
     */
    public static void shutdownPooling(){
        manager.shutdown();
    }


}
