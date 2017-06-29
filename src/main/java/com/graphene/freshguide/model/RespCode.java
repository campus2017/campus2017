package com.graphene.freshguide.model;

/**
 * @author Mingxin Wang
 * @since 2017-06-16 上午12:26
 */
public enum RespCode {

  OK(0, "ok"),

  WRONG_ARGUMENT(400, "wrong argument"),
  SERVER_ERROR(500, "server error"),;

  public int code;
  public String message;

  RespCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
