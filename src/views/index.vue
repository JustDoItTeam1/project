<template>
  <div>

  <div id="container" style="width: 100%;height: 670px;position:relative ">
  </div>
<!--  <el-dialog   :visible.sync="vformVisible" append-to-body>-->
<!--    <detailForm  ref="detailfrom" :msg="nomsg"></detailForm>-->
<!--  </el-dialog>-->
    <el-dialog title='施工项目详情' :visible.sync="vformVisible" width="500px" append-to-body>
      <el-form ref="projectForm" :model="projectListOne" :rules="rules" label-width="120px">
        <el-form-item label="项目id"  >
          <el-input v-model="projectListOne.projectId" :disabled="true"/>

        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="projectListOne.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目位置" prop="projectLocation">
          <el-input v-model="projectListOne.projectLocation" placeholder="请输入项目位置" />
        </el-form-item>
        <el-form-item label="项目位置(地图)" prop="projectLongLat">
          <el-input v-model="projectListOne.projectLongLat" placeholder="请输入项目位置(地图)" />
          <el-button type="primary" @click="mapshow" plain size="small">选择</el-button>
        </el-form-item>
        <el-form-item label="项目负责人" prop="projectManger">
          <el-input v-model="projectListOne.projectManger" placeholder="请输入项目负责人" />
        </el-form-item>
        <el-form-item label="负责人联系电话" prop="projectPhone">
          <el-input v-model="projectListOne.projectPhone" placeholder="请输入负责人联系电话" />
        </el-form-item>
        <el-form-item label="施工单位名称" prop="projectBuilderName">
          <el-input v-model="projectListOne.projectBuilderName" placeholder="请输入施工单位名称" />
        </el-form-item>
        <el-form-item label="是否完工" prop="projectFinishedFlag">
          <el-input v-model="projectListOne.projectFinishedFlag" placeholder="请输入施工单位名称" :disabled="true" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="info"  @click="clickSeige" style="float: left" v-if="siegeVV" round>查看围蔽详情</el-button>
        <el-button type="info"  @click="clickSeigeAdd" style="float: left" v-if="addSiegeVV" round>新增围蔽方案</el-button>
        <el-button type="primary" @click="submitProject">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>



<!--    <div style="position: absolute;left: 2%;top:5%;width: 200px">-->
<!--    </div>-->

<!--    切换图层-->
<!--    <div style="position: absolute;left: 80%;top:2%;width: 260px">-->
<!--      <el-radio-group v-model="radio1" @change="radioChange">-->
<!--        <el-radio-button label="查看围蔽方案"></el-radio-button>-->
<!--        <el-radio-button label="查看施工项目"></el-radio-button>-->
<!--      </el-radio-group>-->

<!--    </div>-->

    <!--   左边搜索框-->
<!--    <div style="position: absolute;left:1%;top:2%;width: 450px;border-radius: 10px;background-color: white">-->
      <div style="position: absolute;left: 1%;top:2%;">
      <el-form  ref="queryForm" :inline="true" >
        <el-form-item  >
          <el-input
            v-model="searchName"
            :placeholder="placeh"
            clearable
            size=" small"
            style="width: 270px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="small" @click="handleQuery">搜索</el-button>
        </el-form-item>
      </el-form>
    </div>
<!--    搜索结果-->
    <div class="search" style="position: absolute;left:1%;top:9%;width: 270px;background-color: white" v-if="resultVS">
      <el-card class="box-card" v-for="o in this.searchSeige" :key="o" >

        <div  class="text item">
          <el-button
            size="samll"
            type="text"
            @click="handleClick(o)"

          > {{o.ssProjectName}}</el-button>
        </div>
<!--        <div  class="text item">-->
<!--          {{o.ssProjectName}}-->
<!--        </div>-->
        <div  class="text item">
          {{o.ssBuilderName}}
        </div>
      </el-card>

<!--      <el-table-->
<!--        :data="searchSeige"-->
<!--        style="width: 100%; font-size: 14px;"-->
<!--        :show-header="false">-->
<!--        <el-table-column-->

