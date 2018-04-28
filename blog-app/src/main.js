
import Vue from 'vue'
import App from './App'

import router from './router'
import store from './store'

import lodash from 'lodash'

import ElementUI from 'element-ui'
import '@/assets/theme/index.css'

import '@/assets/icon/iconfont.css'

import {formatTime} from "./utils/time";


Vue.config.productionTip = false

Vue.use(ElementUI)

Object.defineProperty(Vue.prototype, '$_', { value: lodash })


Vue.directive('title',  function (el, binding) {
  document.title = el.dataset.title
})
// 格式话时间
Vue.filter('format', formatTime)

new Vue({
  el: '#app',
  router,
  store,
  template: '<App/>',
  components: { App }
})
