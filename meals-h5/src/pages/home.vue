<template>
  <div class="wrapper">
    <div class="merchants">
      <div class="mer-item" v-for="item in merchantList" :key="item.merchantId">
        <div class="mer-name">{{item.name}}</div>
        <div class="mer-desc">{{item.desc}}</div>
        <div class="mer-location">{{item.location}}</div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      merchantList: []
    }
  },
  created() {
    this.getMerchantList()
  },
  methods: {
    getMerchantList() {
      this.fetch({
        url: this.apis.getMerchantList
      }).then((res) => {
        if (res && res.length) {
          this.merchantList = res
        }
      })
    }
  }
}
</script>
<style lang="less" scoped>
.wrapper {
  width: 100%;
  height: 100%;
}
.merchants {
  width: 100%;
  .mer-item {
    width: 15rem;
    height: 15rem;
  }
}
</style>
