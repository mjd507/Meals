<template>
  <div id="app">
    <m-header/>
    <router-view/>
    <!-- helper layout -->
    <m-loading v-if="showLoading" />
    <m-login v-if="showLoginLayout" />
  </div>
</template>

<script>
import mHeader from '@/components/header'
import mLoading from '@/components/loading'
import mLogin from '@/components/login'
import bus from './modules/EventBus'
import EventDef from './modules/EventDef'

export default {
  name: 'App',
  components: {
    mHeader,
    mLoading,
    mLogin
  },
  data() {
    return {
      // 信息提示
      showMsg: false,
      // Loading 提示
      showLoading: false,
      // 登录面板
      showLoginLayout: false
    }
  },
  created() {
    // 提示消息控制
    bus.$on(EventDef.showMsg, (msgObj) => {
      this.showMsgByType(msgObj)
    })
    // Loading 加载控制
    bus.$on(EventDef.showLoading, (status) => {
      this.showLoading = status
    })
    // showLoginLayout 登录面板
    bus.$on(EventDef.showLoginLayout, (status) => {
      this.showLoginLayout = status
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
  min-width: 1000px;
}
</style>
