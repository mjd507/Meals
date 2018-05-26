<template>
  <div class="header">
    <div class="title">途虎订餐系统</div>
    <div class="user" @click="handleClick">{{currentStatus === userStatusDef.notLogin.code ? userStatusDef.notLogin.desc : currentStatus === userStatusDef.noUserName.code ? userStatusDef.noUserName.desc : userName }}</div>
  </div>
</template>

<script>
import store from 'store'
import bus from '../modules/EventBus'
import EventDef from '../modules/EventDef'

export default {
  data() {
    return {
      userStatusDef: {
        notLogin: { code: 1, desc: '登录' },
        noUserName: { code: 2, desc: '补全个人信息' },
        hasUserName: { code: 3, desc: '登录并已补全信息' }
      },
      currentStatus: 1
    }
  },
  created() {
    bus.$on(EventDef.updateUserInfo, (userInfo) => {
      this.showUserStatus(userInfo)
    })
    const userInfo = store.get('userInfo')
    this.showUserStatus(userInfo)
  },
  methods: {
    showUserStatus(userInfo) {
      const { userId, userName } = userInfo || ''
      if (!userId) {
        this.currentStatus = this.userStatusDef.notLogin.code
      } else if (!userName) {
        this.currentStatus = this.userStatusDef.noUserName.code
      } else {
        // 如果是更新信息，currentStatus 状态没变
        // 这里先更新没有用户名的状态，10ms 在后刷一次
        this.currentStatus = this.userStatusDef.noUserName.code
        setTimeout(() => {
          this.userName = userName
          this.currentStatus = this.userStatusDef.hasUserName.code
        }, 10)
      }
    },
    handleClick() {
      const { currentStatus, userStatusDef } = this
      if (currentStatus === userStatusDef.notLogin.code) {
        // 显示登录面板
        bus.$emit(EventDef.showLoginLayout, true)
      } else if (currentStatus === userStatusDef.noUserName.code) {
        // 显示用户信息面板
        bus.$emit(EventDef.showUserLayout, true)
      } else if (currentStatus === userStatusDef.hasUserName.code) {
        // 显示用户信息面板
        bus.$emit(EventDef.showUserLayout, true)
      }
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
  .user {
    width: 10rem;
    text-align: right;
    color: white;
    font-size: 1rem;
  }
}
</style>
