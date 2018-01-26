<template>
<el-card class="me-area">
	  <div slot="header" class="me-article-header">
	  	<el-button v-if="weight > 0" class="me-article-icon" type="text">置顶</el-button>
	    <a @click="view(1)">{{title}}</a>
	    <span class="me-pull-right me-article-count">
	    	<i class="me-icon-comment"></i>&nbsp;{{comments}}
	    </span>
	    <span class="me-pull-right me-article-count">
	    	<i class="el-icon-view"></i>&nbsp;{{views}}
	    </span>
	  </div>
	  <div class="me-artile-description">
	    	{{summary}}
	  </div>
	  <div class="me-article-footer">
	  	<span class="me-article-author">
	    	<i class="me-icon-author"></i>&nbsp;{{author.nickname}}
	    </span>
	  	
	  	<el-tag v-for="t in tags" :key="t.tagname" size="mini" type="warning">{{t.tagname}}</el-tag>
	  	
	  	<span class="me-pull-right me-article-count">
	    	<i class="el-icon-time"></i>&nbsp;{{createDate}}
	    </span>
	  	
	  </div>
	</el-card>
</template>

<script>

export default {
  name: 'ArticleItem',
  props:{
  	id:String,
  	weight:Number,
  	title:String,
  	comments:Number,
  	views:Number,
  	summary:String,
  	author:Object,
  	tags:Array,
  	createDate:String
  },
  data () {
    return {
    }
  },
  methods:{
  	view (id){
  		this.$router.push({ path: `/view/${id}` })
  	}
  }
}
</script>

<style scoped>

.me-article-header {
	/*padding: 10px 18px;*/
}
.me-article-title {
	color: #333;
}
.me-article-icon {
	padding: 3px 8px;
}
.me-article-count {
	color: #a6a6a6;
	padding-left: 14px;
}
.me-pull-right {
	float: right;
}
.me-artile-description {
	font-size: 14px;
	line-height: 20px;
	padding-left: 10px;
	margin-bottom: 18px;
	text-indent: 2.5em;
}
.me-article-author {
	padding-left: 10px;
	color: #a6a6a6;
	padding-right: 18px;
	
}
.el-tag {
	margin-left: 6px;
}
</style>
