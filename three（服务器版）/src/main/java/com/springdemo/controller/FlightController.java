package com.springdemo.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
public class FlightController {

    @RequestMapping(value="/index",method=RequestMethod.GET,produces="text/html; charset=UTF-8")
    @ResponseBody
    public String index(){
        String data = "[{'date':'2014-10-28','time':'15:37:21','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-10-23','time':'21:36:44','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-10-21','time':'15:29:18','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-10-21','time':'15:06:51','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-10-10','time':'14:56:28','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回。测试用例'},{'date':'2014-09-25','time':'14:03:00','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-09-24','time':'11:35:38','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回。测试用例。'},{'date':'2014-09-24','time':'11:34:59','name':'博洋梦澜棉被','amount':'1','eCoin':'190','state':'兑换失败','info':'您兑换的商品申请处理失败，金币已退回。测试用例。'},{'date':'2014-09-22','time':'15:34:59','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-09-01','time':'12:38:36','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'},{'date':'2014-08-29','time':'18:26:05','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回。失败处理'},{'date':'2014-08-29','time':'18:24:37','name':'米奇真空古堡杯','amount':'1','eCoin':'323','state':'兑换失败','info':'您兑换的商品申请处理失败，金币已退回。处理中，失败处理。'},{'date':'2014-08-18','time':'17:41:38','name':'手机50元话费充值','amount':'1','eCoin':'190','state':'兑换失败','info':'充值失败，E币已退回'}]";
        return data;
    }
    
	public String ReadFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(
					fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
}
