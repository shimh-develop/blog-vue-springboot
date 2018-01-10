<template>
<el-container>
	<el-header class="me-area">
		<el-row class="me-header">
  			<el-col :span="4" class="me-header-left">
 				<router-link to="/" class="me-title">
					<img src="../../../static/logo.png"/>
				</router-link>
  			</el-col>
  			<el-col :span="4" :offset="2">
 				<div class="me-write-info">写文章</div>
  			</el-col>
  			<el-col :span="4" :offset="8">
  				<div class="me-write-btn">
 				<el-button round @click="">发布</el-button>
 				<el-button round @click="cancel">取消</el-button>
 				</div>
  			</el-col>
		</el-row>
  	</el-header>
	  
	<el-container class="me-area me-write-box">
		<el-main class="me-write-main">
			<div class="me-write-title">
				<el-input resize="none" 
					  type="textarea"
					  autosize
					  placeholder="请输入标题"
					  class="me-write-input">
					</el-input>
				
			</div>
			<markdown-editor placeholder="正文..." class="me-write-editor"></markdown-editor>
		</el-main>
	</el-container>
	 	  
	<go-top></go-top>  
</el-container>
	
</template>

<script>
import MarkdownEditor from '@/components/markdown/MarkdownEditor'	

export default {
  name: 'Write',
  data (){
  	return {
  		value:'## 123456 ##',
  		mark:{
  			 toolbarsFlag: false , 
  			 subfield: false, 
  			 default_open: "preview"
  		}
  	}
  },
  methods:{
  	cancel (){
  		this.$confirm('文章将不会保存, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.push('/')
        }).catch(() => {
          
        });
  	}
  },
  components:{
  	'markdown-editor':MarkdownEditor
  }
}
</script>

<style>
.el-header {
	position: fixed;
	z-index: 9999;
	min-width: 100%;
	box-shadow: 0 2px 3px hsla(0,0%,7%,.1), 0 0 0 1px hsla(0,0%,7%,.1);
}
.me-write-info {
	line-height: 60px;
	font-size: 18px;
	font-weight: 600;
}
.me-write-btn {
	margin-top: 10px;
}
.me-container{
	margin-bottom: 100px;
}
.me-write-box {
	margin-top: 80px;
	margin-left: 200px;
	margin-right: 200px;
}
.me-write-main {
	padding: 0;
}
.me-write-title {
}
.me-write-input textarea{
	font-size: 32px;
	font-weight: 600;
	height: 20px;
	border: none;
}
.me-write-editor {
	min-height: 650px!important;
}
.me-header-left{
	margin-top: 10px;
}
.me-title img{
	max-height: 2.4rem;
	max-width: 100%;
}
</style>
