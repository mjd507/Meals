package io.mjd507.service.impl;

import io.mjd507.service.MerchantService;
import io.mjd507.dao.MerchantServiceMapper;
import io.mjd507.entity.MerchantMetaVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mjd on 2018/4/18 19:25
 */
@Service
public class MerchantServiceImpl implements MerchantService {

  @Autowired
  MerchantServiceMapper merchantServiceMapper;

  @Override
  public List<MerchantMetaVo> findWaitActiveMerchant() {
    return merchantServiceMapper.findWaitActiveMerchant();
  }

  @Override
  public boolean activeMerchant(String merchantId) {
    return merchantServiceMapper.activeMerchant(merchantId) == 1;
  }

  @Override
  public boolean addMerchant(MerchantMetaVo merchantVo) {
    return merchantServiceMapper.addMerchant(merchantVo) == 1;
  }

  @Override
  public boolean updateMerchant(MerchantMetaVo merchantVo) {
    return merchantServiceMapper.updateMerchant(merchantVo) == 1;
  }

  @Override
  public boolean deleteMerchant(String merId) {
    return merchantServiceMapper.deleteMerchant(merId) == 1;
  }


  @Override
  public List<MerchantMetaVo> getAllMerchants() {
    return merchantServiceMapper.getAllMerchants();
  }

}
