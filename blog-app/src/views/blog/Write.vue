<template>
<div id="write">
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
 				<el-button round @click="publishShow">发布</el-button>
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
	
	<el-dialog title="摘要 分类 标签" 
		:visible.sync="publishVisible"
		:close-on-click-modal=false
		custom-class="me-dialog">
	  <el-form :model="form">
	    <el-form-item >
	    	<el-input type="textarea"
				  :rows="6"
				  placeholder="请输入摘要">
			</el-input>
	    </el-form-item>
	    <el-form-item label="文章分类" >
	      <el-select v-model="form.region" placeholder="请选择文章分类">
	        <el-option label="java" value="shanghai"></el-option>
	        <el-option label="vue" value="beijing"></el-option>
	      </el-select>
	    </el-form-item>
	    
	      <el-form-item label="文章标签" prop="type">
		    <el-checkbox-group v-model="form.type">
		      <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
		      <el-checkbox label="地推活动" name="type"></el-checkbox>
		      <el-checkbox label="线下主题活动" name="type"></el-checkbox>
		      <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
		      <el-checkbox label="单纯品牌曝光1" name="type"></el-checkbox>
		      <el-checkbox label="单纯品牌曝光2" name="type"></el-checkbox>
		      <el-checkbox label="单纯品牌曝光3" name="type"></el-checkbox>
		    </el-checkbox-group>
		  </el-form-item>
	  </el-form>
	  <div slot="footer" class="dialog-footer">
	    <el-button @click="publishVisible = false">取 消</el-button>
	    <el-button type="primary" @click="publish">发布</el-button>
	  </div>
	</el-dialog>
</el-container>
</div>
</template>

<script>
import MarkdownEditor from '@/components/markdown/MarkdownEditor'	

export default {
  name: 'Write',
  data (){
  	return {
  		publishVisible:false,
  		value:'## 123456 ##',
  		mark:{
  			 toolbarsFlag: false , 
  			 subfield: false, 
  			 default_open: "preview"
  		},
  		form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },
        formLabelWidth: '120px'
  	}
  },
  methods:{
  	publishShow (){
  		this.publishVisible = true;
  	},
  	publish (){
  		this.publishVisible = false;
  		const loading = this.$loading({
          lock: true,
          text: '发布中，请稍后...'
        });
        let that = this;
        setTimeout(() => {
          loading.close();
          that.$message({
          	message: '发布成功！',
          	type: 'success',
          	showClose:true
        });
        that.$router.push('/')
        }, 4000);
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
/*	margin-top: 80px;
	margin-left: 200px;
	margin-right: 200px;*/
	width: 700px;
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
	z-index: 6!important;
}
.me-header-left{
	margin-top: 10px;
}
.me-title img{
	max-height: 2.4rem;
	max-width: 100%;
}
</style>
