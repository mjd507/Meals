<template>
  <div id="app">
    <mHeader/>
    <router-view/>
    <!-- helper layout -->
    <mLogin v-if="showLoginLayout" />
    <mLoading v-if="showLoading" />
    <mUserInfo v-if="showUserLayout" />
  </div>
</template>

<script>
import Header from '@/components/header'
import Login from '@/components/login'
import Loading from '@/components/loading'
import UserInfo from '@/components/userInfo'
import bus from './modules/EventBus'
import EventDef from './modules/EventDef'

export default {
  name: 'App',
  components: {
    mHeader: Header,
    mLogin: Login,
    mLoading: Loading,
    mUserInfo: UserInfo
  },
  data() {
    return {
      // 登录面板
      showLoginLayout: false,
      // 信息提示
      showMsg: false,
      // Loading 提示
      showLoading: false,
      // 用户信息设置面板
      showUserLayout: false
    }
  },
  created() {
    // 登录页面控制
    bus.$on(EventDef.showLoginLayout, (status) => {
      this.showLoginLayout = status
    })
    // 提示消息控制
    bus.$on(EventDef.showMsg, (msgObj) => {
      this.showMsgByType(msgObj)
    })
    // Loading 加载控制
    bus.$on(EventDef.showLoading, (status) => {
      this.showLoading = status
    })
    // 用户信息设置界面
    bus.$on(EventDef.showUserLayout, (status) => {
      this.showUserLayout = status
    })
  },
  methods: {
    showMsgByType(msgObj) {
      const { text, type } = msgObj
      const { MsgType } = EventDef
      if (type === MsgType.ERROR) {
        this.$message.error(text)
      } else if (type === MsgType.INFO) {
        this.$message(text)
      } else if (type === MsgType.SUCCESS) {
        this.$message({
          message: text,
          type: 'success'
        })
      } else if (type === MsgType.WARNING) {
        this.$message({
          message: text,
          type: 'warning'
        })
      }
    }
  }
}
</script>

<style lang="less" scoped>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100%;
  height: 100%;
  box-sizing: border-box;
  text-align: left;
  margin: auto;
  background: #fff;
  position: relative;
}
</style>
