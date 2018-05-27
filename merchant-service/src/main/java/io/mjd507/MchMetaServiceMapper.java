package io.mjd507;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 登录配置数据
 *
 * Created by mjd on 2018/4/18 19:26
 */
/*
CREATE TABLE `tb_mch_meta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `mch_user_id` varchar(50) NOT NULL DEFAULT '',
  `mch_id` varchar(50) NOT NULL DEFAULT '' COMMENT '商户ID',
  `mch_name` varchar(100) NOT NULL DEFAULT '',
  `mch_logo` varchar(100) DEFAULT NULL,
  `mch_desc` varchar(200) DEFAULT NULL,
  `mch_location` varchar(200) NOT NULL DEFAULT '',
  `is_active` smallint(4) DEFAULT '0' COMMENT '0-未激活，1-已激活',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_mch_id` (`mch_id`),
  UNIQUE KEY `idx_mch_user_id` (`mch_user_id`),
  UNIQUE KEY `idx_mch_user_id_mch_id` (`mch_user_id`,`mch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 */
@Mapper
public interface MchMetaServiceMapper {

  @Select({
      "SELECT `id`,`mch_user_id`,`mch_id`,`mch_name`,`mch_logo`,`mch_desc`,`mch_location`,`is_active`,"
          + "`created_at`,`updated_at` FROM tb_mch_meta WHERE `is_active` = 1"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "mch_user_id", property = "mchUserId"),
      @Result(column = "mch_id", property = "mchId"),
      @Result(column = "mch_name", property = "mchName"),
      @Result(column = "mch_logo", property = "mchLogo"),
      @Result(column = "mch_desc", property = "mchDesc"),
      @Result(column = "mch_location", property = "mchLocation"),
      @Result(column = "is_active", property = "isActive"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  List<MchMetaDo> getActiveMerchants();

  @Select({
      "SELECT `id`,`mch_user_id`,`mch_id`,`mch_name`,`mch_logo`,`mch_desc`,`mch_location`,`is_active`,"
          + "`created_at`,`updated_at` FROM tb_mch_meta WHERE `is_active` = 0"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "mch_user_id", property = "mchUserId"),
      @Result(column = "mch_id", property = "mchId"),
      @Result(column = "mch_name", property = "mchName"),
      @Result(column = "mch_logo", property = "mchLogo"),
      @Result(column = "mch_desc", property = "mchDesc"),
      @Result(column = "mch_location", property = "mchLocation"),
      @Result(column = "is_active", property = "isActive"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  List<MchMetaDo> getWaitActiveMerchant();

  @Select({
      "SELECT `id`,`mch_user_id`,`mch_id`,`mch_name`,`mch_logo`,`mch_desc`,`mch_location`,`is_active`,"
          + "`created_at`,`updated_at` FROM tb_mch_meta WHERE `mch_user_id` = #{mchUserId}"})
  @Results({
      @Result(column = "id", property = "id"),
      @Result(column = "mch_user_id", property = "mchUserId"),
      @Result(column = "mch_id", property = "mchId"),
      @Result(column = "mch_name", property = "mchName"),
      @Result(column = "mch_logo", property = "mchLogo"),
      @Result(column = "mch_desc", property = "mchDesc"),
      @Result(column = "mch_location", property = "mchLocation"),
      @Result(column = "is_active", property = "isActive"),
      @Result(column = "created_at", property = "createdAt"),
      @Result(column = "updated_at", property = "updatedAt"),
  })
  MchMetaDo getMchByUserId(String mchUserId);

  @Insert({
      "INSERT INTO tb_mch_meta (`mch_user_id`,`mch_id`,`mch_name`,`mch_logo`,`mch_desc`,"
          + "`mch_location`,`is_active`,`created_at`,`updated_at`) VALUES (#{mchUserId},#{mchId},"
          + "#{mchName},#{mchLogo},#{mchDesc},#{mchLocation},0,now(),now())"})
  int addMerchant(MchMetaDo mchMetaDo);

  @UpdateProvider(type = MchMetaServiceMapSql.class, method = "updateSelective")
  int updateSelective(MchMetaDo mchMetaDo);

  @Delete({"DELETE FROM tb_mch_meta WHERE mch_user_id = #{mchUserId}"})
  int deleteMerchant(String mchUserId);

  @Update({"UPDATE tb_mch_meta set is_active = 1 WHERE mch_id = #{mchId}"})
  int activeMerchant(String mchId);

}
