package io.mjd507.http;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/3/24 23:11
 */
@Setter
@Getter
public class HttpRequest {

  private String url;
  private String method;
  private Map<String, String> headers;
  private Map<String, String> params;

}
