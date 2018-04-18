/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.dao;

import io.mjd507.entity.MerchantVo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author mjd
 * @date 2018/4/18 19:26
 */
@Mapper
public interface MerchantServiceMapper {

  @Select({"SELECT * FROM tb_merchants"})
  List<MerchantVo> getAllMerchants();

  @Insert({"INSERT INTO tb_merchants (name,logo,desc,location) VALUES (#{MerchantVo.name}, "
      + "#{MerchantVo.logo}, #{MerchantVo.desc}, #{MerchantVo.location})"})
  int addMerchant(@Param("MerchantVo") MerchantVo merchantVo);

  @Update({"UPDATE tb_merchants set (name,logo,desc,location) VALUES (#{MerchantVo.name}, "
      + "#{MerchantVo.logo}, #{MerchantVo.desc}, #{MerchantVo.location}) WHERE id = #{MerchantVo.id}"})
  int updateMerchant(@Param("MerchantVo") MerchantVo merchantVo);

  @Delete({"DELETE FROM tb_merchants WHERE id = #{merId}"})
  int deleteMerchant(int merId);
}
