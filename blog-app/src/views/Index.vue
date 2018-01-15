<template>
<div>
  <el-container>
    <el-main>
    
			<article-item v-for="a in 8" :key="a" v-bind="articles"></article-item>
			
    </el-main>
    
    <el-aside>
    	
    	<card-me class="me-area"></card-me>
    	
    	<card-article cardHeader="最新文章" :articles="newArticle"></card-article>
    	
    	<card-article cardHeader="最热文章" :articles="hotArticle"></card-article>
    		
    </el-aside>
     
  </el-container>
</div>
</template>

<script>
import CardMe from '@/components/card/CardMe'
import CardArticle from '@/components/card/CardArticle'
import ArticleItem from '@/components/article/ArticleItem'
	
export default {
  name: 'index',
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
    	},
      hotArticle:[
      	{id:'7',name:'为什么程序员那么有钱为什么程序员那么有钱为什么程序员那么有钱'},
      	{id:'8',name:'为什么程序员那么有钱为什么程序员那么有钱为什么程序员那么有钱'},
      	{id:'9',name:'为什么程序员那么有钱为什么程序员那么有钱为什么程序员那么有钱'},
      	{id:'10',name:'搭建element-ui的Vue前端工程操作'},
      	{id:'11',name:'搭建element-ui的Vue前端工程操作'},
      	{id:'12',name:'搭建element-ui的Vue前端工程操作'}
      ],
      newArticle:[
      	{id:'1',name:'为什么程序员那么有钱为什么程序员那么有钱为什么程序员那么有钱'},
      	{id:'2',name:'为什么程序员那么有钱为什么程序员那么有钱为什么程序员那么有钱'},
      	{id:'3',name:'为什么程序员那么有钱为什么程序员那么有钱为什么程序员那么有钱'},
      	{id:'4',name:'搭建element-ui的Vue前端工程操作'},
      	{id:'5',name:'搭建element-ui的Vue前端工程操作'},
      	{id:'6',name:'搭建element-ui的Vue前端工程操作'}
      ]
    }
  },
  methods:{
  	view (id){
  		this.$router.push({ path: `/view/${id}` })
  	}
  },
  components:{
  	'card-me':CardMe,
  	'card-article':CardArticle,
  	'article-item':ArticleItem
  }
}
</script>

<style scoped>

.el-container{
/*	margin-top: 80px;
	margin-left: 100px;
	margin-right: 100px;*/
	width: 1000px;
	/*margin: 80px auto;*/
}

.el-aside {
	margin-left: 20px;
	width: 300px;
}
  
.el-main {
	padding: 0px;
  line-height: 16px;
}
.el-card{
	border-radius: 0;
}
.el-card:not(:first-child){
	margin-top: 20px;
	border-radius: 0;
}
</style>
