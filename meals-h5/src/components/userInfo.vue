<template>
  <div class="layout-user-setting" v-if="showUserSettingLayout">
    <div class="panel">
      <div class="panel-close el-icon-close" @click="hidePanel"></div>
      <!-- <el-select class="department" v-model="currDepartment" placeholder="选择部门">
        <el-option v-for="item in departments" :key="item.value" :value="item.value">
        </el-option>
      </el-select> -->
      <el-select class="group" v-model="currGroup" placeholder="选择组别">
        <el-option v-for="item in groups" :key="item.value" :value="item.value">
        </el-option>
      </el-select>
      <el-input class="userName" v-model="userName" type="text" placeholder="输入姓名"></el-input>
      <el-button class="btn-login" type="danger" @click="confirm">确定</el-button>
    </div>
  </div>
</template>

<script>
import store from 'store'
import bus from '@/modules/EventBus'
import EventDef from '../modules/EventDef'

export default {
  data() {
    return {
      showUserSettingLayout: false,
      departments: [{ value: '研发部' }],
      currDepartment: '研发部',
      groups: [
        { value: '大前端组' },
        { value: '业务系统组' },
        { value: '金融研发组' },
        { value: '马牌sass组' }
      ],
      currGroup: '',
      userName: ''
    }
  },
  created() {
    const userInfo = store.get('userInfo') || ''
    const { userName, department, userGroup } = userInfo
    this.userName = userName
    this.currDepartment = department
    this.currGroup = userGroup
  },
  methods: {
    confirm() {
      if (!this.vaildInput()) return
      this.fetch({
        url: this.apis.updateUser,
        method: 'POST',
        data: {
          userName: this.userName,
          department: this.currDepartment,
          userGroup: this.currGroup
        }
      }).then((res) => {
        if (res) {
          this.hidePanel()
          bus.$emit(EventDef.showMsg, {
            text: res,
            type: EventDef.MsgType.INFO
          })
          this.getUserInfo()
        }
      })
    },
    getUserInfo() {
      this.fetch({
        url: this.apis.getUserInfo
      }).then((res) => {
        if (res) {
          store.set('userInfo', res)
          bus.$emit(EventDef.updateUserInfo, res)
        }
      })
    },
    hidePanel() {
      this.showUserSettingLayout = false
    },
    vaildInput() {
      if (!this.userName) {
        bus.$emit(EventDef.showMsg, {
          text: '输入用户名',
          type: EventDef.MsgType.WARNING
        })
        return false
      }
      return true
    }
  }
}
</script>

<style lang="less" scoped>
@item-width: 22rem;
@item-margin: 1.5rem;
.layout-user-setting {
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  box-sizing: border-box;
  z-index: 9;
  background: rgba(0, 0, 0, 0.3);
  display: flex;
  align-items: center;
  justify-content: center;
  .panel {
    width: 32.5rem;
    height: 20rem;
    background: #fff;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    border-radius: 1rem;
    position: relative;
    .panel-close {
      content: '';
      position: absolute;
      top: 1rem;
      right: 2rem;
      color: #ccc;
    }
    .department,
    .group,
    .userName {
      width: @item-width;
      margin-top: @item-margin;
    }
    .btn-login {
      width: @item-width;
      margin-top: @item-margin;
    }
  }
}
</style>
