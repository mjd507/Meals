<template>
  <div class="wrapper">
    <div class="merchants">
      <div class="mer-item">
        <div class="mer-name"></div>
        <div class="mer-location"></div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {}
  },
  created() {
    this.getMerchantList()
  },
  methods: {
    getMerchantList() {
      this.fetch({
        url: this.apis.getMerchantList
      }).then({})
    }
  }
}
</script>
<style lang="less" scoped>
.wrapper {
  width: 100%;
  background: #f0f0f0;
}
</style>
