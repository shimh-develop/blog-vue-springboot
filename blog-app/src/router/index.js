import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/Home'
import Index from '@/views/Index'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Log from '@/views/Log'
import BlogWrite from '@/views/blog/BlogWrite'
import BlogView from '@/views/blog/BlogView'

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
	    	},
	    	{
	    		path: 'view/:id',
	    		component:BlogView
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
    	component:BlogWrite
    }
  ]
})
