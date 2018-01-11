import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/Home'
import Index from '@/views/Index'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Log from '@/views/Log'
import Write from '@/views/blog/Write'

Vue.use(Router)

export default new Router({
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
	    	}
      ]
    },
    {
    	 path: '/user/login',
    	 component:Login
    },
    {
    	 path: '/user/register',
    	 component:Register
    },
    {
    	path: '/blog/write',
    	component:Write
    }
  ]
})
