package io.mjd507;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mjd on 2018/4/26 20:39
 */
/*
CREATE TABLE `tb_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(50) NOT NULL DEFAULT '',
  `mch_id` varchar(50) NOT NULL DEFAULT '',
  `mch_name` varchar(100) NOT NULL DEFAULT '',
  `meal_name` varchar(255) NOT NULL DEFAULT '',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';
 */
@Mapper
public interface OrderMapper {

  @Insert({
      "INSERT INTO tb_order (`user_id`,`mch_id`,`mch_name`,`meal_name`,`created_at`,`updated_at`) "
          + "VALUES (#{userId},#{mchId},#{mchName},#{mealName},now(),now())"})
  int submitOrder(OrderDo orderDo);

  @Select({"SELECT `id`,`user_id`,`mch_id`,`mch_name`,`meal_name`,`created_at`,`updated_at` "
      + "FROM tb_order WHERE user_id = #{userId}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "mch_id", property = "mchId"),
      @Result(column = "mch_name", property = "mchName"),
      @Result(column = "meal_name", property = "mealName"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  List<OrderDo> findOrderByUser(String userId);

  @Select({"SELECT `id`,`user_id`,`mch_id`,`mch_name`,`meal_name`,`created_at`,`updated_at` "
      + "FROM tb_order WHERE to_days(`created_at`) = to_days(now())"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "user_id", property = "userId"),
      @Result(column = "mch_id", property = "mchId"),
      @Result(column = "mch_name", property = "mchName"),
      @Result(column = "meal_name", property = "mealName"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  List<OrderDo> findTodayOrder();
}
