package io.mjd507.analysis;

import com.google.common.cache.CacheStats;
import com.google.common.collect.Maps;
import io.mjd507.cache.AbsLoadingCache;
import io.mjd507.module.login.TokenCache;
import io.mjd507.module.user.UserCache;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mjd on 2018/6/14 17:17
 */
@Component
public class CacheManager {

  @Autowired
  TokenCache tokenCache;

  @Autowired
  UserCache userCache;

  private static Map<String, AbsLoadingCache<?, ?>> cacheMap = Maps.newLinkedHashMap();

  @PostConstruct
  public void init() {
    cacheMap.put("TokenCache", tokenCache);
    cacheMap.put("UserCache", userCache);
  }

  private static AbsLoadingCache getCacheByName(String cacheName) {
    return cacheMap.get(cacheName);
  }

  /**
   * 获取所有缓存的名字（即缓存实现类的名称）
   */
  private static Set<String> getCacheNames() {
    return cacheMap.keySet();
  }

  /**
   * 返回所有缓存的统计数据
   *
   * @return List<Map < 统计指标 , 统计数据>>
   */
  public static ArrayList<Map<String, Object>> getAllCacheStats() {
    List<String> cacheNameList = new ArrayList<>(cacheMap.keySet());
    Collections.sort(cacheNameList);//按照字母排序

    //遍历所有缓存，获取统计数据
    ArrayList<Map<String, Object>> list = new ArrayList<>();
    for (String cacheName : cacheNameList) {
      list.add(getCacheStatsToMap(cacheName));
    }
    return list;
  }

  /**
   * 返回一个缓存的统计数据
   */
  public static Map<String, Object> getCacheStatsToMap(String cacheName) {
    Map<String, Object> map = new LinkedHashMap<>();
    AbsLoadingCache<?, ?> cache = getCacheByName(cacheName);
    CacheStats cs = cache.getLoadingCache().stats();
    NumberFormat percent = NumberFormat.getPercentInstance(); // 建立百分比格式化用
    percent.setMaximumFractionDigits(1); // 百分比小数点后的位数
    map.put("cacheName", cacheName);
    map.put("size", cache.getLoadingCache().size());
    map.put("maximumSize", cache.getMaximumSize());
    map.put("survivalDuration", cache.getExpireAfterWriteDuration());
    map.put("hitCount", cs.hitCount());
    map.put("hitRate", percent.format(cs.hitRate()));
    map.put("missRate", percent.format(cs.missRate()));
    map.put("loadSuccessCount", cs.loadSuccessCount());
    map.put("loadExceptionCount", cs.loadExceptionCount());
    map.put("totalLoadTime", cs.totalLoadTime() / 1000000);
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    if (cache.getResetTime() != null) {
      map.put("resetTime", df.format(cache.getResetTime()));
    }
    map.put("highestSize", cache.getHighestSize());
    if (cache.getHighestTime() != null) {
      map.put("highestTime", df.format(cache.getHighestTime()));
    }
    return map;
  }

  /**
   * 清空缓存
   */
  public static void resetCache(String cacheName) {
    AbsLoadingCache cache = getCacheByName(cacheName);
    cache.getLoadingCache().invalidateAll();
    cache.setResetTime(new Date());
  }

}
