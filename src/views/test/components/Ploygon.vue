<template>
<!--<div id="map_container" style="height: 600px;width: 900px"></div>-->
<div>
<!--  <iframe src="static/polygon.html" frameborder="0" style="width: 720px;height: 600px" id="myframe"></iframe>-->

  <div style="position:absolute;z-index:9999;">
    <div style="height: 10px"></div>
    <div style="font-size:large">操作说明：通过鼠标左键点击来绘制多边形，通过鼠标右键点击结束当前多边形绘制</div>
    <div  style='width: 24rem;'>
      <div class="input-item">
        <el-button type="warning" @click="clearP">清除</el-button>
        <el-button type="primary" @click="onSubmit">提交</el-button>

      </div>
    </div>
  </div>
  <!--地图容器-->
  <div id="container3" style="width: 100%;height:650px;position: relative; "></div>


</div>
</template>

<script>
    export default {
        name: "Ploygon",
         props:['msg'],


      data(){
          return{
            ploy:"",

             mapObj: null,   //定义全局地图
             mouseTool:null,//鼠标绘图工具
            // polygon:null,//多边形
            // polygonPath:[],//获取多边形点坐标
             overlays:[],//路径
             overlayObject:[],
          }
      },
      mounted() {

        this.init();

      },
      methods:{
          //初始化地图
        init:function () {

          let map = new AMap.Map('container3', {
            center: [104.07, 30.67],
            resizeEnable: true,
            expandZoomRange: true,
            zooms: [3, 20],
            zoom: 12,
          })

          let self=this;
          AMap.plugin(["AMap.MouseTool"],function () {
            self.mouseTool = new AMap.MouseTool(map);
          });
          //监听draw事件可获取画好的覆盖物
          // var overlays = [];
          // var overlay=[];
         self.mouseTool.on('draw',function(e){
            self.overlays.push(e.obj.getPath());

            self.overlayObject.push(e.obj);

          })
          self.mouseTool.polygon({
            fillColor:'#00b0ff',
            strokeColor:'#80d8ff'
            //同Polygon的Option设置
          });


          self.mapObj=map;
        //   self.mapObj = map;
        //   //生成鼠标绘图工具
        //   self.mouseTool = new AMap.MouseTool(self.mapObj);
        //   self.mouseTool.on('draw',function(e){
        //       self.mapObj.setDefaultCursor("crosshair");
        //       self.polygonPath=e.obj.getPath();
        //       self.drawpolygon();
        //       self.mouseTool.close(true) //去掉蓝色底层
        //
        // })

        },

        // //添加多边形到地图
        // drawpolygon() {
        //   let self = this;
        //   self.polygon = new AMap.Polygon({
        //     path: self.polygonPath,
        //     fillColor: '#fff', // 多边形填充颜色
        //     borderWeight: 1, // 线条宽度，默认为 1
        //     strokeWeight: 1,
        //     fillOpacity: 0.5,
        //     strokeColor: 'red', // 线条颜色
        //   });
        //   self.mapObj.add(self.polygon); //多边形添加到地图层里
        //   self.mapObj.setFitView(); //显示多边形图案
        // },

        //清除地图上的多边形
        clearP(){
          let self=this;
          console.log(self.overlays);
          self.overlayObject=[];
          self.overlays=[];

          self.mapObj.clearMap();
          //self.mapObj.remove(self.overlayObject);

        },
        //提交多边形给父表单
        onSubmit(){
          let self=this;
          var str="";
          if(self.overlays.length==1)
          {
            for(var i=0;i<self.overlays.length;i++)
            {
              for(var j=0;j<self.overlays[i].length;j++)
              {
                str=str+self.overlays[i][j].lng;
                str=str+","+self.overlays[i][j].lat;
                str=str+";";

              }

            }
            // 给父组件传递子组件的值（点的位置）
            self.msg=str;
            self.$emit('myfun',self.msg);
            // window.alert("提交成功！")
            this.$message({
              message: '提交成功！',
              type: 'success'
            });

          }
          else{
            //window.alert("只能绘制一个多边形区域，请重新绘制！");
            this.$message.error('只能绘制一个多边形区域，请重新绘制！');
            map.remove(self.overlayObject);
            self.overlayObject=[];
            self.overlays=[];
          }


        },

      },

    }
</script>

<style scoped>
  .input-item{
    height: 2.2rem;
  }

</style>
