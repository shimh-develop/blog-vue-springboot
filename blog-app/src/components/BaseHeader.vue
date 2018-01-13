<template>
  <el-header class="me-area">
		<el-row class="me-header">
			  <el-col :span="4" class="me-header-left">
			  	 <router-link to="/" class="me-title">
			  	 	<img src="../../static/logo.png"/>
			  	</router-link>
			  </el-col>
			  <el-col class="" :span="20">
			  	<el-menu :router=true menu-trigger="click" active-text-color="#5FB878" :default-active="activeIndex" mode="horizontal" >
					  <el-menu-item index="/">首页</el-menu-item>
					  <el-submenu index="2">
					    <template slot="title">文章分类</template>
					    <el-menu-item index="2-1">java</el-menu-item>
					    <el-menu-item index="2-2">js</el-menu-item>
					    <el-menu-item index="2-3">vue</el-menu-item>
					  </el-submenu>
					  <el-menu-item index="/log">日志</el-menu-item>
					  <el-menu-item index="4">留言板</el-menu-item>
					  
					  <el-col class="" :span="4" :offset="6">
					  <el-menu-item index="/write"><i class="me-icon-comment"></i>&nbsp;&nbsp;写博客</el-menu-item>
					  
					  </el-col>
					  
					  <el-col class="" :span="4" :offset="2">
					  <el-menu-item index="/login">登录</el-menu-item>
					  <el-menu-item index="/register">注册</el-menu-item>
					  </el-col>
					</el-menu>
			  </el-col>
					  
		  	</el-row>
		  </el-header>
</template>

<script>
export default {
  name: 'BaseHeader',
  data (){
  	return {
  		activeIndex: '/'
  	}
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
</style>
