package com.zhenghan.qunar;

import com.google.common.collect.Maps;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;
import java.util.Stack;

/**
 * Created by Administrator on 2016/11/12.
 * 该类提供
 * 使用正则去分析js源码中函数全局变量等部分工具函数
 * ScriptEnginer去执行js函数的一些工具函数
 */
public class JScriptParser {
    public static ScriptEngineManager manager  =new ScriptEngineManager();
    /**
     * 需要提取的一些全局变量的名称
     */
    private static String[] VAR_NAME ={"dynamicurl","wzwschallenge","wzwschallengex","template","encoderchars"};

    /**
     *
     * @param evalSource
     * @return
     * @description
     * evalSource是带有eval()进行加密的函数 执行eval后返回的是源码
     * 在js中通常alert eval()中内容就可以得到源码。这里直接将执行函数赋值给一个变量
     * 然后执行。之后取出变量既可得到该eval解密后的源码字符串
     */
    public static String getSource(String evalSource){
        ScriptEngine engine = manager.getEngineByName("javascript");
        evalSource=evalSource.replaceAll("\\)$","");
        evalSource=evalSource.replaceAll("^\\s*eval\\s*\\(","");
        try {
            engine.eval("var source="+evalSource);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
      return (String) engine.get("source");
    }

    /**
     *
     * @param source 源码 形如x=1; 提取出1
     * @param name 需要提取的全局变量的名称
     * @return 返回一个全局变量的值
     * @throws ScriptException
     *
     */
    public static Object getVar(String source,String name) throws ScriptException {
        //首先有5个变量要得到这5个变量首先截取第一个var到;的距离
        ScriptEngine engine = manager.getEngineByName("javascript");
        engine.eval(source);
        return engine.get(name);
    }

    /**
     *
     * @param source 整个js源码
     * @return 返回一个全部的全局变量的名称和值
     * @throws ScriptException
     * @description
     * 得到一个全局变量的名称和值
     */
    public static Map<String,String> getValue(String source) throws ScriptException {
        Map<String,String> params = Maps.newHashMap();
        int last = 0;
        int index = source.indexOf(";");
        for(int i =0;i<VAR_NAME.length;i++){
            params.put(VAR_NAME[i],getVar(source.substring(last,index),VAR_NAME[i]).toString());
            last = index;
            index = source.indexOf(";",last+1);
        }
        return params;
    }
    /**
     * @param source 源码
     * @return 一个js函数字符串 形如 function func1(){...}
     * @description
     * 为了获取函数,压栈一直到碰到'}',那么为一个函数
     * 其中可能因为该函数的字符串变量中含有'}'而出现问题
     * 但是解析回来的几个函数模板中都没有含有'}'的字符串操作所以没有处理这种情况
     * 进行压栈
     *
     */
    public static String getFunc(String source){
        Stack wordStack = new Stack();
        int begin=  source.indexOf("function");
        int end  = source.indexOf('{');
        String funcName = source.substring(begin,end);
        String funcBlock = source.substring(end,source.length());
        int i = 0;
        wordStack.push(funcBlock.charAt(i));
        for(i=1;i<funcBlock.length()&&!wordStack.isEmpty();i++){
            char temp = funcBlock.charAt(i);
            if(temp=='{'){
                wordStack.push('{');
            }else if(temp == '}'){
                wordStack.pop();
            }
        }
        return funcName+funcBlock.substring(0,i);
    }

    /**
     *
      * @param evalSource
     * @param n
     * @return 返回函数的字符串
     * @description
     * 该函数可以得到Js中任意的函数比如你想得到从
     * 文件流的第一个函数那么调用
     * getNFunc(evalFunc,1)
     * 那么将得到第一个函数的字符串
     */
    public static String getNFunc(String evalSource,int n){
        for(int i =0;i<n-1;i++){
            evalSource = evalSource.substring(evalSource.indexOf("function")+1,evalSource.length());
        }
        return getFunc(evalSource.substring(evalSource.indexOf("function"),evalSource.length()));
    }

    /**
     *
     * @param function
     * @return
     * @description
     * 通过函数块得到函数名称
     */
    public static String getFuncName(String function){
        return function.substring(function.indexOf("function")+"function".length(),function.indexOf("(")).trim();
    }

}
