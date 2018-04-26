package io.mjd507.service;

import io.mjd507.dao.OrderMealsMapper;
import io.mjd507.entity.OrderVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/26 20:46
 */
@Service
public class OrderService {

  @Autowired
  OrderMealsMapper orderMapper;

  public boolean addOrder(OrderVo orderVo) {
    return orderMapper.AddOrder(orderVo) == 1;
  }

  public List<OrderVo> findOrder(String userId) {
    return orderMapper.findOrderByUser(userId);
  }
}
