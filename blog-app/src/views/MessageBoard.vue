<template>
  <div>
    <el-container class="">
      <el-main class="me-main">
        <el-alert
          title="不能留言哦。。。。。。。。。。。"
          type="warning"
          center
          show-icon>
        </el-alert>

      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'MessageBoard',
    data() {
      return {}
    }
  }
</script>

<style scoped>
  .el-container {
    width: 700px;
  }

  .me-main {
    overflow: hidden;
  }

  .me-log-box {
    margin-left: 30%;
    margin-top: 20px;

  }

</style>
