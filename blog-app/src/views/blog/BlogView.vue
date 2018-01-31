<template>
<div class="me-view-body">
<el-container class="me-view-container">
    <el-main>
		<div class="me-view-card">
			<h1 class="me-view-title">{{article.title}}</h1>
			<div class="me-view-author">
				<a class="">
					<img class="me-view-picture" :src="article.author.avatar"></img>
				</a>
				<div class="me-view-info">
					<span>{{article.author.nickname}}</span>
					<div class="me-view-meta">
						<span>{{article.createDate}}</span>
						<span>阅读   {{article.viewCounts}}</span>
						<span>评论   {{article.commentCounts}}</span>
					</div>
				</div>
			</div>
			<div class="me-view-content">
				<markdown-editor :editor=article.editor></markdown-editor>
		  	</div>
		  	
		  	<div class="me-view-end">
		  	<el-alert
			    title="文章End..."
			    type="success"
			    center
			    :closable="false">
			  </el-alert>
		  	</div>
		  	
		  	<div class="me-view-tag">
		  		<el-tag v-for="t in article.tags" :key="t.id" class="me-view-tag-item" type="success">{{t.tagname}}</el-tag>
		  	</div>
		  	
		  	<div class="me-view-comment">
		  		<div class="me-view-comment-write">
					<el-row :gutter="20">
					  <el-col :span="2">
					  	<a class="">
							<img class="me-view-picture" :src="avatar"></img>
						</a>
					  </el-col>
					  <el-col :span="22">
					  	<el-input
						  type="textarea"
						  :autosize="{ minRows: 2}"
						  placeholder="你的评论..."
						  class="me-view-comment-text"
						  v-model="comment.content"
						  resize="none">
						</el-input>
					  </el-col>
					</el-row>
					
		  			<el-row :gutter="20">
					  <el-col :span="2" :offset="22">
					  	<el-button type="text" @click="publishComment()">评论</el-button>
					  </el-col>
					</el-row>
		  		</div>
		  		
		  		<div class="me-view-comment-title">
		  			<span>{{comments.length}} 条评论</span>
		  		</div>
		  		
		  		<div class="me-view-comment-item" v-for="(c,index) in comments" :key="c.id">
		  			<div class="me-view-comment-author">
						<a class="">
							<img class="me-view-picture" :src="c.author.avatar"></img>
						</a>
						<div class="me-view-info">
							<span class="me-view-nickname">{{c.author.nickname}}</span>
							<div class="me-view-meta">
								<span>{{comments.length - index}}楼</span>
								<span>{{c.createDate}}</span>
							</div>
						</div>
					</div>
					<div>
						<p class="me-view-comment-content">{{c.content}}</p>
						<!--<div class="me-view-comment-tools">
							<a class="me-view-comment-tool">
								<i class="me-icon-comment"></i>&nbsp;120人赞
							</a>
							<a class="me-view-comment-tool">
								<i class="me-icon-comment"></i>&nbsp;回复
							</a>
						</div>-->
					</div>
		  		</div>
		  		
		  	</div>
		  	
		</div>
    </el-main>

</el-container>
</div> 
</template>

<script>
import MarkdownEditor from '@/components/markdown/MarkdownEditor'	
import {viewArticle} from '@/api/article'
import {getCommentsByArticle, publishComment} from '@/api/comment'

import default_avatar from '@/assets/img/default_avatar.png'

export default {
  name: 'BlogView',
  created() {
  	this.getArticle()
  },
  watch: {
    '$route': 'getArticle'
  },
  data () {
    return {
    	article: {
    		id:'',
		  	title:'',
		  	commentCounts:0,
		  	viewCounts:0,
		  	summary:'',
		  	author:{},
		  	tags:[],
		  	createDate:'',
		  	editor: {
		  		value: '',
		  		toolbarsFlag:false, 
		  		subfield:false,
		  		default_open:'preview'
		  	}
    	},
    	comments:[],
    	comment:{
    		article:{},
    		content:''
    	}
    }
  },
  computed: {
  	avatar() {
  		let avatar = this.$store.state.avatar
  		
  		if(avatar.length > 0){
  			return avatar
  		}
  		return default_avatar
  	}
  },
  methods: {
  	getArticle() {
  		let that = this
  		viewArticle(that.$route.params.id).then(data => {
			Object.assign(that.article, data.data)
			that.article.editor.value = data.data.body.content
			
			that.getCommentsByArticle()
  		}).catch(error =>{
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '文章加载失败',showClose: true})
  			}
  		})
  	},
  	publishComment() {
  		let that = this
  		that.comment.article.id = that.article.id
  		
  		publishComment(that.comment).then(data => {
  			that.$message({type: 'success', message: '评论成功',showClose: true})
  			that.comments.unshift(data.data)
  			that.comment.content = ''
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '评论失败',showClose: true})
  			}
  		})
  	},
  	getCommentsByArticle() {
  		let that = this
  		getCommentsByArticle(that.article.id).then(data => {
  			that.comments = data.data
  		}).catch(error => {
  			if(error !== 'error'){
  				that.$message({type: 'error', message: '评论加载失败',showClose: true})
  			}
  		})
  	}
  },
  components:{
  	'markdown-editor':MarkdownEditor
  },
  //组件内的守卫 调整body的背景色
  beforeRouteEnter(to, from, next) {
    window.document.body.style.backgroundColor = '#fff';
    next();
  },
  beforeRouteLeave(to, from, next) {
    window.document.body.style.backgroundColor = '#f5f5f5';
    next();
  }
}
</script>

<style>
.me-view-body {
	margin: 100px auto 140px;
}
.me-view-container {
	width: 700px;
}
.el-main {
	overflow: hidden;
}
.me-view-title {
	font-size: 34px;
    font-weight: 700;
    line-height: 1.3;
}
.me-view-author {
	margin: 30px 0;
	vertical-align: middle;
}
.me-view-picture {
	width: 40px;
    height: 40px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
    background-color: #5fb878;
}
.me-view-info {
	display: inline-block;
	vertical-align: middle;
	margin-left: 8px;
}
.me-view-meta {
	font-size: 12px;
	color: #969696;
}
.me-view-end {
	margin-top:20px;
}
.me-view-tag {
	margin-top:20px;
}
.me-view-tag-item {
	margin: 0 4px;
}
.me-view-comment {
	margin-top:60px;
}
.me-view-comment-title{
	font-weight: 600;
	border-bottom: 1px solid #f0f0f0;
	padding-bottom: 20px; 
}
.me-view-comment-write {
	margin-top:20px;
}
.me-view-comment-text {
	font-size: 16px;
}

.v-show-content {
	padding: 8px 25px 15px 0px!important;
}

.v-note-wrapper .v-note-panel {
	box-shadow: none!important;
}
.me-view-comment-item {
	margin-top: 20px;
	border-bottom: 1px solid #f0f0f0;
}
.me-view-comment-author {
	margin: 10px 0;
	vertical-align: middle;
}
.me-view-nickname {
	font-size: 14px;
}
.me-view-comment-content {
	line-height: 1.5;
}
.me-view-comment-tools {
	margin-top: 4px;
	margin-bottom: 20px;
}
.me-view-comment-tool {
	color: #a6a6a6;
	padding-right: 14px;
}
</style>
