package com.graphene.freshguide.model;

import java.io.Serializable;

/**
 * @author Mingxin Wang
 * @since 2017-06-16 上午12:30
 */
public class Resp implements Serializable {

  public static final Resp OK = Resp.from(RespCode.OK);
  public static final Resp SERVER_ERROR = Resp.from(RespCode.SERVER_ERROR);

  private int code;
  private String message;
  private Object data;

  public static Resp ok(Object data) {
    return builder().code(RespCode.OK).data(data).build();
  }

  public static Resp from(RespCode respCode) {
    return builder().code(respCode.code).message(respCode.message).build();
  }

  public static Builder builder() {
    return new Builder();
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  @Override
  public String toString() {
    return "Resp{" +
        "code=" + code +
        ", message='" + message + '\'' +
        ", data=" + data +
        '}';
  }

  public static class Builder {

    private int code;
    private String message;
    private Object data;

    public Builder code(RespCode code) {
      return code(code.code);
    }

    public Builder code(int code) {
      this.code = code;
      return this;
    }

    public Builder message(String message) {
      this.message = message;
      return this;
    }

    public Builder data(Object data) {
      this.data = data;
      return this;
    }

    public Resp build() {
      Resp resp = new Resp();
      resp.setCode(code);
      resp.setMessage(message);
      resp.setData(data);
      return resp;
    }
  }
}
