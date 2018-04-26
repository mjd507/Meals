package io.mjd507.order;

import io.mjd507.common.UserAttrSetter;
import io.mjd507.common.constants.Constants;
import io.mjd507.common.request.DataResponse;
import io.mjd507.entity.OrderVo;
import io.mjd507.entity.UserVo;
import io.mjd507.order.request.OrderVoReq;
import io.mjd507.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/26 21:02
 */
@RestController
public class OrderController extends UserAttrSetter {

  @Autowired
  OrderService orderService;

  @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
  public DataResponse<String> submitOrder(@ModelAttribute(Constants.USER_ATTR) UserVo user,
      @RequestBody OrderVoReq orderVoReq) {
    OrderVo orderVo = new OrderVo();
    orderVo.setUserId(user.getUserId());
    orderVo.setMerchantId(orderVoReq.getMerchantId());
    orderVo.setMerchantName(orderVoReq.getMerchantName());
    orderVo.setMealsIds(listToStr(orderVoReq.getMealsIds()));
    orderVo.setMealsNames(listToStr(orderVoReq.getMealsNames()));
    boolean isAdd = orderService.addOrder(orderVo);
    return new DataResponse<>(isAdd ? "提交成功" : "提交失败");
  }

  private String listToStr(List<String> list) {
    StringBuilder builder = new StringBuilder();
    builder.append("[");
    list.forEach((item) -> {
      builder.append(item).append(",");
    });
    if (list.size() != 0) {
      builder.setLength(builder.length() - 1);
    }
    builder.append("]");
    return builder.toString();
  }

  @RequestMapping(value = "getOrders", method = RequestMethod.GET)
  public DataResponse<List<OrderVo>> findOrders(@ModelAttribute(Constants.USER_ATTR) UserVo user) {
    List<OrderVo> orders = orderService.findOrder(user.getUserId());
    return new DataResponse<>(orders);
  }
}
