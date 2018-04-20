package io.mjd507.common.controller;

import io.mjd507.common.request.DataResponse;
import io.mjd507.common.request.QiniuTokenReq;
import io.mjd507.qiuniu.QiniuUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/4/17 20:52
 */
@Api(value = "QiniuController", description = "七牛云相关API")
@RestController
@RequestMapping(value = "qiniu")
public class QiniuController {

  private static final Logger logger = LoggerFactory.getLogger(QiniuController.class);

  @ApiOperation(value = "获取七牛上传token", notes = "获取七牛云存储等token，request中支持一次获取多个文件的token")
  @RequestMapping(value = "getUploadToken", method = RequestMethod.POST)
  public DataResponse<List<String>> getUploadToken(@RequestBody QiniuTokenReq request) {

    List<String> tokens = new ArrayList<>();
    for (String key : request.getKeys()) {
      String token = QiniuUtils.uploadToken(request.getBucket(), key);
      tokens.add(token);
    }
    return new DataResponse<>(tokens);
  }
}