<!--          style="width:50%">-->
<!--          <template slot-scope="scope" >-->
<!--            <el-button-->
<!--              size="mini"-->
<!--              type="text"-->
<!--              @click="handleClick(scope.row)"-->

<!--            > {{scope.row.ssProjectName}}</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--          prop="ssBuilderName"-->
<!--          style="width:50%" >-->
<!--        </el-table-column>-->
<!--&lt;!&ndash;        <el-table-column&ndash;&gt;-->
<!--&lt;!&ndash;          prop="ssBuilderName">&ndash;&gt;-->
<!--&lt;!&ndash;        </el-table-column>&ndash;&gt;-->
<!--      </el-table>-->
      <div style="position: absolute;left: 27%;top:100%;">
       <el-pagination

        layout="prev, pager, next"
        :total="total"

       >
       </el-pagination>

      </div>
    </div>

<!--    项目搜索结果-->
    <div class="search" style="position: absolute;left:1%;top:9%;width: 270px;background-color: white" v-if="resultVP">
      <el-card class="box-card" v-for="o in this.searchProject" :key="o" >

        <div  class="text item">
          <el-button
            size="samll"
            type="text"
            @click="handleClick(o)"

          > {{o.projectName}}</el-button>
        </div>
        <!--        <div  class="text item">-->
        <!--          {{o.ssProjectName}}-->
        <!--        </div>-->
        <div  class="text item">
          {{o.projectBuilderName}}
        </div>
      </el-card>

      <!--      <el-table-->
      <!--        :data="searchSeige"-->
      <!--        style="width: 100%; font-size: 14px;"-->
      <!--        :show-header="false">-->
      <!--        <el-table-column-->

      <!--          style="width:50%">-->
      <!--          <template slot-scope="scope" >-->
      <!--            <el-button-->
      <!--              size="mini"-->
      <!--              type="text"-->
      <!--              @click="handleClick(scope.row)"-->

      <!--            > {{scope.row.ssProjectName}}</el-button>-->
      <!--          </template>-->
      <!--        </el-table-column>-->
      <!--        <el-table-column-->
      <!--          prop="ssBuilderName"-->
      <!--          style="width:50%" >-->
      <!--        </el-table-column>-->
      <!--&lt;!&ndash;        <el-table-column&ndash;&gt;-->
      <!--&lt;!&ndash;          prop="ssBuilderName">&ndash;&gt;-->
      <!--&lt;!&ndash;        </el-table-column>&ndash;&gt;-->
      <!--      </el-table>-->
      <div style="position: absolute;left: 27%;top:100%;">
        <el-pagination

          layout="prev, pager, next"
          :total="total"

        >
        </el-pagination>

      </div>
    </div>
<!--弹出围蔽详情表格-->
<!--    <el-dialog :visible.sync="siegeV" width="98%" append-to-body title="围蔽信息详情">-->
<!--    <el-table-->
<!--      :data="schemeListOne"-->
<!--      row-key="ssId"-->
<!--      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"-->
<!--    >-->
<!--      <el-table-column label="施工项目id" align="center" prop="ssProjectId" width="110" />-->
<!--      <el-table-column label="施工项目名称" align="center" prop="ssProjectName" width="110" />-->
<!--      <el-table-column label="施工单位名称" align="center" prop="ssBuilderName" width="110"/>-->
<!--      <el-table-column label="围蔽阶段" align="center" prop="ssStage" width="110"/>-->
<!--      <el-table-column label="围蔽状态" align="center" prop="ssStatus" width="170" />-->
<!--      <el-table-column label="围蔽车道" align="center" prop="ssLane" width="130"/>-->

<!--      <el-table-column label="开始时间" align="center" prop="ssStartTime" width="140">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.ssStartTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="结束时间" align="center" prop="ssEndTime" width="140">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.ssEndTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="围蔽性质" align="center" prop="ssProperties" width="120" />-->
<!--      <el-table-column label="围蔽区域(地图)" align="center" prop="ssRange" :show-overflow-tooltip="true" width="200"/>-->

<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">-->

