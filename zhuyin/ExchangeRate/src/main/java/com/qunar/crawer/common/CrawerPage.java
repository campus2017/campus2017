package com.qunar.crawer.common;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.util.zip.ZipInputStream;

/**
 * Created by zhuyin on 16-11-16.
 *分析从今天开始过去 30 天时间里,中国人民银行公布的人民币汇率中间价,得到人民币对美元、欧元、
 港币的汇率,形成 excel 文件输出。汇率数据找相关的数据源,自己爬数据分析。
 */
public class CrawerPage {

    public static String getHtml(String url){
        String html = "";
        HttpGet get=new HttpGet(url);
        HttpClient client = new DefaultHttpClient();
        try {
            HttpResponse response = client.execute(get);
            StatusLine statusLine = response.getStatusLine();
            if(statusLine.getStatusCode()==200){
                HttpEntity entity = response.getEntity();
                html=getHtmlFromEntity(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return html;
    }

    private static String getHtmlFromEntity(HttpEntity entity) throws IOException {

        String html="";
        Header encoding = entity.getContentEncoding();
        if(entity.getContentLength()<2147483647L){
            if(null!=encoding && "gzip".equals(encoding.getValue())){
                EntityUtils.toString(new GzipDecompressingEntity(entity));
            }else{
                html=EntityUtils.toString(entity);
            }
        }else{
            InputStream in = entity.getContent();
            if(in!=null&&"gzip".equals(encoding.getValue())){
                html = unzip(in, ContentType.getOrDefault(entity).getCharset().toString());
            }else{
                html = stream2String(in,ContentType.getOrDefault(entity).getCharset().toString());
            }
        }
        return html;
    }

    private static String stream2String(InputStream in, String charset) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in,charset));
        String line=null;
        while((line=reader.readLine())!=null){
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }

    private static String unzip(InputStream in, String charset) throws IOException {
        ZipInputStream zis = new ZipInputStream(in);
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        int len=0;
        while ((len=zis.read(b))!=-1){
            out.write(b,0,len);
        }
        return new String(out.toByteArray(),charset);
    }
}
