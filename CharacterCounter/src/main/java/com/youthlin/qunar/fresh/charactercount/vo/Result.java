package com.youthlin.qunar.fresh.charactercount.vo;

import java.io.Serializable;

/**
 * Created by youthlin.chen on 2016-12-9 009.
 * Result Value Object
 */
public class Result implements Serializable {
    private int code = 0;
    private String msg = "";
    private Object data = "";

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
