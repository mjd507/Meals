package io.mjd507.user.interceptor;

import io.mjd507.GsonUtils;
import io.mjd507.common.constants.Constants;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.UserVo;
import io.mjd507.service.IUserService;
import io.mjd507.service.impl.UserLoginServiceImpl;
import io.mjd507.service.impl.UserServiceImpl;
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
  UserServiceImpl userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String authorization = request.getHeader(Constants.USER_ATTR);
    if (authorization != null) {
      UserVo user = userService.findUserById(authorization);
      if (user != null) {
        request.setAttribute(Constants.USER_ATTR, user);
        return true;
      } else {
        authFailure(response);
      }
    } else {
      authFailure(response);
    }
    return false;
  }

  private void authFailure(HttpServletResponse response) throws IOException {
    DataResponse responseData = new DataResponse<>();
    responseData.setAuthFailure();
    response.setContentType("application/json; charset=utf-8");
    response.getWriter().write(GsonUtils.toJsonStr(responseData));
  }
}
