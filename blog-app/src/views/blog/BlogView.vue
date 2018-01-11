<template>
	<div>
  <el-container class="me-view-container">
    <el-main>
			<!--<el-card class="me-view-card">
			  <div slot="header" >
			  	非法方法
			  </div>
			  
			  <div>
  				<markdown-editor placeholder="$route.params.id" 
							class="me-write-editor"
							:toolbarsFlag=false 
							:subfield=false
							default_open="preview"
							value="## 如何学习java ##"></markdown-editor>
			  </div>
			  
			</el-card>-->
			<div class="me-view-card">
				<div>标题</div>
				<div>
  				<markdown-editor :config=config></markdown-editor>
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
}
.v-note-wrapper .v-note-panel {
	box-shadow: none!important;
}
</style>
