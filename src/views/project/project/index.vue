<template>
  <div class="app-container">
    <!--    搜索-->
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="项目位置" prop="projectLocation">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectLocation"-->
      <!--          placeholder="请输入项目位置"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="项目位置(地图)" prop="projectLongLat">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectLongLat"-->
      <!--          placeholder="请输入项目位置(地图)"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="负责人" prop="projectManger">
        <el-input
          v-model="queryParams.projectManger"
          placeholder="请输入负责人"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="联系电话" prop="projectPhone">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectPhone"-->
      <!--          placeholder="请输入联系电话"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="施工单位名称" prop="projectBuilderName" label-width="100px">
        <el-input
          v-model="queryParams.projectBuilderName"
          placeholder="请输入施工单位名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="施工单位id" prop="projectBuilderId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectBuilderId"-->
      <!--          placeholder="请输入施工单位id"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="创建的交管人员id" prop="projectTrafficStaffId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectTrafficStaffId"-->
      <!--          placeholder="请输入创建的交管人员id"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="项目完工标识" prop="projectFinishedFlag">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectFinishedFlag"-->
      <!--          placeholder="请输入项目完工标识"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="删除标识" prop="projectDeleteFlag">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.projectDeleteFlag"-->
      <!--          placeholder="请输入删除标识"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="更新时间" prop="projectUpdateFlag">-->
      <!--        <el-date-picker clearable size="small" style="width: 200px"-->
      <!--          v-model="queryParams.projectUpdateFlag"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="选择更新时间">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:project:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:project:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:project:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:project:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 表格-->
    <el-table v-loading="loading" :data="projectList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="项目id" align="center" prop="projectId" />-->
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目位置" align="center" prop="projectLocation" />
      <!--      <el-table-column label="项目位置(地图)" align="center" prop="projectLongLat" />-->
      <el-table-column label="负责人" align="center" prop="projectManger" />
      <el-table-column label="联系电话" align="center" prop="projectPhone" />
      <el-table-column label="施工单位名称" align="center" prop="projectBuilderName" />
      <!--      <el-table-column label="施工单位id" align="center" prop="projectBuilderId" />-->
      <!--      <el-table-column label="创建的交管人员id" align="center" prop="projectTrafficStaffId" />-->
      <el-table-column label="项目是否完工" align="center" prop="projectFinishedFlag" />
      <!--      <el-table-column label="删除标识" align="center" prop="projectDeleteFlag" />-->
      <!--      <el-table-column label="更新时间" align="center" prop="projectUpdateFlag" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.projectUpdateFlag, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="260px">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:project:edit']"
          >完善/修改</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:project:remove']"
          >删除</el-button>
          <!--          <el-button @click="mapView(scope.row)"type="text" size="small">查看地图</el-button>-->
          <el-button
            @click="projectdone(scope.row)"
            type="text"
            size="small"
            v-hasPermi="['project:project:finish']"
            v-if="scope.row.projectFinishedFlag=='continue'"
          >项目完工</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加施工单位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>

        <!--        <el-form-item label="施工单位名称" prop="projectBuilderName">-->
        <!--          <el-input v-model="form.projectBuilderName" placeholder="请输入施工单位名称" />-->
        <!--        </el-form-item>-->

        <el-form-item label="施工单位名称" prop="projectBuilderName">
          <el-select v-model="form.projectBuilderName" placeholder="请选择施工单位名称" clearable size="small" >
            <el-option v-for="item in builderList"  :value="item.id" >{{ item.name }}</el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改施工项目对话框 -->
    <el-dialog :title="title" :visible.sync="openupd" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目位置" prop="projectLocation">
          <el-input v-model="form.projectLocation" placeholder="请输入文字描述的项目位置" />
        </el-form-item>
        <el-form-item label="项目位置(地图)" prop="projectLongLat">
          <el-input v-model="form.projectLongLat" placeholder="请选择地图中的项目位置" />
          <el-button @click="mapshow" type="primary" plain>选择围蔽区域</el-button>
        </el-form-item>

        <el-form-item label="负责人" prop="projectManger">
          <el-input v-model="form.projectManger" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="projectPhone">
          <el-input v-model="form.projectPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="施工单位名称" prop="projectBuilderName">
          <el-select v-model="form.projectBuilderName" placeholder="请选择施工单位名称" clearable size="small" >
            <el-option v-for="item in builderList"  :value="item.id" >{{ item.name }}</el-option>
          </el-select>
        </el-form-item>

        <!--        <el-form-item label="施工单位id" prop="projectBuilderId">-->
        <!--          <el-input v-model="form.projectBuilderId" placeholder="请输入施工单位id" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="创建的交管人员id" prop="projectTrafficStaffId">-->
        <!--          <el-input v-model="form.projectTrafficStaffId" placeholder="请输入创建的交管人员id" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="项目完工标识" prop="projectFinishedFlag">-->
        <!--          <el-input v-model="form.projectFinishedFlag" placeholder="请输入项目完工标识" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="删除标识" prop="projectDeleteFlag">-->
        <!--          <el-input v-model="form.projectDeleteFlag" placeholder="请输入删除标识" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="更新时间" prop="projectUpdateFlag">-->
        <!--          <el-date-picker clearable size="small" style="width: 200px"-->
        <!--            v-model="form.projectUpdateFlag"-->
        <!--            type="date"-->
        <!--            value-format="yyyy-MM-dd"-->
        <!--            placeholder="选择更新时间">-->
        <!--          </el-date-picker>-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!--ploygon子组件，选择地图中位置-->
    <el-dialog width="80%"   :visible.sync="pointVisible" append-to-body>
      <Ploygon v-if="pointVisible" ref="Ploygon" :msg="form.projectLongLat" @myfun="myf"></Ploygon>
    </el-dialog>
    <!--mapview子组件，显示地图中位置-->
    <el-dialog width="80%"   :visible.sync="mapVisible" append-to-body>
      <mapView v-if="mapVisible" ref="mapView" :msg="epolygon" ></mapView>
    </el-dialog>

  </div>
