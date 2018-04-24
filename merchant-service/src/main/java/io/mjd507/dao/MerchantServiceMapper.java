package io.mjd507.dao;

import io.mjd507.entity.MerchantMetaVo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 登录配置数据
 *
 * Created by mjd on 2018/4/18 19:26
 */
@Mapper
public interface MerchantServiceMapper {

  @Select({"SELECT * FROM tb_merchant_meta"})
  List<MerchantMetaVo> getAllMerchants();

  @Insert({"INSERT INTO tb_merchant_meta (merchantId,name,logo,`desc`,location,createAt,updateAt) VALUES "
      + "(#{MerchantVo.merchantId},#{MerchantVo.name},#{MerchantVo.logo},#{MerchantVo.desc},#{MerchantVo.location},now(),now())"})
  int addMerchant(@Param("MerchantVo") MerchantMetaVo merchantVo);

  @Update({"UPDATE tb_merchant_meta set (name,logo,desc,location,updateAt) VALUES (#{MerchantVo.name},"
      + "#{MerchantVo.logo},#{MerchantVo.desc},#{MerchantVo.location},now()) WHERE merchantId = #{MerchantVo.merchantId}"})
  int updateMerchant(@Param("MerchantVo") MerchantMetaVo merchantVo);

  @Delete({"DELETE FROM tb_merchant_meta WHERE merchantId = #{merchantId}"})
  int deleteMerchant(int merchantId);

  @Select({"SELECT * FROM tb_merchant_meta WHERE isActive = '2'"})
  List<MerchantMetaVo> findWaitActiveMerchant();

  @Update({"UPDATE tb_merchant_meta set isActive = 1 WHERE merchantId = #{merchantId}"})
  int activeMerchant(String merchantId);

}
