package io.mjd507.dao;

import io.mjd507.entity.MerchantUserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mjd on 2018/4/21 18:29
 */
@Mapper
public interface MerchantUserServiceMapper {

  @Insert({"INSERT INTO tb_merchant_user (userId) VALUES (#{UserVo.userId})"})
  int addUser(@Param("UserVo") MerchantUserVo user);

  /**
   * 一个参数可以不加 @Param("")
   */
  @Select({"SELECT * FROM tb_merchant_user WHERE userId = #{userId}"})
  MerchantUserVo findUserByUserId(String userId);

  @Update({
      "UPDATE tb_merchant_user set userName=#{UserVo.userName},nickName=#{UserVo.nickName},"
          + "phone=#{UserVo.phone},avatar=#{UserVo.avatar},updatedAt=now() "
          + "WHERE userId = #{userId}"})
  int updateUserByUserId(@Param("UserVo") MerchantUserVo user, @Param("userId") String userId);

  @Delete({"DELETE FROM tb_merchant_user WHERE userId = #{userId}"})
  int deleteUserByUserId(String userId);

}
