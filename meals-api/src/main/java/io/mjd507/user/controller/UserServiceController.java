package io.mjd507.user.controller;

import io.mjd507.CopyUtils;
import io.mjd507.common.UserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.common.DataResponse;
import io.mjd507.module.user.UserDo;
import io.mjd507.module.user.UserDto;
import io.mjd507.module.user.UserService;
import io.mjd507.module.user.UserVo;
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
@Api(value = "UserServiceController", description = "用户相关api")
@RestController
@RequestMapping(value = "user")
public class UserServiceController extends UserAttrSetter {

  @Autowired
  UserService userService;

  @ApiOperation(value = "手机号验证码登录")
  @RequestMapping(value = "loginByPhone", method = RequestMethod.POST)
  @ResponseBody
  public DataResponse<UserVo> loginByPhone(@RequestBody LoginReqVo reqVo) {
    UserVo userVo = userService.loginByPhone(reqVo.getPhone(), reqVo.getVerifyCode());
    DataResponse<UserVo> response = new DataResponse<>(userVo);
    if (userVo == null) {
      response.setFailure("登录失败");
    }
    return response;
  }

  @ApiOperation(value = "更新用户信息", notes = "全字段更新，不要传空覆盖原有值")
  @ResponseBody
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  public DataResponse<String> updateUser(@ModelAttribute(Constants.HEADER_AUTH) UserDto userDto,
      @RequestBody UserVo userVo) {
    UserDo user = CopyUtils.copyObject(userVo, UserDo.class);
    user.setUserId(userDto.getUserId());
    int count = userService.updateUserById(user);
    return new DataResponse<>(count > 0 ? "更新成功" : "更新失败");
  }

  @ApiOperation(value = "删除用户")
  @ResponseBody
  @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
  public DataResponse<String> deleteUser(@ModelAttribute(Constants.HEADER_AUTH) UserDto userDto) {
    int count = userService.deleteUserById(userDto.getUserId());
    return new DataResponse<>(count > 0 ? "删除成功" : "删除失败");
  }

  @ResponseBody
  @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
  public DataResponse<UserVo> getUserInfo(@ModelAttribute(Constants.HEADER_AUTH) UserDto userDto) {
    UserDto dto = userService.findUserById(userDto.getUserId());
    UserVo userVo = CopyUtils.copyObject(dto, UserVo.class);
    return new DataResponse<>(userVo);
  }

}
