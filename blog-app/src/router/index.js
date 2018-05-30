import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/Home'
/*import Index from '@/views/Index'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Log from '@/views/Log'
import MessageBoard from '@/views/MessageBoard'
import BlogWrite from '@/views/blog/BlogWrite'
import BlogView from '@/views/blog/BlogView'
import BlogAllCategoryTag from '@/views/blog/BlogAllCategoryTag'
import BlogCategoryTag from '@/views/blog/BlogCategoryTag'*/

import {Message} from 'element-ui';


//import { createStore } from '@/store'

import {getToken} from '@/request/token'

Vue.use(Router)

export function createRouter() {
  //const store = createStore()

  const router = new Router({
    mode: 'history', // 注意这里要使用history模式，因为hash不会发送到服务端
    fallback: false,
    routes: [
      {
        path: '/write/:id?',
        name: 'BlogWrite',
        component: () => import('@/views/blog/BlogWrite'),
        meta: {
          requireLogin: true
        },
      },
      {
        path: '',
        //name: 'Home',
        component: Home,
        children: [
          {
            path: '/',
            name: 'Index',
            component: () => import('@/views/Index')
          },
          {
            path: '/log',
            name: 'Log',
            component: () => import('@/views/Log')
          },
          {
            path: '/archives/:year?/:month?',
            name: 'BlogArchive',
            component: () => import('@/views/blog/BlogArchive')
          },
          {
            path: '/messageBoard',
            name: 'MessageBoard',
            component: () => import('@/views/MessageBoard')
          },
          {
            path: '/view/:id',
            name: 'BlogView',
            component: () => import('@/views/blog/BlogView')
          },
          {
            path: '/:type/all',
            name: 'BlogAllCategoryTag',
            component: () => import('@/views/blog/BlogAllCategoryTag')
          },
          {
            path: '/:type/:id',
            name: 'BlogCategoryTag',
            component: () => import('@/views/blog/BlogCategoryTag')
          }
        ]
      },
      {
        path: '/login',
        name: 'Login',
        component: () => import('@/views/Login')
      },
      {
        path: '/register',
        name: 'Register',
        component: () => import('@/views/Register')
      }
    ],
    scrollBehavior(to, from, savedPosition) {
      return {x: 0, y: 0}
    }
  })

  router.beforeEach((to, from, next) => {
    const $store = router.app.$options.store || ''
    const state = $store.state || ''
    if (getToken()) {
      if (to.path === '/login') {
        next({path: '/'})
      } else {
        if (!state.account || state.account.length === 0) {
          $store.dispatch('getUserInfo').then(data => { //获取用户信息
            next()
          }).catch(() => {
            next({path: '/'})
          })
        } else {
          next()
        }
      }
    } else {
      if (_CLIENT_ && to.matched.some(r => r.meta.requireLogin)) {
        Message({
          type: 'warning',
          showClose: true,
          message: '请先登录哦'
        })

      }
      else {
        next();
      }
    }
  })

  // 修改body背景颜色
  const whiteBackground = ['BlogAllCategoryTag','BlogView','BlogWrite']
  router.afterEach((to, from) => {
    if(whiteBackground.indexOf(to.name) > -1){
      _CLIENT_ && (window.document.body.style.backgroundColor = '#fff');
    }else{
      _CLIENT_ && (window.document.body.style.backgroundColor = '#f5f5f5');
    }
  })

  return router
}
