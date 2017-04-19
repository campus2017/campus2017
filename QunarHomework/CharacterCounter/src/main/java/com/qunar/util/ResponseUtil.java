package com.qunar.util;

/**
 * Created by Abby on 17/2/5.
 */
import net.sf.json.JSONObject;

import java.util.List;

public class ResponseUtil {
    public static String  returnSuccess(List<JSONObject> data){
        JSONObject params = new JSONObject();
        params.put("ret", "true");
        params.put("errcode", 0);
        params.put("data", data);
        return params.toString();
    }
    public static String returnFail(String error){
        JSONObject params = new JSONObject();
        params.put("ret", "false");
        params.put("errcode", -1);
        params.put("data", error);
        return params.toString();
    }

    public static String  returnFileSuccess(String data){
        JSONObject params = new JSONObject();
        params.put("ret", "true");
        params.put("errcode", 0);
        params.put("data", data);
        return params.toString();
    }
}
