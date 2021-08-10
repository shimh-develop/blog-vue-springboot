# blog-vue-springboot
基于Vue+SpringBoot构建的博客项目


#项目访问地址http://82.156.209.49:9001/

#技术栈
项目前端Vue+ElementUI
后端：springboot、Shiro、JPA、Mysql、Redis、RabbitMQ、阿里云OSS对象存储、阿里云短信服务

#项目功能
文章浏览、文章发布、评论发布、标签、分类、登录注册（短信验证码）、留言板、文章归档

#项目启动
可以前后端分离启动，先修改application.yml以及阿里云对象service类里的相关配置
前端：npm install -> npm run dev
启动后端即可访问localhost：8080

纯后端启动，前端页面已经打包到后端static中
启动后访问localhost:9001即可访问


#效果图如下：
![image](https://user-images.githubusercontent.com/41831536/128821629-03be1fcb-a434-4434-9079-e1b6f86ddd89.png)

