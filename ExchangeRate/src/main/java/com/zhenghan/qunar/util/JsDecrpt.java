package com.zhenghan.qunar.util;

import com.zhenghan.qunar.JScriptParser;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/11/13.
 * 使用ScriptEnginer去执行函数
 * 给类提供一个外部接口cookieValue
 * 因为第一次的请求会请求到eval()加密的js。
 * 网页上的逻辑上请求到之后 eval()-->得到源码-->浏览器执行js源码-->利用一些加密函数(每次请求过来的加密函数可能不一样貌似由一个全局变量template来定)
 * -->之后设置好执行了加密函数Cookie之后去重新请求加密url
 * 这里这个类就是用来模拟js。得到Cookie的封装。该类就是上面的逻辑层
 * 只暴露一个函数cookieValue(source)去执行得到需要设置的cookie的值,和该次需要请求的加密的url
 */
public enum JsDecrpt implements DecryptParser {
    INSTANCE;
    public static String ENCRIPTFUNC="";
    public static String CONFIRMFunc ="";
    /**
     *
     * @param encoderchars 全局变量encoderchars的值
     * @param funcName 函数名称
     * @param args 函数参数
     * @param funContent 函数块字符串
     * @return
     * @throws ScriptException
     */
    protected  String  encryptFunction(String encoderchars,String funcName,String args,String funContent) throws ScriptException {
        ScriptEngine engine = JScriptParser.manager.getEngineByName("javascript");
        engine.eval("var encoderchars=\""+encoderchars+"\";");
        engine.eval(funContent);
        engine.eval("var temp="+funcName+"(\""+args+"\");");
        return engine.get("temp").toString();
    }

    /**
     *
     * @param wzwschallenge  全局变量
     * @param wzwschallengex 全局变量
     * @param funcName 函数名称
     * @return
     * @throws ScriptException
     */
    protected  String confirmFunction(String wzwschallenge,String wzwschallengex,String funcName) throws ScriptException {
        ScriptEngine engine = JScriptParser.manager.getEngineByName("javascript");
        engine.eval("var wzwschallenge=\""+wzwschallenge+"\";");
        engine.eval("var wzwschallengex=\""+wzwschallengex+"\";");
        engine.eval(CONFIRMFunc);
        engine.eval("var temp = "+funcName+"();");
        return engine.get("temp").toString();
    }

    /**
     * 得到Cookie直接调用
     * @param source 源码
     * @return 需要设置的cookie的键值,和本次需要去请求的加密的url
     * @description
     * 在爬取得到js加密的源码后直接调用该函数就可以得到需要封装的cookie,需要请求调用的url
     */
    public  Map<String,String> cookieValue(String source){
        try {
            //通过JScriptParser得到想要的函数字符串和想要的全局变量的字符串值
            String code = JScriptParser.getSource((source));
            Map<String,String> map = JScriptParser.getValue(code);
            ENCRIPTFUNC = JScriptParser.getNFunc(code,1);
            CONFIRMFunc = JScriptParser.getNFunc(code,3);

            //执行JS引擎得到对应函数之后的值,之后,通过设置2个cookie和一个动态url
            String wzwstemplate =encryptFunction(map.get("encoderchars"),JScriptParser.getFuncName(ENCRIPTFUNC)
                    ,map.get("template"),ENCRIPTFUNC);
            String wzwschallenge = encryptFunction(map.get("encoderchars"),JScriptParser.getFuncName(ENCRIPTFUNC)
                    ,confirmFunction(map.get("wzwschallenge"), map.get("wzwschallengex"),CONFIRMFunc),ENCRIPTFUNC);
            String dynamicurl = map.get("dynamicurl");

            //清空map,并给需要的cookie和动态url赋值
            map.clear();
            map.put("wzwstemplate",wzwstemplate);
            map.put("wzwschallenge",wzwschallenge);
            map.put("dynamicurl",dynamicurl);
            return map;
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return new HashMap<String, String>();
    }
}
