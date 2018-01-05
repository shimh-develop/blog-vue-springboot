<template>
  <el-container>
    <el-main>
    	<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>文章内容</p>
			</el-card>
    </el-main>
    
    
    <el-aside>
    	
    	<el-card class="box-card me-area">
			  <p>标签内容</p>
			</el-card>
    	
    	<el-card class="box-card me-area">
			  <p>标签内容</p>
			</el-card>
			
			<el-card class="box-card me-area">
			  <p>标签内容</p>
			</el-card>
    </el-aside>
    
     
     
  </el-container>
  
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style scoped>

.el-container{
	margin-top: 80px;
	margin-left: 20px;
	margin-right: 20px;
}

.el-aside {
	
	margin-left: 20px;
	width:"300px";
  text-align: center;
  line-height: 200px;
}
  
.el-main {
	padding: 0px;
  text-align: center;
  line-height: 160px;
}

.el-card{
	border-radius: 0;
}

.el-card:not(:first-child){
	margin-top: 20px;
	border-radius: 0;
}



</style>
