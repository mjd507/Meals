package io.mjd507;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/26 20:46
 */
@Service
public class OrderService {

  @Autowired
  OrderMapper orderMapper;

  public String submitOrder(OrderDo orderDo) {
    if (!DateUtils.isWorkDay()) {
      return "非工作日无法订餐";
    }
    if (DateUtils.isOver3Clock()) {
      return "订餐时间已截止";
    }
    // 限制一个用户一天只下一单
    if (hasSubmit(orderDo.getUserId())) {
      return "请勿重复订餐";
    }
    int count = orderMapper.submitOrder(orderDo);
    return count > 0 ? "提交成功" : "提交失败";
  }

  private boolean hasSubmit(String userId) {
    List<OrderDto> userOrders = this.findOrderByUser(userId);
    List<OrderDto> todayOrders = this.findTodayOrder();
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

  public List<OrderDto> findOrderByUser(String userId) {
    List<OrderDo> orderDoList = orderMapper.findOrderByUser(userId);
    return CopyUtils.copyList(orderDoList, OrderDto.class);
  }

  public List<OrderDto> findTodayOrder() {
    List<OrderDo> orderDoList = orderMapper.findTodayOrder();
    return CopyUtils.copyList(orderDoList, OrderDto.class);
  }

  public String cancelOrder(String userId) {
    int count = orderMapper.cancelOrder(userId);
    return count > 0 ? "取消成功" : "取消失败";
  }
}
