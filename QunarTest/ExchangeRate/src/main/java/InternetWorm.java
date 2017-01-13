

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/10.
 */
public class InternetWorm {
    private static HttpClient httpClient = new HttpClient();

    public static String downloadPageHtml(String path) throws IOException {

        GetMethod getMethod = new GetMethod(path);
        String result=null;

        int statusCode = httpClient.executeMethod(getMethod);

        if (statusCode == HttpStatus.SC_OK) {
            result = getMethod.getResponseBodyAsString();
        }
        return result;
    }
}
