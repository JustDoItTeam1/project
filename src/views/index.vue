<template>
  <div >

  <div id="container" style="width: 100%;height: 800px">
  </div>
<!--  <el-dialog   :visible.sync="vformVisible" append-to-body>-->
<!--    <detailForm  ref="detailfrom" :msg="nomsg"></detailForm>-->
<!--  </el-dialog>-->
    <el-dialog title='施工项目详情' :visible.sync="vformVisible" width="500px" append-to-body>
      <el-form ref="projectForm" :model="projectListOne" :rules="rules" label-width="120px">
<!--        <el-form-item label="项目id"  >-->
<!--          <el-input v-model="projectListOne.projectId" :disabled="true"/>-->

<!--        </el-form-item>-->
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
        <el-button type="success"  @click="clickSeige" style="float: left" v-if="siegeVV" round>查看围蔽详情</el-button>
        <el-button type="success"  @click="clickSeigeAdd" style="float: left" v-if="addSiegeVV" round>新增围蔽方案</el-button>
        <el-button type="success"  @click="clickOverlaySearch" style="float: left"  round>查看项目标牌</el-button>
        <el-button type="primary" @click="submitProject">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>

      </div>
    </el-dialog>

    <div style="position: absolute;right: 1%;top:2%;">
      <el-button type="primary" @click="addMapSign" v-if="addSignV" >增加地图标牌</el-button>
<!--      v-hasPermi="['sign:map:add']"-->
    </div>
    <div style="position: absolute;right: 1%;top:2%;">
      <el-button type="success" @click="outSign" v-if="overlaySearchV">退出查看标牌</el-button>
    </div>


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
        <el-form-item>
          <el-button type="cyan" icon="el-icon-search" size="small" @click="handleQueryMap">画图搜索</el-button>
        </el-form-item>

<!--        <el-form-item style="position: absolute;left: 15%;top:60%;">-->
<!--          <el-radio-group v-model="radio">-->
<!--            <el-radio :label="1">施工项目</el-radio>-->
<!--            <el-radio :label="2">标牌</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
      </el-form>
    </div>

    <!--   画图搜索结果 标牌 项目-->


    <div style="position: absolute;left:1%;top: 9%;width: 270px;height:90%;" v-if="drawV">
      <!--     项目-->
      <el-scrollbar style="height: 100%">

<div>
      <el-card class="box-card" v-for="o in this.projectDrawList" :key="o" style="height: 90px">

        <div  class="text item">
          <el-button
            size="samll"
            type="text"
            @click="handleClickP(o)"

          > {{o.projectName}}</el-button>
        </div>
        <!--        <div  class="text item">-->
        <!--          {{o.ssProjectName}}-->
        <!--        </div>-->
        <div  class="text item">
          {{o.projectBuilderName}}
        </div>
      </el-card>

      </div>

<!--      标牌-->
      <div>
      <el-card class="box-card" v-for="o in this.signDrawList" :key="o" style="height: 90px" >

        <div  class="text item">
          <el-button
            size="samll"
            type="text"
            @click="handleClickSign(o)"

          > {{o.name}}</el-button>
        </div>
        <!--        <div  class="text item">-->
        <!--          {{o.ssProjectName}}-->
        <!--        </div>-->
        <div  class="text item">
          {{o.roadsection}}
        </div>
      </el-card>
      </div>

      </el-scrollbar>
<!--      <div style="position: absolute;left: 27%;top:100%;">-->
<!--        <el-pagination-->

<!--          layout="prev, pager, next"-->
<!--          :total="total"-->

<!--        >-->
<!--        </el-pagination>-->


<!--      </div>-->
    </div>

<!--    搜索结果 标牌-->


    <div style="position: absolute;left:1%;top: 9%;width: 270px;height:90%;" v-if="resultVS">
      <el-scrollbar style="height: 100%">
      <el-card class="box-card" v-for="o in this.searchSeige" :key="o" style="height: 90px" >

        <div  class="text item">
          <el-button
            size="samll"
            type="text"
            @click="handleClickSign(o)"

          > {{o.name}}</el-button>
        </div>
<!--        <div  class="text item">-->
<!--          {{o.ssProjectName}}-->
<!--        </div>-->
        <div  class="text item">
          {{o.roadsection}}
        </div>
      </el-card>
      </el-scrollbar>
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
<!--      <div style="position: absolute;left: 27%;top:100%;">-->
<!--       <el-pagination-->

<!--        layout="prev, pager, next"-->
<!--        :total="total"-->

<!--       >-->
<!--       </el-pagination>-->

<!--      </div>-->
    </div>

<!--    项目搜索结果-->
    <div  style="position: absolute;left:1%;top:9%;width: 270px;height:90%;" v-if="resultVP">
      <el-scrollbar style="height: 100%">
      <el-card class="box-card" v-for="o in this.searchProject" :key="o" style="height: 90px">

        <div  class="text item">
          <el-button
            size="samll"
            type="text"
            @click="handleClickP(o)"

          > {{o.projectName}}</el-button>
        </div>
        <!--        <div  class="text item">-->
        <!--          {{o.ssProjectName}}-->
        <!--        </div>-->
        <div  class="text item">
          {{o.projectBuilderName}}
        </div>
      </el-card>
      </el-scrollbar>

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
<!--      <div style="position: absolute;left: 27%;top:100%;">-->
<!--        <el-pagination-->

<!--          layout="prev, pager, next"-->
<!--          :total="total"-->

<!--        >-->
<!--        </el-pagination>-->

