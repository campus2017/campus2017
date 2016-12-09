package com.youthlin.qunar.diff.vo;

/**
 * Created by youthlin.chen on 2016-11-17 017.
 * json result
 */
public class Result {
    private int code = 0;
    private Object data = "";

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
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

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
