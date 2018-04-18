/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.service;

import io.mjd507.entity.MerchantVo;
import java.util.List;

/**
 * @author mjd
 * @date 2018/4/18 19:22
 */
public interface MerchantService {

  List<MerchantVo> getAllMerchants();

  int addMerchant(MerchantVo merchantVo);

  int updateMerchant(MerchantVo merchantVo);

  int deleteMerchant(int merId);

}
