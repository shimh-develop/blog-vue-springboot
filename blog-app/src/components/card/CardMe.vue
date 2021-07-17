<template>
  <el-card>
    <h1 class="me-header">关于我  </h1>
    <div class="me-author-description">
    </div>
   <div>
      <i class="me-icon-author">作者: 李庆义</i>
   </div>
    
    <i class="me-icon-job">邮箱:<a> leeqingyi_work@163.com</a></i>
  
  </el-card>

</template>

<script>
  export default {
    name: 'CardMe',
    data() {
      return {
        github: {
          title: 'github',
          message: '<a target="_blank" href="https://github.com/newcalendar">https://github.com/newcalendar</a>'
        }
      }
    },
    methods: {
      showTool(tool) {
        this.$message({
          duration: 0,
          showClose: true,
          dangerouslyUseHTMLString: true,
          message: '<strong>' + tool.message + '</strong>'
        });
      }
    }
  }
</script>

<style scoped>
  .me-header {
    text-align: left;
    font-size: 15px;
    border-bottom: 1px solid #5FB878;
  }

  .me-author-description {
    padding: 8px 0;
  }


  .me-author-tool {
    text-align: left;
    padding-top: 10px;
  }

</style>
