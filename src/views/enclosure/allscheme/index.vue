<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="110px">

      <el-form-item label="施工项目名称" prop="ssProjectName">
        <el-input
          v-model="queryParams.ssProjectName"
          placeholder="请输入施工项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工单位名称" prop="ssBuilderName">
        <el-input
          v-model="queryParams.ssBuilderId"
          placeholder="请输入施工单位名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>

    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['enclosure:allscheme:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="allschemeList" @selection-change="handleSelectionChange" row-key="ssId"  :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="围蔽方案id" align="center" prop="ssId" />-->
<!--      <el-table-column label="施工项目id" align="center" prop="ssProjectId"  width="110" />-->
      <el-table-column label="施工项目名称" align="center" prop="ssProjectName"  width="110" />
      <el-table-column label="施工单位名称" align="center" prop="ssBuilderName" width="110"/>
      <el-table-column label="围蔽状态" align="center" prop="ssStatus" width="170" />
      <el-table-column label="围蔽车道" align="center" prop="ssLane"  width="130"/>
      <el-table-column label="围蔽阶段" align="center" prop="ssStage"  width="110"/>
      <el-table-column label="开始时间" align="center" prop="ssStartTime" width="140">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ssStartTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="ssEndTime" width="140">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.ssEndTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="围蔽性质" align="center" prop="ssProperties"  width="120"/>
      <el-table-column label="围蔽区域(地图)" align="center" prop="ssRange" :show-overflow-tooltip="true" width="200"/>
      <el-table-column label="查看围蔽区域" align="center" class-name="small-padding fixed-width" width="150">
        <template slot-scope="scope" >
          <el-button size="mini"
                     type="text"
                     @click="handleMapview(scope.row)"
                     v-hasPermi="['system:menu:mapview']"
                     v-if=scope.row.ssmap
          >查看地图</el-button>
        </template>
      </el-table-column>

<!--      <el-table-column label="围蔽拒绝建议" align="center" prop="ssSuggessions" />-->
<!--      <el-table-column label="围蔽文件路径" align="center" prop="ssFilePath" />-->

<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['enclosure:allscheme:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['enclosure:allscheme:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改围蔽信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="围蔽状态">
          <el-radio-group v-model="form.ssStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="围蔽车道" prop="ssLane">
          <el-input v-model="form.ssLane" placeholder="请输入围蔽车道" />
        </el-form-item>
        <el-form-item label="围蔽阶段" prop="ssStage">
          <el-select v-model="form.ssStage" placeholder="请选择围蔽阶段">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" prop="ssStartTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.ssStartTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="ssEndTime">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.ssEndTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="围蔽区域(地图)" prop="ssRange">
          <el-input v-model="form.ssRange" placeholder="请输入围蔽区域(地图)" />
        </el-form-item>
        <el-form-item label="项目id" prop="ssProjectId">
          <el-input v-model="form.ssProjectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="围蔽性质" prop="ssProperties">
          <el-select v-model="form.ssProperties" placeholder="请选择围蔽性质">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="围蔽拒绝建议" prop="ssSuggessions">
          <el-input v-model="form.ssSuggessions" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="围蔽文件路径" prop="ssFilePath">
          <el-input v-model="form.ssFilePath" placeholder="请输入围蔽文件路径" />
        </el-form-item>
        <el-form-item label="施工单位id" prop="ssBuilderId">
          <el-input v-model="form.ssBuilderId" placeholder="请输入施工单位id" />
        </el-form-item>
        <el-form-item label="审核交管人员id" prop="ssTrafficStaffId">
          <el-input v-model="form.ssTrafficStaffId" placeholder="请输入审核交管人员id" />
        </el-form-item>
        <el-form-item label="删除标识" prop="ssDeleteFlag">
          <el-input v-model="form.ssDeleteFlag" placeholder="请输入删除标识" />
        </el-form-item>
        <el-form-item label="审核标识" prop="ssVerifyFlag">
          <el-input v-model="form.ssVerifyFlag" placeholder="请输入审核标识" />
        </el-form-item>
        <el-form-item label="审核时间" prop="ssVerifyDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.ssVerifyDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择审核时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="ssUpdateFlag">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.ssUpdateFlag"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择更新时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--mapview子组件，显示地图中位置-->
    <el-dialog width="80%"   :visible.sync="mapVisible" append-to-body>
      <mapView v-if="mapVisible" ref="mapView" :msg="epolygon" ></mapView>
    </el-dialog>

  </div>
</template>

<script>
import { listAllscheme, getAllscheme, delAllscheme, addAllscheme, updateAllscheme, exportAllscheme } from "@/api/enclosure/allscheme";
import mapView from "../../map/components/mapView";
export default {
  name: "Allscheme",
  components:{mapView},
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 围蔽信息表格数据
      allschemeList: [],
      //孩子数量
      cnum:'',
      //每个项目的围蔽阶段数
      wbnum:[],
      //表格合并中合并开始行
      wbn:[],
      //表格合并的列数
      wbl:[0,1,2,11],
      //是否显示map
      mapVisible:false,
      //传递给子组件mapview的地图ploygon
      epolygon:"",
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        ssStatus: null,
        ssLane: null,
        ssStage: null,
        ssStartTime: null,
        ssEndTime: null,
        ssRange: null,
        ssProjectId: null,
        ssProperties: null,
        ssSuggessions: null,
        ssFilePath: null,
        ssBuilderId: null,
        ssTrafficStaffId: null,
        ssDeleteFlag: null,
        ssVerifyFlag: null,
        ssVerifyDate: null,
        ssUpdateFlag: null,
        ssBuilderName:null,
        ssProjectName:null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //表格合并
    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      console.log(this.wbn[0]);
      for (var j=0;j<this.wbl.length;j++)
      {
        for (var i=0;i<this.wbn.length;i++)
        {
          //第一列
          if (columnIndex === this.wbl[j]) {
            if (rowIndex  === this.wbn[i]) {
              return {
                rowspan: this.wbnum[i],
                colspan: 1
              };
            }
            if((parseInt(rowIndex) < (parseInt(this.wbn[i])+parseInt(this.wbnum[i])))&&(parseInt(rowIndex) > parseInt(this.wbn[i]))){
              return {
                rowspan: 0,
                colspan: 0
              };
            }
          }
        }
      }

    },

    /** 查询围蔽信息列表 */
    getList() {
      this.loading = true;
      listAllscheme(this.queryParams).then(response => {
        this.allschemeList = response.rows;
        this.total = response.total;
          this.loading = false;
        //假数据
      //
      //   this.wbnum=[2,3,2],
      //     this.wbn=[0,2,5],
      //     this.allschemeList=[
      //       {
      //         children:[
      //           {
      //
      //             ss:false,
      //             ssmap:true,
      //             ssId:11,
      //             ssStatus: '修筑路基',
      //             ssLane: '左二、三车道',
      //             ssStage: '1',
      //             ssStartTime: '2020-11-14',
      //             ssEndTime: '2020-12-14',
      //             ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //             ssProperties: '全封闭',
      //             // ssSuggessions: null,
      //
      //             // ssFilePath: null,
      //             ssBuilderName:null,
      //             ssProjectId: null,
      //             ssProjectName: null,
      //           },
      //           {
      //             ss:false,
      //             ssmap:true,
      //             ssId:12,
      //             ssStatus: '修筑排水设施',
      //             ssLane: '左二、三车道',
      //             ssStage: '2',
      //             ssStartTime: '2020-12-14',
      //             ssEndTime: '2020-12-30',
      //             ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //             ssProperties: '全封闭',
      //             // ssSuggessions: null,
      //
      //             // ssFilePath: null,
      //             ssBuilderName:null,
      //             ssProjectId: null,
      //             ssProjectName: null,
      //           }
      //         ],
      //         ss:true,
      //         ssmap:false,
      //         childrennum:2,
      //         ssId: 1,
      //         ssStatus: null,
      //         ssLane: null,
      //         ssStage: null,
      //         ssStartTime: null,
      //         ssEndTime: null,
      //         ssRange: null,
      //         ssProperties: null,
      //         // ssSuggessions: null,
      //
      //         // ssFilePath: null,
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 1,
      //         ssProjectName: '行陈路翻新',
      //         // ssBuilderId: 1,
      //         // ssTrafficStaffId: null,
      //         // ssDeleteFlag: null,
      //         // ssVerifyFlag: null,
      //         // ssVerifyDate: null,
      //         // ssUpdateFlag: null
      //       },
      //       {
      //         children:[
      //           {
      //             ss:false,
      //             ssmap:true,
      //             ssId:21,
      //             ssStatus: '修筑路基',
      //             ssLane: '左二、三车道',
      //             ssStage: '1',
      //             ssStartTime: '2020-11-14',
      //             ssEndTime: '2020-12-14',
      //             ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //             ssProperties: '全封闭',
      //             // ssSuggessions: null,
      //
      //             // ssFilePath: null,
      //             ssBuilderName:null,
      //             ssProjectId: null,
      //             ssProjectName: null,
      //           },
      //           {
      //             ss:false,
      //             ssmap:true,
      //             ssId:22,
      //             ssStatus: '修筑排水设施',
      //             ssLane: '左二、三车道',
      //             ssStage: '2',
      //             ssStartTime: '2020-12-14',
      //             ssEndTime: '2020-12-30',
      //             ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //             ssProperties: '全封闭',
      //             // ssSuggessions: null,
      //
      //             // ssFilePath: null,
      //             ssBuilderName:null,
      //             ssProjectId: null,
      //             ssProjectName: null,
      //           },
      //           {
      //             ss:false,
      //             ssmap:true,
      //             ssId:23,
      //             ssStatus: '修筑排水设施',
      //             ssLane: '左二、三车道',
      //             ssStage: '3',
      //             ssStartTime: '2020-12-14',
      //             ssEndTime: '2020-12-30',
      //             ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //             ssProperties: '全封闭',
      //             // ssSuggessions: null,
      //
      //             // ssFilePath: null,
      //             ssBuilderName:null,
      //             ssProjectId: null,
      //             ssProjectName: null,
      //           }
      //         ],
      //         ss:true,
      //         ssmap:false,
      //         ssId: 2,
      //         childrennum:3,
      //         ssStatus: null,
      //         ssLane: null,
      //         ssStage: null,
      //         ssStartTime: null,
      //         ssEndTime: null,
      //         ssRange: null,
      //         ssProperties: null,
      //         // ssSuggessions: null,
      //
      //         // ssFilePath: null,
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 2,
      //         ssProjectName: '校园路翻新',
      //         // ssBuilderId: 1,
      //         // ssTrafficStaffId: null,
      //         // ssDeleteFlag: null,
      //         // ssVerifyFlag: null,
      //         // ssVerifyDate: null,
      //         // ssUpdateFlag: null
      //       }
      //     ],
      //     this.allschemeList1=[
      //       {
      //         ss:'false',
      //         ssId:11,
      //         ssStatus: '修筑路基',
      //         ssLane: '左二、三车道',
      //         ssStage: '1',
      //         ssStartTime: '2020-11-14',
      //         ssEndTime: '2020-12-14',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //         ssProperties: '全封闭',
      //         // ssSuggessions: null,
      //
      //         // ssFilePath: null,
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 1,
      //         ssProjectName: '行陈路翻新',
      //       },
      //       {
      //         ss:'false',
      //         ssId:2,
      //         ssStatus: '修筑排水设施',
      //         ssLane: '左二、三车道',
      //         ssStage: '2',
      //         ssStartTime: '2020-12-14',
      //         ssEndTime: '2020-12-30',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //         ssProperties: '全封闭',
      //         // ssSuggessions: null,
      //
      //         // ssFilePath: null,
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 1,
      //         ssProjectName: '行陈路翻新',
      //       },
      //
      //       {
      //         ssId:3,
      //         ssStatus: '修筑路基',
      //         ssLane: '左二、三车道',
      //         ssStage: '1',
      //         ssStartTime: '2020-11-14',
      //         ssEndTime: '2020-12-14',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //         ssProperties: '全封闭',
      //         ssBuilderName:'中铁三局',
      //         ssProjectId: 2,
      //         ssProjectName: '校园路翻新',
      //
      //       },
      //       {
      //         ssId:22,
      //         ssStatus: '修筑排水设施',
      //         ssLane: '左二、三车道',
      //         ssStage: '2',
      //         ssStartTime: '2020-12-14',
      //         ssEndTime: '2020-12-30',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //         ssProperties: '全封闭',
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 2,
      //         ssProjectName: '校园路翻新',
      //
      //       },
      //       {
      //         ssId:23,
      //         ssStatus: '修筑排水设施',
      //         ssLane: '左二、三车道',
      //         ssStage: '2',
      //         ssStartTime: '2020-12-14',
      //         ssEndTime: '2020-12-30',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //         ssProperties: '全封闭',
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 2,
      //         ssProjectName: '校园路翻新',
      //
      //       },
      //       {
      //         ss:'false',
      //         ssId:31,
      //         ssStatus: '修筑路基',
      //         ssLane: '左二、三车道',
      //         ssStage: '1',
      //         ssStartTime: '2020-11-14',
      //         ssEndTime: '2020-12-14',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //         ssProperties: '全封闭',
      //         // ssSuggessions: null,
      //
      //         // ssFilePath: null,
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 3,
      //         ssProjectName: '犀安路翻新',
      //       },
      //       {
      //         ss:'false',
      //         ssId:32,
      //         ssStatus: '修筑路基',
      //         ssLane: '左二、三车道',
      //         ssStage: '1',
      //         ssStartTime: '2020-11-14',
      //         ssEndTime: '2020-12-14',
      //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
      //
      //         ssProperties: '全封闭',
      //         // ssSuggessions: null,
      //
      //         // ssFilePath: null,
      //         ssBuilderName:'中铁一局',
      //         ssProjectId: 3,
      //         ssProjectName: '犀安路翻新',
      //       },
      //     ],
      //     this.total=2,
      //
      //
      //   this.loading = false;
      });
    },
    /** 查看地图按钮操作 */
    handleMapview(e){
      this.mapVisible=true;
      this.epolygon=e.ssRange;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        ssId: null,
        ssStatus: "0",
        ssLane: null,
        ssStage: null,
        ssStartTime: null,
        ssEndTime: null,
        ssRange: null,
        ssProjectId: null,
        ssProperties: null,
        ssSuggessions: null,
        ssFilePath: null,
        ssBuilderId: null,
        ssTrafficStaffId: null,
        ssDeleteFlag: null,
        ssVerifyFlag: null,
        ssVerifyDate: null,
        ssUpdateFlag: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.ssId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加围蔽信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ssId = row.ssId || this.ids
      getAllscheme(ssId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改围蔽信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ssId != null) {
            updateAllscheme(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addAllscheme(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ssIds = row.ssId || this.ids;
      this.$confirm('是否确认删除围蔽信息编号为"' + ssIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAllscheme(ssIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有围蔽信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAllscheme(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
