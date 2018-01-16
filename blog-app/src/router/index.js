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
    	component:BlogWrite
    }
  ]
})


export default router
