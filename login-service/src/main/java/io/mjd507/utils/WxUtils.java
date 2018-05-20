package io.mjd507.utils;

import io.mjd507.entity.WeAppSession;
import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mjd on 2018/5/20 14:20
 */
public class WxUtils {

  public static final String AppId = "wx1e34341b5e666a16";

  public static final String AppSecret = "f90c40631e154d148aabdeca43011a2b";

  public static WeAppSession wxAppLogin(String code) {
    String url = "https://api.weixin.qq.com/sns/jscode2session";
    String appId = AppId;
    String appSecret = AppSecret;
    HttpRequest request = new HttpRequest();
    Map<String, String> params = new HashMap<>();
    params.put("appid", appId);
    params.put("secret", appSecret);
    params.put("js_code", code);
    params.put("grant_type", "authorization_code");
    request.setUrl(url);
    request.setParams(params);
    return HttpUtils.doRequest(request, WeAppSession.class);
  }
}
