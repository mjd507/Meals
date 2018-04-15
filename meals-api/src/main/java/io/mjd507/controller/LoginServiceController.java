package io.mjd507.controller;

import io.mjd507.base.DataResponse;
import io.mjd507.entity.UserVo;
import io.mjd507.service.impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mjd
 * @date 2018/3/25 18:57
 */
@RestController
@RequestMapping(value = "/meals/")
public class LoginServiceController {

  @Autowired
  private LoginServiceImpl loginService;

  @RequestMapping(value = "loginByWeApp")
  public DataResponse<UserVo> loginByWeApp(String code) {
    UserVo userVo = loginService.loginByWeApp(code);
    DataResponse<UserVo> response = new DataResponse<>(userVo);
    if(userVo == null){
      response.setFailure("微信服务异常");
    }
    return response;
  }

}
