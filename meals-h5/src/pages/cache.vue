<template>
  <div class="wrapper">
    <div class="cacheItem" v-for="item in cacheList" :key="item.cacheName">
      <div class="item-name">名 称 ：{{item.cacheName}}</div>
      <div class="item-size">数量 ：{{item.cacheSize}} / {{item.maximumSize}}</div>
      <div class="high-size">最大数量： {{item.highestSize}} </div>
      <div class="high-time">最大数量发生时间： {{item.highestTime}}</div>
      <div class="high-time">缓存时间 ：{{item.cacheDuration}} / {{item.cacheDurationTimeUnit}}</div>
      <div class="item-hit">请求命中数 ： {{item.hitCount}}</div>
      <div class="item-succ-excp">加载成功/异常数： {{item.loadSuccessCount}} / {{item.loadExceptionCount}}</div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      cacheList: []
    }
  },
  created() {
    this.fetch({
      url: this.apis.viewCache
    }).then((res) => {
      if (res && res.length) {
        this.cacheList = res
      }
    })
  }
}
</script>
<style lang="less" scoped>
.wrapper {
  display: flex;
}
.cacheItem {
  width: 25%;
  padding: 20px;
  margin-top: 20px;
  margin-left: 20px;
  box-sizing: border-box;
  border-radius: 4px;
  background: #f0f0f0;
  .item-name {
  }
  .item-size {
  }
  .high-size {
  }
  .high-time {
  }
  .item-hit {
  }
  .item-succ-excp {
  }
}
</style>
