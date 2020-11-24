<template>
  <div>
  <div id="container2" style="width: 100%;height: 670px"></div>
  </div>
</template>

<script>
    export default {
      name: "select",


      methods: {
        init: function () {
          //初始化地图
          //var satellite = new AMap.TileLayer.Satellite();
          let map = new AMap.Map('container2', {
            center: [104.07, 30.67],
            resizeEnable: true,
            // expandZoomRange: true,
            // zooms: [3, 20],
            zoom: 12,
          })
        },
        mounted() {
          this.init();
        }
      }
    }
</script>

<style scoped>

</style>