<!--      </div>-->
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
    <!--弹出标牌详情表格-->
    <el-dialog title='标牌信息详情' :visible.sync="signV" width="500px" :before-close="signClose" append-to-body>
      <el-form ref="projectForm" :model="signListOne"  label-width="120px">
        <el-form-item label="标牌名称"  >
          <el-select v-model="signListOne.name" placeholder="请输入标牌名称" clearable >
           <el-option v-for="item in signList"  :value="item.name" :label="item.name">{{item.name}}</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标识码" prop="projectName" v-if="delSignBut">
          <el-input v-model="signListOne.id" placeholder="请输入标识码" />
        </el-form-item>
        <el-form-item label="道路" prop="projectLocation">
          <el-input v-model="signListOne.roadsection" placeholder="请输入所在道路" />
        </el-form-item>
        <el-form-item label="具体位置" prop="projectLongLat">
          <el-input v-model="signListOne.detailedadd" placeholder="请输入标牌的具体位置" />
        </el-form-item>
        <el-form-item label="施工项目名称" prop="projectManger">
          <el-select v-model="signListOne.projectName" placeholder="请选择项目名称" clearable  >
            <el-option v-for="item in projectList"  :value="item.name" :label="item.name">{{item.name}}</el-option>
          </el-select>
<!--          <el-input v-model="signListOne.projectName" placeholder="请输入所属施工项目的名称" />-->
        </el-form-item>
        <el-form-item label="备注" prop="projectPhone">
          <el-input v-model="signListOne.remark" placeholder="请输入备注" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addSignCom" v-if="addSignBut">新增标牌</el-button>
        <el-button type="danger" @click="deleteSign" v-if="delSignBut" >删除该标牌</el-button>
<!--        v-hasPermi="['sign:map:delete']"-->
      </div>
    </el-dialog>

<!--    <el-dialog :visible.sync="signV" width="750px"  append-to-body title="标牌信息详情">-->
<!--      <el-form style="position: relative" :model="signListOne">-->
<!--        <div >-->
<!--          <br>-->
<!--          <label class="labelSeige" style="font-weight: 700">{{signListOne.name}}</label>-->
<!--          &lt;!&ndash;       <div style="position: absolute;left: 20%;">&ndash;&gt;-->
<!--          <label class="labelSeige" style="font-weight: 700">标识码:</label>-->
<!--          <label class="labelSeige" style="width: 560px">{{signListOne.id}}</label><br>-->
<!--          <label class="labelSeige" style="font-weight: 700;width: 120px">道路:</label>-->
<!--          <label class="labelSeige" style="width: 560px">{{signListOne.roadsection}}</label><br>-->
<!--          <label class="labelSeige" style="font-weight: 700;width: 120px">具体位置:</label>-->
<!--          <label class="labelSeige" style="width: 560px">{{signListOne.detailedadd}}</label><br>-->
<!--          <label class="labelSeige" style="font-weight: 700;width: 120px">施工项目名称:</label>-->
<!--          <label class="labelSeige" style="width: 560px">{{signListOne.projectName}}</label><br>-->
<!--          <label class="labelSeige" style="font-weight: 700;width: 120px">备注:</label>-->
<!--          <label class="labelSeige" style="width: 560px">{{signListOne.remark}}</label><br>-->
<!--          &lt;!&ndash;       </div>&ndash;&gt;-->
<!--        </div>-->
<!--      </el-form>-->
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
    <el-dialog width="80%"   :visible.sync="pointVisible2" append-to-body>
      <Ploygon v-if="pointVisible2" ref="Ploygon2" :msg="form.ssRange" @myfun="myf2"></Ploygon>
    </el-dialog>

    <!--新增-->
    <el-dialog
      :title="title"
      :visible.sync="open"
      width="600px"
      append-to-body
      @close="cancelseige"
      @opened="opendig"
     >
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="施工项目名称" prop="ssProjectName">
          <el-select v-model="addS.ssProjectId" placeholder="请选择项目名称" clearable size="small" >
            <el-option v-for="item in projectList"  :value="item.id" :label="item.name">{{item.name}}</el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="施工单位名称" prop="ssBuilderId">
          <el-select v-model="addS.ssBuilderId" placeholder="请选择施工单位名称" clearable size="small" >
            <el-option v-for="item in builderList"  :value="item.id" :label="item.name">{{ item.name }}</el-option>
          </el-select>
        </el-form-item>

        <el-tag
          :key="tag"
          v-for="tag in dynamicTags"
          closable
          :disable-transitions="false"

          @close="handleClose(tag)"
          @click="handleClick(tag)">
          {{tag}}
        </el-tag>
        <el-input
          class="input-new-tag"
          v-if="inputVisible"
          v-model="inputValue"
          ref="saveTagInput"
          size="small"
          @keyup.enter.native="handleInputConfirm"
          @blur="handleInputConfirm"
        >
        </el-input>
        <el-button v-else class="button-new-tag" size="small" @click="showInput">+围蔽阶段</el-button>

        <div  v-for="stagenum in addS.stage" v-show="stagenum.show" >
          <el-form-item label="围蔽阶段" prop="ssStage">
            <el-select v-model="stagenum.ssStage" placeholder="请选择围蔽阶段">
              <el-option label="1" value="1" />
              <el-option label="2" value="2" />
              <el-option label="3" value="3" />
              <el-option label="4" value="4" />
              <el-option label="5" value="5" />
              <el-option label="6" value="6" />
            </el-select>
          </el-form-item>
          <el-form-item label="围蔽状态" prop="ssStatus">
            <el-input v-model="stagenum.ssStatus" placeholder="请输入围蔽状态" />
          </el-form-item>
          <el-form-item label="围蔽车道" prop="ssLane">
            <el-input v-model="stagenum.ssLane" placeholder="请输入围蔽车道" />
          </el-form-item>

          <el-form-item label="开始时间" prop="ssStartTime">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="stagenum.ssStartTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择开始时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="结束时间" prop="ssEndTime">
            <el-date-picker clearable size="small" style="width: 200px"
                            v-model="stagenum.ssEndTime"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="选择结束时间">
            </el-date-picker>
          </el-form-item>
          <!--        <el-form-item label="围蔽区域(地图)" prop="ssRange">-->
          <!--          <el-input v-model="form.ssRange" placeholder="请输入围蔽区域(地图)" />-->
          <!--        </el-form-item>-->
          <el-form-item label="围蔽性质" prop="ssProperties">
            <el-select v-model="stagenum.ssProperties" placeholder="请选择围蔽性质">
              <el-option label="全封闭" value="全封闭" />
              <el-option label="半封闭" value="半封闭" />
            </el-select>
          </el-form-item>

          <el-form-item label="附件上传" >
            <el-upload
              ref="upload"
              class="upload-demo"
              action="http://localhost/dev-api/common/upload/"

              multiple
              :limit="1"
              :on-success="myUpload"
              :file-list="fileList">
              <el-button size="small" type="primary"  v-model="filen" >点击上传</el-button>
              <div slot="tip" class="el-upload__tip">只能上传1个文件，且不超过10MB</div>
            </el-upload>
          </el-form-item>

          <el-form-item label="围蔽区域(地图)" prop="ssRange">
            <el-input v-model="stagenum.ssRange" placeholder="请选择围蔽区域(地图)" />
            <el-button @click="mapshow2" type="primary" plain>选择围蔽区域</el-button>

          </el-form-item>


        </div>
        <div id="container1" style="width: 95%;height:210px;position:relative ">

        </div>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancelseige">取 消</el-button>
      </div>
    </el-dialog>


  </div>


