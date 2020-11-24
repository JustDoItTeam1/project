<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="110px">
<!--      <el-form-item label="围蔽状态" prop="ssStatus">-->
<!--        <el-input-->
<!--          v-model="queryParams.ssStatus"-->
<!--          placeholder="请输入围蔽状态"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="围蔽车道" prop="ssLane">-->
<!--        <el-input-->
<!--          v-model="queryParams.ssLane"-->
<!--          placeholder="请输入围蔽车道"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="围蔽阶段" prop="ssStage">-->
<!--        <el-select v-model="queryParams.ssStage" placeholder="请选择围蔽阶段" clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="开始时间" prop="ssStartTime">-->
<!--        <el-date-picker clearable size="small" style="width: 200px"-->
<!--          v-model="queryParams.ssStartTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择开始时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="结束时间" prop="ssEndTime">-->
<!--        <el-date-picker clearable size="small" style="width: 200px"-->
<!--          v-model="queryParams.ssEndTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择结束时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="围蔽区域(地图)" prop="ssRange">-->
<!--        <el-input-->
<!--          v-model="queryParams.ssRange"-->
<!--          placeholder="请输入围蔽区域(地图)"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="施工项目名称" prop="ssProjectId" width="110px">
        <el-input
          v-model="queryParams.ssProjectName"
          placeholder="请输入施工项目名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="围蔽性质" prop="ssProperties">-->
<!--        <el-select v-model="queryParams.ssProperties" placeholder="请选择围蔽性质" clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="围蔽拒绝建议" prop="ssSuggessions">-->
<!--        <el-input-->
<!--          v-model="queryParams.ssSuggessions"-->
<!--          placeholder="请输入围蔽拒绝建议"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="施工单位名称" prop="ssBuilderId" width="110px">
        <el-input
          v-model="queryParams.ssBuilderId"
          placeholder="请输入施工单位名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="审核标识" prop="ssVerifyFlag">-->
<!--        <el-input-->
<!--          v-model="queryParams.ssVerifyFlag"-->
<!--          placeholder="请输入审核标识"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="审核时间" prop="ssVerifyDate">-->
<!--        <el-date-picker clearable size="small" style="width: 200px"-->
<!--          v-model="queryParams.ssVerifyDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择审核时间">-->
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
          v-hasPermi="['enclosure:scheme:add']"
        >新增</el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['enclosure:scheme:export']"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

<!--    树形表格-->
    <el-table
      v-loading="loading"
      :data="schemeList"
      row-key="ssId"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
<!--      <el-table-column label="施工项目id" align="center" prop="ssProjectId" width="110"/>-->
<!--      <el-table-column label="施工项目名称" align="center" prop="ssProjectname" />-->
<!--      <el-table-column label="施工单位名称" align="center" prop="ssBuildername" />-->
<!--      <el-table-column label="围蔽状态" align="center" prop="ssStatus" />-->
<!--      <el-table-column label="围蔽车道" align="center" prop="ssLane" />-->
<!--      <el-table-column label="围蔽阶段" align="center" prop="ssStage" />-->
<!--      <el-table-column label="开始时间" align="center" prop="ssStartTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.ssStartTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="结束时间" align="center" prop="ssEndTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.ssEndTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="围蔽区域(地图)" align="center" prop="ssRange" :show-overflow-tooltip="true" width="200"/>-->

<!--      <el-table-column label="围蔽性质" align="center" prop="ssProperties" />-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150"   prop="ss">-->

<!--        <template slot-scope="scope">-->
<!--          <el-button size="mini"-->
<!--                     type="text"-->
<!--                     icon="el-icon-edit"-->
<!--                     @click="handleUpdate(scope.row)"-->
<!--                     v-hasPermi="['system:menu:edit']"-->
<!--                     v-if=scope.row.ss-->
<!--          >修改</el-button>-->

<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-plus"-->
<!--            @click="handleAdd(scope.row)"-->
<!--            v-hasPermi="['system:menu:add']"-->
<!--          >下载附件</el-button>-->

<!--        </template>-->

