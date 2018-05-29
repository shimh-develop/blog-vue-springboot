<template>
  <div id="home">
    <el-container>

    	<base-header :activeIndex="activeIndex"></base-header>

		  <router-view class="me-container"/>

			<base-footer v-show="footerShow"></base-footer>

		</el-container>

  </div>

</template>

<script>
import BaseFooter from '@/components/BaseFooter'
import BaseHeader from '@/views/BaseHeader'

export default {
  name: 'Home',
  data (){
  	return {
  			activeIndex: '/',
  			footerShow:false
  	}
  },
  components:{
  	'base-header':BaseHeader,
  	'base-footer':BaseFooter
  },
  beforeRouteEnter (to, from, next){

  	 next(vm => {
       vm.changeFooterAndAcitveTab(to)
  	})
  },
  beforeRouteUpdate (to, from, next){
	  this.changeFooterAndAcitveTab(to)
	  next()
	},
  methods: {
    changeFooterAndAcitveTab(to) {
      if(to.path == '/'){
        this.footerShow = true
      }else{
        this.footerShow = false
      }
      this.activeIndex = to.path
    }
  }
}
</script>

<style>

.me-container{
  margin: 100px auto 140px;
}
</style>
