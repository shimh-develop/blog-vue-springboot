# ssr服务端渲染

> 按照官方的demo修改，但没有做服务端的数据预取。

# 运行

将项目clone到本地 切换到ssr分支

## 后端启动（作为数据接口和图片访问）
1. 将blog-api导入到IDE工具中
2. resources/sql/blog-schema.sql、blog-data.sql导入MySQL数据库
3. 打开Redis数据库
4. resources/application.properties 修改MySQL、Redis连接
5. Runas运行SpringBoot项目

## 前端

1. 打开命令行
	> cd blog-app

	> npm install

2. 开发模式：
	> npm run dev

3. 生产模式：
  > npm run build

  > npm run start

4 访问 http://localhost:8081

# 更新历史

## 2018-05-23

```
# 初步搭建ssr
```

## 2018-05-24

```
# 添加过滤器调整图片路径
# document window localStorage对象使用根据环境变量判断
# 增加配置文件 src/config

```

## 2018-05-29

```
# 背景色调整放到router.afterEach中集中处理
# 修改router中访问store的方式
# 底部栏和tab激活修改
```


