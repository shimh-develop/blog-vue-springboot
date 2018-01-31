<template>
<div>
  <el-container>
    <el-main class="me-articles">
    
    	<article-item v-for="a in articles" :key="a.id" v-bind="a"></article-item>
			
    </el-main>
    
    <el-aside>
    	
    	<card-me class="me-area"></card-me>
    	<card-tag :tags="hotTags"></card-tag>
    	<card-article cardHeader="最新文章" :articles="newArticles"></card-article>
    	
    	<card-article cardHeader="最热文章" :articles="hotArticles"></card-article>
    		
    </el-aside>
     
  </el-container>
</div>
</template>

<script>
import CardMe from '@/components/card/CardMe'
import CardArticle from '@/components/card/CardArticle'
import CardTag from '@/components/card/CardTag'
import ArticleItem from '@/components/article/ArticleItem'

import {getArticles, getHotArtices, getNewArtices} from '@/api/article'
import {getHotTags} from '@/api/tag'

export default {
  name: 'Index',
	created() {
		this.getArticles()
		this.getHotArtices()
		this.getNewArtices()
		this.getHotTags()
  },
  data () {
    return {
    	articles:[],
    	hotTags:[],
      hotArticles:[],
      newArticles:[]
    }
  },
  methods:{
  	view (id){
  		this.$router.push({ path: `/view/${id}` })
  	},
  	getArticles() {
  		let that = this
  		getArticles().then(data => {
  			that.articles = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '文章加载失败!',showClose: true})
  			}
  		})
  		
  	},
  	getHotArtices() {
  		let that = this
  		getHotArtices().then(data => {
  			that.hotArticles = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '最热文章加载失败!',showClose: true})
  			}
  			
  		})
  		
  	},
  	getNewArtices() {
  		let that = this
  		getNewArtices().then(data => {
  			that.newArticles = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '最新文章加载失败!',showClose: true})
  			}
  			
  		})
  		
  	},
  	getHotTags() {
  		let that = this
  		getHotTags().then(data => {
  			that.hotTags = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '最热标签加载失败!',showClose: true})
  			}
  			
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