<!--      </el-table-column>-->
      <el-table-column label="施工项目id" align="center" prop="ssProjectId" width="110" />
      <el-table-column label="施工项目名称" align="center" prop="ssProjectName" width="110" />
      <el-table-column label="施工单位名称" align="center" prop="ssBuilderName" width="110"/>
      <el-table-column label="围蔽阶段" align="center" prop="ssStage" width="110"/>
      <el-table-column label="围蔽状态" align="center" prop="ssStatus" width="170" />
      <el-table-column label="围蔽车道" align="center" prop="ssLane" width="130"/>

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
      <el-table-column label="围蔽性质" align="center" prop="ssProperties" width="120" />
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

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">

        <template slot-scope="scope" >
          <el-button
            size="mini"
            type="text"
            @click="handleDownload(scope.row)"
            v-hasPermi="['system:menu:download']"
            v-if=scope.row.ss
          >下载附件</el-button>
          <el-button size="mini"
                     type="text"
                     @click="handleAgree(scope.row)"
                     v-hasPermi="['system:menu:agree']"
                     v-if=scope.row.ss
          >通过</el-button>
          <el-button size="mini"
                     type="text"
                     @click="handleDisagree(scope.row)"
                     v-hasPermi="['system:menu:edit']"
                     v-if=scope.row.ss
          >否决</el-button>


        </template>

      </el-table-column>
    </el-table>


<!--    <el-table-->
<!--      v-loading="loading"-->
<!--      :data="schemeList"-->
<!--      :span-method="objectSpanMethod"-->
<!--      @selection-change="handleSelectionChange">-->
<!--&lt;!&ndash;      <el-table-column type="selection" width="55" align="center"  fixed="left"/>&ndash;&gt;-->
<!--      <el-table-column label="施工项目id" align="center" prop="ssProjectId" width="110"  fixed="left"/>-->
<!--      <el-table-column label="施工项目名称" align="center" prop="ssProjectName" width="110" fixed="left"/>-->
<!--      <el-table-column label="施工单位名称" align="center" prop="ssBuilderName" width="110"/>-->
<!--      <el-table-column label="围蔽状态" align="center" prop="ssStatus" width="170" />-->
<!--      <el-table-column label="围蔽车道" align="center" prop="ssLane" width="130"/>-->
<!--      <el-table-column label="围蔽阶段" align="center" prop="ssStage" width="110"/>-->
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
<!--      <el-table-column label="查看围蔽区域" align="center" class-name="small-padding fixed-width" width="150">-->
<!--        <template slot-scope="scope" >-->
<!--        <el-button size="mini"-->
<!--                   type="text"-->
<!--                   @click="handleMapview(scope.row)"-->
<!--                   v-hasPermi="['system:menu:mapview']"-->
<!--        >查看地图</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->

<!--        <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="150">-->

<!--        <template slot-scope="scope" >-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            @click="handleDownload(scope.row)"-->
<!--            v-hasPermi="['system:menu:download']"-->
<!--          >下载附件</el-button>-->
<!--          <el-button size="mini"-->
<!--                     type="text"-->
<!--                     @click="handleAgree(scope.row)"-->
<!--                     v-hasPermi="['system:menu:agree']"-->
<!--          >通过</el-button>-->
<!--          <el-button size="mini"-->
<!--                     type="text"-->
<!--                     @click="handleDisagree(scope.row)"-->
<!--                     v-hasPermi="['system:menu:edit']"-->
<!--          >否决</el-button>-->


<!--        </template>-->

<!--      </el-table-column>-->
<!--    </el-table>-->

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

<!--    <table>-->
<!--      <tr v-for="(emp,index) in schemeList">-->

<!--        <td>{{index+1}}</td>-->
<!--        <td>{{emp.ssBuildername}}</td>-->
<!--        <td>{{emp.ssProjectId}}</td>-->
<!--        <td>{{emp.ssProjectname}}</td>-->
<!--        <td>-->
<!--          <button class="btn btn btn-danger" @click="delete_emp(index)">del</button>-->
<!--          <button class="btn btn btn-primary" @click="modify_emp(index)">mod</button>-->
<!--        </td>-->
<!--        <el-collapse v-model="enclosureList" @change="handleChange">-->
<!--          <el-collapse-item title="一致性 Consistency"  name="enclosureList.index">-->
<!--            <td>{{index+1}}</td>-->
<!--            <td>{{emp.ssBuildername}}</td>-->
<!--            <td>{{emp.ssProjectId}}</td>-->
<!--            <td>{{emp.ssProjectname}}</td>-->
<!--            <td>-->
<!--              <button class="btn btn btn-danger" @click="delete_emp(index)">del</button>-->
<!--              <button class="btn btn btn-primary" @click="modify_emp(index)">mod</button>-->
<!--            </td>-->
<!--          </el-collapse-item>-->
<!--        </el-collapse>-->
<!--      </tr>-->

