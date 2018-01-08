<template>
	<el-card>
    	<h1 class="me-author-name">shimh</h1>
    	<!--<h3 class="me-author-description">java开发工程师</h3>-->
    	<span><i class="el-icon-location"></i> &nbsp;山东&济南</span>
    	<span><i class="el-icon-location"></i> &nbsp;java开发工程师</span>
    
	</el-card>

</template>

<script>
export default {
  name: 'CardMe',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<style scoped>
.me-author-name {
	text-align: center;
	font-size: 30px;
	border-bottom: 1px solid #ebeef5;
}
.me-author-description {
	text-align: center;
}



</style>