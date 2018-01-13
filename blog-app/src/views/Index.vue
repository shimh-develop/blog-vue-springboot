<template>
<div>
  <el-container>
    <el-main>
    	<el-card class="me-area" v-for="o in 8" :key="o">
			  <div slot="header" class="me-article-header">
			  	<el-button class="me-article-icon" type="text">置顶</el-button>
			    	<a @click="view(o)">搭建element-ui的Vue前端工程操作</a>
			    <span class="me-pull-right me-article-count">
			    	<i class="me-icon-comment"></i>&nbsp;20
			    </span>
			    <span class="me-pull-right me-article-count">
			    	<i class="el-icon-view"></i>&nbsp;10
			    </span>
			  </div>
			  <div class="me-artile-description">
			    	基于Spring+SpringMVC+Mybatis分布式敏捷开发系统架构，提供整套公共微服务服务模块：集中权限管理
			    	基于 分布式敏捷开发系统架构，提供整套公共微服务服务模块：集中权限管理
			  </div>
			  <div class="me-article-footer">
			  	<span class="me-article-author">
			    	<i class="me-icon-author"></i>&nbsp;史明辉
			    </span>
			  	
			  	<el-tag size="mini" type="warning">前端</el-tag>
			  	<el-tag size="mini" type="warning">Vue</el-tag>
			  	<el-tag size="mini" type="warning">element-ui</el-tag>
			  	
			  	<span class="me-pull-right me-article-count">
			    	<i class="el-icon-time"></i>&nbsp;3天前
			    </span>
			  	
			  </div>
			</el-card>
			
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
	
export default {
  name: 'index',
  data () {
    return {
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
  	'card-article':CardArticle
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
.me-article-header {
	/*padding: 10px 18px;*/
}
.me-article-title {
	color: #333;
}
.me-article-icon {
	padding: 3px 8px;
}
.me-article-count {
	color: #a6a6a6;
	padding-left: 14px;
}
.me-pull-right {
	float: right;
}
.me-artile-description {
	font-size: 14px;
	line-height: 20px;
	padding-left: 10px;
	margin-bottom: 18px;
	text-indent: 2.5em;
}
.me-article-author {
	padding-left: 10px;
	color: #a6a6a6;
	padding-right: 18px;
	
}

</style>
