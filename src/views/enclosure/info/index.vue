<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="110px">
      <el-form-item label="施工项目名称" prop="riProjectName">
        <el-input
          v-model="queryParams.riProjectName"
          placeholder="请输入施工项目名称"
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
          v-hasPermi="['enclosure:info:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="整改信息id" align="center" prop="riId" />-->
      <el-table-column label="施工项目名称" align="center" prop="riProjectName" />
      <el-table-column label="整改截至时间" align="center" prop="riDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.riDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="整改需求" align="center" prop="riRequirements" />
<!--      <el-table-column label="现场照片地址" align="center" prop="riPhotoPath" />-->
      <el-table-column label="整改状态" align="center" prop="riStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini"
                     type="text"
                     @click="handleMapview(scope.row)"
                     v-hasPermi="['system:menu:downloadP']"
          >下载现场照片</el-button>
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

    <!-- 添加或修改整改信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="riProjectName">
          <el-input v-model="form.riProjectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="整改截至时间" prop="riDate">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.riDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择整改截至时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="整改需求" prop="riRequirements">
          <el-input v-model="form.riRequirements" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="提交交警id" prop="riPoliceId">
          <el-input v-model="form.riPoliceId" placeholder="请输入提交交警id" />
        </el-form-item>
        <el-form-item label="现场照片地址" prop="riPhotoPath">
          <el-input v-model="form.riPhotoPath" placeholder="请输入现场照片地址" />
        </el-form-item>
        <el-form-item label="整改状态">
          <el-radio-group v-model="form.riStatus">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="删除标识" prop="riDeleteFlag">
          <el-input v-model="form.riDeleteFlag" placeholder="请输入删除标识" />
        </el-form-item>
        <el-form-item label="更新时间" prop="riUpdateFlag">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.riUpdateFlag"
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
  </div>
</template>

<script>
import { listInfo, getInfo, delInfo, addInfo, updateInfo, exportInfo } from "@/api/enclosure/info";

export default {
  name: "Info",
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
      // 整改信息表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        riProjectId: null,
        riDate: null,
        riRequirements: null,
        riPoliceId: null,
        riPhotoPath: null,
        riStatus: null,
        riDeleteFlag: null,
        riUpdateFlag: null,
        riProjectName:null,
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
    /** 查询整改信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
        this.total = response.total;
        this.loading = false;
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
        riId: null,
        riProjectId: null,
        riDate: null,
        riRequirements: null,
        riPoliceId: null,
        riPhotoPath: null,
        riStatus: "0",
        riDeleteFlag: null,
        riUpdateFlag: null,
        riProjectName:null,
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
      this.ids = selection.map(item => item.riId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加整改信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const riId = row.riId || this.ids
      getInfo(riId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.riId != null) {
            updateInfo(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addInfo(this.form).then(response => {
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
      const riIds = row.riId || this.ids;
      this.$confirm('是否确认删除整改信息编号为"' + riIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delInfo(riIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有整改信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
