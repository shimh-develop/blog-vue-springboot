<template>
  <div v-title :data-title="title">
    <el-container class="me-area">
      <el-main class="me-main">
        <!--<el-steps class="me-log-box" :space="100"  direction="vertical">
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

              </el-steps>-->
        <pre>

			## 2018-01-04

      ```
      # 使用vue的Webpack模板生成脚手架
      ```

      ## 2018-01-05

      ```
      # 引入ElementUI

      # babel-plugin-component自定义主题
      # 首页
      # 登陆页
      # 注册页面
      # 日志页
      ```
      ## 2018-01-07

      ```
      # 调整底部栏始终固定在底部
      # 日志页 添加时间轴
      # 首页的文章列表
      ```
      ## 2018-01-08

      ```
      # 使用组件-博客作者tab页
      # 添加第三方图标
      ```

      ## 2018-01-09

      ```
      # 调整顶部导航栏：激活文字颜色，click点击
      # 组件-最新文章tab页

      # 最新文章、最热文章使用相同组件
      # 底部栏设计
      # 页面与两边边距改为100
      ```

      ## 2018-01-10

      ```
      # 写博客 引入mavonEditor编辑器
      # 顶部导航栏都放入一个Menu中
      # 写文章页面
      #　mavonEditor局部引入

      #　页面的中间区域固定宽度，自动居中
      # 发布和取消
      # 发布dialog

      ```
      ## 2018-01-11

      ```
      # 文章组件用守卫来改变body背景色
      # 调整登陆和注册页面，使其居中

      #子页面调整根元素为div
      #文章详情页

      ```
      ## 2018-01-12

      ```
      # 文章详情页  内容  评论等

      ```
      ## 2018-01-13

      ```
      ## 重新调整页面结构
        #顶部和底部 抽成  BaseHeader BaseFooter 组件
        #BlogView为单独页，以前是Home的子路由

      ```
      ## 2018-01-15

      ```
      # 文章分类去掉子级
      # 将首页的文章列表抽成 ArticleItem组件
      # 增加文章的评论展示
      # 增加文章分类、标签页

      ```

      ## 2018-01-15  2

      ```
      # 回到顶部去掉过渡动画（影响顶部导航栏）
      # 顶部导航栏 增加登录后菜单
      # 首页增加 最热标签
      # 增加 文章分类 标签的详情页
      # 将文章详情页、 文章分类标签页 改为Home的子路由（以前单独页）
      # Home组件增加路由判断：更正导航栏的状态、条件显示底部栏

      ```

      ## 2018-01-16

      ```
      # 将写文章的顶部Header合并到BaseHeader中
      # 图片都放到了static目录下

      ```

      ## 2018-01-24

      ```
      # 将自定义的theme放到assets下
      # 加入axios
      # 加入vuex
      # 实现登录
      # 实现退出

      ```

      ## 2018-01-25

      ```
      # 实现注册逻辑
      # 写文章功能实现
      # 写文章时支持插入图片

      ```
      ## 2018-01-26

      ```
      # 引入lodash工具类
      # 优化写文章的工具栏：滚动时固定顶部
      # 写文章 后台获取文章分类和标签

      # 首页的文章列表

      ```

      ## 2018-01-27

      ```
      # 修改首页文章列表的样式
      # 首页加载文章功能
      # 文章查看功能
      # 文章分类和标签功能列表

      ```

      ## 2018-01-28

      ```
      # 文章分类和标签详情

      ```

      ## 2018-01-29

      ```
      # 文章分类和标签的文章数
      # 首页最热文章
      # 首页最新文章
      # 首页最热标签

      ```
      ## 2018-01-30

      ```
      # BaseHeader放回views中
      # 修改Axios后置拦截，全局处理错误
      # 将登录 退出 和头像 放到一起

      ```
      ## 2018-01-31

      ```
      # 文章发表评论功能
      # 用户默认头像
      # 整理代码

      ```
      ## 2018-01-31 2

      ```
      # proxyTable接口转发
      # webpack模块按需加载(路由组件)

      ```

      ## 2018-04-14 ~ 2018-04-20 重要修改

      ```
      # 首页 文章分类 标签中的文章列表增加滑动分页
      # 增加文章归档功能
      # 格式化代码

      ```
			 </pre>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: 'Log',
    data() {
      return {}
    },
    computed: {
      title (){
        return '日志 - For Fun'
      }
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