<!--        <template slot-scope="scope" >-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            @click="handleDownload(scope.row)"-->
<!--            v-hasPermi="['system:menu:download']"-->
<!--            v-if=scope.row.ss-->
<!--          >下载附件</el-button>-->
<!--          <el-button size="mini"-->
<!--                     type="text"-->
<!--                     @click="handleAgree(scope.row)"-->
<!--                     v-hasPermi="['system:menu:agree']"-->
<!--                     v-if="(scope.row.ss)&&(scope.row.ssVerifyFlag!='pass')"-->
<!--          >通过</el-button>-->
<!--          <el-button size="mini"-->
<!--                     type="text"-->
<!--                     @click="handleDisagree(scope.row)"-->
<!--                     v-hasPermi="['system:menu:edit']"-->
<!--                     v-if="(scope.row.ss)&&(scope.row.ssVerifyFlag!='pass')"-->
<!--          >否决</el-button>-->


<!--        </template>-->

<!--      </el-table-column>-->
<!--    </el-table>-->
<!--    </el-dialog>-->

    <!--弹出围蔽详情表格-->
    <el-dialog :visible.sync="siegeV" width="750px"  append-to-body title="围蔽信息详情" >
      <el-form style="position: relative">

<!--        <label class="labelSeige" style="font-weight: 700;">施工项目id:</label>-->
<!--        <label class="labelSeige" style="width: 70px;font-weight: 900;">{{schemeListOne.ssProjectId}}</label>-->
<!--        </div>-->
<!--        <div style="position: absolute;left: 27%;top:20px;">-->
        <label class="labelSeige" style="font-weight: 700">施工项目名称:</label>
        <label class="labelSeige" style="width: 250px;font-weight: 900;">{{schemeListOne.ssProjectName}}</label>
<!--        </div>-->
<!--        <div style="position: absolute;left: 65%;top:20px;">-->
        <label class="labelSeige" style="font-weight: 700">施工单位名称:</label>
        <label class="labelSeige" style="width: 200px;font-weight: 900;">{{schemeListOne.ssBuilderName}}</label>
<!--        </div>-->
        <br>
        <div style="position: absolute;top:35px;width: 90%">

          <el-tag type="danger" dark style="position: absolute;left:5%">{{verifyFlag}}</el-tag>
          <el-button
            size="small"
            type="info"
            style="position: absolute;left: 90%;"
            @click="handleDownload"
            v-hasPermi="['enclosure:scheme:download']"
            round
          >下载附件</el-button>
          <el-button
            size="small"
            type="primary"
            style="position: absolute;left: 73%"
            @click="handleAgree"
            v-hasPermi="['enclosure:scheme:review']"
            v-if="schemeListOne.ssVerifyFlag=='review'"
            round
          >通过</el-button>
          <el-button
            size="small"
            type="warning"
            style="position: absolute;left: 58%;"
            @click="handleDisagree"
            v-hasPermi="['enclosure:scheme:review']"
            v-if="schemeListOne.ssVerifyFlag=='review'"
            round
          >否决</el-button>

        </div>

        <br>
        <br>
        <br>
        <br>
        <div v-if="schemeListOne.ssSuggessions!=null">
          <label class="labelSeige" style="font-weight: 700;width: 120px" >拒绝理由:</label>
          <label class="labelSeige" style="width: 560px">{{schemeListOne.ssSuggessions}}</label><br>
        </div>
        <br>
     <div v-for="(key,index) in schemeListOne.children" >
       <br>
<!--       <div style="position: absolute;left: 20%;">-->
       <label class="labelSeige" style="font-weight: 900;width: 120px">围蔽阶段:</label>
       <label class="labelSeige" style="font-weight: 900;width: 560px">{{key.ssStage}}</label><br>
       <label class="labelSeige" style="font-weight: 700;width: 120px">围蔽车道:</label>
       <label class="labelSeige" style="width: 560px">{{key.ssLane}}</label><br>
       <label class="labelSeige" style="font-weight: 700;width: 120px">开始时间:</label>
       <label class="labelSeige" style="width: 560px">{{key.ssStartTime}}</label><br>
       <label class="labelSeige" style="font-weight: 700;width: 120px">结束时间:</label>
       <label class="labelSeige" style="width: 560px">{{key.ssEndTime}}</label><br>
       <label class="labelSeige" style="font-weight: 700;width: 120px">围蔽状态:</label>
       <label class="labelSeige" style="width: 560px">{{key.ssStatus}}</label><br>

       <label class="labelSeige" style="font-weight: 700;width: 120px;">围蔽性质:</label>
       <label class="labelSeige" style="width: 560px">{{key.ssProperties}}</label><br>

       <label class="labelSeige" style="font-weight: 700;width: 120px;vertical-align:top">围蔽区域(地图):</label>
       <label class="labelSeige" style="width: 560px;vertical-align:top">{{key.ssRange}}</label><br>

