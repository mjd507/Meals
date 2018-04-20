package io.mjd507.common.request;

/**
 * Created by mjd on 2018/3/25 18:44
 */
public class DataResponse<T> {

  private static final int SUCCESS = 1;

  private static final int FAILURE = 0;

  private static final int AUTH_FAILURE = 401;

  private int code;

  private T data;

  private String msg;

  public DataResponse() {

  }

  public DataResponse(T data) {
    this.code = SUCCESS;
    this.data = data;
    this.msg = "ok";
  }

  public void setFailure(String msg) {
    this.code = FAILURE;
    this.msg = msg;
  }

  public void setAuthFailure() {
    this.code = AUTH_FAILURE;
    this.msg = "Unauthorized";
    this.data = null;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }
}
