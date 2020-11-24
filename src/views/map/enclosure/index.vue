<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目id" prop="projectid">
        <el-input
                v-model="queryParams.projectid"
                placeholder="请输入项目id"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"/>
      </el-form-item>

      <el-form-item label="围蔽状态" prop="enclosurestatus">
        <el-input
                v-model="queryParams.enclosurestatus"
                placeholder="请输入围蔽状态"
                clearable
                size="small"
                @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="围蔽阶段" prop="phaseid">
        <el-select v-model="queryParams.phaseid" placeholder="请选择围蔽阶段" clearable size="small">
          <el-option label="1" value="1" />
          <el-option label="2" value="2" />
          <el-option label="3" value="3" />
          <el-option label="4" value="4" />
          <el-option label="5" value="5" />
          <el-option label="6" value="6" />
        </el-select>
      </el-form-item>

      <el-form-item label="开始时间" prop="startDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.endDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间">
        </el-date-picker>
      </el-form-item>

<!--      <el-form-item label="围蔽区域" prop="ploygon">-->
<!--        <el-input-->
<!--          v-model="queryParams.ploygon"-->
<!--          placeholder="请输入围蔽区域"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="围蔽车道" prop="lane">
        <el-input
          v-model="queryParams.lane"
          placeholder="请输入围蔽车道"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="里程桩号" prop="enclosurelenght">
        <el-input
          v-model="queryParams.enclosurelenght"
          placeholder="请输入里程桩号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="封闭性质" prop="enclosureproperty">
        <el-select v-model="queryParams.enclosureproperty" placeholder="请选择封闭性质" clearable size="small">
          <el-option label="全封闭" value="全封闭" />
          <el-option label="半封闭" value="半封闭" />
        </el-select>
      </el-form-item>
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
          v-hasPermi="['map:enclosure:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['map:enclosure:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['map:enclosure:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['map:enclosure:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>



    <el-table v-loading="loading" :data="enclosureList" @selection-change="handleSelectionChange">



      <el-table-column type="selection" width="55" align="center" />



      <el-table-column label="项目账号" align="center" prop="projectid" />


      <el-table-column label="围蔽状态" align="center" prop="enclosurestatus" />
      <el-table-column label="围蔽阶段" align="center" prop="phaseid" />
      <el-table-column label="开始时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="围蔽区域" align="center" prop="ploygon"  width="170" />
      <el-table-column label="围蔽车道" align="center" prop="lane" />
      <el-table-column label="里程桩号" align="center" prop="enclosurelenght" />
      <el-table-column label="封闭性质" align="center" prop="enclosureproperty" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['map:enclosure:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['map:enclosure:remove']"
          >删除</el-button>
        </template>
      </el-table-column>


    </el-table>

    <el-collapse v-model="enclosureList" @change="handleChange">
      <el-collapse-item title="一致性 Consistency" name="enclosureList.index">
      </el-collapse-item>
    </el-collapse>

    <table>
      <tr v-for="(emp,index) in biaotou">
        <td>{{index+1}}</td>
        <td>{{emp.name}}</td>
        <td>{{emp.age}}</td>
        <td>{{emp.gender}}</td>
        <td>
          <button class="btn btn btn-danger" @click="delete_emp(index)">del</button>
          <button class="btn btn btn-primary" @click="modify_emp(index)">mod</button>
        </td>
      </tr>
    </table>

    <el-table v-loading="loading" :data="encllist" @selection-change="handleSelectionChange">



      <el-table-column type="selection" width="55" align="center" />



      <el-table-column label="项目账号" align="center" prop="projectid" />


      <el-table-column label="围蔽状态" align="center" prop="enclosurestatus" />
      <el-table-column label="围蔽阶段" align="center" prop="phaseid" />
      <el-table-column label="开始时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="围蔽区域" align="center" prop="ploygon"  width="170" />
      <el-table-column label="围蔽车道" align="center" prop="lane" />
      <el-table-column label="里程桩号" align="center" prop="enclosurelenght" />
      <el-table-column label="封闭性质" align="center" prop="enclosureproperty" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['map:enclosure:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['map:enclosure:remove']"
          >删除</el-button>
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

    <!-- 添加或修改围蔽方案信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <addedit  v-if="open" ref="addEdit" :msg="form" @updateL="updateL"></addedit>
    </el-dialog>





  </div>
</template>

<script>
import { listEnclosure, getEnclosure, delEnclosure, addEnclosure, updateEnclosure, exportEnclosure } from "@/api/map/enclosure";
import addedit from "../components/addEdit";

export default {
  name: "Enclosure",
  components:{addedit},

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
      // 围蔽方案信息表格数据
      enclosureList: [],

      biaotou:[],
      encllist:[],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
        enclosureproperty: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      // //是否新增或修改成功
      // sta:false,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    updateL(st){
      if(st==true){
        this.getList();
      }
    },



    /** 查询围蔽方案信息列表 */
    getList() {
      this.loading = true;
      listEnclosure(this.queryParams).then(response => {
        this.enclosureList = response.rows;


        console.log(this.enclosureList);
        this.encllist=[
          {projectid: 114415,enclosurestatus:null,phaseid:null,startDate:null,endDate:null,ploygon: null, lane: null, enclosurelenght: null, enclosureproperty: null},
          {projectid: 114142,enclosurestatus:null,phaseid:null,startDate:null,endDate:null,ploygon: null, lane: null, enclosurelenght: null, enclosureproperty: null},
          {projectid: 777854,enclosurestatus:null,phaseid:null,startDate:null,endDate:null,ploygon: null, lane: null, enclosurelenght: null, enclosureproperty: null},
        ],
          this.biaotou=[{projectid:'项目id',enclosurestatus:'围蔽状态',phaseid:'阶段',startDate:'开始时间',endDate:'结束时间',lane:"车道数",enclosurelenght:"围蔽长度",enclosureproperty:"围蔽性质",},],

        this.total = response.total;
        this.loading = false;
      });
    },



    // 表单重置
    reset() {
      this.form = {
        enclosurestatus: null,
        phaseid: null,
        startDate: null,
        endDate: null,
        projectid: null,
        ploygon: null,
        lane: null,
        enclosurelenght: null,
        enclosureproperty: null,
        id: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加围蔽方案信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getEnclosure(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改围蔽方案信息";
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除围蔽方案信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delEnclosure(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有围蔽方案信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportEnclosure(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
