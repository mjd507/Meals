package io.mjd507.dao;

import io.mjd507.entity.UserVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author mjd
 * @date 2018/4/15 17:05
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
      "UPDATE tb_user set (userName,nickName,phone,userAvatar,department,userGroup,isGroupLeader) "
          + "values (#{UserVo.userName},#{UserVo.nickName},#{UserVo.phone},#{UserVo.userAvatar},"
          + "#{UserVo.department},#{UserVo.userGroup},#{UserVo.isGroupLeader}) WHERE userId = #{userId}"})
  int updateUserByUserId(@Param("UserVo") UserVo user, @Param("userId") String userId);

  @Delete({"DELETE FROM tb_user WHERE userId = #{userId}"})
  int deleteUserByUserId(String userId);

}
