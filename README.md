
Vue + SpringBoot实现的博客系统

# 效果图

## 首页

![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/index.png)

## 登录页
![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/login.png)

## 注册页
![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/register.png)

## 文章分类-标签、详情
![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/ct.png)

![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/ct-detail.png)

## 写文章
![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/write.png)

## 文章详情
![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/detail.png)

## 评论
![image](https://github.com/shimh-develop/blog-vue-springboot/blob/master/document/comment.png)

# 技术

## 前端  blog-app

- Vue
- Vue-router
- Vuex
- ElementUI
- mavon-editor
- lodash
- axios
- Webpack

## 后端  blog-api

- SpringBoot
- Shiro
- Jpa
- Redis
- Fastjson
- Druid
- MySQL
- Maven

# 实现功能

## 整体 

- 用户：登录 注册 退出
- 首页：文章列表、最热标签、最新文章、最热文章
- 文章分类-标签：列表、详情
- 文章：写文章、文章详情
- 评论：文章添加评论

## 后端
- 用户、文章、文章分类、标签和评论 增删改查api接口
- 基于token权限控制
- Redis存储Session
- 全局异常处理

# 待实现功能
- 文章、评论等的分页
- 评论回复、点赞
- 留言板
- 后端日志记录
- ......

# 运行

将项目clone到本地

## 方式一  直接运行SpringBoot项目（已将打包的静态文件放到了 resources/static下）
1. 将blog-api导入到IDE工具中
2. resources/sql/blog-schema.sql、blog-data.sql导入MySQL数据库
3. 打开Redis数据库
4. resources/application.properties 修改MySQL、Redis连接
5. Runas运行,访问：http://localhost:8888

## 方式二  前后分离（开发方式）
1. 按方式一运行blog-api，提供api数据接口
2. 打开命令行	
	> cd blog-app  
	
	> npm install  
	
	> npm run dev  
	
3. 访问：http://localhost:8080
4. 修改blog-app/src 下的文件进行开发
5. npm run build 生成最终静态文件



