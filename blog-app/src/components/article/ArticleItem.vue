<template>

  <el-card class="me-area" :body-style="{ padding: '20px' }" >
    <div class="left-photo">
       <img class="me-photo" :src="photo" @click="view(id)"/>
    </div>
    <div class="right-text">
    <div class="me-article-header">
      <a @click="view(id)" class="me-article-title">{{title}}</a>
      <el-button v-if="weight > 0" class="me-article-icon" type="text">置顶</el-button>
      <span class="me-pull-right me-article-count">
	    	<i class="me-icon-comment"></i>&nbsp;{{commentCounts}}
	    </span>
      <span class="me-pull-right me-article-count">
	    	<i class="el-icon-view"></i>&nbsp;{{viewCounts}}
	    </span>
    </div>

    <div class="me-artile-description">     
      {{summary}}         
    </div>

    <div class="me-article-footer">
	  	<span class="me-article-author">
	    	<i class="me-icon-author"></i>&nbsp;{{author.nickname}}
	    </span>
      <el-tag v-for="t in tags" :key="t.tagname" size="mini" type="success">{{t.tagname}}</el-tag>
      <span class="me-pull-right me-article-count">
	    	<i class="el-icon-time"></i>&nbsp;{{createDate | format}}
	    </span>

    </div>
    </div>
  </el-card>
</template>

<script>
  import { formatTime } from "../../utils/time";

  export default {
    name: 'ArticleItem',
    props: {
      id: Number,
      weight: Number,
      title: String,
      commentCounts: Number,
      viewCounts: Number,
      summary: String,
      author: Object,
      url: String,
      tags: Array,
      photo: String,
      createDate: String
    },
    data() {
      return {
      }
    },
    methods: {
      view(id) {
        this.$router.push({path: `/view/${id}`})
      }
    }
  }
</script>

<style scoped>

  .me-article-header {
    /*padding: 10px 18px;*/
    padding-bottom: 10px;
  }

  .me-article-title {
    font-weight: 600;
  }

  .me-article-icon {
    padding: 3px 8px;
  }

  .me-article-count {
    color: #a6a6a6;
    padding-left: 14px;
    font-size: 13px;
  }

  .me-pull-right {
    float: right;
  }

  .me-artile-description {
    font-size: 13px;
    line-height: 24px;
    margin-bottom: 10px;
  }

  .me-article-author {
    color: #a6a6a6;
    padding-right: 18px;
    font-size: 13px;
  }

  .el-tag {
    margin-left: 6px;
  }
  .me-photo{
    margin-left: auto;
    width: 200px;
    height: 100px;
  }
  .left-photo{
    float: left;
    margin-right: 20px;
  }
  .right-text{
    float:initial;
   
  }
  .me-area{
    height: 130px;
  }


</style>
