<template>
<div v-title data-title="留言板">

<el-card class="el-card-d" shadow="always">
    <h2 >留言板</h2>
             <div class="infinite-list-wrapper" style="overflow:auto;">
             
                <el-timeline
                  infinite-scroll-disabled="disabled">
                <div v-if="boards.length>0"> 
                  <el-timeline-item v-for="(item,index) in boards"  :key="index" :timestamp='item.createtime' placement="top">
                    <el-card class="el-card-m" style="height:120px">
                      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{item.context}}</p>
                    </el-card>
                  </el-timeline-item>
                </div>
                <div v-else>
                   <el-timeline-item placement="top">
                    <el-card class="el-card-m" style="height:120px">
                      <h4>Lee：</h4>
                      <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 说点什么吧😁</p>
                    </el-card>
                  </el-timeline-item>
                </div>
              </el-timeline> 
              </div>
              <div class="el-card-messages" >
                <el-form ref="bord" :model="board" >
                 <el-input type="textarea" :rows="5" placeholder="匿名留言，但请文明发言" maxlength="200" v-model="board.context"></el-input>
                </el-form>
                 <el-button type="info" round class="submit-message" @click="submitMessage">留言</el-button>
              </div>
            </el-card>
</div>
</template>
<script>
import {submint} from '@/api/board'

import submit from '@/api/board'
import getAll from '@/api/board'

export default {
   data() {
    return {
        board:{
        context:''
        },
        boards:[]
    };
  },
    created() {
   this.findMessage();
     this.$router.afterEach((to,from,next)=>{
　　　　window,scrollTo(0,0)
　　})
  },
  mounted() {
  },
   methods: {
 submitMessage(){
      if(this.board.context==''){
          this.$message('写点啥提交也行呀😉');
          return;
      } 
    submit.add(this.board).then(res=> {
    this.$message({message: '留言成功',type: 'success',showClose: true});
    }).catch((error)=>{
      if (error !=='error'){
        this.$message({message: error,type: 'error',showClose: true});
      }
    })
      },
     findMessage(){
       getAll.getAllBoards().then(res=>{
         this.boards=res.data;
       })
     }



   }
}
</script>


<style>
   .infinite-list-wrapper{
      width: 100%;
      height: 400px;
    }
    .submit-message{
        width: 100%; 
        background: rgb(235, 245, 247);
        color: cadetblue;
        letter-spacing:20px;
    }
 @media screen and (max-width: 3000px) and (min-width: 767px) {
    .el-card-d{
      float: left;
      margin-top: 20px;
      margin-left: 10%; 
      width: 80%;
      height: 90%;
    }

   }
  /*
  屏幕小于768px的
  */
  @media screen and (max-width:768px) and (min-width: 100px){  
     .el-card-d{

      width: 100%;
      height: 100%;
    }
  }

  .el-card-d{
    width: 800px;
    height: 630px;
    margin-left: 0%;;
  }
h2{
  color: rgb(22, 115, 190);
}
</style>