<!--       </div>-->
    </div>
<!--        <el-form ref="form" :model="schemeListOne"  label-width="80px">-->
<!--          <el-form-item label="用户账户" prop="adminUsername">-->
<!--            <label  >{{schemeListOne.ssProjectName}}</label>-->
<!--          </el-form-item>-->
<!--          <el-form-item label="密码" prop="adminPassword">-->
<!--            <label  >{{schemeListOne.ssBuilderName}}</label>-->
<!--          </el-form-item>-->
<!--        </el-form>-->
<!--        <div slot="footer" class="dialog-footer">-->
<!--          <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--          <el-button @click="cancel">取 消</el-button>-->
<!--        </div>-->
     </el-form>

<!--            <el-button-->
<!--              size="mini"-->
<!--              type="text"-->
<!--              @click="handleDownload(scope.row)"-->
<!--              v-hasPermi="['system:menu:download']"-->
<!--             -->
<!--            >下载附件</el-button>-->
<!--            <el-button size="mini"-->
<!--                       type="text"-->
<!--                       @click="handleAgree(scope.row)"-->
<!--                       v-hasPermi="['system:menu:agree']"-->
<!--                       v-if="(scope.row.ss)&&(scope.row.ssVerifyFlag!='pass')"-->
<!--            >通过</el-button>-->
<!--            <el-button size="mini"-->
<!--                       type="text"-->
<!--                       @click="handleDisagree(scope.row)"-->
<!--                       v-hasPermi="['system:menu:edit']"-->
<!--                       v-if="(scope.row.ss)&&(scope.row.ssVerifyFlag!='pass')"-->
<!--            >否决</el-button>-->


    </el-dialog>
<!--否决弹窗-->
    <el-dialog :visible.sync="opendisa" width="500px" append-to-body title="请添加拒绝围蔽方案的原因或建议">
      <el-form ref="elForm" :model="Suggessions"  size="medium" label-width="100px">
        <el-form-item label="原因或建议" prop="suggestion">
          <el-input v-model="Suggessions.suggestion" type="textarea" placeholder="请输入拒绝围蔽方案的原因或建议"
                    :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="handelConfirmReason">确定</el-button>
        <el-button @click="close">取消</el-button>

      </div>
    </el-dialog>

    <!--ploygon子组件，选择地图中位置-->
    <el-dialog width="80%"   :visible.sync="pointVisible" append-to-body>
      <Ploygon v-if="pointVisible" ref="Ploygon" :msg="projectListOne.projectLongLat" @myfun="myf"></Ploygon>
    </el-dialog>
  </div>


</template>

