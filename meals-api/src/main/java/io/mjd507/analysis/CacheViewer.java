package io.mjd507.analysis;

import io.mjd507.common.DataResponse;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mjd on 2018/6/14 18:57
 */
@RestController
@RequestMapping(value = "cache")
public class CacheViewer {

  @RequestMapping(value = "/stats", method = RequestMethod.GET)
  public DataResponse<Map<String, Object>> cacheStats(String cacheName) {
    Map<String, Object> map = CacheManager.getCacheStatsToMap(cacheName);
    return new DataResponse(map);
  }

  @RequestMapping(value = "/stats/all", method = RequestMethod.GET)
  public DataResponse<List<Map<String, Object>>> allCacheStats() {
    List<Map<String, Object>> list = CacheManager.getAllCacheStats();
    return new DataResponse(list);
  }

  /**
   * 清空缓存数据、并返回清空后的统计信息
   */
  @RequestMapping(value = "/reset", method = RequestMethod.GET)
  public DataResponse<String> cacheReset(String cacheName) {
    CacheManager.resetCache(cacheName);
    return new DataResponse<>("已经成功重置了缓存");
  }

}
