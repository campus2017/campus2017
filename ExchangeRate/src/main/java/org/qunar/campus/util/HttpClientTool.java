package org.qunar.campus.util;

import org.apache.commons.collections.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * 调用能力平台接口工具
 * 
 */
public class HttpClientTool {
	protected static Logger log = LoggerFactory.getLogger(HttpClientTool.class);
	// 返回结果处理
	private static ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
		public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
			int status = response.getStatusLine().getStatusCode();
			if (status >= 200 && status <= 600) {
				HttpEntity entity = response.getEntity();
				return entity != null ? EntityUtils.toString(entity, "utf-8") : null;
			} else {
				throw new ClientProtocolException("Unexpected response status: " + status);
			}
		}
	};

	

	/**
	 * 获取请求参数
	 * 
	 * @param params
	 * @return
	 */
	private static List<NameValuePair> getPostParam(Map<String, Object> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (String key : params.keySet()) {
				nvps.add(new BasicNameValuePair(key, params.get(key) == null ? "" : params.get(key).toString()));
			}
		}
		return nvps;
	}
	
	/**
	 * post方式发起请求，获取返回数据，参数是json字符串,返回也是json字符串
	 * 
	 * @param reqUrl
	 * @param paramJson
	 * @return
	 */
	//为了避免打印照片造成日志过大，占用了大部分的磁盘空间,此接口只有照片相关的才会调，所以不打印日志
	public static String doPostJson(String reqUrl, String paramJson) {
		// 获取数据
		String responseBody = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(reqUrl);
		try {
				//log.debug("[doPost请求地址]" + reqUrl);
				//log.debug("[doPost请求参数]" + paramJson);
//			httppost.addHeader("Content-type","application/json; charset=utf-8");
//			httppost.setHeader("Accept", "application/json");
			StringEntity se = new StringEntity(paramJson, "utf-8");
//			se.setContentEncoding("UTF-8");
			se.setContentType("application/json");
			httppost.setEntity(se);
			responseBody = httpclient.execute(httppost, responseHandler);
			log.debug("[doPost返回报文]" + responseBody);
		} catch (Exception e) {
			String errMsg = "[doPost调用 " + reqUrl + " 异常]";
			log.error(errMsg, e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return responseBody;
	}
	
	/**
	 * get方式发起请求，获取返回数据
	 * @param intfUrl
	 * @param params
	 * @return
	 */
	public static String doGet(String intfUrl, Map<String, Object> params) {
		// 获取数据
		String responseBody = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			String reqUrl = getGetUrlByParams(intfUrl, params); //拼接最终get请求url
			HttpGet httpGet = new HttpGet(reqUrl);
			log.debug("[doGet req]" + reqUrl);
//			HttpGet httpGet = new HttpGet(
//					reqUrl + "?" + EntityUtils.toString(new UrlEncodedFormEntity(getPostParam(param), Charsets.UTF_8)));
			responseBody = httpclient.execute(httpGet, responseHandler);
			log.debug("[doGet resp]" + responseBody);
		} catch (Exception e) {
			String errMsg = "[doGet exception]";
			log.error(errMsg, e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return responseBody;
	}
	
	/**
	 * post方式发起请求，获取返回数据, 参数是 类似表单提交的方式
	 * 
	 * @param reqUrl
	 * @param param
	 * @return
	 */
	public static String doPost(String reqUrl, Map<String, Object> param) {
		// 获取数据
		String responseBody = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		log.debug("[doPost req url]" + reqUrl);
		//log.debug("[doPost req param]" + param);
		HttpPost httppost = new HttpPost(reqUrl);
		try {
			httppost.setEntity(new UrlEncodedFormEntity(getPostParam(param), Charset.forName("UTF-8")));
			responseBody = httpclient.execute(httppost, responseHandler);
			log.debug("[doPost resp]" + responseBody);
		} catch (Exception e) {
			String errMsg = "[doPost " + reqUrl + " exception]";
			log.error(errMsg, e);
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}
		return responseBody;
	}
	
	/**
	 * 根据 请求参数Map，和接口地址，拼装 get请求的最终url
	 * 注意map只能一层结构，不支持多层级
	 * @param intfUrl
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private static String getGetUrlByParams(String intfUrl, Map<String, Object> params) throws UnsupportedEncodingException {
		StringBuffer reqUrl = new StringBuffer();
		
		if (MapUtils.isNotEmpty(params)) {
			reqUrl.append(intfUrl + "?");
			
			for (String key : params.keySet()) {
				reqUrl.append(key);
				reqUrl.append("=");
				reqUrl.append(URLEncoder.encode(params.get(key).toString(), "UTF-8")); //不然get的url会有特殊字符里报错
				reqUrl.append("&");
			}
			
			reqUrl.deleteCharAt(reqUrl.length()-1);
		}
		return reqUrl.toString();
	}
	
}
