<template>
	<div id="categoryTag">
	<el-container>
    	<base-header></base-header>
    	<div class="me-ct-body">
		<el-container class="me-ct-container">
		    <el-main>
				<el-tabs v-model="activeName" >
				    <el-tab-pane label="分类" name="category">
				    	<ul class="me-ct-items">
				    		<li v-for="ct in 8" :key="ct" class="me-ct-item">
								  <div class="me-ct-content">
								  	<a class="me-ct-info">
								  		<img class="me-ct-img" src="../../../build/logo.png" />
								  		<h4 class="me-ct-name">Vue.js</h4>
								  		<p class="me-ct-description">javascript的mvvm框架ffffffffffssssss</p>
								  	</a>
								  	
								  	<div class="me-ct-meta">
								  		<span>1000  文章</span>
								  	</div>
								  </div>
				    		</li>
				    	</ul>
				    </el-tab-pane>
				    <el-tab-pane label="标签" name="tag">
				    	<ul class="me-ct-items">
				    		<li v-for="ct in 8" :key="ct" class="me-ct-item">
								  <div class="me-ct-content">
								  	<a class="me-ct-info">
								  		<img class="me-ct-img" src="../../../build/logo.png" />
								  		<h4 class="me-ct-name">Vue.js</h4>
								  	</a>
								  	
								  	<div class="me-ct-meta">
								  		<span>1000  文章</span>
								  	</div>
								  </div>
				    		</li>
				    	</ul>
				    
				    </el-tab-pane>
			  	</el-tabs>
		    </el-main>
  		</el-container>
		</div> 
	</el-container>
		
<go-top></go-top>
</div>
</template>

<script>
import BaseHeader from '@/components/BaseHeader'
export default {
  name: 'categoryTag',
  data () {
    return {
    	 activeName: 'category'
    }
  },
  components:{
  	'base-header':BaseHeader
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
.me-ct-body {
	margin: 60px auto 140px;
}
.me-ct-container {
	width: 1000px;
}
.me-ct-items {
    padding-top: 2rem;
}
.me-ct-item {
	width: 25%;
    display: inline-block;
    margin-bottom: 2.4rem;
    padding: 0 .7rem;
    box-sizing: border-box;
}
.me-ct-content {
	display: inline-block;
    width: 100%;
    background-color: #fff;
    border: 1px solid #f1f1f1;
    transition: border-color .3s;
    text-align: center;
    padding: 1.5rem 0;
}
.me-ct-info {
    cursor: pointer;
}
.me-ct-img {
	margin: -40px 0 10px;
	width: 60px;
    height: 60px;
    vertical-align: middle;
    
}
.me-ct-name {
	font-size: 21px;
    font-weight: 150;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    margin-top: 4px;
}
.me-ct-description {
    font-size: 16px;
    line-height: 25px;
}
.me-ct-meta {
	font-size: 12px;
	color: #969696;
}
</style>
