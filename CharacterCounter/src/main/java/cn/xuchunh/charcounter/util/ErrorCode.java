package cn.xuchunh.charcounter.util;

/**
 * Created by XuChunH on 2017/6/15.
 */
public enum ErrorCode {

    TEXT_PARSE_ERROR(1, "文本解析出错"),
    FILE_UPLOAD_ERROR(2, "文件上传失败"),
    FILE_PARSE_ERROR(3, "文件解析失败"),
    FILE_NOT_EXIST(4, "文件不存在"),
    FILE_IS_EMPTY(5, "文件为空");

    private int code;
    private String cause;

    ErrorCode(int code, String cause) {
        this.code = code;
        this.cause = cause;
    }

    public int getCode() {
        return code;
    }

    public String getCause() {
        return cause;
    }
}
