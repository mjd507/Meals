package io.mjd507.order;

import io.mjd507.CopyUtils;
import io.mjd507.OrderDo;
import io.mjd507.OrderDto;
import io.mjd507.OrderService;
import io.mjd507.OrderVo;
import io.mjd507.common.DataResponse;
import io.mjd507.common.UserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.module.user.UserDto;
import io.mjd507.order.request.OrderVoReq;
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
  public DataResponse<String> submitOrder(@ModelAttribute(Constants.HEADER_AUTH) UserDto userDto,
      @RequestBody OrderVoReq orderVoReq) {
    // 限制一个用户一天只下一单
    if (hasSubmit(userDto.getUserId())) {
      DataResponse<String> response = new DataResponse<>();
      response.setFailure("请勿重复订餐");
      return response;
    }
    OrderDo orderDo = new OrderDo();
    orderDo.setUserId(userDto.getUserId());
    orderDo.setMchId(orderVoReq.getMchId());
    orderDo.setMchName(orderVoReq.getMchName());
    orderDo.setMealName(listToStr(orderVoReq.getMealName()));
    int count = orderService.submitOrder(orderDo);
    return new DataResponse<>(count > 0 ? "提交成功" : "提交失败");
  }

  private boolean hasSubmit(String userId) {
    List<OrderDto> userOrders = orderService.findOrderByUser(userId);
    List<OrderDto> todayOrders = orderService.findTodayOrder();
    if (userOrders == null || userOrders.size() == 0) {
      return false;
    }
    OrderDto userLastOrder = userOrders.get(userOrders.size() - 1);
    for (OrderDto today : todayOrders) {
      if (userLastOrder.getId().equals(today.getId())) {
        return true;
      }
    }
    return false;
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

  @RequestMapping(value = "getUserOrders", method = RequestMethod.GET)
  public DataResponse<List<OrderVo>> findOrders(
      @ModelAttribute(Constants.HEADER_AUTH) UserDto userDto) {
    List<OrderDto> orderDtos = orderService.findOrderByUser(userDto.getUserId());
    List<OrderVo> orderVos = CopyUtils.copyList(orderDtos, OrderVo.class);
    return new DataResponse<>(orderVos);
  }

  @RequestMapping(value = "getTodayOrders", method = RequestMethod.GET)
  public DataResponse<List<OrderVo>> getTodayOrders() {
    List<OrderDto> orderDtos = orderService.findTodayOrder();
    List<OrderVo> orderVos = CopyUtils.copyList(orderDtos, OrderVo.class);
    return new DataResponse<>(orderVos);
  }
}
