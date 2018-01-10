<template>
<el-container>
  <el-header class="me-area">
  		<el-row class="me-header">
			  <el-col :span="4" class="me-header-left">
			  	 <router-link to="/" class="me-title">
			  	 	<img src="../../../static/logo.png"/>
			  	</router-link>
			  </el-col>
			  
  	</el-row>
  </el-header>
	  
 	<el-container class="me-area me-write-box">
		<el-main class="me-main">
			 
			 <mavon-editor v-model="value"/>
		</el-main>
	</el-container>
	 	  
	  
</el-container>
	
</template>

<script>
export default {
  name: 'write',
  data (){
  	return {
  		value:''
  	}
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
.me-container{
 margin-bottom: 100px;
}
.me-write-box {
	margin-top: 80px;
	margin-left: 200px;
	margin-right: 200px;
}
.me-header-left{
		margin-top: 10px;
}
.me-title img{
	max-height: 2.4rem;
	max-width: 100%;
}
</style>
