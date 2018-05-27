package io.mjd507.order.request;

import java.util.List;
import lombok.Data;

/**
 * Created by mjd on 2018/4/26 21:38
 */
@Data
public class OrderVoReq {

  private String mchId;
  private String mchName;
  private List<String> mealName;
}
