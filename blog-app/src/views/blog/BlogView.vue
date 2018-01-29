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
						<span>阅读   {{article.views}}</span>
						<span>评论   {{article.comments}}</span>
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
		  		<el-tag v-for="t in article.tags" :key="t.id" class="me-view-tag-item" type="warning">{{t.tagname}}</el-tag>
		  	</div>
		  	
		  	<div class="me-view-comment">
		  		<div class="me-view-comment-write">
					<el-row :gutter="20">
					  <el-col :span="2">
					  	<a class="">
							<img class="me-view-picture" src="../../../static/kebi.jpg"></img>
						</a>
					  </el-col>
					  <el-col :span="22">
					  	<el-input
						  type="textarea"
						  :autosize="{ minRows: 2}"
						  placeholder="你的评论..."
						  class="me-view-comment-text"
						  resize="none">
						</el-input>
					  </el-col>
					</el-row>
					
		  			<el-row :gutter="20">
					  <el-col :span="2" :offset="22">
					  	<el-button type="text">评论</el-button>
					  </el-col>
					</el-row>
		  		</div>
		  		
		  		<div class="me-view-comment-title">
		  			<span>8 条评论</span>
		  		</div>
		  		
		  		<div class="me-view-comment-item" v-for="c in 4" :key="c">
		  			<div class="me-view-comment-author">
						<a class="">
							<img class="me-view-picture" src="../../../static/kebi.jpg"></img>
						</a>
						<div class="me-view-info">
							<span>史明辉</span>
							<div class="me-view-meta">
								<span>{{c}}楼</span>
								<span>2018.01.12 15:45</span>
							</div>
						</div>
					</div>
					<div>
						<p class="me-view-comment-content">真特么矫情。你想要的样子，是以现状作为基础的幻想。你站在现在已拥有的条件下去谈论那些所缺失的，便觉得现实亏欠了自己太多太多。而当你为了这些幻想牺牲现状或者以现状做为代价的时候，你才会发现自己你得到的一切都不是想象的那么回事。你更多的是在为自己的欲望找借口。安贫乐道也是一种心态和修行，这往往比欲望和抱怨更难忍受。当然要努力，但心态更重要。</p>
						<div class="me-view-comment-tools">
							<a class="me-view-comment-tool">
								<i class="me-icon-comment"></i>&nbsp;120人赞
							</a>
							<a class="me-view-comment-tool">
								<i class="me-icon-comment"></i>&nbsp;回复
							</a>
						</div>
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
		  	comments:0,
		  	views:0,
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
    	}
    }
  },
  methods: {
  	getArticle() {
  		let that = this
  		viewArticle(that.$route.params.id).then(data => {
  			if(data.code === 0){
  				Object.assign(that.article, data.data)
  				that.article.editor.value = data.data.body.content
  			}
  		}).catch(error =>{
  			that.$message({type: 'error', message: '文章加载失败!'})
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
	width: 48px;
    height: 48px;
    border: 1px solid #ddd;
    border-radius: 50%;
    vertical-align: middle;
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
/*.me-view-content {
	margin-top: -28px;
}*/
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
