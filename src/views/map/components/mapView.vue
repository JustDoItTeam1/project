<template>
  <div>
<!--    <el-input v-model=msg placeholder="项目id" v-if=false style="width: 200px;font-size: x-large"></el-input>-->
  <div id="container" style="width: 1130px;height: 700px"></div>
  </div>
</template>

<script>
    export default {
        name: "mapView",
      props:['msg'],
      data(){
        return {
          temp:[],
          ploy2:[],

        }
        },
      methods: {
        init: function () {
          let map = new AMap.Map('container', {
            center: [104.07, 30.67],
            resizeEnable: true,
            zoom: 18,
          })

          //console.log(this.msg);
          this.temp = this.msg.split(';');
          //console.log(this.temp);

            this.path = [];
            for (var j = 0; j < this.temp.length - 1; j++) {
              this.ploy2 = this.temp[j].split(',');
              this.path.push(new AMap.LngLat(this.ploy2[0] - 0, this.ploy2[1] - 0));
              map.setCenter(new AMap.LngLat(this.ploy2[0] - 0, this.ploy2[1] - 0));
            }
            var polygon = new AMap.Polygon({
              path: this.path,
              fillColor: '#fff', // 多边形填充颜色
              borderWeight: 2, // 线条宽度，默认为 1
              strokeColor: 'red', // 线条颜色
            });


            map.add(polygon);

          }
        },


      mounted() {

        this.init();
      },
    }
</script>

<style scoped>

</style>
