## sms-service 短信服务

- 发送验证码短信

  ```java
  /**
   * "发送成功"，  "验证码仍有效，请勿重复发送"， "发送失败"
   */
  String sendSms(String phone)
  ```

- 验证手机号-验证码

  ```java
  /**
   * true 验证通过
   */
  boolean isVCodeValid(String phone, String code)
  ```

## login-service 登录服务

LoginDto 实体中包含了 userId，token，phone，openId，expiredAt 信息

- 根据 token 查找 LoginDto

  ```java
  /**
   * token 是否有效
   */
  LoginDto findValidByToken(String token);
  ```

- 根据手机号验证码登录，返回 LoginDto 

  ```java
  /**
   * 根据手机号+验证码的方式登录
   */
  LoginDto loginByPhone(String phone, String verifyCode);
  ```

- 根据微信 openId 登录，返回 LoginDto 

  ```java
  /**
   * 根据 微信小程序 授权登录
   * @param code 小程序登录的 code，用来向微信换取用户的唯一标识
   */
  LoginDto loginByWeApp(String code);
  
  ```

- 根据 token 获取 userId

  ```java
  /**
   * 删除用户所有过期 token 的数据
   */
  void deleteExpiredToken(String userId);
  ```

## user-service 用户模块

包含 normal-user-service 和 merchant-user-service 两个子模块，接口差不太多，以 normal-user-service 为例。

UserVo 包含了 token 以及用户的基本信息

- 根据手机号验证码调用登录模块，返回 UserVo

  ```java
  UserVo loginByPhone(String phone, String verifyCode)
  ```

- 根据 userId 找出 UserDto，UserDto 包含了用户的基本信息

  ```java
  /**
   * 根据 userId 找出 UserDto
   */
  UserDto findUserById(String userId);
  ```

- 更新用户信息

  ```java
  /**
   * 更新用户信息
   */
  int updateUserById(UserDo userDo);

  ```
  
- 删除用户

  ```java
  /**
   * 删除用户
   */
  int deleteUserById(String userId);
  
  ```

## common-utils 模块

包含基本的工具类。Bean 实体拷贝，日期转换，json 工具，MD5 等。

## qiniu-service 模块

七牛图片上传相关服务

## 业务模块

merchant-service 商家服务模块

merchant-api 对商户端的 api 以及对用户端的 api

order-service 用户端订单服务模块

meals-api 用户端 api 接口


