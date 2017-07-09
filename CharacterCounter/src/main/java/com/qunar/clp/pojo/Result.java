package com.qunar.clp.pojo;

import com.qunar.clp.constants.ResultStatus;

/**
 * Created by Asus on 2016/12/15.
 */
public class Result {
    private int code;
    private String msg;
    private Object data;
    public Result(ResultStatus resultStatus){
        this.code=resultStatus.getCode();
        this.msg=resultStatus.getMsg();
    }

    public Result(ResultStatus resultStatus, Object data) {
        this.code=resultStatus.getCode();
        this.msg=resultStatus.getMsg();
        this.data = data;
    }
}
