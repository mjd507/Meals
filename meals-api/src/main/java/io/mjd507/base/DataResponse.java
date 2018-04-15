package io.mjd507.base;

/**
 * @author mjd
 * @date 2018/3/25 18:44
 */

public class DataResponse<T> {

  public static final String SUCCESS = "1";

  public static final String FAILURE = "0";

  private String code;

  private T data;

  private String msg;

  public DataResponse(T data) {
    this.code = SUCCESS;
    this.data = data;
    this.msg = "ok";
  }

  public void setFailure(String msg){
    this.code = FAILURE;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
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
