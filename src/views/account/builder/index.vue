<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户账号" prop="builderUsername">
        <el-input
          v-model="queryParams.builderUsername"
          placeholder="请输入用户账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工单位名称" prop="builderName">
        <el-input
          v-model="queryParams.builderName"
          placeholder="请输入施工单位名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--      <el-form-item label="密码" prop="builderPassword">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.builderPassword"-->
      <!--          placeholder="请输入密码"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="施工单位法人" prop="builderCorporate">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.builderCorporate"-->
      <!--          placeholder="请输入施工单位法人"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="联系电话" prop="builderPhone">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.builderPhone"-->
      <!--          placeholder="请输入联系电话"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="公司地址" prop="builderAddress">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.builderAddress"-->
      <!--          placeholder="请输入公司地址"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="企业编号" prop="builderEnterpriseNumber">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.builderEnterpriseNumber"-->
      <!--          placeholder="请输入企业编号"-->
      <!--          clearable-->
      <!--          size="small"-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
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
          v-hasPermi="['account:builder:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['account:builder:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['account:builder:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['account:builder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="builderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="施工单位id" align="center" prop="builderId" />
      <el-table-column label="用户账号" align="center" prop="builderUsername" />
      <el-table-column label="施工单位名称" align="center" prop="builderName" />
      <!--      <el-table-column label="密码" align="center" prop="builderPassword" />-->
      <el-table-column label="施工单位法人" align="center" prop="builderCorporate" />
      <el-table-column label="联系电话" align="center" prop="builderPhone" />
      <el-table-column label="公司地址" align="center" prop="builderAddress" />
      <el-table-column label="企业编号" align="center" prop="builderEnterpriseNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate"
            v-hasPermi="['account:builder:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete"
            v-hasPermi="['account:builder:remove']"
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

    <!-- 添加施工单位用户对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户账号" prop="builderUsername">
          <el-input v-model="form.builderUsername" placeholder="请输入用户账号" />
        </el-form-item>
        <el-form-item label="施工单位名称" prop="builderName">
          <el-input v-model="form.builderName" placeholder="请输入施工单位名称" />
        </el-form-item>
        <el-form-item label="密码" prop="builderPassword">
          <el-input v-model="form.builderPassword" placeholder="请输入密码" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 修改施工单位用户对话框 -->
    <el-dialog :title="title" :visible.sync="openupdate" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户账号" prop="builderUsername">
          <el-input v-model="form.builderUsername" placeholder="请输入用户账号" />
        </el-form-item>
        <el-form-item label="施工单位名称" prop="builderName">
          <el-input v-model="form.builderName" placeholder="请输入施工单位名称" />
        </el-form-item>
        <el-form-item label="原密码" prop="builderPassword">
          <el-input v-model="form.builderPassword" placeholder="请输入原密码" />
        </el-form-item>
        <!--        <el-form-item label="新密码" prop="builderPassword">-->
        <!--          <el-input v-model="form.builderPassword" placeholder="请输入密码" />-->
        <!--        </el-form-item>-->
        <el-form-item label="施工单位法人" prop="builderCorporate">
          <el-input v-model="form.builderCorporate" placeholder="请输入施工单位法人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="builderPhone">
          <el-input v-model="form.builderPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="公司地址" prop="builderAddress">
          <el-input v-model="form.builderAddress" placeholder="请输入公司地址" />
        </el-form-item>
        <el-form-item label="企业编号" prop="builderEnterpriseNumber">
          <el-input v-model="form.builderEnterpriseNumber" placeholder="请输入企业编号" />
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
  import { listBuilder, getBuilder, delBuilder, addBuilder, updateBuilder, exportBuilder } from "@/api/account/builder";

  export default {
    name: "Builder",
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
        // 施工单位用户表格数据
        builderList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        openupdate: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          builderUsername: null,
          builderName: null,
          builderPassword: null,
          builderCorporate: null,
          builderPhone: null,
          builderAddress: null,
          builderEnterpriseNumber: null,
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
      /** 查询施工单位用户列表 */
      getList() {
        this.loading = true;
        listBuilder(this.queryParams).then(response => {
          this.builderList = response.rows;
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
          builderId: null,
          builderUsername: null,
          builderName: null,
          builderPassword: null,
          builderCorporate: null,
          builderPhone: null,
          builderAddress: null,
          builderEnterpriseNumber: null,
          builderDeleteFlag: null
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
        this.ids = selection.map(item => item.builderId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加施工单位用户";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const builderId = row.builderId || this.ids
        getBuilder(builderId).then(response => {
          this.form = response.data;
          this.openupdate = true;
          this.title = "修改施工单位用户";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.builderId != null) {
              updateBuilder(this.form).then(response => {
                if (response.code === 200) {
                  this.msgSuccess("修改成功");
                  this.open = false;
                  this.getList();
                }
              });
            } else {
              addBuilder(this.form).then(response => {
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
        const builderIds = row.builderId || this.ids;
        this.$confirm('是否确认删除施工单位用户编号为"' + builderIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delBuilder(builderIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有施工单位用户数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportBuilder(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
      }
    }
  };
</script>
