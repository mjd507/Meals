<template>
  <div id="app">
    <m-header/>
    <router-view/>
    <!-- helper layout -->
    <m-loading v-if="showLoading" />
  </div>
</template>

<script>
import mHeader from '@/components/header'
import mLoading from '@/components/loading'
import bus from './modules/EventBus'
import EventDef from './modules/EventDef'

export default {
  name: 'App',
  components: {
    mHeader,
    mLoading
  },
  data() {
    return {
      // 信息提示
      showMsg: false,
      // Loading 提示
      showLoading: false
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
