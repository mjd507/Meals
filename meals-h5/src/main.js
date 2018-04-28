// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from 'store'
import App from './App'
import router from './router'
import apis from './modules/Apis'
import fetch from './modules/fetch'

Vue.use(ElementUI)
Vue.config.productionTip = false
Vue.prototype.apis = apis
Vue.prototype.fetch = fetch
Vue.prototype.store = store

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

