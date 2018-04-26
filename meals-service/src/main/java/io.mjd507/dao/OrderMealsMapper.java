package io.mjd507.dao;

import io.mjd507.entity.OrderVo;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mjd on 2018/4/26 20:39
 */
@Mapper
public interface OrderMealsMapper {

  @Insert({"INSERT INTO tb_order (`userId`,`merchantId`,`merchantName`,`mealsIds`,`mealsNames`,`createdAt`) "
      + "VALUES (#{OrderVo.userId},#{OrderVo.merchantId},#{OrderVo.merchantName},#{OrderVo.mealsIds},#{OrderVo.mealsNames},now())"})
  int AddOrder(@Param("OrderVo") OrderVo orderVo);

  @Select({"SELECT * FROM tb_order WHERE userId = #{userId}"})
  List<OrderVo> findOrderByUser(String userId);

}