<!--    </table>-->

<!--    <el-table v-loading="loading" :data="schemeList" @selection-change="handleSelectionChange">-->
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="施工项目id" align="center" prop="ssProjectId" />-->
<!--      <el-table-column label="施工项目名称" align="center" prop="ssProjectname" />-->
<!--      <el-table-column label="施工单位名称" align="center" prop="ssBuildername" />-->
<!--      <el-table-column label="围蔽状态" align="center" prop="ssStatus" />-->
<!--      <el-table-column label="围蔽车道" align="center" prop="ssLane" />-->
<!--      <el-table-column label="围蔽阶段" align="center" prop="ssStage" />-->
<!--      <el-table-column label="开始时间" align="center" prop="ssStartTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.ssStartTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="结束时间" align="center" prop="ssEndTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.ssEndTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="围蔽区域(地图)" align="center" prop="ssRange" />-->
<!--      <el-table-column label="项目id" align="center" prop="ssProjectId" />-->
<!--      <el-table-column label="围蔽性质" align="center" prop="ssProperties" />-->
<!--&lt;!&ndash;      <el-table-column label="围蔽拒绝建议" align="center" prop="ssSuggessions" />&ndash;&gt;-->
<!--&lt;!&ndash;      <el-table-column label="施工单位id" align="center" prop="ssBuilderId" />&ndash;&gt;-->
<!--&lt;!&ndash;      <el-table-column label="审核标识" align="center" prop="ssVerifyFlag" />&ndash;&gt;-->
<!--&lt;!&ndash;      <el-table-column label="审核时间" align="center" prop="ssVerifyDate" width="180">&ndash;&gt;-->
<!--&lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--&lt;!&ndash;          <span>{{ parseTime(scope.row.ssVerifyDate, '{y}-{m}-{d}') }}</span>&ndash;&gt;-->
<!--&lt;!&ndash;        </template>&ndash;&gt;-->
<!--&lt;!&ndash;      </el-table-column>&ndash;&gt;-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['enclosure:scheme:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['enclosure:scheme:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--    </el-table>-->

<!--    <pagination-->
<!--      v-show="total>0"-->
<!--      :total="total"-->
<!--      :page.sync="queryParams.pageNum"-->
<!--      :limit.sync="queryParams.pageSize"-->
<!--      @pagination="getList"-->
<!--    />-->



    <!-- 添加或修改围蔽信息对话框 -->
<!--    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>-->
<!--      <el-form ref="form" :model="form" :rules="rules" label-width="110px">-->
<!--        <el-form-item label="施工项目名称" prop="ssProjectName">-->
<!--          <el-select v-model="form.ssProjectName" placeholder="请选择项目名称" clearable size="small" >-->
<!--            <el-option v-for="item in projectList"  :value="item.id" >{{ item.name }}</el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="施工单位名称" prop="ssBuilderName">-->
<!--          <el-select v-model="form.ssBuilderName" placeholder="请选择施工单位名称" clearable size="small" >-->
<!--          <el-option v-for="item in builderList"  :value="item.id" >{{ item.name }}</el-option>-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="围蔽阶段" prop="ssStage">-->
<!--          <el-select v-model="form.ssStage" placeholder="请选择围蔽阶段">-->
<!--            <el-option label="1" value="1" />-->
<!--            <el-option label="2" value="2" />-->
<!--            <el-option label="3" value="3" />-->
<!--            <el-option label="4" value="4" />-->
<!--            <el-option label="5" value="5" />-->
<!--            <el-option label="6" value="6" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="围蔽状态" prop="ssStatus">-->
<!--          <el-input v-model="form.ssStatus" placeholder="请输入围蔽状态" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="围蔽车道" prop="ssLane">-->
<!--          <el-input v-model="form.ssLane" placeholder="请输入围蔽车道" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="开始时间" prop="ssStartTime">-->
<!--          <el-date-picker clearable size="small" style="width: 200px"-->
<!--            v-model="form.ssStartTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择开始时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="结束时间" prop="ssEndTime">-->
<!--          <el-date-picker clearable size="small" style="width: 200px"-->
<!--            v-model="form.ssEndTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择结束时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="围蔽区域(地图)" prop="ssRange">-->
<!--          <el-input v-model="form.ssRange" placeholder="请输入围蔽区域(地图)" />-->
<!--        </el-form-item>-->

<!--        <el-form-item label="围蔽性质" prop="ssProperties">-->
<!--          <el-select v-model="form.ssProperties" placeholder="请选择围蔽性质">-->
<!--            <el-option label="全封闭" value="全封闭" />-->
<!--            <el-option label="半封闭" value="半封闭" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
<!--&lt;!&ndash;        <el-form-item label="围蔽拒绝建议" prop="ssSuggessions">&ndash;&gt;-->
<!--&lt;!&ndash;          <el-input v-model="form.ssSuggessions" placeholder="请输入围蔽拒绝建议" />&ndash;&gt;-->
<!--&lt;!&ndash;        </el-form-item>&ndash;&gt;-->

<!--&lt;!&ndash;        <el-form-item label="审核标识" prop="ssVerifyFlag">&ndash;&gt;-->
<!--&lt;!&ndash;          <el-input v-model="form.ssVerifyFlag" placeholder="请输入审核标识" />&ndash;&gt;-->
<!--&lt;!&ndash;        </el-form-item>&ndash;&gt;-->
<!--&lt;!&ndash;        <el-form-item label="审核时间" prop="ssVerifyDate">&ndash;&gt;-->
<!--&lt;!&ndash;          <el-date-picker clearable size="small" style="width: 200px"&ndash;&gt;-->
<!--&lt;!&ndash;            v-model="form.ssVerifyDate"&ndash;&gt;-->
<!--&lt;!&ndash;            type="date"&ndash;&gt;-->
<!--&lt;!&ndash;            value-format="yyyy-MM-dd"&ndash;&gt;-->
<!--&lt;!&ndash;            placeholder="选择审核时间">&ndash;&gt;-->
<!--&lt;!&ndash;          </el-date-picker>&ndash;&gt;-->
<!--&lt;!&ndash;        </el-form-item>&ndash;&gt;-->
<!--      </el-form>-->
<!--      <div slot="footer" class="dialog-footer">-->
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
<!--        <el-button @click="cancel">取 消</el-button>-->
<!--      </div>-->
<!--    </el-dialog>-->

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="施工项目名称" prop="ssProjectName">
          <el-select v-model="form.ssProjectName" placeholder="请选择项目名称" clearable size="small" >
            <el-option v-for="item in projectList"  :value="item.id" >{{ item.name }}</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="施工单位名称" prop="ssBuilderName">
          <el-select v-model="form.ssBuilderName" placeholder="请选择施工单位名称" clearable size="small" >
            <el-option v-for="item in builderList"  :value="item.id" >{{ item.name }}</el-option>
          </el-select>
        </el-form-item>


        <el-form-item label="围蔽阶段" prop="ssStage">
          <el-select v-model="form.ssStage" placeholder="请选择围蔽阶段">
            <el-option label="1" value="1" />
            <el-option label="2" value="2" />
            <el-option label="3" value="3" />
            <el-option label="4" value="4" />
            <el-option label="5" value="5" />
            <el-option label="6" value="6" />
          </el-select>
        </el-form-item>
        <el-form-item label="围蔽状态" prop="ssStatus">
          <el-input v-model="form.ssStatus" placeholder="请输入围蔽状态" />
        </el-form-item>
        <el-form-item label="围蔽车道" prop="ssLane">
          <el-input v-model="form.ssLane" placeholder="请输入围蔽车道" />
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

        <el-form-item label="围蔽性质" prop="ssProperties">
          <el-select v-model="form.ssProperties" placeholder="请选择围蔽性质">
            <el-option label="全封闭" value="全封闭" />
            <el-option label="半封闭" value="半封闭" />
          </el-select>
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


    <el-dialog :visible.sync="opendisa" width="500px" append-to-body title="请添加拒绝围蔽方案的原因或建议">
      <el-form ref="elForm" :model="Suggessions"  size="medium" label-width="100px">
        <el-form-item label="原因或建议" prop="sugge">
          <el-input v-model="Suggessions.sugge" type="textarea" placeholder="请输入拒绝围蔽方案的原因或建议"
                    :autosize="{minRows: 4, maxRows: 4}" :style="{width: '100%'}"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" @click="handelConfirmReason">确定</el-button>
        <el-button @click="close">取消</el-button>

      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listEnclosure, getEnclosure, delEnclosure, addEnclosure, updateEnclosure, exportEnclosure } from "@/api/enclosure/enclosure";
