<template>
<div id="write">
<el-container>
  	<base-header :simple=true>
		<el-col :span="4" :offset="2">
			<div class="me-write-info">写文章</div>
		</el-col>
		<el-col :span="4" :offset="6">
			<div class="me-write-btn">
			<el-button round @click="publishShow">发布</el-button>
			<el-button round @click="cancel">取消</el-button>
			</div>
		</el-col>
  	</base-header>
	  
	<el-container class="me-area me-write-box">
		<el-main class="me-write-main">
			<div class="me-write-title">
				<el-input resize="none" 
					  type="textarea"
					  autosize
					  v-model="articleForm.title"
					  placeholder="请输入标题"
					  class="me-write-input">
					</el-input>
				
			</div>
			<markdown-editor :editor="articleForm.editor" class="me-write-editor"></markdown-editor>
		</el-main>
	</el-container>
	 	  
	<go-top></go-top>  
	
	<el-dialog title="摘要 分类 标签" 
		:visible.sync="publishVisible"
		:close-on-click-modal=false
		custom-class="me-dialog">
		
	  <el-form :model="articleForm" ref="articleForm" :rules="rules">
	    <el-form-item prop="summary">
	    	<el-input type="textarea"
	    		  v-model="articleForm.summary"
				  :rows="6"
				  placeholder="请输入摘要">
			</el-input>
	    </el-form-item>
	    <el-form-item label="文章分类" prop="category">
	      <el-select v-model="articleForm.category" placeholder="请选择文章分类">
	        <el-option label="云计算  " value="1"></el-option>
	        <el-option label="开发技术 " value="2"></el-option>
	      </el-select>
	    </el-form-item>
	    
	      <el-form-item label="文章标签" prop="tags">
		    <el-checkbox-group v-model="articleForm.tags">
		      <el-checkbox label="1" name="tags">前端</el-checkbox>
		      <el-checkbox label="2" name="tags">Vue</el-checkbox>
		      <el-checkbox label="3" name="tags">ElementUI</el-checkbox>
		    </el-checkbox-group>
		  </el-form-item>
	  </el-form>
	  <div slot="footer" class="dialog-footer">
	    <el-button @click="publishVisible = false">取 消</el-button>
	    <el-button type="primary" @click="publish('articleForm')">发布</el-button>
	  </div>
	</el-dialog>
</el-container>
</div>
</template>

<script>
import BaseHeader from '@/components/BaseHeader'
import MarkdownEditor from '@/components/markdown/MarkdownEditor'
import {publishArticle} from '@/api/article'

export default {
  name: 'BlogWrite',
  mounted() {
		window.addEventListener('scroll', this.wrapEditorToolBarToFixed(), false);
  },
  beforeDestroy() {
  		window.removeEventListener('scroll', this.wrapEditorToolBarToFixed(),  false)
  },
  data (){
  	return {
  		publishVisible:false,
  		articleForm: {
  			id: '',
			title: '',
	    	summary: '',
	    	category: '',
	    	tags: [],
	    	editor: {
	    		value: '',
	    		ref: '',//保存mavonEditor实例  实际不该这样
	    		default_open: 'edit',
	    		toolbars: {
					bold: true, // 粗体
			  		italic: true, // 斜体
			  		header: true, // 标题
			  		underline: true, // 下划线
			  		strikethrough: true, // 中划线
			  		mark: true, // 标记
			  		superscript: true, // 上角标
			  		subscript: true, // 下角标
			  		quote: true, // 引用
			  		ol: true, // 有序列表
			  		ul: true, // 无序列表
			  		imagelink: true, // 图片链接
			  		code: true, // code
			  		fullscreen: true, // 全屏编辑
			  		readmodel: true, // 沉浸式阅读
			  		help: true, // 帮助
			  		undo: true, // 上一步
			  		redo: true, // 下一步
			  		trash: true, // 清空
			  		navigation: true, // 导航目录
			  		//subfield: true, // 单双栏模式
			  		preview: true, // 预览
	    		}
	    	}
	    },
        rules: {
	      	summary: [
	        	{ required: true, message: '请输入摘要', trigger: 'blur' }
	      	],
	      	category: [
	        	{ required: true, message: '请选择文章分类', trigger: 'change' }
	      	],
	      	tags: [
	        	{ type: 'array', required: true, message: '请选择标签', trigger: 'change' }
	      	]
        }
  	}
  },
  methods:{
  	publishShow() {
  		if(!this.articleForm.title){
  			this.$message({message: '标题不能为空哦',type: 'warning'})
  			return
  		}
  		
  		if(!this.articleForm.editor.ref.d_render){
  			this.$message({message: '内容不能为空哦',type: 'warning'})
  			return
  		}
  		
  		this.publishVisible = true;
  	},
  	publish(articleForm) {
  		
  		let that = this
  		
  		this.$refs[articleForm].validate((valid) => {
          if (valid) {
            
           	let tags = this.articleForm.tags.map(function (item) {
			  return {id: item};
			});
			           
           	let article = {
            	id: this.articleForm.id,
            	title: this.articleForm.title,
            	summary: this.articleForm.summary,
            	category:{
            		id: this.articleForm.category
            	},
            	tags: tags,
            	body: {
            		content: this.articleForm.editor.value,
            		contentHtml: this.articleForm.editor.ref.d_render
            	}
            	
            }
           
            console.info(article)
            
            this.publishVisible = false;
            
            let loading = this.$loading({
	        	lock: true,
	        	text: '发布中，请稍后...'
	        })
            
            publishArticle(article).then((data) => {
            	console.info(data)
            	loading.close();
				if(data.code == 0){
					console.info(data.data.articleId)
		          	that.$message({message: '发布成功啦',type: 'success',showClose:true})
		          	that.$router.push('/')
				}else{
					that.$message({message: data.msg,type: 'error',showClose:true});
				}
					
			}).catch((error) => {
				loading.close();
    			that.$message({
		          message: error,
		          type: 'error'
		        });
  			})
			
          } else {
            console.log('error submit!!');
            return false;
          }
        });
  	},
  	cancel (){
  		this.$confirm('文章将不会保存, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$router.push('/')
        }).catch(() => {
          
        });
  	},
  	wrapEditorToolBarToFixed() {
  		return this.$_.throttle(this.editorToolBarToFixed, 1000)
  	},
  	editorToolBarToFixed() {
  		
  		let toolbar = document.querySelector('.v-note-op');
		let curHeight = document.documentElement.scrollTop || document.body.scrollTop; 
		if(curHeight >= 160){
			toolbar.classList.add("me-write-toolbar-fixed");
		}else{
			toolbar.classList.remove("me-write-toolbar-fixed");
		}
  	}
  },
  components:{
  	'base-header':BaseHeader,
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
.el-header {
	position: fixed;
	z-index: 1024;
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
.me-write-box {
	max-width: 700px;
	margin: 80px auto 0;
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
.me-write-toolbar-fixed {
	position: fixed;
    width: 700px !important;
    top: 60px;
}
.v-note-op {
    box-shadow: none !important;
}
.auto-textarea-input,.auto-textarea-block {
	font-size: 18px !important;
}
</style>