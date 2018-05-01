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
  const userInfo = store.get('userInfo') || ''
  const reqObj = {
    url,
    method,
    data,
    params: data,
    timeout: 5000,
    headers: Object.assign({
      Authorization: userInfo.userId ? userInfo.userId : ''
    }, headers)
  }
  if (method === 'get' || method === 'GET') {
    delete reqObj.data
  } else {
    delete reqObj.params
  }
  const myfetch = axios(reqObj)
  return myfetch
    .then((res) => {
      hideLoadingFun()
      // TODO:  HTTP 响应状态码不为 200 时的处理
      const { code, msg } = res.data;
      if (code - 0 === 1) {
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
      const { response } = error || '';
      const { status } = response || '';
      if (status - 0 === 500) {
        bus.$emit(EventDef.showMsg, {
          text: '服务器异常',
          type: EventDef.MsgType.ERROR
        })
      }
      return error
    })
}

export default fetch
