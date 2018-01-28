<template>
<div class="me-ct-body">
<el-container class="me-ct-container">
    <el-main>
		<div class="me-ct-title me-area">
			<template v-if="this.$route.params.type === 'tag'">
				<img class="me-ct-picture" :src="ct.avatar" />
				<h3 class="me-ct-name">{{ct.tagname}}</h3>
			</template>
			
			<template v-else>
				<img class="me-ct-picture" :src="ct.avatar" />
				<h3 class="me-ct-name">{{ct.categoryname}}</h3>
				<p>{{ct.description}}</p>
			</template>
			
			<span class="me-ct-meta">1000  文章</span>
		</div>
		
		<div class="me-ct-articles">
			<article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
		</div>
		
    </el-main>
</el-container>
</div> 
</template>

<script>
import ArticleItem from '@/components/article/ArticleItem'
import {getArticlesByCategory, getArticlesByTag} from '@/api/article'
import {getTag} from '@/api/tag'
import {getCategory} from '@/api/category'


export default {
  name: 'BlogCategoryTag',
  created() {
  	this.getCategoryOrTagAndArticles()
  },
  watch: {
    '$route': 'getCategoryOrTagAndArticles'
  },
  data () {
    return {
    	ct: {},
    	articles: []
    	
    	/*articles:{
    	  isTop:true,
		  	title:'搭建element-ui的Vue前端工程操作',
		  	commentCount:20,
		  	viewCount:10,
		  	abstract:'基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构，提供整套公共微服务服务模块：集中权限管理基于 分布式敏捷开发系统架构，提供整套公共微服务服务模块',
		  	author:'史明辉',
		  	tags:['前端','vue','elementUI'],
		  	createTime:'3天前'
    	}*/
    }
  },
  methods:{
  	getCategoryOrTagAndArticles() {
  		let id = this.$route.params.id
  		let type = this.$route.params.type
  		if('tag' === type){
  			this.getTag(id)
  			this.getArticlesByTag(id)
  		}else{
  			this.getCategory(id)
  			this.getArticlesByCategory(id)
  		}
  		
  	},
  	getCategory(id) {
  		let that = this
  		getCategory(id).then(data => {
  			if(data.code === 0){
  				that.ct = data.data
  			}
  		}).catch(error => {
  			that.$message({type: 'error', message: '文章分类加载失败!'})
  		})
  	},
  	getTag(id) {
  		let that = this
  		getTag(id).then(data => {
  			if(data.code === 0){
  				that.ct = data.data
  			}
  		}).catch(error => {
  			that.$message({type: 'error', message: '标签加载失败!'})
  		})
  	},
  	getArticlesByCategory(id) {
  		let that = this
  		getArticlesByCategory(id).then(data => {
  			if(data.code === 0){
  				that.articles = data.data
  			}
  		}).catch(error => {
  			that.$message({type: 'error', message: '文章加载失败!'})
  		})
  	},
  	getArticlesByTag(id) {
  		let that = this
  		getArticlesByTag(id).then(data => {
  			if(data.code === 0){
  				that.articles = data.data
  			}
  		}).catch(error => {
  			that.$message({type: 'error', message: '文章加载失败!'})
  		})
  	}
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
