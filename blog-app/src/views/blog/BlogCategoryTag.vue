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
			
			<span class="me-ct-meta">{{articles.length}} 文章</span>
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
  			that.ct = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '文章分类加载失败',showClose: true})
  			}
  		})
  	},
  	getTag(id) {
  		let that = this
  		getTag(id).then(data => {
  			that.ct = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '标签加载失败',showClose: true})
  			}
  		})
  	},
  	getArticlesByCategory(id) {
  		let that = this
  		getArticlesByCategory(id).then(data => {
  			that.articles = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '文章加载失败',showClose: true})
  			}
  		})
  	},
  	getArticlesByTag(id) {
  		let that = this
  		getArticlesByTag(id).then(data => {
  			that.articles = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '文章加载失败',showClose: true})
  			}
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
