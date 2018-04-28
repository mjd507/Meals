package io.mjd507.common;

import io.mjd507.qiuniu.QiniuUtils;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by mjd on 2018/4/28 10:21
 */
@RestController
public class ImageUploader {

  @ApiOperation(value = "获取上传 token", notes = "由客户端将图片上传七牛云")
  @RequestMapping(value = "getUploadToken", method = RequestMethod.GET)
  public String getUploadToken(String fileName) {
    return QiniuUtils.uploadToken(null, fileName);
  }

  @ApiOperation(value = "服务端上传图片", notes = "建议客户端各自处理图片上传")
  @RequestMapping(value = "uploadImageByStream", method = RequestMethod.POST)
  public String upload(@RequestParam MultipartFile file, String fileName) {
    byte[] bytes = null;
    try {
      bytes = file.getBytes();
      if (fileName == null) {
        fileName = file.getOriginalFilename();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return QiniuUtils.pubicUpload(bytes, fileName);
  }

  @ApiOperation(value = "服务端上传图片", notes = "建议客户端各自处理图片上传")
  @RequestMapping(value = "uploadImageByPath", method = RequestMethod.POST)
  public String upload(String filePath, String fileName) {
    return QiniuUtils.pubicUpload(filePath, fileName);
  }
}
