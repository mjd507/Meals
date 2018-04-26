package io.mjd507.order.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/26 21:38
 */
@Setter
@Getter
public class OrderVoReq {
  private String merchantId;
  private String merchantName;
  private List<String> mealsIds;
  private List<String> mealsNames;
}
