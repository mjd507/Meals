<template>
  <el-dialog title="订餐统计" :visible.sync="showDialog">
    <el-table :data="orderList" class="table">
      <el-table-column property="groupName" label="小组"></el-table-column>
      <el-table-column property="groupUser" label="姓名" min-width="250">
        <template slot-scope="scope">
          <div class="names">
            <div class="name" v-for="item in scope.row.groupUser" :label="item" :key="item">{{item}}</div>
          </div>
        </template>
      </el-table-column>
      <el-table-column property="groupTotal" label="荤素"></el-table-column>
    </el-table>
    <div class="total">总计：{{total}}</div>
  </el-dialog>
</template>
<script>
export default {
  data() {
    return {
      showDialog: false,
      orderList: [],
      total: ''
    }
  },
  methods: {
    handleOrderList(orderList) {
      const table = []
      const groups = {}
      // 分组
      orderList.forEach((orderItem) => {
        let groupName = orderItem.userGroup
        if (!groupName) {
          groupName = '其它'
        }
        if (!groups[`${groupName}`]) {
          groups[`${groupName}`] = [].concat(orderItem)
        } else {
          groups[`${groupName}`].push(orderItem)
        }
      })
      Object.keys(groups).forEach((groupName) => {
        const tableItem = {}
        tableItem.groupName = groupName
        const groupItem = groups[`${groupName}`]
        tableItem.groupUser = groupItem.map(item => item.userName)
        const meat = groupItem.filter(item => item.mealName === '[+1 荤]')
        const noMeat = groupItem.filter(item => item.mealName === '[+1 素]')
        tableItem.groupTotal = `${meat.length}荤${noMeat.length}素`
        table.push(tableItem)
      })
      this.orderList = table
      // 统计
      const totalMeat = orderList.filter(item => item.mealName === '[+1 荤]')
      const totalNoMeat = orderList.filter(item => item.mealName === '[+1 素]')
      this.total = `${totalMeat.length}荤 ${totalNoMeat.length}素`
      this.showDialog = true
    }
  }
}
</script>
<style lang="less" scoped>
.table {
  max-height: 22rem;
  overflow: auto;
  .names {
    display: flex;
    flex-flow: row wrap;
    margin-left: -10px;
    .name {
      margin-left: 10px;
    }
  }
}

.total {
  background: #f0f0f0;
  text-align: right;
  margin-top: .5rem;
  padding: .5rem 1rem;
  font-weight: bold;
}
</style>
