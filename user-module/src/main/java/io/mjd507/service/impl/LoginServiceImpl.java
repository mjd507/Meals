package io.mjd507.service.impl;

import io.mjd507.http.HttpRequest;
import io.mjd507.http.HttpUtils;
import io.mjd507.conf.LoginConfig;
import io.mjd507.dao.LoginServiceMapper;
import io.mjd507.entity.LoginVo;
import io.mjd507.entity.UserVo;
import io.mjd507.entity.WeAppSession;
import io.mjd507.service.ILoginService;
import io.mjd507.service.IUserService;
import io.mjd507.utils.TokenUtils;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录模块服务的具体实现
 *
 * Created by mjd on 2018/3/24 15:03
 */
@Service
public class LoginServiceImpl implements ILoginService {

  @Autowired
  private LoginServiceMapper loginServiceMapper;

  @Autowired
  private IUserService userServiceMapper;

  @Override
  public UserVo loginByPhone(String phone, String verifyCode) {

    return null;
  }

  @Override
  public UserVo loginByWeApp(String code) {
    // https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html
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
    WeAppSession session = HttpUtils.doRequest(request, WeAppSession.class);
    String openId = session.getOpenid();
    if (openId != null) { // 获取到微信用户
      LoginVo loginVo = loginServiceMapper.findUserIdByOpenId(openId);
      if (loginVo != null && loginVo.getUserId() != null) { // 用户存在
        return userServiceMapper.findUserById(loginVo.getUserId());
      } else { //用户不存在，创建新用户
        // 根据 openId 生成 userId
        String userId = TokenUtils.buildUserId(openId);
        UserVo user = new UserVo();
        user.setUserId(userId);
        loginVo = new LoginVo();
        loginVo.setOpenId(openId);
        loginVo.setUserId(userId);
        loginServiceMapper.insertLoginVo(loginVo);
        userServiceMapper.addUser(user);
        return user;
      }
    }
    return null;
  }

  @Override
  public boolean deleteUserToken(String userId) {
    return loginServiceMapper.deleteUserToken(userId) == 1;
  }

}
