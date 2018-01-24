import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/Home'
import Index from '@/views/Index'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Log from '@/views/Log'
import MessageBoard from '@/views/MessageBoard'
import BlogWrite from '@/views/blog/BlogWrite'
import BlogView from '@/views/blog/BlogView'
import BlogAllCategoryTag from '@/views/blog/BlogAllCategoryTag'
import BlogCategoryTag from '@/views/blog/BlogCategoryTag'


import store from '@/store'

import { getToken } from '@/request/token'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '',
          component: Index
        },
	    	{
	    	 path: 'log',
	    	 component:Log
	    	},
	    	{
		    	path: 'messageBoard',
		    	component:MessageBoard
	    	},
		    {
		    	path: 'view/:id',
			    component:BlogView
		    },
		    {
		    	path: ':type/all',
			    component:BlogAllCategoryTag
		    },
		    {
		    	path: ':type/:id',
			    component:BlogCategoryTag
		    }
      ]
    },
    {
    	 path: '/login',
    	 component:Login
    },
    {
    	 path: '/register',
    	 component:Register
    },
    {
    	path: '/write',
    	component:BlogWrite,
    	meta: {
        requireLogin: true
      },
    }
  ]
})

router.beforeEach((to, from, next) => {
	console.info("守卫--" + getToken())
  if (getToken()) { 
    
    if (to.path === '/login') {
      next({ path: '/' })
    } else {
    	console.info(store.state)
      if (store.state.account.length === 0) { 
        store.dispatch('getUserInfo').then(data => { //获取用户信息
          next()
        }).catch(() => {
          store.dispatch('fedLogOut').then(() => {
            next({ path: '/login' })
          })
        })
      }else{
      	next()
      }
    }
  } else {
    if (to.matched.some(r => r.meta.requireLogin)) {
        next({ path: '/login' });
    }
    else {
        next();
    }
  }
})


export default router
