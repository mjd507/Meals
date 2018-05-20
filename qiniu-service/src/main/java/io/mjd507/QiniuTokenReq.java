package io.mjd507;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by mjd on 2018/4/17 20:55
 */
@Setter
@Getter
public class QiniuTokenReq {
  String bucket;
  List<String> keys;
  long expires;
}
