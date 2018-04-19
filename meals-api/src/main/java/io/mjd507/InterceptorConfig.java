/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507;


import io.mjd507.user.interceptor.LoginInterceptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author mjd
 * @date 2018/4/19 21:08
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

  @Bean
  public HandlerInterceptor getLoginInterceptor() {
    return new LoginInterceptor();
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    List<String> loginExcludeList = new ArrayList<>();
    loginExcludeList.add("/**/user/loginByWeApp");
    loginExcludeList.add("/**/user/loginByPhone");
    registry.addInterceptor(getLoginInterceptor())
        .addPathPatterns("/**").excludePathPatterns(loginExcludeList)
    ;
  }
}
