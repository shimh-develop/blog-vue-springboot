// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
//import 'element-ui/lib/theme-chalk/index.css'
import '../static/theme/index.css'
import '@/assets/icon/iconfont.css'

import GoTop from '@/components/gotop/GoTop'

Vue.config.productionTip = false

Vue.use(ElementUI)


Vue.component('go-top', GoTop)

new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
