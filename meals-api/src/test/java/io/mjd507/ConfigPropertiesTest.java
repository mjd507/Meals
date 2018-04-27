package io.mjd507;

import io.mjd507.common.ConfigProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by mjd on 2018/4/27 10:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MealsApplication.class)
public class ConfigPropertiesTest {

  @Test
  public void propertiesTest() {
    System.out.println(ConfigProperties.MerchantUrl);
  }
}
