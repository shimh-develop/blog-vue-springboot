<template>
  <el-header class="me-area">
		<el-row class="me-header">
			  <el-col :span="4" class="me-header-left">
			  	 <router-link to="/" class="me-title">
			  	 	<img src="../../static/logo.png"/>
			  	</router-link>
			  </el-col>
			  
			  
			  <el-col v-if="!simple" :span="spanNumber">
			  	<el-menu :router=true menu-trigger="click" active-text-color="#5FB878" :default-active="activeIndex" mode="horizontal" >
					  <el-menu-item index="/">首页</el-menu-item>
					  <el-menu-item index="/category/all">文章分类</el-menu-item>
					  <el-menu-item index="/tag/all">标签</el-menu-item>
					  <el-menu-item index="/log">日志</el-menu-item>
					  <el-menu-item index="/messageBoard">留言板</el-menu-item>
					  
					  <el-col :span="4" :offset="4">
					  	<el-menu-item index="/write"><i class="el-icon-edit"></i>写文章</el-menu-item>
					  </el-col>
					  
					  <el-col v-if="!isLogin" :span="4" :offset="2">
						  <el-menu-item index="/login"><el-button type="text">登录</el-button></el-menu-item>
						  <el-menu-item index="/register"><el-button type="text">注册</el-button></el-menu-item>
					  </el-col>
						  
					</el-menu>
			  </el-col>
			  
			  <template v-else>
			  	<slot></slot>
			  </template>
			  
			  <el-col v-show="isLogin" :span="2">
				  <el-menu menu-trigger="click" mode="horizontal" active-text-color="#5FB878">
					  <el-submenu index="3">
						    <template slot="title">
									<img class="me-header-picture" src="../../static/kebi.jpg" />						    	
						    </template>
						    <el-menu-item index="3-3"><i class="el-icon-back"></i>退出</el-menu-item>
						</el-submenu>
					</el-menu>	
				</el-col>
				
		</el-row>
	</el-header>
</template>

<script>
export default {
  name: 'BaseHeader',
  props:{
  	activeIndex:String,
  	simple:{
      type: Boolean,
      default: false
    }
  },
  data (){
  	return {
  		isLogin:false
  	}
  },
  computed:{
  	spanNumber (){
  		return this.isLogin ? 16:20
  	}
  },
  methods:{
  }
}
</script>

<style>

.el-header {
  position: fixed;
  z-index: 1024;
  min-width: 100%;
  box-shadow: 0 2px 3px hsla(0,0%,7%,.1), 0 0 0 1px hsla(0,0%,7%,.1);
}
  
.me-title{
	margin-top: 10px;
	font-size: 24px ;
}

.me-header-left{
		margin-top: 10px;
}
.me-title img{
	max-height: 2.4rem;
	max-width: 100%;
}
.me-header-picture {
	width: 36px;
    height: 36px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
}
</style>