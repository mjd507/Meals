<template>
  <div class="layout-login" v-if="showLoginLayout">
    <div class="panel">
      <div class="panel-close el-icon-close" @click="hidePanel"></div>
      <el-input class="phone" v-model="phone" type="text" value="number" placeholder="手机号"></el-input>
      <div class="verifyCode">
        <el-input class="vcode" v-model="verifyCode" placeholder="验证码"></el-input>
        <el-button type="danger" round @click="sendVerifyCode" :disabled=isBtnDisable>{{isBtnDisable ? timeDownText:'发送验证码'}}</el-button>
      </div>
      <el-button class="btn-login" type="danger" @click="loginByPhone">登录</el-button>
    </div>
  </div>
</template>

<script>
import bus from '@/modules/EventBus'
import EventDef from '../modules/EventDef'

export default {
  data() {
    return {
      showLoginLayout: false,
      phone: '',
      verifyCode: '',
      isBtnDisable: false,
      timeDownText: '60s后重试'
    }
  },
  created() {},
  methods: {
    sendVerifyCode() {
      if (!this.validPhoneNumber(this.phone)) return
      this.fetch({
        url: this.apis.sendSms,
        method: 'POST',
        data: {
          phone: this.phone
        },
        headers: {
          'content-type': 'application/x-www-form-urlencoded'
        }
      }).then((res) => {
        if (res) {
          let second = 60
          this.isBtnDisable = true
          this.timer = setInterval(() => {
            second -= 1
            if (second === 0) {
              clearInterval(this.timer)
              this.isBtnDisable = false
            }
            this.timeDownText = `${second}s后重试`
          }, 1000)
          bus.$emit(EventDef.showMsg, {
            text: res,
            type: EventDef.MsgType.INFO
          })
        }
      })
    },
    loginByPhone() {
      if (!this.validPhoneNumber(this.phone)) {
        return
      }
      this.fetch({
        url: this.apis.loginByPhone,
        method: 'POST',
        data: {
          phone: this.phone,
          verifyCode: this.verifyCode
        }
      }).then((res) => {
        if (res) {
          this.store.set('userToken', res.extra)
          this.store.set('userInfo', res)
          this.hidePanel()
          // 更新头部用户个人信息
          bus.$emit(EventDef.updateUserInfo, res)
        }
      })
    },
    // === helper method ===
    validPhoneNumber(phone) {
      if (phone && phone.length === 11 && /^1\d{10}/.test(phone)) {
        return true
      } else {
        bus.$emit(EventDef.showMsg, {
          text: '手机号码错误',
          type: EventDef.MsgType.ERROR
        })
        return false
      }
    },
    hidePanel() {
      this.showLoginLayout = false
    }
  }
}
</script>

<style lang="less" scoped>
@item-width: 22rem;
@item-margin: 1.5rem;
.layout-login {
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
    .phone {
      width: @item-width;
      :focus {
        border: 1px solid #dcdfe6;
      }
    }
    .vcode {
      width: auto;
      :focus {
        border: 1px solid #dcdfe6;
      }
    }
    .verifyCode {
      width: @item-width;
      margin-top: @item-margin;
      display: flex;
      align-items: center;
      justify-content: space-between;
    }
    .btn-login {
      width: @item-width;
      margin-top: @item-margin;
    }
  }
}
</style>
