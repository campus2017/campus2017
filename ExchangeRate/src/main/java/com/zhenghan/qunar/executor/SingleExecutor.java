package com.zhenghan.qunar.executor;

import com.google.common.collect.Lists;
import com.zhenghan.qunar.HtmlParser;
import com.zhenghan.qunar.RateClient;
import com.zhenghan.qunar.bean.RateBean;
import com.zhenghan.qunar.util.ExecelUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2016/11/15.
 * SingleExecutor单线程直接执行的一个Executor类
 */
public class SingleExecutor extends Executor {
    private  Logger logger = LoggerFactory.getLogger(SingleExecutor.class);
    /**
     * 爬取的30天信息都放在这里
     */
    private List<RateBean> list= Lists.newLinkedList();

    /**
     *
     * @param rateClient
     */
    public SingleExecutor(RateClient rateClient) {
        super(rateClient);
    }

    /**
     * @description
     */
    public void doExecuteGetRateBean(){
        try {
            String str = client.requestRateWeb(host+uri);
            if(str==null){
                throw new ClientRqRateSourceException("重定向多次错误");
            }
            //爬取好每个链接后,设置好每个rateBean的日期和需要待爬取的url
            List<RateBean> templist = initNdays(days,str);
            //解析一页一共需要多久？ 记录日志使用
            long start = System.currentTimeMillis();
            logger.debug("开始解析,该页有:"+templist.size()+"项.");

            //执行后将得到的数据加入list
            for(int count =0;count<days;count++) {
                RateBean rateBean = templist.get(count);
                logger.debug("--------第"+(count)+"项-----------");
                //爬取页面文章并返回文章
                String article = getArticle(rateBean);
                //如果爬取得到的文章为空那么不对该文章进行分析也不加入list
                if(article!=null) {
                    list.add(HtmlParser.INSTANCE.parserArticle(article, rateBean));
                }
            }

            //计算最后爬取完毕后的耗时
            long end = System.currentTimeMillis();
            logger.debug("总解析耗时:"+(end-start)+"");

        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                client.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //执行完毕执行关闭连接池
            RateClient.shutdownPooling();
        }
    }

    /**
     *
     * @param rateBean
     * @return article 也就是每个日期下的文章
     * @throws IOException
     * @throws InterruptedException
     * @description
     * 每篇文章说明了各个国家与人民币兑换的比例
     * 函数通过rateBean中的url请求得到页面提取页面中的文章
     * 并每次爬取延迟了部分时间(自己的ip被封过没有延迟的时候)
     */
    private String getArticle(RateBean rateBean) throws IOException, InterruptedException {
        String article = null;
        int redirectCount = 1;
        while(redirectCount<4&&article==null) {
            try {
                //做好延迟,否则可能被封ip。
                Thread.currentThread().sleep(1500);
                article = client.requestRateWeb(host+rateBean.getUrl());
            } catch (ClientProtocolException e) {
                logger.debug("记录开始redirect次数" + (redirectCount) + ",ip地址是:" + host + rateBean.getUrl());
                logger.debug(e.toString());
                //如果发生异常重新生成一个客户端
                client.shutdown();
                client = new RateClient();
            }
            redirectCount++;
        }
        return article;
    }

    /**
     *
     * @param ldays
     * @param source
     * @return
     * @throws InterruptedException
     * @throws ClientProtocolException
     * @description
     * 初始化需要我们爬取的页面的所以url设置好
     */
    private List<RateBean> initNdays(int ldays,String source) throws InterruptedException, ClientProtocolException {
        List<RateBean> templist = HtmlParser.INSTANCE.pickUpDateLink(source);
        while(templist.size()<ldays){
            Thread.currentThread().sleep(1000);
            templist.addAll(HtmlParser.INSTANCE.pickUpDateLink(client.requestRateWeb(host+HtmlParser.INSTANCE.nextPage(source))));
        }
        return templist;
    }

    /**
     * @param confi
     * @description
     * poi包
     * 通过confi配置的文件路径和表格名称生成
     * execel表格
     */
    public void executePoiExecel(ExecelConfig confi) {
        //将数据进行日期排序得到list
        Collections.sort(list);
        ExecelUtils sheetUtils = ExecelUtils.create(confi.getExecelTableName());
        for(int i =0;i<list.size();i++){
            RateBean rateBean = list.get(i);
            Date rateDate = rateBean.getDate();
            SortedMap<String, BigDecimal>  map =rateBean.getMap();
            //将第一行设置为标题
            if(i==0){
                sheetUtils.setRowTitle((SortedSet<String>) map.keySet());
            }
            //创建一行
            HSSFRow row= sheetUtils.createRow(i+1,rateDate);
            int col = 1;
            //设置每一行的每一列的值
            for(Map.Entry<String, BigDecimal> entry:map.entrySet()){
                sheetUtils.setRowValue(row,col,entry.getValue());
                col++;
            }
        }
        try {
            sheetUtils.exportExecelFile(confi.getPath());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            logger.error("设置正确的目录。程序没有找到正确的路径,请在ExecelConfig类中进行设置");
        }
    }

}
