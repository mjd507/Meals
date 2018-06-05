<template>
  <div class="wrapper">
    <div class="mch-list">
      <div class="mch-item" v-for="mch in mchList" :key="mch.mchId">
        <img class="mch-logo" :src="mch.mchLogo" @click="toMeals(mch.mchId)" />
        <div class="mch-info" @click="toMeals(mch.mchId)">
          <div class="mch-name">{{mch.mchName}}</div>
          <div class="mch-desc">{{mch.mchDesc}}</div>
          <div class="mch-location">{{mch.mchLocation}}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      mchList: []
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
          this.mchList = res
        }
      })
    },
    toMeals(mchId) {
      this.$router.push({
        name: 'meals',
        query: {
          mchId
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
.mch-list {
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  box-sizing: border-box;
  .mch-item {
    padding: 1rem;
    box-sizing: border-box;
    width: 30%;
    margin: 2rem 1.5%;
    border: 1px solid #f0f0f0;
    border-radius: 4px;
    background: #fff;
  }
  .mch-logo {
    width: 100%;
    display: block;
  }
  .mch-info {
    padding: 1rem;
    .mch-name {
      color: #000;
      font-size: 1.2rem;
      font-weight: bold;
    }
    .mch-desc {
      margin-top: 0.4rem;
      color: #999;
      font-size: 0.6rem;
    }
    .mch-location {
      color: #999;
      font-size: 0.6rem;
      text-align: right;
    }
  }
}
</style>
