<template>
  <!--<transition name="el-zoom-in-center">-->
  <transition>
    <div @click="toTop" v-show="topShow" class="me-to-top"><i class="el-icon-caret-top"></i></div>
  </transition>
</template>

<script>
  export default {
    name: 'GoTop',
    data() {
      return {
        topShow: false
      }
    },
    methods: {
      toTop() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
        this.topShow = false;
      },
      needToTop() {
        let curHeight = document.documentElement.scrollTop || document.body.scrollTop;

        if (curHeight > 400) {
          this.topShow = true;
        } else {
          this.topShow = false;
        }

      }
    },
    mounted() {
      /**
       * 等到整个视图都渲染完毕
       */
      this.$nextTick(function () {
        window.addEventListener('scroll', this.needToTop);
      });
    }
  }
</script>

<style>
  .me-to-top {
    background-color: #fff;
    position: fixed;
    right: 100px;
    bottom: 150px;
    width: 40px;
    height: 40px;
    border-radius: 20px;
    cursor: pointer;
    transition: .3s;
    box-shadow: 0 0 6px rgba(0, 0, 0, .12);
    z-index: 5;
  }

  .me-to-top i {
    color: #00d1b2;
    display: block;
    line-height: 40px;
    text-align: center;
    font-size: 18px;
  }

</style>
