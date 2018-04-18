/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.service.impl;

import io.mjd507.dao.MerchantServiceMapper;
import io.mjd507.entity.MerchantVo;
import io.mjd507.service.MerchantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mjd
 * @date 2018/4/18 19:25
 */
@Service
public class MerchantServiceImpl implements MerchantService {

  @Autowired
  MerchantServiceMapper merchantServiceMapper;

  @Override
  public List<MerchantVo> getAllMerchants() {
    return merchantServiceMapper.getAllMerchants();
  }

  @Override
  public int addMerchant(MerchantVo merchantVo) {
    return merchantServiceMapper.addMerchant(merchantVo);
  }

  @Override
  public int updateMerchant(MerchantVo merchantVo) {
    return merchantServiceMapper.updateMerchant(merchantVo);
  }

  @Override
  public int deleteMerchant(int merId) {
    return merchantServiceMapper.deleteMerchant(merId);
  }
}
