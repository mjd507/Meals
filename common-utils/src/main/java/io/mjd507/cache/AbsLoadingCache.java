package io.mjd507.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Guava Cache 简单包装
 *
 * Created by mjd on 2018/6/14 16:12
 */
public abstract class AbsLoadingCache<K, V> {

  private static final Logger logger = LoggerFactory.getLogger(AbsLoadingCache.class);
  private int maxSize = 1000;
  private int expireAfterWriteDuration = 30;
  private TimeUnit timeUnit = TimeUnit.MINUTES;

  //Cache初始化或被重置的时间
  private Date resetTime;
  //历史最高记录数
  private long highestSize = 0;
  //创造历史记录的时间
  private Date highestTime;

  private LoadingCache<K, V> loadingCache;

  public LoadingCache<K, V> getLoadingCache() {
    if (loadingCache == null) {
      synchronized (this) {
        if (loadingCache == null) {
          loadingCache = CacheBuilder.newBuilder()
              .maximumSize(maxSize) // 1000 条缓存
              .expireAfterWrite(expireAfterWriteDuration, timeUnit) //数据缓存时间
              .recordStats() // 启用统计
              .removalListener(removalNotification -> {
//                logger.warn("Guava LocalCache Removed Key: {}, Value: {}, Cause:{}",
//                    removalNotification.getKey(), removalNotification.getValue(),
//                    removalNotification.getCause());
              }).build(new CacheLoader<K, V>() {
                @Override
                public V load(K key) {
                  return fetchValue(key);
                }
              });
        }
      }
    }
    return loadingCache;
  }

  /**
   * 根据 key 从数据库或其他数据源中获取 value，该 value 会自动保存到缓存中
   */
  public abstract V fetchValue(K key);

  /**
   * 获取缓存
   */
  public V getCacheValue(K key) {
    V result = null;
    try {
      result = getLoadingCache().get(key);
      if (getLoadingCache().size() > highestSize) {
        highestSize = getLoadingCache().size();
        highestTime = new Date();
      }
    } catch (Exception e) {
      logger.warn("guava 获取缓存异常：{}", e.getMessage());
    }
    return result;
  }

  public void refreshCacheValue(K key) {
    this.getLoadingCache().refresh(key);
  }

  public long getHighestSize() {
    return highestSize;
  }

  public Date getHighestTime() {
    return highestTime;
  }

  public Date getResetTime() {
    return resetTime;
  }

  public void setResetTime(Date resetTime) {
    this.resetTime = resetTime;
  }

  public int getMaximumSize() {
    return maxSize;
  }

  public int getExpireAfterWriteDuration() {
    return expireAfterWriteDuration;
  }

  protected void setMaximumSize(int maximumSize) {
    this.maxSize = maximumSize;
  }

  protected void setExpireAfterWriteDuration(int expireAfterWriteDuration) {
    this.expireAfterWriteDuration = expireAfterWriteDuration;
  }

  protected void setTimeUnit(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }
}