<script>
import {listProject,updateProject} from "../api/project/project";
import {getInfo} from "../api/login";
import { saveAs } from 'file-saver';
import Ploygon from "./test/components/Ploygon";
import {getIdEnclosure,reviewEnclosure,listEnclosure,updateEnclosure,downloadEnclosure} from "../api/enclosure/enclosure";
import detailForm from "./test/components/detailForm";
export default {
  name: "index",
  components: {detailForm,Ploygon},
  props:['msg'],
  data() {
    return {
      //项目id
      //PId:null,
      //查询施工单位参数
      searchName: null,
      searchQueryParams: {
        //seigeName:null,
        projectInfo:null,
      },
      // searchQueryParamsSeige:{
      //   projectId:null,
      // },v:null,
      //围蔽是否通过
      verifyFlag:null,
      //查询结果
      searchSeige:[],
      searchProject:[],
      placeh:"输入施工项目名称、施工方名称搜索",
      //显示
      resultVS:false,
      resultVP:false,
      //首页图层显示
      radio1:"查看围蔽方案",
      //polygon集合
      overlayGroup1:null,
      overlayGroup2:null,
      //地图获取点Ploygon组件是否显示
      pointVisible:false,
      ploy:"",
      //施工项目和围蔽方案查询结果
      ployg:[],
      ployg1:[],
      ployg2:[],
      // 一个施工项目的围蔽详情
      schemeListOne:[],
      // 一个施工项目的详情
      projectListOne:[],


      //新增围蔽窗口
      addSiegeV:false,
      addSiegeVV:false,
      //围蔽详情弹窗
      siegeV:false,
      siegeVV:false,

      ploy1:[],
      ploy2:[],
      temp:[],
      path:[],
      lng:[],
      vformVisible:false,
      // formVisible:false,
      nomsg:{},

      maps:null,

      //拒绝理由弹窗
      opendisa:false,
      //拒绝理由
      Suggessions:{
        suggestion:null,
        trafficId:null,
      },
      // 总条数
      total: 0,
      updataSeige:{
        ssId:null,
        ssVerifyFlag:null,
        ssVerifyDate:null,
      },
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
      // 表单校验
      rules: {
      }

    };
  },
  created() {

  },
  methods: {
    //生成地图上Polygon
    getPolygon(){
      listProject().then(response => {
        //清空覆盖物群体
        this.overlayGroup2.clearOverlays();
        //this.ployg = response.rows;
        this.ployg2=response.rows;
        // console.log(response);
        // console.log(this.ployg2);
        this.createPolygon(this.maps,this.ployg2,this.overlayGroup2);
        this.maps.add(this.overlayGroup2);
        this.overlayGroup2.show();

        // console.log(response);
      });
    },
    // 地图选择子组件显示
    mapshow(){
      this.pointVisible=true;
    },

    // 获取子组件ploygon传来的值
    myf(ms) {
      this.projectListOne.projectLongLat=ms;
    },

    /** 点击搜索结果查看详情操作 */
    handleClick(o){

      //console.log(o)
      this.vformVisible=true;
      this.projectListOne= o;
      //console.log(this.projectListOne);
      this.maps.setCenter(this.projectListOne.marker.getPosition());
      this.maps.setZoom(17);
      // this.siegeV = true;
      //
      // //将object转化为array
      // var myArray=new Array()
      // myArray.push(o);
      // this.schemeListOne= myArray;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      //搜索结果显示

      // if(this.radio1=="查看围蔽方案"){
      //   this.resultVS=true;
      //   this.searchQueryParams.projectInfo=this.searchName;
      //   listEnclosure(this.searchQueryParams).then(response => {
      //     this.searchSeige=response.rows;
      //     this.total=response.total;
      //     console.log(this.searchSeige)
      //   });
      // }
      // else{
        this.resultVP=true;
        this.searchQueryParams.projectInfo=this.searchName;
        listProject(this.searchQueryParams).then(response => {
          this.createPolygon(this.maps,response.rows,this.overlayGroup2);
          this.searchProject=response.rows;
          this.total=response.total;
          //console.log(this.searchProject)
        });


      // }

    },
    /** 通过按钮操作 */
    handleAgree(e){
      // console.log(e);
      this.$confirm('此操作将通过该围蔽方案, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //获取操作人员id
        getInfo().then(response => {
          if(response.roles=="admin"){
            this.Suggessions.trafficId=0;
          }
          else
            this.Suggessions.trafficId=response.user.id;


          this.Suggessions.suggestion="";
          reviewEnclosure(this. schemeListOne.ssProjectId,this.Suggessions).then(response => {
            if (response.code === 200) {
              this.$message({
                type: 'success',
                message: '该围蔽方案成功设置为已通过!'
              });
              this.verifyFlag="已通过";
              this.schemeListOne.ssVerifyFlag="pass"
            }

          });
        })
        // this.getList();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消设置'
        });

      });
    },
    /** 否决按钮操作 */
    handleDisagree(){
      //console.log(e);
      this.Suggessions.suggestion=null;
      this.opendisa = true;
    },
    //否决取消
    close(){
      this.opendisa = false;
      this.Suggessions.suggestion=null;
    },