</template>

<script>
  import { listProject, getProject, delProject, addProject, updateProject, exportProject  } from "@/api/project/project";
  import Ploygon from "../../test/components/Ploygon";
  import mapView from "../../map/components/mapView";
  import {listBuilder} from "@/api/account/builder";
  export default {
    name: "Project",
    components:{Ploygon,mapView},
    props:['msg'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        names:[],
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 施工项目表格数据
        projectList: [],
        // 施工单位表格数据
        builderList: [],
        // 弹出层标题
        title: "",
        // 是否显示修改弹出层
        openupd: false,
        // 是否显示新增弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          projectName: null,
          projectLocation: null,
          projectLongLat: null,
          projectManger: null,
          projectPhone: null,
          projectBuilderName: null,
          projectBuilderId: null,
          projectTrafficStaffId: null,
          projectFinishedFlag: null,
          projectDeleteFlag: null,
          projectUpdateFlag: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          projectPhone: [
            //  { required: true, message: '请输入活动名称', trigger: 'blur' },
            { min: 11, max: 11, message: '长度为11个数字', trigger: 'blur' }
          ],
        },
        //是否显示ploygon
        pointVisible:false,
        //是否显示map
        mapVisible:false,
        //传递给子组件mapview的地图ploygon
        epolygon:"",
        //弹窗测试
        testmbox:"",
      };
    },
    created() {
      this.getList();
      this.getbuilder();
    },
    methods: {
      // 地图选择子组件显示
      mapshow(){
        this.pointVisible=true;
      },
// 获取子组件ploygon传来的值
      myf(ms) {
        this.form.projectLongLat=ms;
        // console.log(this.form.ploygon);
      },
// 地图显示项目位置子组件
      mapView:function(e){
        this.mapVisible=true;
        this.epolygon=e.projectLongLat;
        // console.log(e.projectLongLat);
      },
      //项目完工按钮
      projectdone:function(e){
        this.$confirm('此操作将此施工项目设置为已完工, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$message({
            type: 'success',
            message: '此施工项目成功设置为已完工!'
          });
          //测试
          this.testmbox="已完工";
          console.log(this.testmbox);
          const projectId = e.projectId || this.ids;
            getProject(projectId).then(response => {
              this.form = response.data;
              this.form.projectFinishedFlag="finish";
              updateProject(this.form);
              this.getList();
            });

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消设置'
          });
          //测试
          this.testmbox="未设置完工";
          console.log(this.testmbox)
        });
      },
      /** 查询施工项目列表 */
      getList() {
        this.loading = true;
        listProject(this.queryParams).then(response => {
          this.projectList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查询施工单位列表 ！！！！！！！*/
      getbuilder() {
        // this.builderList=[{name:"暂无",id:"暂无"},{name:"中铁一局",id:"中铁一局"},{name:"中铁二局",id:"中铁二局"}];
        // console.log(this.builderList);
        listBuilder(this.queryParams).then(response => {
          this.builderList=[{name:"暂无",id:"暂无"}];
          console.log(this.builderList);
          for ( let i of response.rows) {
            this.builderList.push({name:i.builderUsername,id:i.builderUsername});
          }
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          projectId: null,
          projectName: null,
          projectLocation: null,
          projectLongLat: null,
          projectManger: null,
          projectPhone: null,
          projectBuilderName: null,
          projectBuilderId: null,
          projectTrafficStaffId: null,
          projectFinishedFlag: null,
          projectDeleteFlag: null,
          projectUpdateFlag: null
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
        this.names=selection.map(item => item.projectName)
        this.ids = selection.map(item => item.projectId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加施工单位";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const projectId = row.projectId || this.ids
        getProject(projectId).then(response => {
          this.form = response.data;
          this.openupd = true;
          this.title = "修改施工单位";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.projectId != null) {
              updateProject(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addProject(this.form).then(response => {
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
        const projectIds = row.projectId || this.ids;
        const projectNames=row.projectName|| this.names;
        this.$confirm('是否确认删除项目名称为"' +projectNames + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delProject(projectIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有施工单位数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportProject(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
