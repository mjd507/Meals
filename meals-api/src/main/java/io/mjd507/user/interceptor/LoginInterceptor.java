/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.user.interceptor;

import io.mjd507.GsonUtils;
import io.mjd507.common.request.DataResponse;
import io.mjd507.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author mjd
 * @date 2018/4/19 21:02
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

  private static Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

  @Autowired
  UserServiceImpl userService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String authorization = request.getHeader("Authorization");
    logger.info(authorization);
    if (authorization != null && userService.findUserById(authorization) != null) {
      request.setAttribute("Authorization",authorization);
      return true;
    } else {
      DataResponse responseData = new DataResponse<>();
      responseData.setAuthFailure();
      response.setContentType("application/json; charset=utf-8");
      response.getWriter().write(GsonUtils.toJsonStr(responseData));
    }
    return false;
  }
}
