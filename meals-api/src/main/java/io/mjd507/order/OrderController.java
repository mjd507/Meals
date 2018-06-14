package io.mjd507.order;

import io.mjd507.CopyUtils;
import io.mjd507.DateUtils;
import io.mjd507.OrderDo;
import io.mjd507.OrderDto;
import io.mjd507.OrderService;
import io.mjd507.OrderVo;
import io.mjd507.common.DataResponse;
import io.mjd507.common.UserAttrSetter;
import io.mjd507.module.login.Constants;
import io.mjd507.module.user.UserDto;
import io.mjd507.module.user.UserService;
import io.mjd507.order.request.OrderVoReq;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
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

  @Autowired
  UserService userService;

  @RequestMapping(value = "submitOrder", method = RequestMethod.POST)
  public DataResponse<String> submitOrder(@ModelAttribute(Constants.HEADER_AUTH) UserDto userDto,
      @RequestBody OrderVoReq orderVoReq) {
    if (DateUtils.isOver3Clock()) {
      DataResponse<String> response = new DataResponse<>();
      response.setFailure("订餐时间已截止");
      return response;
    }
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
  public DataResponse<List<OrderTodayVo>> getTodayOrders() {
    List<OrderDto> orderDtos = orderService.findTodayOrder();
    List<String> userIds = orderDtos.stream().map((OrderDto::getUserId))
        .collect(Collectors.toList());
    List<OrderTodayVo> todayVos = new ArrayList<>();
    if (userIds == null || userIds.size() == 0) {
      return new DataResponse<>(todayVos);
    }
    List<UserDto> userList = userService.findUserList(userIds);

    for (int i = 0; i < orderDtos.size(); i++) {
      OrderTodayVo todayVo = new OrderTodayVo();
      OrderDto orderDto = orderDtos.get(i);
      UserDto userDto = userList.stream()
          .filter(user -> user.getUserId().equals(orderDto.getUserId()))
          .collect(Collectors.toList()).get(0);
      todayVo.setId(orderDto.getId());
      todayVo.setMchName(orderDto.getMchName());
      todayVo.setMealName(orderDto.getMealName());
      todayVo.setCreatedAt(orderDto.getCreatedAt());
      //user
      todayVo.setDepartment(userDto.getDepartment());
      todayVo.setUserGroup(userDto.getUserGroup());
      todayVo.setUserName(userDto.getUserName());
      todayVo.setNickName(userDto.getNickName());
      todayVo.setAvatar(userDto.getAvatar());
      todayVos.add(todayVo);
    }
    return new DataResponse<>(todayVos);
  }
}
