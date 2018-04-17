/*
 * Copyright 2018 tuhu.cn All right reserved. This software is the
 * confidential and proprietary information of tuhu.cn ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Tuhu.cn
 */
package io.mjd507.common.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author mjd
 * @date 2018/4/17 20:55
 */
@Setter
@Getter
public class QiniuTokenReq {
  String bucket;
  List<String> keys;
  long expires;
}
