package cn.xuchunh.charcounter.model;

import java.io.Serializable;

/**
 * Created on 2017/6/13.
 *
 * @author XCH
 */
public class Result<T extends Serializable> {

    // 结果代码，-1 表示成功
    private int code = -1;

    // 结果说明，成功为空
    private String cause;

    // 需要返回的数据
    private T data;

    public Result(int code, String cause) {
        this.code = code;
        this.cause = cause;
    }

    public Result(int code, String cause, T data) {
        this.code = code;
        this.cause = cause;
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", cause='" + cause + '\'' +
                ", data=" + data +
                '}';
    }
}