import mapView from "../../map/components/mapView";
export default {
  name: "Scheme",
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
        ssBuilderId: null,
        ssVerifyFlag: null,
        ssVerifyDate: null,
      },

      // 围蔽信息表格数据
      schemeList: [],
      schemeList1: [],
      //孩子数量
      cnum:'',
      // //每个项目的围蔽阶段数
      // wbnum:[],
      // //表格合并中合并开始行
      // wbn:[],
      // //表格合并的列数
      // wbl:[0,1,2,11],
      //是否显示map
      mapVisible:false,
      //传递给子组件mapview的地图ploygon
      epolygon:"",
      // 表单参数
      form: {},
      //拒绝理由弹窗
      opendisa:false,
      //拒绝理由
      Suggessions:{},
      // 表单校验
      // rules1: {
      //   sugge: [{
      //     required: true,
      //     message: '请输入多行文本',
      //     trigger: 'blur'
      //   }],
      // },
      // 施工项目表格数据
      projectList: [],
      // 施工单位表格数据
      builderList: [],

      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询施工单位列表 ！！！！！！！*/
    getbuilder() {
      this.builderList=[{name:"暂无",id:"暂无"},{name:"中铁一局",id:"中铁一局"},{name:"中铁二局",id:"中铁二局"}];
      console.log(this.builderList)
      // listBuilder(this.queryParams).then(response => {
      //   this.builderList = response.rows;
      // });
    },
    /** 查询项目单位列表 ！！！！！！！*/
    getproject() {
      this.projectList=[{name:"校园路翻新",id:"校园路翻新"},{name:"犀安路翻新",id:"犀安路翻新"},];
      console.log(this.builderList)
      // listBuilder(this.queryParams).then(response => {
      //   this.builderList = response.rows;
      // });
    },

    // onClose() {
    //   this.$refs['elForm'].resetFields()
    // },
    // close() {
    //   this.$emit('update:visible', false)
    // },


    // exChange(row,expandedRows){
    //   console.log(row);
    //   console.log(expandedRows);
    //
    // },
    //表格合并
    // objectSpanMethod({ row, column, rowIndex, columnIndex }) {
    //   for (var j=0;j<this.wbl.length;j++)
    //   {
    //     for (var i=0;i<this.wbn.length;i++)
    //     {
    //       //第一列
    //       if (columnIndex === this.wbl[j]) {
    //         if (rowIndex  === this.wbn[i]) {
    //           return {
    //             rowspan: this.wbnum[i],
    //             colspan: 1
    //           };
    //         }
    //         if((parseInt(rowIndex) < (parseInt(this.wbn[i])+parseInt(this.wbnum[i])))&&(parseInt(rowIndex) > parseInt(this.wbn[i]))){
    //           return {
    //             rowspan: 0,
    //             colspan: 0
    //           };
    //         }
    //       }
    //
    //
    //     }
    //   }
    //
    // },



    /** 查询围蔽信息列表 */
    getList() {

      this.loading = true;
      listEnclosure().then(response => {
        //console.log(response);
        this.schemeList = response.rows;
        // this.schemeList[0].ssId=102;
        // this.schemeList[1].ssId=103;
        // this.schemeList[0].children[0].ssId=10211;
        // this.schemeList[0].children[1].ssId=10312
        // this.schemeList[0].children[0].ssProjectId=null;
        // this.schemeList[0].children[1].ssProjectId=null;


        //console.log(this.schemeList);

        this.projectList=[{name:"校园路翻新",id:"校园路翻新"},{name:"犀安路翻新",id:"犀安路翻新"},];
        //假数据
        this.builderList=[{name:"暂无",id:"暂无"},{name:"中铁一局",id:"中铁一局"},{name:"中铁二局",id:"中铁二局"}];

        // this.wbnum=[2,3,2],
        // this.wbn=[0,2,5],
        // this.schemeList=[
        //   {
        //     children:[
        //       {
        //
        //         ss:false,
        //         ssmap:true,
        //         ssId:0,
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
        //         ssBuilderName:null,
        //         ssProjectId: null,
        //         ssProjectName: null,
        //       },
        //       {
        //         ss:false,
        //         ssmap:true,
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
        //         ssBuilderName:null,
        //         ssProjectId: null,
        //         ssProjectName: null,
        //       }
        //     ],
        //     ss:true,
        //     ssmap:false,
        //     childrennum:2,
        //     ssId: 1,
        //     ssStatus: null,
        //     ssLane: null,
        //     ssStage: null,
        //     ssStartTime: null,
        //     ssEndTime: null,
        //     ssRange: null,
        //     ssProperties: null,
        //     // ssSuggessions: null,
        //
        //     // ssFilePath: null,
        //     ssBuilderName:'中铁一局',
        //     ssProjectId: 1,
        //     ssProjectName: '行陈路翻新',
        //     // ssBuilderId: 1,
        //     // ssTrafficStaffId: null,
        //     // ssDeleteFlag: null,
        //     // ssVerifyFlag: null,
        //     // ssVerifyDate: null,
        //     // ssUpdateFlag: null
        //   },
        //   {
        //     children:[
        //       {
        //         ss:false,
        //         ssmap:true,
        //         ssId:222,
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
        //         ssBuilderName:null,
        //         ssProjectId: null,
        //         ssProjectName: null,
        //       },
        //       {
        //         ss:false,
        //         ssmap:true,
        //         ssId:242,
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
        //         ssBuilderName:null,
        //         ssProjectId: null,
        //         ssProjectName: null,
        //       },
        //       {
        //         ss:false,
        //         ssmap:true,
        //         ssId:352,
        //         ssStatus: '修筑排水设施',
        //         ssLane: '左二、三车道',
        //         ssStage: '3',
        //         ssStartTime: '2020-12-14',
        //         ssEndTime: '2020-12-30',
        //         ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //         ssProperties: '全封闭',
        //         // ssSuggessions: null,
        //
        //         // ssFilePath: null,
        //         ssBuilderName:null,
        //         ssProjectId: null,
        //         ssProjectName: null,
        //       }
        //     ],
        //     ss:true,
        //     ssmap:false,
        //     ssId: 2,
        //     childrennum:3,
        //     ssStatus: null,
        //     ssLane: null,
        //     ssStage: null,
        //     ssStartTime: null,
        //     ssEndTime: null,
        //     ssRange: null,
        //     ssProperties: null,
        //     // ssSuggessions: null,
        //
        //     // ssFilePath: null,
        //     ssBuilderName:'中铁一局',
        //     ssProjectId: 2,
        //     ssProjectName: '校园路翻新',
        //     // ssBuilderId: 1,
        //     // ssTrafficStaffId: null,
        //     // ssDeleteFlag: null,
        //     // ssVerifyFlag: null,
        //     // ssVerifyDate: null,
        //     // ssUpdateFlag: null
        //   }
        // ],
        //   this.schemeList=[
        //     {
        //           ss:false,
        //           ssId:11,
        //           ssStatus: '修筑路基',
        //           ssLane: '左二、三车道',
        //           ssStage: '1',
        //           ssStartTime: '2020-11-14',
        //           ssEndTime: '2020-12-14',
        //           ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //           ssProperties: '全封闭',
        //           // ssSuggessions: null,
        //
        //           // ssFilePath: null,
        //       ssBuilderName:'中铁一局',
        //       ssProjectId: 1,
        //       ssProjectName: '行陈路翻新',
        //         },
        //         {
        //           ss:false,
        //           ssId:2,
        //           ssStatus: '修筑排水设施',
        //           ssLane: '左二、三车道',
        //           ssStage: '2',
        //           ssStartTime: '2020-12-14',
        //           ssEndTime: '2020-12-30',
        //           ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //           ssProperties: '全封闭',
        //           // ssSuggessions: null,
        //
        //           // ssFilePath: null,
        //           ssBuilderName:'中铁一局',
        //           ssProjectId: 1,
        //           ssProjectName: '行陈路翻新',
        //         },
        //
        //         {
        //           ssId:3,
        //           ssStatus: '修筑路基',
        //           ssLane: '左二、三车道',
        //           ssStage: '1',
        //           ssStartTime: '2020-11-14',
        //           ssEndTime: '2020-12-14',
        //           ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //           ssProperties: '全封闭',
        //           ssBuilderName:'中铁三局',
        //           ssProjectId: 2,
        //           ssProjectName: '校园路翻新',
        //
        //         },
        //         {
        //           ssId:22,
        //           ssStatus: '修筑排水设施',
        //           ssLane: '左二、三车道',
        //           ssStage: '2',
        //           ssStartTime: '2020-12-14',
        //           ssEndTime: '2020-12-30',
        //           ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //           ssProperties: '全封闭',
        //           ssBuilderName:'中铁一局',
        //           ssProjectId: 2,
        //           ssProjectName: '校园路翻新',
        //
        //         },
        //         {
        //           ssId:23,
        //           ssStatus: '修筑排水设施',
        //           ssLane: '左二、三车道',
        //           ssStage: '2',
        //           ssStartTime: '2020-12-14',
        //           ssEndTime: '2020-12-30',
        //           ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //           ssProperties: '全封闭',
        //           ssBuilderName:'中铁一局',
        //           ssProjectId: 2,
        //           ssProjectName: '校园路翻新',
        //
        //         },
        //     {
        //       ss:'false',
        //       ssId:31,
        //       ssStatus: '修筑路基',
        //       ssLane: '左二、三车道',
        //       ssStage: '1',
        //       ssStartTime: '2020-11-14',
        //       ssEndTime: '2020-12-14',
        //       ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //       ssProperties: '全封闭',
        //       // ssSuggessions: null,
        //
        //       // ssFilePath: null,
        //       ssBuilderName:'中铁一局',
        //       ssProjectId: 3,
        //       ssProjectName: '犀安路翻新',
        //     },
        //     {
        //       ss:'false',
        //       ssId:32,
        //       ssStatus: '修筑路基',
        //       ssLane: '左二、三车道',
        //       ssStage: '1',
        //       ssStartTime: '2020-11-14',
        //       ssEndTime: '2020-12-14',
        //       ssRange: '103.98622,30.755411;103.991016,30.759071;103.991177,30.758997;103.986296,30.755319;',
        //
        //       ssProperties: '全封闭',
        //       // ssSuggessions: null,
        //
        //       // ssFilePath: null,
        //       ssBuilderName:'中铁一局',
        //       ssProjectId: 3,
        //       ssProjectName: '犀安路翻新',
        //     },
        //   ],

        this.total = response.total;

        // this.total=2,
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    //否决取消
    close(){
      this.opendisa = false;
     this.Suggessions={
       projectid:null,
       sugge:null,
     };
    },
    // 表单重置
    reset() {
      this.form = {
        ssId: null,
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
        ssUpdateFlag: null
      };
      this.resetForm("form");
    },
    /** 下载附件按钮操作 */
    handleDownload(e){
      console.log(e);
    },
    /** 通过按钮操作 */
    handleAgree(e){
      console.log(e);
      this.$confirm('此操作将通过该围蔽方案, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '该围蔽方案成功设置为已通过!'
        });

        this.getList();
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消设置'
        });

      });
    },
    /** 否决按钮操作 */
    handleDisagree(e){
      console.log(e);
      this.Suggessions= {
        projectid:e.ssProjectId,
        sugge:null,
      }
      this.opendisa = true;
    },
    /** 否决确定操作 */
    handelConfirmReason() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
          this.msgSuccess("否决围蔽方案成功");
          this.opendisa = false;
        }
      })
    },
    /** 查看地图按钮操作 */
    handleMapview(e){
      this.mapVisible=true;
      this.epolygon=e.ssRange;
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
      getEnclosure(ssId).then(response => {
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
            updateEnclosure(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addEnclosure(this.form).then(response => {
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
          return delEnclosure(ssIds);
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
          return exportEnclosure(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    }
  }
};
</script>
