<template>
  <div>
<!--    .el-form-item {
    margin-bottom: 5px;
}-->

    <el-form ref="form"  label-width="80px">
      <el-form-item label="项目代码">
        <el-input v-model="msg.id"></el-input>
      </el-form-item>
      <el-form-item label="项目名称">
        <el-input v-model="msg.name"></el-input>
      </el-form-item>
      <el-form-item label="建筑公司">
        <el-input v-model="msg.company"></el-input>
      </el-form-item>
      <el-form-item label="项目位置">
        <el-input v-model="msg.point"></el-input>
        <el-button @click="mapshow">选择</el-button>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit()">修改</el-button>
        &nbsp; &nbsp;
        <router-link :to="{path:'/statusform',query: {id: msg.id,name:msg.name}}" style="color: blue">查看围蔽信息</router-link>
      </el-form-item>
    </el-form>

    <!--ploygon子组件，选择地图中位置-->
    <el-dialog width="80%"   :visible.sync="pointVisible" append-to-body>
      <Ploygon v-if="pointVisible" ref="Ploygon" :msg="form.point" @myfun="myf"></Ploygon>
    </el-dialog>

  </div>
</template>

<script>
  import Ploygon from "./Ploygon";
    export default {
        name: "detailForm",
      components:{Ploygon},
      props:['msg'],
      data() {
        return {
          form: {
            id:'',
            name: '',
            company: '',
            point: ''
          },
          pointVisible:false,
        }
      },
      mounted() {
          this.form=this.msg;
          // console.log("fform");
          // console.log(this.form);
          // console.log(this.msg);
      },
      // beforeDestroy() {
      //   console.log(this.msg);
      // }
      methods:{
        // 地图选择子组件显示
        mapshow(){
          this.pointVisible=true;
        },

        // 获取子组件ploygon传来的值
        myf(ms) {
          this.form.point=ms;
        },

        onSubmit(){

        }
      }
    }
</script>

<style scoped>

</style>