/** 否决确定操作 */
    handelConfirmReason() {
      //获取操作人员id
      getInfo().then(response => {
        if(response.roles=="admin"){
          this.Suggessions.trafficId=0;
        }
        else
          this.Suggessions.trafficId=response.user.id;
        // console.log(response.roles)
        // console.log(this.Suggessions.trafficId)


      this.$refs['elForm'].validate(valid => {
        if (valid) {
          // console.log(this.getCookie("username"));
          //this.Suggessions.suggestion=getQueryString(this.Suggessions.suggestion,"utf8");
          // console.log(this.Suggessions.suggestion)
          reviewEnclosure(this. schemeListOne.ssProjectId,this.Suggessions).then(response => {
            if (response.code === 200) {
              this.msgSuccess("否决围蔽方案成功");
              this.opendisa = false;

              this.verifyFlag="未通过";
              this.schemeListOne.ssVerifyFlag="nopass"
            }
              });
            }

          });
      })
        },
    //   })
    // },
//     //切换图层  radio点击事件
//     radioChange:function(){
//       this.searchName=null;
//       this.resultVS=false;
//       this.resultVP=false;
//       if(this.radio1=="查看围蔽方案"){
//
//         this.overlayGroup1.show();
//         this.overlayGroup2.hide();
//         this.placeh="输入施工项目名称、施工方名称搜索";
//       }
//       else{
//         this.overlayGroup2.show();
//         this.overlayGroup1.hide();
//         this.placeh="输入施工项目名称、施工方名称搜索";
//       }
//
//
// },

    //生成施工项目全部polygon
    createPolygon:function(map,ployg,overlayGroup){

      var temp = [];
      var path = [];
      var ploy2 = [];
      for(var i=0;i<ployg.length;i++)
      {
        temp[i]=ployg[i].projectLongLat.split(';');
      }

      // 生成四边形
      for(var i=0;i<temp.length;i++)
      {

        path = [];
        //  console.log(this.path)
        var a=0;
        var b=0;
        for(var j=0;j<temp[i].length-1;j++)
        {

          ploy2=temp[i][j].split(',');
          a=parseFloat(a)+parseFloat(ploy2[0]);
          b=parseFloat(b)+parseFloat(ploy2[1]);
          path.push(new AMap.LngLat(ploy2[0]-0,ploy2[1]-0));

        }
        a=a/(temp[i].length-1);
        b=b/(temp[i].length-1);
        // console.log("a"+a);
        // console.log("b"+b);
        var pt=new AMap.LngLat(a,b)
        var polygon = new AMap.Polygon({
          path:path,
          fillColor: '#fff', // 多边形填充颜色
          borderWeight: 2, // 线条宽度，默认为 1
          strokeColor: 'red', // 线条颜色
        });

        // polygon.pl=this.ploy2;


        // console.log(ployg);

        var marker = new AMap.Marker({
          position: pt,
          title: ployg[i].projectName
        });
        marker.content =i;
        //marker.content={};
        //marker.cont=ployg[i];


        ployg[i]["marker"]=marker;

        //map.add(marker);
        marker.on('click', markerClick);

        ////////////////////////////////////////////////////////////////////////
        // map.add(polygon);

        overlayGroup.addOverlay(marker);
        overlayGroup.addOverlay(polygon);


        var that =this;
        function markerClick(e) {
         //console.log(e);

          that.maps.setCenter(that.ployg2[parseInt(e.target.content)].marker.getPosition());
          that.maps.setZoom(17);
          that.vformVisible=true;
          // that.formVisible=true;
          //将object转化为array
          // var myArray=new Array()
          // myArray.push(that.ployg2[parseInt(e.target.content)]);
         that.projectListOne= that.ployg2[parseInt(e.target.content)];
         that.searchIdSeige();
         //that.searchQueryParamsSeige.projectId=that.projectListOne.projectId;

          // // 数据脱绑
          // that.nomsg=JSON.parse(JSON.stringify(e.target.cont));
          //   var t=document.getElementById("txt");
          //  t.value=that.nomsg.id
        }
        //polygon.on('click',polygonClick);
        //polygon.emit('click', {target: polygon});
        // clickListener = AMap.event.addListener(polygon, "click", function(e) {
        //   new AMap.Marker({
        //     position: [103.98,30.7615],
        //     map: map
        //   });
        // });
      }
    },

    // //生成围蔽方案全部polygon
    // createSiegePolygon:function(map,ployg,overlayGroup){
    //   for(var m=0;m<ployg.length;m++) {
    //     var temp = [];
    //     var path = [];
    //     var ploy2 = [];
    //     var color = ['red', 'green', 'blue', 'yellow', 'black']
    //
    //
    //     for (var i = 0; i < ployg[m].children.length; i++) {
    //       temp[i] = ployg[m].children[i].ssRange.split(';');
    //       //    console.log(this.temp[i]);
    //
    //     }
    //
    //     // 生成四边形
    //     for (var i = 0; i < temp.length; i++) {
    //
    //       path = [];
    //       //  console.log(this.path)
    //       var a = 0;
    //       var b = 0;
    //       for (var j = 0; j < temp[i].length - 1; j++) {
    //
    //         ploy2 = temp[i][j].split(',');
    //         a = parseFloat(a) + parseFloat(ploy2[0]);
    //         b = parseFloat(b) + parseFloat(ploy2[1]);
    //         path.push(new AMap.LngLat(ploy2[0] - 0, ploy2[1] - 0));
    //
    //       }
    //       a = a / (temp[i].length - 1);
    //       b = b / (temp[i].length - 1);
    //       // console.log("a"+a);
    //       // console.log("b"+b);
    //       var pt = new AMap.LngLat(a, b)
    //       var polygon = new AMap.Polygon({
    //         path: path,
    //         fillColor: '#fff', // 多边形填充颜色
    //         fillOpacity:0,
    //         borderWeight: 2, // 线条宽度，默认为 1
    //         strokeColor: color[i], // 线条颜色
    //       });
    //       overlayGroup.addOverlay(polygon);
    //
    //       // polygon.pl=this.ploy2;
    //
    //
    //       if(i==0){
    //         var marker = new AMap.Marker({
    //           position: pt,
    //           title: ployg[m].ssProjectName
    //         });
    //         marker.content = m;
    //         //marker.content = {};
    //         marker.cont = ployg[m];
    //
    //         // 创建 已经通过审核的红色AMap.Icon 实例：
    //         var icon = new AMap.Icon({
    //           size: new AMap.Size(40, 50),    // 图标尺寸
    //           image: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',  // Icon的图像
    //          // imageOffset: new AMap.Pixel(0, -60),  // 图像相对展示区域的偏移量，适于雪碧图等
    //           imageSize: new AMap.Size(22, 35)   // 根据所设置的大小拉伸或压缩图片
    //         });
    //
    //         if(ployg[m].ssVerifyFlag=='pass')
    //         {
    //           marker.setIcon(icon)
    //
    //         }
    //         //map.add(marker);
    //         marker.on('click', markerClick);
    //
    //         ////////////////////////////////////////////////////////////////////////
    //         // map.add(polygon);
    //
    //         overlayGroup.addOverlay(marker);
    //
    //
    //
    //         var that = this;
    //
    //         function markerClick(e) {
    //           that.siegeV = true;
    //
    //           //将object转化为array
    //           var myArray=new Array()
    //           myArray.push(that.ployg1[parseInt(e.target.content)]);
    //           that.schemeListOne= myArray;
    //
    //
    //         }
    //       }
    //     }
    //
    //   }
    // },

    init:function () {
      //初始化地图
      // var satellite = new AMap.TileLayer.Satellite();
      this.maps = new AMap.Map('container', {
        mapStyle:'amap://styles/797343a394a721796989e608aaeff24d', //设置地图的显示样式
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


     // this.overlayGroup1 = new AMap.OverlayGroup();
      this.overlayGroup2 = new AMap.OverlayGroup();

      //获得每个四边形图层
       //this.createPolygon(this.maps,this.ployg2,this.overlayGroup2);
      //this.createSiegePolygon(this.maps,this.ployg1,this.overlayGroup1);

      //this.maps.add(this.overlayGroup1);
      //this.maps.add(this.overlayGroup2);

    },
    //下载附件
    handleDownload:function () {
      var downloadQuery={fileName:this.schemeListOne.children[0].ssFilePath}
      // downloadEnclosure(downloadQuery).then(response => {
        //const blob = new Blob([response], { type: 'text/plain;charset=utf-8' })
        //saveAs(blob, this.schemeListOne.children[0].ssFilePath)
        // this.download(response.msg);
        // if (response.code === 200) {
        //   this.msgSuccess("修改成功");
        //
        // }
      // });
      return downloadEnclosure(downloadQuery);
    },

    //新增围蔽
    clickSeigeAdd:function(){

    },
    //点击查看围蔽详情
    clickSeige:function(){

      getIdEnclosure(this.projectListOne.projectId).then(response => {

        if (response.code === 200) {
          this.schemeListOne=response.data[0];

          if(this.schemeListOne.ssVerifyFlag=="pass")
            this.verifyFlag="已通过";
          if(this.schemeListOne.ssVerifyFlag=="nopass")
            this.verifyFlag="未通过";
          if(this.schemeListOne.ssVerifyFlag=="review")
            this.verifyFlag="待审核";

        }
      });
      this.addSiegeV=false;
      this.siegeV = true;

    },

    //按id查询围蔽详情
     searchIdSeige:function () {

      getIdEnclosure(this.projectListOne.projectId).then(response => {


        if (response.code === 200) {

          //console.log(response.data.length==0)
          if (response.data.length==0) {
            this.addSiegeVV=true;
            this.siegeVV = false;
          }
          else{
          //console.log(response);
            this.schemeListOne=response.data[0];
            if(this.schemeListOne.ssVerifyFlag=="pass")
              this.verifyFlag="已通过";
            if(this.schemeListOne.ssVerifyFlag=="nopass")
              this.verifyFlag="未通过";
            if(this.schemeListOne.ssVerifyFlag=="review")
              this.verifyFlag="待审核";
             //console.log(this.schemeListOne.ssVerifyFlag);
            this.siegeVV = true;
            this.addSiegeVV=false;
          }
        }
      });
      // //将object转化为array
      // var myArray=new Array()
      // myArray.push(this.ployg1[parseInt(this.PId)]);
      // this.schemeListOne= myArray;
    },
    //项目详情提交
    submitProject:function () {
      this.$refs["projectForm"].validate(valid => {
        if (valid) {
            //console.log(this.projectListOne);
            if(this.projectListOne.marker)
            {
              delete this.projectListOne.marker;
            }

            updateProject(this.projectListOne).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                //刷新地图覆盖物
                this.getPolygon();
                // this.open = false;
                // this.getList();
              }
            });

        }
      });
    },
    //项目提交取消
    cancel() {
      this.vformVisible= false;

    },

  },

  mounted() {

    this.init();

    listProject().then(response => {
      //this.ployg = response.rows;
      this.ployg2=response.rows;
      // console.log(response);
      // console.log(this.ployg2);
      this.createPolygon(this.maps,this.ployg2,this.overlayGroup2);
      this.maps.add(this.overlayGroup2);
      this.overlayGroup2.show();

      // console.log(response);
    });

    // listEnclosure().then(response => {
    //   //this.ployg = response.rows;
    //   this.ployg1=response.rows;
    //
    //   this.createSiegePolygon(this.maps,this.ployg1,this.overlayGroup1);
    //   this.maps.add(this.overlayGroup1);
    //    this.overlayGroup1.show();
    //   //console.log("围蔽"+response.rows)
    // });



  }
}
</script>
<style rel="stylesheet/scss" lang="scss">
  .card{
    position: absolute;
  }
  .text {
    font-size: 14px;
  }

  .item {
    padding: 3px 0;
  }
  .search{
    background-color: white;
  }
  .labelSeige
  {
    display: inline-block;
    font-size: medium;
    line-height: 1.5;
    font-family: 等线;
    font-weight:400;
    width: 15%;
    text-align: left;
  ;
  }

</style>
