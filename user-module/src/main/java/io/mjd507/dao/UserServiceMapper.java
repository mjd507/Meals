package io.mjd507.dao;

import io.mjd507.entity.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by mjd on 2018/4/15 17:05
 */
@Mapper
public interface UserServiceMapper {

  @Insert({"INSERT INTO tb_user (userId) VALUES (#{UserVo.userId})"})
  int addUser(@Param("UserVo") UserVo user);

  /**
   * 一个参数可以不加 @Param("")
   */
  @Select({"SELECT * FROM tb_user WHERE userId = #{userId}"})
  UserVo findUserByUserId(String userId);

  @Update({
      "UPDATE tb_user set userName=#{UserVo.userName},nickName=#{UserVo.nickName},"
          + "phone=#{UserVo.phone},avatar=#{UserVo.avatar},department=#{UserVo.department},"
          + "userGroup=#{UserVo.group},userType=#{UserVo.userType},updatedAt=now() "
          + "WHERE userId = #{userId}"})
  int updateUserByUserId(@Param("UserVo") UserVo user, @Param("userId") String userId);

  @Delete({"DELETE FROM tb_user WHERE userId = #{userId}"})
  int deleteUserByUserId(String userId);

  @Update({"UPDATE tb_user set userType = #{userType} WHERE userId = #{userId}"})
  int setUserType(@Param("userId") String userId, @Param("userType") String userType); // 设置用户类型

}
