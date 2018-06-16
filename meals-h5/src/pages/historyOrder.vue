<template>
  <el-dialog title="订餐历史" :visible.sync="showDialog">
    <el-table :data="orderHistory" class="table">
      <el-table-column property="date" label="订餐时间"></el-table-column>
      <el-table-column property="mealName" label="荤素" ></el-table-column>
    </el-table>
    <div class="total">总计：{{total}}</div>
  </el-dialog>
</template>
<script>
import Utils from '../modules/utils'

export default {
  data() {
    return {
      showDialog: false,
      orderHistory: [],
      total: ''
    }
  },
  methods: {
    showHistory(orderList) {
      orderList.reverse().forEach((orderItem) => {
        orderItem.date = Utils.formatDateTime(orderItem.createdAt)
      })
      this.orderHistory = orderList
      // 统计
      const totalMeat = orderList.filter(item => item.mealName === '[+1 荤]')
      const totalNoMeat = orderList.filter(item => item.mealName === '[+1 素]')
      this.total = `${totalMeat.length}荤 + ${totalNoMeat.length}素 = ${orderList.length}份`
      this.showDialog = true
    }
  }
}
</script>
<style lang="less" scoped>
.table {
  max-height: 21rem;
  overflow: auto;
}

.total {
  background: #f0f0f0;
  text-align: right;
  margin-top: .5rem;
  padding: .5rem 1rem;
  font-weight: bold;
}
</style>
