package io.mjd507.service;

import io.mjd507.entity.MerchantMetaVo;
import java.util.List;

/**
 * Created by mjd on 2018/4/18 19:22
 */
public interface MerchantService {

  // 给 商家审核者 调用

  List<MerchantMetaVo> findWaitActiveMerchant();

  boolean activeMerchant(String merchantId);

  // 商家自己调用
  boolean addMerchant(MerchantMetaVo merchantVo);

  boolean updateMerchant(MerchantMetaVo merchantVo);

  boolean deleteMerchant(String merchantId);

  // 给用户调用

  List<MerchantMetaVo> getAllMerchants();

}
