package io.mjd507.http;

import io.mjd507.GsonUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mjd
 * @date 2018/3/30 16:50
 */
public class HttpUtils {

  private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

  private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  private static OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();

  private static OkHttpClient okClient = okBuilder.build();

//  public static void main(String args[]) {
//    HashMap<String, String> params = new HashMap<>();
//    params.put("a", "1");
//    params.put("b", "2");
//    HttpRequest request = new HttpRequest();
//    doRequest(request);
//  }

  public static String doRequest(HttpRequest request) {
    return doRequest(request, String.class);
  }

  public static <T> T doRequest(HttpRequest request, Class<T> clazz) {
    Request okRequest = buildOkRequest(request);
    return handleResponse(okRequest, clazz);
  }

  private static Request buildOkRequest(HttpRequest request) {
    String url = request.getUrl();
    String method = request.getMethod();
    Map<String, String> headers = request.getHeaders();
    Map<String, String> params = request.getParams();

    Request.Builder requestBuilder = new Request.Builder();

    boolean isGet = method == null || method.equalsIgnoreCase("GET");
    if (isGet) {
      url = buildGetUrl(url, params);
    }
    requestBuilder.url(url);
    if (headers != null) {
      headers.forEach(requestBuilder::addHeader);
    }
    if (!isGet && params != null) { // 针对 post 请求
      String jsonStr = GsonUtils.toJsonStr(params);
      RequestBody body = RequestBody.create(JSON, jsonStr);
      requestBuilder.post(body);
    }
    return requestBuilder.build();
  }

  private static String buildGetUrl(String url, Map<String, String> params) {
    if (params == null) {
      return url;
    }
    StringBuilder builder = new StringBuilder();
    builder.append(url).append("?");
    params.forEach((key, value) -> {
      builder.append(key).append("=").append(value).append("&");
    });
    String strWithDirtyEnd = builder.toString();
    return strWithDirtyEnd.substring(0, strWithDirtyEnd.length() - 1);
  }

  private static <T> T handleResponse(Request request, Class<T> clazz) {
    try {
      Response response = okClient.newCall(request).execute();
      ResponseBody body = response.body();
      T t = null;
      if (body != null) {
        t = GsonUtils.toBean(body.string(), clazz);
      }
      return t;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

}