</template>

<script>
  import {getProcessRemind} from "../api/login";
  import {addEnclosure} from "../api/enclosure/enclosure";
  import {getMapSign,delMapSign,addMapSign,MaplistSign,listSign} from "../api/sign/sign";
  import {getProject,listProject,updateProject,MaplistProject} from "../api/project/project";
import {getInfo} from "../api/login";
  import {listBuilder,getBuilder} from "@/api/account/builder";
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

      radio:1,

      //项目id
      //PId:null,
      //是否显示map
      mapVisible:false,
      //查询施工单位参数
      searchName: null,
      searchQueryParams: {
        //seigeName:null,
        projectInfo:null,
      },
      searchQueryParamsMap: {
        //seigeName:null,

        longitudeAndLatitude:null,
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
      signListOne:{},


      //新增围蔽窗口
      addSiegeV:false,
      addSiegeVV:false,
      //围蔽详情弹窗
      siegeV:false,
      siegeVV:false,

      // 施工项目表格数据
      projectList: [],
      // 施工项目表格数据
      signList: [],

      ploy1:[],
      ploy2:[],
      temp:[],
      path:[],
      lng:[],
      vformVisible:false,
      // formVisible:false,
      nomsg:{},

      maps:null,
      maps2:null,
      mouseTool:null,

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
      },

      markerList:null,
      signV:false,
      addSignBut:false,
      delSignBut:false,

      overlaysMarker:[],

      markerClick:false,
      markerNew:null,

      addSignV:true,
      overlaySearchV:false,
      overlaySearch:null,
      //地图画框搜索结果
      projectDrawList:[],
      signDrawList:[],
      //地图画框搜索显示
      drawV:false,


      //目前正在填写的阶段数
      a:0,
      filen:null,
      fileList:[],
      overlayGroup2:null,
      authenticate:null,


      overlayGroup3:null,

      // 是否显示弹出层
      open: false,
      //新增围蔽的tag
      dynamicTags: ['阶段1', '阶段2', '阶段3'],
      inputVisible: false,
      inputValue: '',
      addS:{
        ssProjectId: null,
        //builderList: null,
        stage:[
          {
            ssStatus: null,
            ssLane: null,
            ssStage: 1,
            ssStartTime: null,
            ssEndTime: null,
            ssRange: null,
            ssProperties: null,
            show:true,
            ssFilePath:null,
          },
          {
            ssStatus: null,
            ssLane: null,
            ssStage: 2,
            ssStartTime: null,
            ssEndTime: null,
            ssRange: null,
            ssProperties: null,
            show:false,
            ssFilePath:null,
          },
          {
            ssStatus: null,
            ssLane: null,
            ssStage: 3,
            ssStartTime: null,
            ssEndTime: null,
            ssRange: null,
            ssProperties: null,
            show:false,
            ssFilePath:null,
          }],
      },
      // 表单参数
      form: {},

      // 施工单位表格数据
      builderList: [],
      //传递给子组件mapview的地图ploygon
      epolygon:"",
      //是否显示ploygon
      pointVisible2:false,

      geocoder:null,
    };
  },
  created() {
    this.getbuilder();
    this.getproject();
    this.getSignList();
    getInfo().then(response => {
      if(response.user.authenticate == 4) {
        this.authenticate=4;
        this.addSignV = false;
      }
    })
    getProcessRemind().then(response => {
      if(response.data){
        this.$notify({
          title: '提示',
          message: '请填写施工项目：\r\n'+response.data+'的施工进度',
          type: 'warning',
          duration: 0
        });
        //console.log(response);
      }
    })
    // getProcessRemind().then(response => {
    //  if(response.data){
    //    this.$notify({
    //      title: '提示',
    //      message: '请填写施工项目：\r'+response.data+'的施工进度',
    //      duration: 0
    //    });
    //    console.log(response);
    //  }
    //
    //
    //   })
  },
  // watch:{
  //   mouseTool(newval){
  //     this.mouseTool.on('draw',function(e){
  //       this.overlaysMarker=e;
  //     })
  //     //this.mouseTool.marker()
  //     console.log(this.overlaysMarker);
  //   }
  // },
  watch:{
    pointVisible2(newVal) {
      if((newVal==false)&&(this.open==true)){


        console.log("this.pointB");
        //this.addS.stage[this.a].ssRange

        this.overlayGroup3.clearOverlays();
        // for(var m=0;m<this.addS.length;m++){
        //   //if(this.addS.stage[m].ssRange!=null)
        //   this.createPolygon(this.maps,this.addS.stage[m].ssRange,this.overlayGroup2);
        //
        // }
        //this.centerp=new AMap.LngLat(104.07, 30.67);

        console.log(this.addS.stage)
        this.createPolygon2(this.maps2,this.addS.stage,this.overlayGroup3,this.center);
        // this.createPolygon(this.maps,this.addS.stage[this.a].ssRange,this.overlayGroup2);

        this.maps2.add(this.overlayGroup3);
        this.overlayGroup3.show();
        // console.log(this.pointA);


        //this.maps.setCenter(this.centerp);
        //console.log(newVal);
      }

    }
  },
  methods: {

    /** 查询施工单位列表 ！！！！！！！*/
    getbuilder() {
      getInfo().then(response => {
        //console.log(response.user.authenticate)
        if (response.user.authenticate == 4) {
          this.builderList.push({name: response.user.userName, id: response.user.builderId});
        }
        else
        {
          // this.builderList=[{name:"暂无",id:"暂无"},{name:"中铁一局",id:"中铁一局"},{name:"中铁二局",id:"中铁二局"}];
          // console.log(this.builderList)
          listBuilder(this.queryParams).then(response => {
            //this.builderList=[{name:"暂无",id:0}];
            for ( let i of response.rows) {
              this.builderList.push({name:i.builderUsername,id:i.builderId});
            }
          });
        }

      });
    },

    mapshow2(){
      this.pointVisible2=true;
    },
    myf2(ms) {
      this.addS.stage[this.a].ssRange=ms;
      this.pointVisible2=false;
      // console.log(this.form.ploygon);
    },
    myf(ms) {
      this.addS.stage[this.a].ssRange=ms;
      this.pointVisible=false;
      // console.log(this.form.ploygon);
    },

    handleClick(tag) {
      // this.maps.destroy();

      this.a=tag.charAt(tag.length-1)-1;
      // if(tag=="阶段1"){
      //   this.stage.stage1.show=true;
      // }
      // if(tag=="阶段4"){

      // for (var i=0;i<a;i++)
      // {
      //   document.write(cars[i] + "<br>");
      // }


      for (var i=0;i<this.addS.stage.length;i++)
      {
        this.addS.stage[i].show=false;
      }
      this.addS.stage[this.a].show=true;

      // var map = new AMap.Map(document.getElementById("container"));
      // this.opendig();
      // this.maps = new AMap.Map('container', {
      //   //mapStyle:'amap://styles/797343a394a721796989e608aaeff24d', //设置地图的显示样式
      //   center: [104.07, 30.67],
      //   resizeEnable: true,
      //   expandZoomRange: true,
      //   zooms: [3, 20],
      //   zoom:15,
      // });
      // // }
      console.log(this.a)
      console.log(this.addS.stage[this.a])
    },
    myUpload(response, file, fileList){
      console.log(response);
      this.addS.stage[this.a].ssFilePath=response.fileName;
    },
    /** 提交按钮 */
    submitForm() {
       this.$refs["form"].validate(valid => {
         if (valid) {
           //     if (this.form.ssId != null) {
           //       updateEnclosure(this.form).then(response => {
           //         if (response.code === 200) {
           //           this.msgSuccess("修改成功");
           //           this.open = false;
           //           this.getList();
           //         }
           //       });
           //     } else {
           console.log(this.addS);
           addEnclosure(this.addS).then(response => {
             if (response.code === 200) {
               this.msgSuccess("新增成功");
               this.open = false;
               this.getList();
               //清除
               this.addS={
                 ssProjectId: null,
                 //builderList: null,
                 stage:[
                   {
                     ssStatus: null,
                     ssLane: null,
                     ssStage: 1,
                     ssStartTime: null,
                     ssEndTime: null,
                     ssRange: null,
                     ssProperties: null,
                     show:true,
                   },
                   {
                     ssStatus: null,
                     ssLane: null,
                     ssStage: 2,
                     ssStartTime: null,
                     ssEndTime: null,
                     ssRange: null,
                     ssProperties: null,
                     show:false,
                   },
                   {
                     ssStatus: null,
                     ssLane: null,
                     ssStage: 3,
                     ssStartTime: null,
                     ssEndTime: null,
                     ssRange: null,
                     ssProperties: null,
                     show:false,
                   }],
               }
               this.dynamicTags=['阶段1', '阶段2', '阶段3'];

             }
           });
         }
       //}
        //}
    });
    },



    cancelseige(){
      //console.log('11111')
      this.open = false;
      this.fileList=[];


      //清除
      this.addS={
        ssProjectId: null,
        //builderList: null,
        stage:[
          {
            ssStatus: null,
            ssLane: null,
            ssStage: 1,
            ssStartTime: null,
            ssEndTime: null,
            ssRange: null,
            ssProperties: null,
            show:true,
            ssFilePath:null,
          },
          {
            ssStatus: null,
            ssLane: null,
            ssStage: 2,
            ssStartTime: null,
            ssEndTime: null,
            ssRange: null,
            ssProperties: null,
            show:false,
            ssFilePath:null,
          },
          {
            ssStatus: null,
            ssLane: null,
            ssStage: 3,
            ssStartTime: null,
            ssEndTime: null,
            ssRange: null,
            ssProperties: null,
            show:false,
            ssFilePath:null,
          }],
      }
      console.log(this.addS)
      this.dynamicTags=['阶段1', '阶段2', '阶段3'];
      //this.$refs['upload'].clearFiles();

    },

    handleInputConfirm() {
      let inputValue = this.inputValue;
      if (inputValue) {
        this.dynamicTags.push(inputValue);
        // this.stage.push(this.add);
      }
      this.inputVisible = false;
      this.inputValue = '';

      // let inputV = this.stage.length+1;
      //
      // this.dynamicTags.push("阶段"+inputV);


      for (var i=0;i<this.addS.stage.length;i++)
      {
        // console.log(i)
        this.addS.stage[i].show=false;
        console.log(this.addS.stage[i])
      }

      //console.log(this.stage)
      // this.add.show=true;
      // this.add.ssStage=inputValue;
      this.addS.stage.push(Object.assign({},this.add));
      this.addS.stage[this.addS.stage.length-1].show=true;
      this.addS.stage[this.addS.stage.length-1].ssStage=this.addS.stage.length;
      //console.log(this.stage.length)
      //console.log(this.stage)
      // this.inputVisible = false;
      // this.inputValue = '';

    },

    showInput() {
      //  this.inputVisible = true;
      //  this.$nextTick(_ => {
      //    this.$refs.saveTagInput.$refs.input.focus();
      // });

      let inputV = this.addS.stage.length+1;
      this.dynamicTags.push("阶段"+inputV);

      for (var i=0;i<this.addS.stage.length;i++)
      {
        // console.log(i)
        this.addS.stage[i].show=false;
        console.log(this.addS.stage[i])
      }

      //console.log(this.stage)
      // this.add.show=true;
      // this.add.ssStage=inputValue;
      this.addS.stage.push(Object.assign({},this.add));
      this.addS.stage[this.addS.stage.length-1].show=true;
      this.addS.stage[this.addS.stage.length-1].ssStage=this.addS.stage.length;
    },


    handleClose(tag) {
      this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
      console.log(tag.charAt(tag.length-1)-1);
      console.log( this.addS.stage[tag.charAt(tag.length-1)-1])
      this.addS.stage[tag.charAt(tag.length-1)-1].show=false;
      this.addS.stage[0].show=true;
      //delete this.addS.stage[tag.charAt(tag.length-1)-1];
    },

    opendig(){
      this.maps2 = new AMap.Map('container1', {
        //mapStyle:'amap://styles/797343a394a721796989e608aaeff24d', //设置地图的显示样式
        center: [104.07, 30.67],
        resizeEnable: true,
        expandZoomRange: true,
        zooms: [3, 20],
        zoom:15,
      });
      this.overlayGroup3 = new AMap.OverlayGroup();

      console.log(this.builderList);
      this.addS.ssProjectId=this.projectListOne.projectId;

      for(var i=0;i<this.builderList.length;i++)
      {
        //console.log(this.builderList[i].name)
        //console.log(this.projectBuilderName)
        if(this.builderList[i].name==this.projectListOne.projectBuilderName){
          this.addS.ssBuilderId=this.builderList[i].id;
          //console.log("yicivi")
        }

      }
    },

    /** 查询项目单位列表 ！！！！！！！*/
    getproject() {
      // this.projectList=[{name:"校园路翻新",id:"校园路翻新"},{name:"犀安路翻新",id:"犀安路翻新"},];
      // console.log(this.builderList)
      listProject().then(response => {
        //this.projectList=[{name:"暂无",id:0}];
        for(let i of response.rows){
          this.projectList.push({name:i.projectName,id:i.projectId});
        }
      });
    },

    /** 查询标牌列表 ！！！！！！！*/
    getSignList() {
      // this.projectList=[{name:"校园路翻新",id:"校园路翻新"},{name:"犀安路翻新",id:"犀安路翻新"},];
      // console.log(this.builderList)
      listSign().then(response => {
        //this.projectList=[{name:"暂无",id:0}];
        for(let i of response.rows){
          this.signList.push({name:i.name});
        }
      });
    },


     // / ** 点击搜索结果查看详情操作 */
    handleClickSign(o){
    //console.log(o)
    this.signV=true;
    this.addSignBut=false;

      if(this.authenticate == 4) {
        this.delSignBut=false;
      }
      else {
        this.delSignBut=true;
      }



    this.signListOne= o;
    //console.log(this.projectListOne);
    this.maps.setCenter(new AMap.LngLat(this.signListOne.longitude,this.signListOne.latitude));
    this.maps.setZoom(17);
    // this.siegeV = true;
    //
    // //将object转化为array
    // var myArray=new Array()
    // myArray.push(o);
    // this.schemeListOne= myArray;
  },


    addSignCom(){
      console.log(this.signListOne);
      delete this.signListOne["id"];
      console.log(this.signListOne);
      addMapSign(this.signListOne).then(response => {
        if (response.code === 200) {
          this.$message({
            type: 'success',
            message: '该标牌新增成功!'
          });
          this.initMarker();
          this.markerNew.hide();
          this.signV=false;

        }

      });
    },
    signClose(){
      this.signListOneRes();
      if (this.markerNew!=null){
        this.maps.remove(this.markerNew);
      }
      this.signV=false;
      this.markerNew=null;
    },



    signListOneRes(){
      this.signListOne={
        id:null,
        name:null,
        projectName:null,
        roadsection:null,
        detailedadd:null,
        remark:null,
      }
    },
    draw(){
      this.mouseTool.polygon({
        fillColor:'#00b0ff',
        strokeColor:'#80d8ff'
        //同Polyline的Option设置

        //同Marker的Option设置
      });
    //
    //
      },
    // pullMarker(e){
    //   console.log(e);
    //
    // },
    handleQueryMap(){
      this.mouseTool.close(true);
      this.draw()
    },
    addMapSign(){
      this.markerClick=true;
      // this.signListOneRes();
      // this.draw()
      //this.mouseTool.close(false);

      //this.mouseTool.marker()


      // this.signV=true;
      // this.addSignBut=true;
      // this.delSignBut=false;
    },
    deleteSign(){

          // console.log(e);
          this.$confirm('是否确认删除标识码为"' + this.signListOne.id + '"的数据项?', '提示', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
          }).then(() => {

              delMapSign(this.signListOne.id).then(response => {
                if (response.code === 200) {
                  this.$message({
                    type: 'success',
                    message: '该标牌已删除!'
                  });

                  this.initMarker();
                  this.signV=false;

                }

              });

            // this.getList();
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '已取消删除'
            });

          });

    },

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
      this.pointVisible=false;
      this.projectListOne.projectLongLat=ms;
    },

    outSign(){
      this.overlayGroup1.show();
      this.overlaySearch.hide();
      this.overlaySearchV=false;
      this.addSignV=true;
    },

    clickOverlaySearch(){
      this.overlaySearchV=true;
      this.addSignV=false;
      this.vformVisible=false;
      this.overlayGroup1.hide();
      console.log(this.projectListOne);
      this.searchQueryParams.projectInfo=this.projectListOne.projectId;
      getProject(this.projectListOne.projectId).then(response => {
        console.log(response);
        //console.log(response.data.signInfoVoList.length)
        if(response.data.signInfoVoList.length==0)
        {
          this.$message({
            message: '该项目暂无标牌',
            type: 'warning'
          });
        }

          this.overlaySearch = new AMap.OverlayGroup();
          this.overlaySearch.clearOverlays();
          this.createSign(this.maps,response.data.signInfoVoList,this.overlaySearch);
          this.maps.add(this.overlaySearch);
          this.overlaySearch.show();


        // overlaySearch

      })

      //this.overlayGroup2.hide();


    },

    /** 点击搜索结果查看详情操作 */
    handleClickP(o){


      console.log(o)
      this.projectListOne=o;
      this.searchIdSeige();
      this.vformVisible=true;
      this.projectListOne= o;
      //console.log(this.projectListOne);
      this.maps.setCenter(this.projectListOne.marker.getPosition());
      this.maps.setZoom(17);
      //this.searchIdSeige();
      // this.siegeV = true;
      //is
      // //将object转化为array
      // var myArray=new Array()
      // myArray.push(o);
      // this.schemeListOne= myArray;
    },
    /** 搜索按钮操作 */
    handleQuery() {

      this.mouseTool.close(true);
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
      console.log(this.searchName)
      if(this.searchName!=null){
        this.searchQueryParams.projectInfo=this.searchName;
        // this.searchQueryParams.pageNum=1;
        // this.searchQueryParams.pageSize=6;

        listProject(this.searchQueryParams).then(response => {
          if((response.msg=="项目查询成功")||(response.msg=="查询成功")){
            console.log(response)
            this.createPolygon(this.maps,response.rows,this.overlayGroup2);
            this.searchProject=response.rows;
            this.total=response.total;
            this.resultVP=true;
            this.resultVS=false;
            this.drawV=false;
            //console.log(this.searchProject)
          }
          if(response.msg=="标牌查询成功"){
            console.log(response)
            //this.createPolygon(this.maps,response.rows,this.overlayGroup2);
            this.searchSeige=response.rows;
            this.total=response.total;
            this.resultVS=true;
            this.resultVP=false;
            this.drawV=false;
            //console.log(this.searchProject)
          }

        });
      }




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
          reviewEnclosure(this.schemeListOne.ssProjectId,this.Suggessions).then(response => {
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

      this.$refs['elForm'].validate(valid => {
        if (valid) {
          // console.log(this.getCookie("username"));
          //this.Suggessions.suggestion=getQueryString(this.Suggessions.suggestion,"utf8");
          // console.log(this.Suggessions.suggestion)
          reviewEnclosure(this.schemeListOne.ssProjectId,this.Suggessions).then(response => {
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
    //生成全部标牌
    createSign:function(map,ployg,overlayGroup){

      // // 创建一个 Icon
      // var xs30 = new AMap.Icon({
      //   // 图标尺寸
      //   //size: new AMap.Size(40, 50),
      //   size: new AMap.Size(30, 30),
      //   // 图标的取图地址
      //   //image: 'https://gaode.com/assets/img/poi-marker.png',
      //   image: require("@/assets/markerPhoto/限速30.png"),
      //
      //   // // 图标所用图片大小
      //   imageSize: new AMap.Size(30, 30),
      //   // // 图标取图偏移量
      //   //imageOffset: new AMap.Pixel(-50, -10)
      // });

      var temp = [];
      var path = [];
      var ploy2 = [];
      var iconType="";
      for(var i=0;i<ployg.length;i++)
      {
        var pt=new AMap.LngLat(ployg[i].longitude,ployg[i].latitude)


        var marker = new AMap.Marker({
          position:pt,
          title: ployg[i].name,
          offset: new AMap.Pixel(0, 0),
          anchor:'center',
        });
        if(ployg[i].name=="限速30"){marker.setIcon(require("@/assets/markerPhoto/限速30.png"))}
        if(ployg[i].name=="限速20"){marker.setIcon(require("@/assets/markerPhoto/限速20.png"))}
        if(ployg[i].name=="禁止车辆临时或长时停放"){marker.setIcon(require("@/assets/markerPhoto/禁止车辆临时或者长时停放.png"))}
        if(ployg[i].name=="禁止鸣喇叭"){marker.setIcon(require("@/assets/markerPhoto/禁止鸣笛.png"))}

        marker.content =i;
        // marker.setLabel({
        //   offset: new AMap.Pixel(0, 5),  //设置文本标注偏移量
        //   content:ployg[i].name, //设置文本标注内容
        //   direction: 'bottom' //设置文本标注方位
        // });
        marker.on('click', signmarkerClick);
        overlayGroup.addOverlay(marker);

        var that =this;
        function signmarkerClick(e) {
          //console.log(e);

          that.maps.setCenter(e.lnglat);
          that.maps.setZoom(17);
          that.signV=true;
          that.addSignBut=false;
          if(that.authenticate == 4) {
            that.delSignBut=false;
          }
          else {
            that.delSignBut=true;
          }
         // that.delSignBut=true;
          console.log(that.signListOne);
          console.log(that.ployg[parseInt(e.target.content)]);
          that.signListOne= that.markerList[parseInt(e.target.content)];

          //将object转化为array
          // var myArray=new Array()
          // myArray.push(that.ployg2[parseInt(e.target.content)]);

         // that.searchIdSeige();
          //that.searchQueryParamsSeige.projectId=that.projectListOne.projectId;

          // // 数据脱绑
          // that.nomsg=JSON.parse(JSON.stringify(e.target.cont));
          //   var t=document.getElementById("txt");
          //  t.value=that.nomsg.id
        }

      }

        ////////////////////////////////////////////////////////////////////////
        // map.add(polygon);





    },
    createPolygon2:function(map,ployg,overlayGroup,centerp){
      overlayGroup.clearOverlays();
      var temp = [];
      var path = [];
      var ploy2 = [];

      var a1=0;
      var b1=0;
      var colorP=['red','green','yellow','white','black','blue']
      for(var i=0;i<ployg.length;i++)
      {
        //ployg[i].projectLongLat可能是空的
        if(ployg[i].ssRange!=null){
          temp[i]=ployg[i].ssRange.split(';');
        }

      }
      var a=0;
      var b=0;
      // 生成四边形
      for(var i=0;i<temp.length;i++)
      {

        path = [];
        //  console.log(this.path)

        for(var j=0;j<temp[i].length-1;j++)
        {

          ploy2=temp[i][j].split(',');
          a=parseFloat(a)+parseFloat(ploy2[0]);
          b=parseFloat(b)+parseFloat(ploy2[1]);
          // a1=parseFloat(a1)+parseFloat(ploy2[0]);
          // b1=parseFloat(b1)+parseFloat(ploy2[1]);
          path.push(new AMap.LngLat(ploy2[0]-0,ploy2[1]-0));

        }
        a=a/(temp[i].length-1);
        b=b/(temp[i].length-1);
        // a1=a1/(temp[i].length-1);
        //b1=b1/(temp[i].length-1);
        console.log("a"+a);
        console.log("b"+b);

        // console.log("a1:"+a1);
        // console.log("b1:"+b1);


        var polygon = new AMap.Polygon({
          path:path,
          fillColor: '#fff', // 多边形填充颜色
          borderWeight: 2, // 线条宽度，默认为 1
          strokeColor: colorP[i], // 线条颜色
        });
        overlayGroup.addOverlay(polygon);
      }
      console.log("a"+a);
      console.log("b"+b);
      // console.log("a1:"+a1);
      // console.log("b1:"+b1);
      var pt=new AMap.LngLat(a,b)
      // centerp=pt
      // console.log(centerp)
      if(temp.length==1)
        map.setCenter(pt);
    },

    //生成施工项目全部polygon
    createPolygon:function(map,ployg,overlayGroup){
      var redIcon = new AMap.Icon({
        // 图标尺寸
        //size: new AMap.Size(40, 50),
        size: new AMap.Size(30, 30),
        // 图标的取图地址
        //image: 'https://gaode.com/assets/img/poi-marker.png',
        image: require("@/assets/markerPhoto/project2.png"),

        // // 图标所用图片大小
        imageSize: new AMap.Size(30, 30),
        // // 图标取图偏移量
        //imageOffset: new AMap.Pixel(-50, -10)
      });

      // var marker = new AMap.Marker({
      //   position:[103.981629,30.762099],
      //
      //   icon:redIcon,
      //   anchor:'bottom-center',
      // });
      // overlayGroup.addOverlay(marker);

      var temp = [];
      var path = [];
      var ploy2 = [];
      for(var i=0;i<ployg.length;i++)
      {
        //ployg[i].projectLongLat可能是空的
        if(ployg[i].projectLongLat!=null){
          temp[i]=ployg[i].projectLongLat.split(';');
        }

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
          title: ployg[i].projectName,
          icon:redIcon,
          offset: new AMap.Pixel(5, 10),
          anchor:'bottom-center',
        });
        marker.content =i;
        //marker.content={};
        //marker.cont=ployg[i];
        marker.setLabel({
          offset: new AMap.Pixel(0, 5),  //设置文本标注偏移量
          content:ployg[i].projectName, //设置文本标注内容
          direction: 'bottom' //设置文本标注方位
        });


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

     this.geocoder = new AMap.Geocoder({
        // city 指定进行编码查询的城市，支持传入城市名、adcode 和 citycode
        city: '028'
      })

      var that=this;



      this.maps.on('click', function(e) {

        console.log(e)
        if(that.markerClick==true){
          console.log(e.lnglat.getLng() + ',' + e.lnglat.getLat());
          var pt=new AMap.LngLat(e.lnglat.getLng(),e.lnglat.getLat())

          that.markerNew = new AMap.Marker({
            position:pt,
          });
          that.maps.add(that.markerNew);



          setTimeout(function(){
            that.signListOneRes();
            that.signV=true;
            that.geocoder.getAddress(pt, function(status, result) {
              if (status === 'complete' && result.info === 'OK') {
                // result为对应的地理位置详细信息
                that.signListOne.roadsection=result.regeocode.addressComponent.street;
                that.signListOne.detailedadd=result.regeocode.addressComponent.street;
              }
            })
            that.signListOne.latitude=e.lnglat.getLat();
            that.signListOne.longitude=e.lnglat.getLng();

            that.addSignBut=true;
            that.delSignBut=false;
            that.markerClick=false;

            }, 1000);

        }


      });


      this.mouseTool = new AMap.MouseTool(this.maps);
      this.mouseTool.on('draw',function(e){
        // //初始化
        // this.overlaysMarker=[];
        // // this.searchQueryParamsMap={
        // //   //seigeName:null,
        // //
        // //   longitudeAndLatitude:null,
        // // },
        // console.log(e.obj.Ce.path);
        // this.overlaysMarker.push(e.obj.Ce.path);
        // console.log(this.overlaysMarker);


        var longlat="";

        for(var i=0;i<e.obj.Ce.path.length;i++){
          longlat=longlat+e.obj.Ce.path[i].lng;
          longlat=longlat+","+e.obj.Ce.path[i].lat;
          longlat=longlat+"!"
          console.log("1");
          //this.overlaysMarker[i]=longlat;

        }
        console.log(longlat);
        that.searchQueryParamsMap.longitudeAndLatitude=longlat;
        that.mouseTool.close(false);
        MaplistProject(that.searchQueryParamsMap).then(response => {
          if (response.code === 200) {
            //console.log(that.drawV)
            that.projectDrawList=response.data;
            console.log(that.projectDrawList);
            that.drawV=true;
            that.resultVP=false;
            that.resultVS=false;
          }
        });
        MaplistSign(that.searchQueryParamsMap).then(response => {
          if (response.code === 200) {
            that.signDrawList=response.rows;
            //console.log(that.signDrawList)
            that.drawV=true;
            that.resultVP=false;
            that.resultVS=false;
            //console.log(that.drawV)
          }
        });

      })
      //this.mouseTool.on('draw',this.pullMarker(e));

      this.overlayGroup1 = new AMap.OverlayGroup();
      this.overlayGroup2 = new AMap.OverlayGroup();

      //获得每个四边形图层
       //this.createPolygon(this.maps,this.ployg2,this.overlayGroup2);
      //this.createSiegePolygon(this.maps,this.ployg1,this.overlayGroup1);

      //this.maps.add(this.overlayGroup1);
      //this.maps.add(this.overlayGroup2);

    },
    //下载附件
    handleDownload:function () {
      // var downloadQuery={fileName:this.schemeListOne.children[0].ssFilePath}
      // // downloadEnclosure(downloadQuery).then(response => {
      //   //const blob = new Blob([response], { type: 'text/plain;charset=utf-8' })
      //   //saveAs(blob, this.schemeListOne.children[0].ssFilePath)
      //   // this.download(response.msg);
      //   // if (response.code === 200) {
      //   //   this.msgSuccess("修改成功");
      //   //
      //   // }
      // // });
      // return downloadEnclosure(downloadQuery);
      //上线记得改url！！！！！！！！！！！！！！！！！！！！！
      window.location.href= "http://localhost/dev-api/common/download?fileName=" + encodeURI(this.schemeListOne.children[0].ssFilePath);
    },

    //新增围蔽
    clickSeigeAdd:function(){
      this.open=true;
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
        //console.log(this.projectListOne)

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
            this.vformVisible=false;

        }
      });
    },
    //项目提交取消
    cancel() {
      this.vformVisible= false;

    },
    initMarker(){
      getMapSign().then(response => {
        this.overlayGroup1.clearOverlays();
        this.markerList=response.rows;
        this.createSign(this.maps,this.markerList,this.overlayGroup1);
        this.maps.add(this.overlayGroup1);
        this.overlayGroup1.show();
      })
    },

  },

  mounted() {


    this.init();

    getMapSign().then(response => {
      this.markerList=response.rows;
      this.createSign(this.maps,this.markerList,this.overlayGroup1);
      this.maps.add(this.overlayGroup1);
      this.overlayGroup1.show();
    }),

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
