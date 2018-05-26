package io.mjd507.user.controller;

import io.mjd507.CopyUtils;
import io.mjd507.common.MchUserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.common.DataResponse;
import io.mjd507.module.login.LoginService;
import io.mjd507.module.mchuser.MchUserDo;
import io.mjd507.module.mchuser.MchUserDto;
import io.mjd507.module.mchuser.MchUserService;
import io.mjd507.module.mchuser.MchUserVo;
import io.mjd507.user.request.LoginReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/19 19:41
 */
@Api(value = "MchUserServiceController", description = "商户用户相关api")
@RestController
public class MchUserServiceController extends MchUserAttrSetter {

  @Autowired
  LoginService loginService;

  @Autowired
  MchUserService mchUserService;

  @ApiOperation(value = "手机号验证码登录")
  @RequestMapping(value = "loginByPhone", method = RequestMethod.POST)
  @ResponseBody
  public DataResponse<MchUserVo> loginByPhone(@RequestBody LoginReqVo reqVo) {
    MchUserVo mchUserVo = mchUserService.loginByPhone(reqVo.getPhone(), reqVo.getVerifyCode());
    DataResponse<MchUserVo> response = new DataResponse<>(mchUserVo);
    if (mchUserVo == null) {
      response.setFailure("登录失败");
    }
    return response;
  }

  @ApiOperation(value = "更新用户信息")
  @ResponseBody
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  public DataResponse<String> updateUser(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto,
      @RequestBody MchUserVo userVo) {
    MchUserDo mchUserDo = CopyUtils.copyObject(userVo, MchUserDo.class);
    mchUserDo.setUserId(userDto.getUserId());
    int count = mchUserService.updateUserById(mchUserDo);
    return new DataResponse<>(count > 0 ? "更新成功" : "更新失败");
  }

  @ApiOperation(value = "删除用户")
  @ResponseBody
  @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
  public DataResponse<String> deleteUser(@ModelAttribute(Constants.HEADER_AUTH) MchUserDto userDto) {
    int count = mchUserService.deleteUserById(userDto.getUserId());
    return new DataResponse<>(count > 0 ? "删除成功" : "删除失败");
  }

}
