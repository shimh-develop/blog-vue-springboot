<template>
<div class="me-ct-body">
<el-container class="me-ct-container">
    <el-main>
		<div class="me-ct-title me-area">
			<img class="me-ct-picture" src="../../../static/vue.png" />
			<h3 class="me-ct-name">vue框架</h3>
			<p>javascript的mvvm框架ffffffffffssssssjavascript的mvvm框架ffffffffffssssss</p>
			
			<span class="me-ct-meta">1000  文章</span>
		</div>
		
		<div class="me-ct-articles">
			<article-item v-for="a in 8" :key="a" v-bind="articles"></article-item>
		</div>
		
    </el-main>
</el-container>
</div> 
</template>

<script>
import ArticleItem from '@/components/article/ArticleItem'
export default {
  name: 'BlogCategoryTag',
  data () {
    return {
    	articles:{
    	  isTop:true,
		  	title:'搭建element-ui的Vue前端工程操作',
		  	commentCount:20,
		  	viewCount:10,
		  	abstract:'基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构，提供整套公共微服务服务模块：集中权限管理基于 分布式敏捷开发系统架构，提供整套公共微服务服务模块',
		  	author:'史明辉',
		  	tags:['前端','vue','elementUI'],
		  	createTime:'3天前'
    	}
    }
  },
  computed: {
  	
  },
  methods:{
  },
  components:{
  	'article-item':ArticleItem
  }
}
</script>

<style>
.me-ct-body {
	margin: 60px auto 140px;
	min-width: 100%;
}
.el-main {
	padding: 0;
}
.me-ct-title{
	text-align: center;
	height: 150px;
	padding: 20px;
}
.me-ct-picture {
	width: 60px;
    height: 60px;
}
.me-ct-name {
	font-size: 28px;
}
.me-ct-meta {
	font-size: 12px;
	color: #969696;
}

.me-ct-articles {
	width: 640px;
	margin: 30px auto;
}

.el-card{
	border-radius: 0;
}
.el-card:not(:first-child){
	margin-top: 20px;
	border-radius: 0;
}

</style>
