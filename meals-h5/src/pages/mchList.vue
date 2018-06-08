<template>
  <div class="wrapper">
    <div class="left">
      <div class="mch-item">
        <img class="mch-logo" v-if="mch.mchLogo" :src="mch.mchLogo+'?imageView2/0/w/400'" />
        <div class="mch-info">
          <div class="mch-name">{{mch.mchName}}</div>
          <div class="mch-desc">{{mch.mchDesc}}</div>
          <div class="mch-location">{{mch.mchLocation}}</div>
        </div>
      </div>
      <div class="btn-choose">
        <div class="no-meat" @click="submit(0)">+1 素</div>
        <div class="meat" @click="submit(1)">+1 荤</div>
      </div>
    </div>
    <div class="right">
      <div class="order-title">
        <div class="title">今日订餐</div>
        <div class="refresh" @click="refresh">刷新列表</div>
        <div class="statistical" @click="statistical">统计</div>
      </div>
      <div class="order-list">
        <div class="order-item" v-for="item in todayOrderList" :key="item.id">
          {{item.showName}} - {{item.date}}
        </div>
      </div>
    </div>
    <pop-dialog ref="popDialog" />
  </div>
</template>
<script>
import bus from '../modules/EventBus'
import EventDef from '../modules/EventDef'
import PopDialog from './popDialog'

export default {
  components: {
    PopDialog
  },
  data() {
    return {
      mch: '',
      todayOrderList: []
    }
  },
  created() {
    this.getMerchantList()
    this.getTodayOrders()
  },
  methods: {
    getMerchantList() {
      this.fetch({
        url: this.apis.getMerchantList
      }).then((res) => {
        if (res && res.length) {
          this.mch = res[0]
        }
      })
    },
    getTodayOrders() {
      this.fetch({
        url: this.apis.getTodayOrders
      }).then((res) => {
        if (res && res.length) {
          const list = res.map((item) => {
            const date = new Date(item.createdAt)
            const hour = date.getHours().toString()
            const showHour = hour.length > 1 ? hour : `0${hour}`
            const min = date.getMinutes().toString()
            const showMin = min.length > 1 ? min : `0${min}`
            const sec = date.getSeconds().toString()
            const showSec = sec.length > 1 ? sec : `0${sec}`
            item.date = `${showHour}:${showMin}:${showSec}`
            const { userGroup, userName, mealName } = item
            item.showName = `${userGroup || ''} ${userName || ''} ${mealName ||
              ''}`
            return item
          })
          // for (let i = 0; i < 10; i++) {
          //   list = list.concat(list)
          // }
          this.todayOrderList = list
        }
      })
    },
    refresh() {
      this.getTodayOrders()
    },
    submit(type) {
      this.fetch({
        url: this.apis.submitOrder,
        data: {
          mchId: this.mch.mchId,
          mchName: this.mch.mchName,
          mealName: type === 0 ? ['+1 素'] : ['+1 荤']
        },
        method: 'POST'
      }).then((res) => {
        if (res) {
          bus.$emit(EventDef.showMsg, {
            text: res,
            type: EventDef.MsgType.INFO
          })
        }
        this.refresh()
      })
    },
    statistical() {
      this.$refs.popDialog.handleOrderList(this.todayOrderList)
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
  display: flex;
  box-sizing: border-box;
}
.left {
  width: 50%;
  box-sizing: border-box;
  padding: 2rem;
}

.mch-item {
  padding: 1rem;
  box-sizing: border-box;
  width: 20rem;
  height: 19rem;
  min-width: 20rem;
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

.btn-choose {
  width: 20rem;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 2rem;
  .meat {
    width: 20%;
    background: #f56c6c;
    color: #fff;
    text-align: center;
    border: 1px solid #f56c6c;
    border-radius: 4px;
    padding: 6px 10px;
  }
  .no-meat {
    width: 20%;
    background: #ecf5ff;
    color: #409eff;
    text-align: center;
    border: 1px solid #b3d8ff;
    border-radius: 4px;
    padding: 6px 10px;
  }
}

.right {
  width: 50%;
  box-sizing: border-box;
  padding: 2rem;
  .order-title {
    display: flex;
    // justify-content: space-between;
    .title {
      color: #000;
      font-weight: bold;
      font-size: 1.2rem;
    }
    .statistical {
      font-size: 0.8rem;
      background: #f56c6c;
      color: #fff;
      text-align: center;
      border: 1px solid #f56c6c;
      border-radius: 4px;
      padding: 4px 10px;
      margin-left: 1.2rem;
    }
    .refresh {
      font-size: 0.8rem;
      background: #f56c6c;
      color: #fff;
      text-align: center;
      border: 1px solid #f56c6c;
      border-radius: 4px;
      padding: 4px 10px;
      margin-left: 1.2rem;
    }
  }
  .order-list {
    height: 25rem;
    overflow: auto;
    margin-top: 10px;
    .order-item {
      color: #909399;
      font-size: 1rem;
      padding-top: 10px;
    }
  }
}
</style>
