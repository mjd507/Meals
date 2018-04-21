package io.mjd507.qiuniu;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import java.util.ResourceBundle;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 七牛相关工具类
 *
 * Created by mjd on 2018/3/25 11:19
 */
public class QiniuUtils {

  public static final Logger logger = LoggerFactory.getLogger(QiniuUtils.class);

  private static String accessKey;
  private static String secretKey;
  private static String bucket;
  private static String domain;
  private static String privateBucket;
  private static String privateDomain;
  private static Auth auth;
  private static UploadManager uploadManager;

  static {
    try {

      ResourceBundle rb = ResourceBundle.getBundle("qiniu");

      accessKey = rb.getString("qiniu.accessKey");
      secretKey = rb.getString("qiniu.secretKey");
      domain = rb.getString("qiniu.domain");
      bucket = rb.getString("qiniu.bucket");

      auth = Auth.create(accessKey, secretKey);

      Zone z = Zone.zone0();
      Configuration c = new Configuration(z);

      uploadManager = new UploadManager(c);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String uploadToken(String requestBucket, String key) {
    if (requestBucket == null) {
      requestBucket = bucket;
    }
    return auth.uploadToken(requestBucket, key);
  }

  public static String pubicUpload(byte[] data, String name) {
    String token = uploadToken(bucket, null);
    String key = name == null ? UUID.randomUUID().toString() : name;
    try {
      uploadManager.put(data, key, token);
    } catch (QiniuException e) {
      Response r = e.response;
      // 请求失败时简单状态信息
      logger.error(r.toString());
    }
    return domain + key;
  }

  public static String pubicUpload(String filePath, String name) {
    String token = uploadToken(bucket, null);
    String key = name == null ? UUID.randomUUID().toString() : name;
    try {
      uploadManager.put(filePath, key, token);
    } catch (QiniuException e) {
      Response r = e.response;
      // 请求失败时简单状态信息
      logger.error(r.toString());
    }
    return domain + key;
  }
}
