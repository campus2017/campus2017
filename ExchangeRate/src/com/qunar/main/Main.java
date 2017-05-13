package com.qunar.main;

import com.qunar.util.DateUtil;
import com.qunar.util.JsoupUtil;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by DELL on 2017/5/1.
 * Description:程序主入口
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Logger logger = Logger.getLogger(Main.class);
		logger.info("程序开始");
		new JsoupUtil("http://www.safe.gov.cn/AppStructured/view/project_RMBQuery.action?projectBean.startDate="+DateUtil.getMonthAgoDate()+"&projectBean.endDate="+DateUtil.getCurrentDate());
		logger.info("运行结束");
	}
}
