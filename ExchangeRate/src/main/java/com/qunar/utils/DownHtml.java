package com.qunar.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by isc on 2017/1/9.
 * 根据直接抓取的html发现是一段js
 * 执行此js得到第二段js
 * 执行第二段js获取两个cookie值
 * 把cookie值传入，得到真正的html
 */
public class DownHtml {

    Map<String, String> cookies = new HashMap<String, String>();
    String baseUrl = "http://www.pbc.gov.cn";

    /**
     * 使用jsoup提供的接口访问url
     * @param url 传入网站url
     * @return 返回泛型Optional<Document>
     */
    public Optional<Document> connect(String url) {

        Connection connect = Jsoup.connect(url);
        Connection.Response response = null;
        try {
            response = connect.execute();
            //保存cookie
            cookies.putAll(response.cookies());
            Document document = response.parse();
            //获取script标签及内容
            Elements elements = document.getElementsByTag("script");
            //js代码的格式为 eval(..function(){}()..)
            String jsCode = elements.get(0).html();
            //去掉外层的eval（）
            jsCode = jsCode.substring(jsCode.indexOf("(") + 1, jsCode.lastIndexOf(")"));

            //执行
            Optional<String> secondJS = executeFirstJS(jsCode);//执行第一段JS
            Optional<Document> documentRe = executeSecondJS(secondJS.get());//获取html
            return documentRe;
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * 执行第一段JS，用于获取第二段JS
     *
     * @param code 第一段JS
     * @return 第二段JS
     */
    private Optional<String> executeFirstJS(String code) {
        try {
            //获取js执行引擎
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
            code = "var result = " + code + ";";
            engine.eval(code);
            String result = (String) engine.get("result");
            return Optional.of(result);     // of方法通过工厂方法创建Optional类,
        } catch (Exception e) {
            System.err.println("第一段JS执行出错");
            e.printStackTrace();
            return Optional.empty();
        }
    }

    /**
     * 执行第二段JS
     * @param code
     * @return document
     */
    private Optional<Document> executeSecondJS(String code) {
        try {
            //获取js执行引擎
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
            //去掉自动执行过程，改为手动执行，
            code = code.substring(0, code.lastIndexOf("}") + 1);//去掉HXXTTKKLLPPP5();
            engine.eval(code);
            Invocable invocable = (Invocable) engine;
            //分析js，生成第一个cookie
            Integer template = (Integer) engine.get("template");
            String wzwstemplate = (String) invocable.invokeFunction("KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU", String.valueOf(template));
            cookies.put("wzwstemplate", wzwstemplate);//保存cookie
            // 生成第二个cookie
            String confirm = (String) invocable.invokeFunction("QWERTASDFGXYSF");
            String wzwschallenge = (String) invocable.invokeFunction("KTKY2RBD9NHPBCIHV9ZMEQQDARSLVFDU", confirm);
            cookies.put("wzwschallenge", wzwschallenge);//保存cookie
            //获取动态url
            String dynamicurl = (String) engine.get("dynamicurl");
            String newUrl = baseUrl + dynamicurl;
            Connection connection = Jsoup.connect(newUrl).cookies(cookies);
            Document document = connection.execute().parse();
            return Optional.of(document);
        } catch (Exception e) {
            System.err.println("第二段JS执行出错");
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
