package io.mjd507.service.impl;

import io.mjd507.dao.LoginServiceMapper;
import io.mjd507.entity.LoginVo;
import io.mjd507.entity.MerchantUserVo;
import io.mjd507.entity.UserVo;
import io.mjd507.entity.WeAppSession;
import io.mjd507.service.ILoginService;
import io.mjd507.service.IUserService;
import io.mjd507.sms.SmsService;
import io.mjd507.utils.LoginUtils;
import io.mjd507.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录模块服务的具体实现
 *
 * Created by mjd on 2018/3/24 15:03
 */
@Service
public class UserLoginServiceImpl implements ILoginService<UserVo> {

  @Autowired
  private LoginServiceMapper loginServiceMapper;

  @Autowired
  private IUserService<UserVo> userService;

  @Autowired
  private SmsService smsService;

  @Override
  public UserVo loginByPhone(String phone, String verifyCode) {
    boolean validSuccess = smsService.hasPhoneAndCode(phone, verifyCode);
    // 短信验证通过
    if (validSuccess) {
      UserVo userByPhone = userService.findUserByPhone(phone);
      if (userByPhone != null && userByPhone.getUserId() != null) { // 用户存在
        return userByPhone;
      } else {
        String userId = TokenUtils.buildUserId(phone);
        UserVo user = new UserVo();
        user.setUserId(userId);
        user.setPhone(phone);
        LoginVo loginVo = new LoginVo();
        loginVo.setUserId(userId);
        loginVo.setPhone(phone);
        loginServiceMapper.insertLoginVo(loginVo);
        userService.addUser(user);
        return user;
      }
    }
    return null;
  }

  @Override
  public UserVo loginByWeApp(String code) {
    // https://mp.weixin.qq.com/debug/wxadoc/dev/api/api-login.html
    WeAppSession session = LoginUtils.wxAppLogin(code);
    String openId = session.getOpenid();
    if (openId != null) { // 获取到微信用户
      LoginVo loginVo = loginServiceMapper.findUserIdByOpenId(openId);
      if (loginVo != null && loginVo.getUserId() != null) { // 用户存在
        return userService.findUserById(loginVo.getUserId());
      } else { //用户不存在，创建新用户
        // 根据 openId 生成 userId
        String userId = TokenUtils.buildUserId(openId);
        UserVo user = new UserVo();
        user.setUserId(userId);
        loginVo = new LoginVo();
        loginVo.setOpenId(openId);
        loginVo.setUserId(userId);
        loginServiceMapper.insertLoginVo(loginVo);
        userService.addUser(user);
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
