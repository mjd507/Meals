package io.mjd507.module.user;

import io.mjd507.cache.AbsLoadingCache;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mjd on 2018/6/14 17:18
 */
@Component
public class UserCache extends AbsLoadingCache<String, UserDo> {

  public UserCache() {
    setMaximumSize(500);
    setExpireAfterAccessDuration(2);
    setTimeUnit(TimeUnit.DAYS);
  }

  @Autowired
  UserServiceMapper userServiceMapper;

  @Override
  public UserDo fetchValue(String userId) {
    return userServiceMapper.findUserByUserId(userId);
  }
}
