package io.mjd507.utils;

import io.mjd507.conf.LoginConfig;
import io.mjd507.entity.WeAppSession;
import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mjd on 2018/4/21 18:50
 */
public class LoginUtils {

  public static WeAppSession wxAppLogin(String code) {
    String url = "https://api.weixin.qq.com/sns/jscode2session";
    String appId = LoginConfig.AppId;
    String appSecret = LoginConfig.AppSecret;
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
