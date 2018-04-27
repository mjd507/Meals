package io.mjd507.common;

import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by mjd on 2018/4/27 09:34
 */
@Getter
@Setter
@ToString
@Component
public class ConfigProperties {

  @Value("${merchant.url}")
  public String MerchantUrlTemp;

  public static String MerchantUrl;

  @PostConstruct
  public void init() {
    MerchantUrl = MerchantUrlTemp;
  }

}
