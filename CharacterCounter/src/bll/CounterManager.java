package bll;


import controller.IndexController;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Collections.*;

/**
 * Created by Administrator on 2017/2/10.
 */
public class CounterManager {
    public CounterManager(){

    }

    public void TextManager(String text,HttpServletResponse response,String type){
        Map<String, String> retMap = this.TextResolver(text);
        if(retMap == null){
            this.RenderData(response,null,type);
            return;
        }

        JSONObject jsonObject = JSONObject.fromObject(retMap);
        this.RenderData(response,jsonObject,type);
    }

    //analysis the text
    private Map<String, String> TextResolver(String text){
        if(text == null || text.length()<=0){
            return  null;
        }

        Map<String, String> retMap = new HashMap<String, String>(10);

        int engCount = 0,numCount = 0,chnCount = 0,symCount = 0,len = text.length();

        char tmp = ' ';
        for(int i = 0;i < len;i++){
            tmp = text.charAt(i);
            //english
            if((tmp >= 'a' && tmp <= 'z') || (tmp >= 'A' && tmp <= 'Z')){
                engCount++;
            }
            else if(tmp >='0' && tmp <= '9'){//num
                numCount++;
            }
            else if(IsChinese(tmp)){
                chnCount++;
            }
            else if(IsChinesePunctuation(tmp)||IsEnglishPunctuation(tmp)){
                symCount++;
            }
        }

        retMap.put("engCount",engCount + "");
        retMap.put("numCount",numCount + "");
        retMap.put("chnCount",chnCount + "");
        retMap.put("symCount",symCount + "");

        //get top 3
        List<Map.Entry<String,String>> list =
                new ArrayList<Map.Entry<String,String>>(retMap.entrySet());

        sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
                return (Integer.parseInt(o2.getValue())-Integer.parseInt(o1.getValue()));
            }
        });

        if(list == null || list.size() != 4){
            return null;
        }

        retMap.put("firstName",list.get(0).getKey());
        retMap.put("firstCount",list.get(0).getValue());
        retMap.put("secondName",list.get(1).getKey());
        retMap.put("secondCount",list.get(1).getValue());
        retMap.put("thirdName",list.get(2).getKey());
        retMap.put("thirdCount",list.get(2).getValue());

        return retMap;
    }

    //response the result
    private void RenderData(HttpServletResponse response, JSONObject data, String type) {
        PrintWriter printWriter = null;
        String msg = "";

        if(type == "file"){
            msg="<script>parent.formCallBack('" +"1"+ "'," + data + ")</script>";
        }
        else{
            msg = data+"";
        }

        try {
            printWriter = response.getWriter();
            printWriter.print(msg);
        } catch (IOException ex) {
            Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (null != printWriter) {
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    private boolean IsChinese(char t){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(t);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
            return true;
        }
        return false;
    }

    public boolean IsChinesePunctuation(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
            return true;
        } else {
            return false;
        }
    }

    public boolean IsEnglishPunctuation(char ch) {
        if (0x21 <= ch && ch <= 0x2F) return true;
        if (0x3A <= ch && ch <= 0x40) return true;
        if (0x5B <= ch && ch <= 0x60) return true;
        if (0x7B <= ch && ch <= 0x7E) return true;

        return false;
    }
}
