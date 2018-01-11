<template>
	<div>
	<el-container class="me-area">
		<el-main class="me-main">
			 <el-steps class="me-log-box" :space="100"  direction="vertical">
			    <el-step title="步骤 1" status="process"  icon="el-icon-time" description="步骤 1步骤 1步骤 1步骤 1步骤 1"></el-step>
			    <el-step title="步骤 2" status="process" icon="el-icon-time"></el-step>
			    <el-step title="步骤 3" status="process" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			  	<el-step title="步骤 3" status="process" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>

			    <el-step title="步骤 3" status="process" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" status="process" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" status="process" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" status="process" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			    <el-step title="步骤 3" icon="el-icon-time" description="这是一段很长很长很长的描述性文字"></el-step>
			 
			 </el-steps>
		</el-main>
	</el-container>
	</div>
</template>

<script>
export default {
  name: 'log',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style scoped>
.el-container{
/*	margin-top: 80px;
	margin-left: 100px;
	margin-right: 100px;*/
	width: 700px;
	margin: 80px auto;
	
}
.me-main{
	overflow: hidden;
}
.me-log-box{
	margin-left: 30%;
	margin-top: 20px;
	
}

</style>