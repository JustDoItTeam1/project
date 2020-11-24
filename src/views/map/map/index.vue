<template>
  <div>
  <div id="container" style="width: 100%;height: 670px">

  </div>
  <el-dialog   :visible.sync="vformVisible" append-to-body>
    <detailForm  ref="detailfrom" :msg="nomsg"></detailForm>
  </el-dialog>
  </div>
</template>

<script>
import { listEnclosure } from "@/api/map/enclosure";
import detailForm from "../../test/components/detailForm";
export default {
  name: "Map",
  components: {detailForm},
  data() {
    return {
      ploy:"",
      ployg:[],
      // iframeWin:{}
      ploy1:[],
      ploy2:[],
      temp:[],
      path:[],
      lng:[],
      vformVisible:false,
      // formVisible:false,
      nomsg:{},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        enclosurestatus: null,
        phaseid: null,
        startDate: null,
        endDate: null,
        projectid: null,
        ploygon: null,
        lane: null,
        enclosurelenght: null,
        enclosureproperty: null
      },
    };
  },
  created() {

    listEnclosure(this.queryParams).then(response => {
      //this.ployg = response.rows;
      console.log(response)
    });

    this.ployg=[{id: '0000000001',
      name: '校园路有轨电车施工',
      company:'十七冶',
      principal:'王三',
      money:'50万人民币',
      status:'水泥',
      address: '安徽省马鞍山市',
      point:'103.981063,30.761549;103.981476,30.761014;103.98153,30.761028;103.981112,30.761558;',},
      {id: '0000000002',
        name: '天府路施工',
        company:'十七冶',
        principal:'王三',
        money:'50万人民币',
        status:'水泥',
        address: '安徽省马鞍山市',
        point:'103.980701,30.761388;103.980734,30.761361;103.978652,30.760273;103.978625,30.760282;',},
      {id: '0000000003',
        name: '犀安路施工',
        company:'十七冶',
        principal:'王三',
        money:'50万人民币',
        status:'水泥',
        address: '安徽省马鞍山市',
        point:'103.989255,30.757623;103.987114,30.755982;103.987157,30.755936;103.989266,30.757586;',},
      {id: '0000000004',
        name: '测试',
        company:'十七冶',
        principal:'王三',
        money:'50万人民币',
        status:'水泥',
        address: '安徽省马鞍山市',
        point:'104.083099,30.64344;104.083162,30.643444;104.084355,30.646787;104.084271,30.646822;',},]

  },
  methods: {
    init:function () {
      //初始化地图
      // var satellite = new AMap.TileLayer.Satellite();
      let map = new AMap.Map('container', {
        center: [104.07, 30.67],
        resizeEnable: true,
        expandZoomRange: true,
        zooms: [3, 20],
        zoom:12,
        // layers:[
        //
        //   // 卫星
        //   new AMap.TileLayer.Satellite(),
        //   // 路网
        //   new AMap.TileLayer.RoadNet()
        // ]
      })

      for(var i=0;i<this.ployg.length;i++)
      {
        this.temp[i]=this.ployg[i].point.split(';');
        //    console.log(this.temp[i]);

      }
      for(var i=0;i<this.temp.length;i++)
      {

        this.path = [];
        //  console.log(this.path)
        var a=0;
        var b=0;
        for(var j=0;j<this.temp[i].length-1;j++)
        {

          this.ploy2=this.temp[i][j].split(',');
          a=parseFloat(a)+parseFloat(this.ploy2[0]);
          b=parseFloat(b)+parseFloat(this.ploy2[1]);
          this.path.push(new AMap.LngLat(this.ploy2[0]-0,this.ploy2[1]-0));

        }
        a=a/(this.temp[i].length-1);
        b=b/(this.temp[i].length-1);
        // console.log("a"+a);
        // console.log("b"+b);
        var pt=new AMap.LngLat(a,b)
        var polygon = new AMap.Polygon({
          path:this.path,
          fillColor: '#fff', // 多边形填充颜色
          borderWeight: 2, // 线条宽度，默认为 1
          strokeColor: 'red', // 线条颜色
        });

        // polygon.pl=this.ploy2;


        var marker = new AMap.Marker({
          position: pt,
          title: this.ployg[i].name
        });
        marker.content = i;
        marker.content={};
        marker.cont=this.ployg[i];

        map.add(marker);
        marker.on('click', markerClick);
        //  marker.emit('click', {target: marker});

        // var info= '<div>'+
        //   '&nbsp;&nbsp;'+this.ployg[i].name+'</br>'+
        //   '<input id="lnglat2container" type="button" class="btn" value="获取该位置经纬度"   onclick="{window.sessionStorage.setItem(\'watchStorage\',\'true\') ;console.log(window.sessionStorage.getItem(\'watchStorage\'));console.log(\'nihao\');}"/>'+
        //   '</div>';
        //  info.push("<div><p>"+this.ployg[i].name+"</p>");
        //this.info.push(['<div><router-link :to"{path:'/gd'}">xiangqing</router-link>']
        //   info.push( ['<button class="btn" id="butt"> 车辆详情</button>' ]);
        //   info.push('<div><router-link :to=\"{path:\'\\gd\'}\">xiangqing</router-link></div>')
        //  info.push('<a href = "http://localhost:8080/#/detailform?name="+this.ployg[i].name>查看该项目详细信息</a>')
        //  info.push('<a href = "http://localhost:8080/#/detailform?m="this.ployg[i].name>查看该项目详细信息</a>')
        //   info.push(  '<input id="lnglat2container" type="button" class="btn" value="获取该位置经纬度" onclick="getLngLat()"/>')
        // info.push('<a href="" id="n" οnclick="t()"></a>')
        // polygon.content=info;
        //console.log(polygon);

        map.add(polygon);
        //polygon.on('click',polygonClick);
        //polygon.emit('click', {target: polygon});
        // clickListener = AMap.event.addListener(polygon, "click", function(e) {
        //   new AMap.Marker({
        //     position: [103.98,30.7615],
        //     map: map
        //   });
        // });
      }


      var that =this;
      function markerClick(e) {
        that.vformVisible=true;
        // that.formVisible=true;
        //console.log(e.target.cont);
        // 数据脱绑
        that.nomsg=JSON.parse(JSON.stringify(e.target.cont));
        //   var t=document.getElementById("txt");
        //  t.value=that.nomsg.id
      }


    },
  },
  mounted() {
    this.init();
  }
}
</script>
