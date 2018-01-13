<template>
  <div id="home">
    <el-container>
    	
    	<base-header></base-header>
		  
		  <router-view class="me-container"/>
		  
			<base-footer></base-footer>
		  
		</el-container>
		
  	<go-top></go-top>
  </div>
  
</template>

<script>
import BaseFooter from '@/components/BaseFooter'
import BaseHeader from '@/components/BaseHeader'

export default {
  name: 'Home',
  data (){
  	return {
  		activeIndex: '/'
  	}
  },
  components:{
  	'base-header':BaseHeader,
  	'base-footer':BaseFooter
  }
}
</script>

<style>

/*.el-header {
  position: fixed;
  z-index: 1024;
  min-width: 100%;
  box-shadow: 0 2px 3px hsla(0,0%,7%,.1), 0 0 0 1px hsla(0,0%,7%,.1);
}*/
.me-container{
  /*margin-bottom: 60px;*/
     margin: 100px auto 140px;
}
  
/*.me-title{
	margin-top: 10px;
	font-size: 24px ;
}

.me-for {
	color: #36ac70;
}
.me-fun {
	color: #28374b;
}

.me-header-left{
		margin-top: 10px;
}
.me-title img{
	max-height: 2.4rem;
	max-width: 100%;
}
.temp{
	 background: #99a9bf;
	  border-radius: 4px;
}*/
</style>
