package io.mjd507.user.controller;

import io.mjd507.common.UserAttrSetter;
import io.mjd507.common.constants.Constants;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.UserVo;
import io.mjd507.service.impl.UserLoginServiceImpl;
import io.mjd507.service.impl.UserServiceImpl;
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
public class UserServiceController extends UserAttrSetter {

  @Autowired
  UserServiceImpl userService;

  @Autowired
  UserLoginServiceImpl loginService;

  @ApiOperation(value = "设置用户类型", notes = "普通用户、商家、审核人、小组长")
  @ResponseBody
  @RequestMapping(value = "setUserType", method = RequestMethod.POST)
  public DataResponse<String> setUserType(@ModelAttribute(Constants.USER_ATTR) UserVo user,
      String userType) {
    boolean success = userService.setUserType(user.getUserId(), userType);
    return new DataResponse<>(success ? "设置成功" : "设置失败");
  }

  @ApiOperation(value = "更新用户信息", notes = "全字段更新，不要传空覆盖原有值")
  @ResponseBody
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  public DataResponse<String> updateUser(@ModelAttribute(Constants.USER_ATTR) UserVo user,
      @RequestBody UserVo userVo) {
    userVo.setUserId(user.getUserId());
    boolean success = userService.updateUserById(userVo);
    return new DataResponse<>(success ? "更新成功" : "更新失败");
  }

  @ApiOperation(value = "删除用户")
  @ResponseBody
  @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
  public DataResponse<String> deleteUser(@ModelAttribute(Constants.USER_ATTR) UserVo user) {
    boolean isDeleteToken = loginService.deleteUserToken(user.getUserId());
    boolean isDeleteUser = userService.deleteUserById(user.getUserId());
    return new DataResponse<>(isDeleteToken && isDeleteUser ? "删除成功" : "删除失败");
  }

}
