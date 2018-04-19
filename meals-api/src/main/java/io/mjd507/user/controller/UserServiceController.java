/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.user.controller;

import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.UserVo;
import io.mjd507.service.impl.UserServiceImpl;
import io.mjd507.user.request.UserTypeReqVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjd
 * @date 2018/4/19 19:41
 */
@Api(value = "UserServiceController", description = "用户相关api")
@RestController
@RequestMapping(value = "user")
public class UserServiceController {

  @Autowired
  UserServiceImpl userService;

  @ApiOperation(value = "设置用户类型", notes = "普通用户、商家、审核人、小组长")
  @ResponseBody
  @RequestMapping(value = "setUserType", method = RequestMethod.POST)
  public DataResponse<String> setUserType(UserTypeReqVo userTypeReqVo) {
    int rows = userService.setUserType(userTypeReqVo.getUserId(), userTypeReqVo.getUserType());
    return new DataResponse<>(rows == 1 ? "设置成功" : "设置失败");
  }

  @ApiOperation(value = "更新用户信息", notes = "全字段更新，不要传空覆盖原有值")
  @ResponseBody
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  public DataResponse<String> setUserType(UserVo userVo) {
    int rows = userService.updateUserById(userVo.getUserId(), userVo);
    return new DataResponse<>(rows == 1 ? "更新成功" : "更新失败");
  }

  @ApiOperation(value = "删除用户")
  @ResponseBody
  @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
  public DataResponse<String> deleteUser(String userId) {
    int rows = userService.deleteUserById(userId);
    return new DataResponse<>(rows == 1 ? "删除成功" : "删除失败");
  }

}
