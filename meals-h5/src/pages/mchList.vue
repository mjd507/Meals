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
      <div class="btn-choose" v-if="!hasOrder">
        <div class="no-meat" @click="submit(0)">+1 素</div>
        <div class="meat" @click="submit(1)">+1 荤</div>
      </div>
      <div class="btn-cancel" @click="cancel" v-if="hasOrder">取消今日订餐</div>
    </div>
    <div class="right">
      <div class="order-title">
        <div class="title">今日订餐</div>
        <div class="refresh" @click="refresh">刷新列表</div>
        <div class="statistical" @click="statistical">统计</div>
        <div class="history" @click="history">我的订餐历史</div>
      </div>
      <div class="order-list">
        <div class="order-item" v-for="item in todayOrderList" :key="item.id">
          <div class="item-groupname">{{item.userGroup}}</div>
          <div class="item-username">{{item.userName}}</div>
          <div class="item-mealname">{{item.mealName}}</div>
          <div class="item-space"></div>
          <div class="item-time">{{item.date}}</div>
        </div>
      </div>
    </div>
    <pop-dialog ref="popDialog" />
    <history-order ref="historyOrder" />
  </div>
</template>
<script>
import bus from '../modules/EventBus'
import EventDef from '../modules/EventDef'
import PopDialog from './popDialog'
import HistoryOrder from './historyOrder'
import Utils from '../modules/utils'

export default {
  components: {
    PopDialog,
    HistoryOrder
  },
  data() {
    return {
      mch: '',
      todayOrderList: [],
      hasOrder: false
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
            item.date = Utils.formatDateTime2(item.createdAt)
            item.userGroup = item.userGroup || '其它'
            return item
          })
          this.todayOrderList = list.reverse()
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
          this.hasOrder = res === '提交成功'
        }
        this.refresh()
      })
    },
    cancel() {
      this.fetch({
        url: this.apis.cancelOrder,
        method: 'POST'
      }).then((res) => {
        if (res) {
          bus.$emit(EventDef.showMsg, {
            text: res,
            type: EventDef.MsgType.INFO
          })
          this.hasOrder = false
        }
      })
    },
    statistical() {
      this.$refs.popDialog.handleOrderList(this.todayOrderList)
    },
    history() {
      this.fetch({
        url: this.apis.getUserOrders
      }).then((res) => {
        if (res && res.length) {
          this.$refs.historyOrder.showHistory(res)
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
.btn-cancel {
  width: 20rem;
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-top: 2rem;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
  padding: 6px 10px;
  box-sizing: border-box;
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
      margin-left: 2rem;
    }
    .history {
      font-size: 0.8rem;
      background: #ecf5ff;
      color: #409eff;
      text-align: center;
      border: 1px solid #ecf5ff;
      border-radius: 4px;
      padding: 4px 10px;
      margin-left: 6rem;
    }
    .refresh {
      font-size: 0.8rem;
      background: #f56c6c;
      color: #fff;
      text-align: center;
      border: 1px solid #f56c6c;
      border-radius: 4px;
      padding: 4px 10px;
      margin-left: 2rem;
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
      display: flex;
      .item-groupname {
        min-width: 4rem;
        text-align: center;
      }
      .item-username {
        width: 8rem;
        text-align: center;
      }
      .item-mealname {
        min-width: 2rem;
      }
      .item-space {
        min-width: 2rem;
      }
      .item-time {
        min-width: 4rem;
      }
    }
  }
}
</style>
