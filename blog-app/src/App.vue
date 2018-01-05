<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'app'
}
</script>

<style>
* { margin: 0; padding: 0; }

body {
	background-color: #f5f5f5;
	font-weight: 400;
	font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
	line-height: 1.5;
}
a {
    color: #00d1b2;
    cursor: pointer;
    text-decoration: none;
    transition: none 86ms ease-out;
}
	.me-area{
		/*text-align: center;*/
		background-color: #fff;
	}
</style>
