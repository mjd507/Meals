package io.mjd507.service;

/**
 * 登录模块服务
 * T 用户实体
 * Created by mjd on 2018/3/24 13:36
 */
public interface ILoginService<T> {

  /**
   * 根据手机号+验证码的方式登录
   *
   * @param phone 手机号
   * @param verifyCode 验证码
   * @return 用户信息
   */
  T loginByPhone(String phone, String verifyCode);


  /**
   * 根据 微信小程序 授权登录
   *
   * @param code 小程序登录的 code，用来向微信换取用户的唯一标识
   * @return 用户信息
   */
  T loginByWeApp(String code);


  boolean deleteUserToken(String userId);
}
