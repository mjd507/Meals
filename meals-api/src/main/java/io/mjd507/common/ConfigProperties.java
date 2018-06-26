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
@Component
public class ConfigProperties {

  @Value("${mch_host}")
  public String mchHost;

  @Value("${ip_white_list}")
  public String ipWhiteList;

  public static String MerchantUrl;

  public static String IpWhiteList;

  @PostConstruct
  public void init() {
    MerchantUrl = mchHost;
    IpWhiteList = ipWhiteList;
  }

}
