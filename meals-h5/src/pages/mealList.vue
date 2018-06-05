<template>
  <div class="wrapper">
    <div class="meal-list">
      <div class="meal-item" v-for="meal in mealList" :key="meal.id">
        <img class="meal-logo" :src="meal.mealPic" />
        <div class="meal-info">
          <div class="meal-name">{{meal.mealName}}</div>
          <div class="meal-desc">{{meal.mealDesc}}</div>
          <div class="meal-price">{{meal.mealPrice}}</div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      mealList: []
    }
  },
  created() {
    const mchId = this.$route.query.mchId
    console.warn(mchId)
    this.getMealList(mchId)
  },
  methods: {
    getMealList(mchId) {
      this.fetch({
        url: this.apis.getMealsByMchId,
        data: {
          mchId
        }
      }).then((res) => {
        if (res && res.length) {
          this.mealList = res
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
.meal-list {
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  box-sizing: border-box;
  .meal-item {
    padding: 1rem;
    box-sizing: border-box;
    width: 30%;
    margin: 2rem 1.5%;
    border: 1px solid #f0f0f0;
    border-radius: 4px;
    background: #fff;
  }
  .meal-logo {
    width: 100%;
    display: block;
  }
  .meal-info {
    padding: 1rem;
    .meal-name {
      color: #000;
      font-size: 1.2rem;
    }
    .meal-desc {
      margin-top: 0.4rem;
      color: #999;
      font-size: 0.6rem;
    }
    .meal-price {
      color: #999;
      font-size: 0.6rem;
      text-align: right;
    }
  }
}
</style>
