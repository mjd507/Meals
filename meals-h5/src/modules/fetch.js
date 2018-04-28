import axios from 'axios'
import store from 'store'
import bus from './EventBus'
import EventDef from './EventDef'

const showLoadingFun = () => {
  bus.$emit(EventDef.showLoading, true)
}

const hideLoadingFun = () => {
  bus.$emit(EventDef.showLoading, false)
}

const fetch = (opts) => {
  const { url, data, method = 'get', headers, showLoading = true } = opts

  if (showLoading) { showLoadingFun() }
  const userInfo = store.get('userInfo')
  const myfetch = axios({
    url,
    method,
    data,
    timeout: 5000,
    headers: Object.assign({
      Authorization: userInfo.userId ? userInfo.userId : ''
    }, headers)
  })
  return myfetch
    .then((res) => {
      hideLoadingFun()
      const { code, msg } = res.data;
      if (code - 0 === 0) {
        return res.data.data;
      } else if (code - 0 === 401) {
        // 显示登录页面
        bus.$emit(EventDef.showLoginLayout, true)
        return null;
      }
      if (msg) { // 将错误信息显示处来
        bus.$emit(EventDef.showMsg, {
          text: msg,
          type: EventDef.MsgType.ERROR
        })
      }
      return null;
    })
    .catch((error) => {
      hideLoadingFun()
      return error
    })
}

export default fetch
