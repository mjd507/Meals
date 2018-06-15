package io.mjd507.module.login;

import io.mjd507.cache.AbsLoadingCache;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mjd on 2018/6/14 17:04
 */
@Component
public class TokenCache extends AbsLoadingCache<String, LoginDo> {

  public TokenCache() {
    setMaximumSize(1000);
    setExpireAfterAccessDuration(2);
    setTimeUnit(TimeUnit.DAYS);
  }

  @Autowired
  LoginServiceMapper loginMapper;

  @Override
  public LoginDo fetchValue(String key) {
    return loginMapper.findByToken(key);
  }

}
