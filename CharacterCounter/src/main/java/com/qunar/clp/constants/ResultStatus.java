package com.qunar.clp.constants;

/**
 * Created by Asus on 2016/12/15.
 */
public enum ResultStatus {
    OK(0,"success");
    private int code;
    private String msg;
    ResultStatus(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
