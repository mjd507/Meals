package io.mjd507.user;

import io.mjd507.module.login.LoginDto;
import io.mjd507.module.login.LoginService;
import io.mjd507.module.login.LoginServiceImpl;
import io.mjd507.module.login.LoginServiceMapper;
import io.mjd507.module.login.TokenUtils;
import io.mjd507.module.sms.SmsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mjd on 2018/6/24 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class LoginTest {

  @InjectMocks
  private LoginService loginService = new LoginServiceImpl();

  @Mock
  private SmsService smsService;

  @Mock
  private LoginServiceMapper loginMapper;

  @Before
  public void initMocks() {
    MockitoAnnotations.initMocks(this);
    // 绕过短信验证
    Mockito.when(smsService.isVCodeValid(Mockito.anyString(), Mockito.anyString()))
        .thenReturn(true);
    // 绕过登录信息插入
    Mockito.when(loginMapper.insertLoginInfo(Mockito.any())).thenReturn(1);

    Mockito.when(loginMapper.findByUserId(Mockito.anyString())).thenReturn(null);
  }

  @Test
  public void testLoginByPhone() {
    String phone = "13800000000";

    LoginDto loginDto = loginService.loginByPhone(phone, "anyString");

    Assert.assertNotNull(loginDto);
    Assert.assertEquals(loginDto.getUserId(), TokenUtils.buildUserIdByPhone(phone));
  }

}
