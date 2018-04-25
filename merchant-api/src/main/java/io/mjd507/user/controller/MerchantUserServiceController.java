package io.mjd507.user.controller;

import io.mjd507.common.MerchantUserAttrSetter;
import io.mjd507.common.constants.Constants;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.MerchantUserVo;
import io.mjd507.service.impl.MerchantLoginServiceImpl;
import io.mjd507.service.impl.MerchantUserServiceImpl;
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
@Api(value = "MerchantUserServiceController", description = "商户用户相关api")
@RestController
public class MerchantUserServiceController extends MerchantUserAttrSetter {

  @Autowired
  MerchantUserServiceImpl merchantUserService;

  @Autowired
  MerchantLoginServiceImpl merchantLoginService;

  @ApiOperation(value = "更新用户信息", notes = "全字段更新，不要传空覆盖原有值，手机号暂不支持修改")
  @ResponseBody
  @RequestMapping(value = "updateUser", method = RequestMethod.POST)
  public DataResponse<String> updateUser(@ModelAttribute(Constants.USER_ATTR) MerchantUserVo user,
      @RequestBody MerchantUserVo userVo) {
    userVo.setUserId(user.getUserId());
    boolean success = merchantUserService.updateUserById(userVo);
    return new DataResponse<>(success ? "更新成功" : "更新失败");
  }

  @ApiOperation(value = "删除用户")
  @ResponseBody
  @RequestMapping(value = "deleteUser", method = RequestMethod.POST)
  public DataResponse<String> deleteUser(@ModelAttribute(Constants.USER_ATTR) MerchantUserVo user) {
    boolean isDeleteToken = merchantLoginService.deleteUserToken(user.getUserId());
    boolean isDeleteUser = merchantUserService.deleteUserById(user.getUserId());
    return new DataResponse<>(isDeleteToken && isDeleteUser ? "删除成功" : "删除失败");
  }

}
