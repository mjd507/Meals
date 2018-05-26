package io.mjd507.user.interceptor;

import io.mjd507.GsonUtils;
import io.mjd507.common.DataResponse;
import io.mjd507.module.login.Constants;
import io.mjd507.module.login.LoginDto;
import io.mjd507.module.login.LoginService;
import io.mjd507.module.mchuser.MchUserDto;
import io.mjd507.module.mchuser.MchUserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Created by mjd on 2018/4/19 21:02
 */
public class UserLoginInterceptor extends HandlerInterceptorAdapter {

  private static Logger logger = LoggerFactory.getLogger(UserLoginInterceptor.class);

  @Autowired
  MchUserService mchUserService;

  @Autowired
  LoginService loginService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String authorization = request.getHeader(Constants.HEADER_AUTH);
    if (authorization != null) {
      if (!authorization.startsWith("Bearer ") && authorization.substring(7).trim().length() == 0) {
        return false;
      }
      String token = authorization.split(" ")[1];
      LoginDto loginDto = loginService.findValidByToken(token);
      if (loginDto != null) {
        MchUserDto user = mchUserService.findUserById(loginDto.getUserId());
        request.setAttribute(Constants.HEADER_AUTH, user);
        return true;
      }
    }
    authFailure(response);
    return false;
  }

  private void authFailure(HttpServletResponse response) throws IOException {
    DataResponse responseData = new DataResponse<>();
    responseData.setAuthFailure();
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().write(GsonUtils.toJsonStr(responseData));
  }
}
