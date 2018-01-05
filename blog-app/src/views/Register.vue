<template>
<div id="register">
	<!--<video preload="auto" class="me-video-player" autoplay="autoplay" loop="loop">
		<source src="../../static/vedio/sea.mp4" type="video/mp4">
	</video>-->
		    	
  <div class="me-login-box me-login-box-radius">
		<h1>ForFun 注册</h1>
		
		<el-form ref="form">
		  <el-form-item>
		    <el-input placeholder="用户名"></el-input>
		  </el-form-item>
		  
		  <el-form-item>
		    <el-input placeholder="昵称"></el-input>
		  </el-form-item>
		  
		  <el-form-item>
		    <el-input placeholder="密码"></el-input>
		  </el-form-item>
		  
		  <el-form-item size="small" class="me-login-button">
		    <el-button type="primary">注册</el-button>
		  </el-form-item>
		</el-form>
		
		<div class="me-login-design">
			<p>Designed by 
			<strong><router-link to="/" class="me-login-design-color">ForFun</router-link></strong>
			</p>
		</div>
		
	</div>
</div>
</template>

<script>
export default {
  name: 'register',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  },
  	mounted() {
			/**
			 * 等到整个视图都渲染完毕
			 */
      /*this.$nextTick(function () {  
         window.addEventListener('resize', this.needToTop);  
      }); */ 
    }
}
</script>

<style scoped>
#login{
	min-width: 100%;
	min-height: 100%;
}

.me-video-player{
  background-color: transparent;
  width:100%;
  height: 100%; 
  object-fit: fill; 
	display: block;
	position: absolute;
	left:0;
	z-index: 0;
	top: 0;
  }
  .me-login-box{
  	position: relative;
    width: 300px;
    height: 320px;
    background-color: white;
    margin-top: 150px;
    margin-left: auto;
    margin-right: auto; 
    padding: 30px;
  }
  .me-login-box-radius{
  	border-radius: 10px;
    box-shadow: 0px 0px 1px 1px rgba(161, 159, 159, 0.1);
	}
  	
  .me-login-box h1{
  	text-align: center;
		font-size: 24px;
		margin-bottom: 20px;
		vertical-align: middle;
  }
  
  .me-login-design{
  	text-align: center;
    /* margin-top: 30px; */
  	font-family: 'Open Sans', sans-serif;
  	font-size: 18px;
  }
  .me-login-design-color{
  	color: #5FB878 !important;
  }
.me-login-button{
	text-align: center;
}
.me-login-button button{
	width: 100%;
}

</style>