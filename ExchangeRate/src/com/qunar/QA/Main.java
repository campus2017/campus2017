package com.qunar.QA;

/**
 * @author  Nicole
 * @Time  2017/7/2
 * @Description 分析从今天开始过去30天时间里，中国人民银行公布的人民币汇率中间价，得到人民币对美元、欧元、港币的汇率，形成excel文件输出。
 * 在当前目录的excel文件夹下生成了表格ExchangeRate.xls
 */

import org.jsoup.nodes.Document;

public class Main {

    public static void main(String[] args) {

        String dol_url="http://www.kuaiyilicai.com/huilv/d-safe-usd.html";       // 人民币对美元的汇率网站
        String eur_url="http://www.kuaiyilicai.com/huilv/d-safe-eur.html";       // 人民币对欧元的汇率网站
        String hk_url="http://www.kuaiyilicai.com/huilv/d-safe-hkd.html";        // 人民币对港币的汇率网站
    //    String jap_url="http://www.kuaiyilicai.com/huilv/d-safe-jpy.html";     // 人民币对日元的汇率网站
    //    String pou_url="http://www.kuaiyilicai.com/huilv/d-safe-jpy.html";     // 人民币对英镑的汇率网站

        LinkWebByJsoup lw=new LinkWebByJsoup();
        Document dol_doc=lw.linkWebByJsoup(dol_url);
        Document eur_doc=lw.linkWebByJsoup(eur_url);
        Document hk_doc=lw.linkWebByJsoup(hk_url);

        GetExchangeRate ger=new GetExchangeRate();
        String[][] dol_data=ger.getExchangeRate(dol_doc);              // 获取人民币对美元的汇率数据（包括日期和汇率）
        String[][] eur_data=ger.getExchangeRate(eur_doc);              // 获取人民币对欧元的汇率数据（包括日期和汇率）
        String[][] hk_data=ger.getExchangeRate(hk_doc);                // 获取人民币对港币的汇率数据（包括日期和汇率）

        CreateExcel ce=new CreateExcel();
        ce.createExcl(dol_data,eur_data,hk_data);                          //创建汇率的excel表格
    }
}
