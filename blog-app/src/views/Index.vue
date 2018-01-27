<template>
<div>
  <el-container>
    <el-main class="me-articles">
    
    	<article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
    	<!--<article-item v-for="a in 4" :key="a" v-bind="articlesTemp2"></article-item>-->
			<article-item v-for="a in 4" :key="a+4" v-bind="articlesTemp"></article-item>
			
    </el-main>
    
    <el-aside>
    	
    	<card-me class="me-area"></card-me>
    	<card-tag :tags="hotTags"></card-tag>
    	<card-article cardHeader="最新文章" :articles="newArticle"></card-article>
    	
    	<card-article cardHeader="最热文章" :articles="hotArticle"></card-article>
    		
    </el-aside>
     
  </el-container>
</div>
</template>

<script>
import CardMe from '@/components/card/CardMe'
import CardArticle from '@/components/card/CardArticle'
import CardTag from '@/components/card/CardTag'
import ArticleItem from '@/components/article/ArticleItem'
	
import {getArticles} from '@/api/article'

export default {
  name: 'index',
	mounted() {
		this.getArticles()
  },
  data () {
    return {
    	articlesTemp:{
    	  weight:0,
		  	title:'搭建element-ui的Vue前端工程操作',
		  	comments:20,
		  	views:10,
		  	summary:'基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构，提供整套公共微服务服务模块：集中权限管理基于 分布式敏捷开发系统架构，提供整套公共微服务服务模块',
		  	author:{
		  		nickname: '史明辉'
		  	},
		  	tags:[
		  		{tagname: '前端'},
		  		{tagname: 'Vue'},
		  		{tagname: 'ElementUI'}
		  	],
		  	createDate:'3天前'
    	},
    	articlesTemp2:{
    	  weight:1,
		  	title:'搭建element-ui的Vue',
		  	comments:20,
		  	views:10,
		  	summary:'基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构，提供整套公共微服务服务模块',
		  	author:{
		  		nickname: '史明辉'
		  	},
		  	tags:[
		  		{tagname: '前端'},
		  		{tagname: 'Vue'},
		  		{tagname: 'ElementUI'}
		  	],
		  	createDate:'3天前'
    	},
    	articles:[],
    	hotTags:[
    		{id:1,name:'前端'},
    		{id:2,name:'后端'},
    		{id:3,name:'代码规范'},
    		{id:4,name:'程序员'},
    		{id:5,name:'开源'},
    		{id:6,name:'设计模式'}
    	],
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
  	},
  	getArticles() {
  		let that = this
  		getArticles().then(data => {
  			if(data.code == 0){
  				that.articles = data.data
  			}
  		}).catch(error => {
  			that.$message({type: 'error', message: '文章加载失败!'})
  		})
  		
  	}
  },
  components:{
  	'card-me':CardMe,
  	'card-article':CardArticle,
  	'card-tag':CardTag,
  	'article-item':ArticleItem
  }
}
</script>

<style scoped>

.el-container{
	width: 960px;
}

.el-aside {
	margin-left: 20px;
	width: 260px;
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
.me-articles .el-card:not(:first-child){
	margin-top: 10px;
	border-radius: 0;
}
</style>
