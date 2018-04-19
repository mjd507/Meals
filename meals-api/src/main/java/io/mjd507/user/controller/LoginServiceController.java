package io.mjd507.user.controller;

import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.UserVo;
import io.mjd507.service.impl.LoginServiceImpl;
import io.mjd507.user.request.LoginReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjd
 * @date 2018/3/25 18:57
 */
@Api(value = "LoginServiceController", description = "登录相关api")
@RestController
@RequestMapping(value = "user")
public class LoginServiceController {

  @Autowired
  private LoginServiceImpl loginService;

  @ApiOperation(value = "微信小程序登录", notes = "使用小程序 code 登录，如果用户不存在则创建后登陆，存在则直接登陆，"
      + "成功后返回 UserVo 对象，里面有 userId 字段，放在 header 的 access-token 中，用于后续验证")
  @RequestMapping(value = "loginByWeApp", method = RequestMethod.POST)
  @ResponseBody
  public DataResponse<UserVo> loginByWeApp(@RequestBody LoginReqVo reqVo) {
    UserVo userVo = loginService.loginByWeApp(reqVo.getCode());
    DataResponse<UserVo> response = new DataResponse<>(userVo);
    if (userVo == null) {
      response.setFailure("微信服务异常");
    }
    return response;
  }

  @ApiOperation(value = "手机号验证码登录", notes = "使用手机号登录，如果用户不存在则创建后登陆，存在则直接登陆，"
      + "成功后返回 UserVo 对象，里面有 userId 字段，放在 header 的 access-token 中，用于后续验证")
  @RequestMapping(value = "loginByPhone", method = RequestMethod.POST)
  @ResponseBody
  public DataResponse<UserVo> loginByPhone(@RequestBody LoginReqVo reqVo) {
    UserVo userVo = loginService.loginByPhone(reqVo.getPhone(), reqVo.getVerifyCode());
    DataResponse<UserVo> response = new DataResponse<>(userVo);
    if (userVo == null) {
      response.setFailure("登录失败");
    }
    return response;
  }

}
