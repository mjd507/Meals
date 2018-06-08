<template>
  <div class="header">
    <div class="title">订餐系统</div>
    <div class="right">
      <button v-if="userInfo && userInfo.userName" @click="showUserSettingLayout">{{userInfo.userName}}</button>
      <button v-else @click="login">登录</button>
    </div>
    <user-setting-layout ref="usersetting"></user-setting-layout>
  </div>
</template>

<script>
import store from 'store'
import userSettingLayout from '@/components/userInfo'
import bus from '../modules/EventBus'
import EventDef from '../modules/EventDef'

export default {
  components: {
    userSettingLayout
  },
  data() {
    return {
      userInfo: ''
    }
  },
  created() {
    bus.$on(EventDef.updateUserInfo, (userInfo) => {
      this.userInfo = userInfo
    })
    this.userInfo = store.get('userInfo')
  },
  methods: {
    login() {
      // 显示登录面板
      bus.$emit(EventDef.showLoginLayout, true)
    },
    showUserSettingLayout() {
      // 显示用户信息面板
      this.$refs.usersetting.showUserSettingLayout = true
    }
  }
}
</script>

<style lang="less" scoped>
.header {
  height: 3rem;
  background: #d12a3e;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 2rem;
  .title {
    color: white;
    font-size: 1.2rem;
    font-weight: bold;
  }
  .right {
    width: 10rem;
    text-align: right;
    button {
      border: 0px;
      background: transparent;
      color: white;
      font-size: 1rem;
      &:focus {
        outline: transparent;
      }
    }
  }
}
</style>
