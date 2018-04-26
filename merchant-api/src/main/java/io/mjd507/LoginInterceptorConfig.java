package io.mjd507;


import io.mjd507.user.interceptor.UserLoginInterceptor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 用户拦截路径配置
 *
 * Created by mjd on 2018/4/19 21:08
 */
@Configuration
public class LoginInterceptorConfig extends WebMvcConfigurationSupport {

  @Bean
  public HandlerInterceptor loginInterceptor() {
    return new UserLoginInterceptor();
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {

    List<String> merExclude = new ArrayList<>();
    merExclude.add("/loginByWeApp");
    merExclude.add("/loginByPhone");
    merExclude.add("/sendSms");
    merExclude.add("/reviewer/**");
    merExclude.add("/api/**");
    registry.addInterceptor(loginInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns(merExclude)
    ;
  }
}
