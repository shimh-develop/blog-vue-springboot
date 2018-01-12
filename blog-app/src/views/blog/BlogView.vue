<template>
	<div>
  <el-container class="me-view-container">
    <el-main>
		<div class="me-view-card">
			<h1 class="me-view-title">搭建element-ui的Vue前端工程操作</h1>
			<div class="me-view-author">
				<a class="">
					<img class="me-view-picture" src="../../../static/kebi.jpg"></img>
				</a>
				<div class="me-view-info">
					<span>史明辉</span>
					<div class="me-view-meta">
						<span>2018.01.12 15:45</span>
						<span>阅读   1000</span>
						<span>评论   500</span>
					</div>
				</div>
			</div>
			<div>
				<markdown-editor :config=config></markdown-editor>
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
		  		<el-tag class="me-view-tag-item" type="warning">前端</el-tag>
			  	<el-tag class="me-view-tag-item" type="warning">Vue</el-tag>
			  	<el-tag class="me-view-tag-item" type="warning">element-ui</el-tag>
		  	</div>
		  	
		  	<div class="me-view-comment">
		  		
		  		<div class="me-view-comment-write">
		  			<!--<a class="">
						<img class="me-view-picture" src="../../../static/kebi.jpg"></img>
					</a>
					
		  			<el-input
					  type="textarea"
					  :autosize="{ minRows: 2, maxRows: 4}"
					  placeholder="请输入内容">
					</el-input>-->
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
		  		
		  	</div>
		  	
		</div>
    </el-main>
    
  </el-container>
  </div>
</template>

<script>
import MarkdownEditor from '@/components/markdown/MarkdownEditor'	
export default {
  name: 'BlogView',
  data () {
    return {
    	config:{
				toolbarsFlag:false, 
				subfield:false,
				default_open:'preview',
				value:`在文字写书写不同数量的#可以完成不同的标题，如下：
# 一级标题 
## 二级标题 
### 三级标题 
#### 四级标题 
##### 五级标题 
###### 六级标题

等号及减号也可以进行标题的书写，不过只能书写二级标题，并且需要写在文字的下面，减号及等号的数量不会影响标题的基数，如下：

二级标题 
=========

二级标题 
# 一级标题 
## 二级标题 
### 三级标题 
#### 四级标题 
##### 五级标题 
###### 六级标题

等号及减号也可以进行标题的书写，不过只能书写二级标题，并且需要写在文字的下面，减号及等号的数量不会影响标题的基数，如下：

二级标题 
=========

二级标题 
---------`
    	}
    	
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
.me-view-container {
	width: 700px;
	margin: 80px auto 0;
	/*background-color: gainsboro;*/
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
</style>
