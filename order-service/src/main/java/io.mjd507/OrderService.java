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

  public int submitOrder(OrderDo orderDo) {
    return orderMapper.submitOrder(orderDo);
  }

  public List<OrderDto> findOrderByUser(String userId) {
    List<OrderDo> orderDoList = orderMapper.findOrderByUser(userId);
    return CopyUtils.copyList(orderDoList, OrderDto.class);
  }

  public List<OrderDto> findTodayOrder() {
    List<OrderDo> orderDoList = orderMapper.findTodayOrder();
    return CopyUtils.copyList(orderDoList, OrderDto.class);
  }
}
