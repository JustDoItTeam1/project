<template>
  <!-- 添加或修改围蔽方案信息对话框 -->
  <div>
    <el-form ref="form" :model="form"  label-width="80px">

      <el-form-item label="项目id" prop="projectid">
        <el-input v-model="form.projectid" placeholder="请输入项目id" />
      </el-form-item>

      <el-form-item label="围蔽状态" prop="enclosurestatus">
        <el-input v-model="form.enclosurestatus" placeholder="请选择围蔽状态"/>
      </el-form-item>

      <el-form-item label="围蔽阶段" prop="phaseid">
        <el-select v-model="form.phaseid" placeholder="请输入围蔽阶段" clearable size="small">
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
                        v-model="form.startDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker clearable size="small" style="width: 200px"
                        v-model="form.endDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="选择结束时间">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="围蔽区域" prop="ploygon">
        <el-input v-model="form.ploygon" placeholder="请输入围蔽区域" />
        <el-button @click="mapshow" type="primary" plain>选择围蔽区域</el-button>
      </el-form-item>
      <el-form-item label="围蔽车道" prop="lane">
        <el-input v-model="form.lane" placeholder="请输入围蔽车道" />
      </el-form-item>
      <el-form-item label="里程桩号" prop="enclosurelenght">
        <el-input v-model="form.enclosurelenght" placeholder="请输入里程桩号" />
      </el-form-item>
      <el-form-item label="封闭性质" prop="enclosureproperty">
        <el-select v-model="form.enclosureproperty" placeholder="请选择封闭性质">
          <el-option label="全封闭" value="全封闭" />
          <el-option label="半封闭" value="半封闭" />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitForm">确 定</el-button>
      <el-button @click="cancel">取 消</el-button>
    </div>

    <!--ploygon子组件，选择地图中位置-->
    <el-dialog width="80%"   :visible.sync="pointVisible" append-to-body>
      <Ploygon v-if="pointVisible" ref="Ploygon" :msg="form.ploygon" @myfun="myf"></Ploygon>
    </el-dialog>



  </div>
</template>

<script>
  import Ploygon from "../../test/components/Ploygon";
  import {addEnclosure,updateEnclosure} from "../../../api/map/enclosure";

  export default {
        name: "addEdit",
      components:{Ploygon},
      props:['msg'],
      data() {
        return {
          //是否显示ploygon
          pointVisible:false,
          form:{},
        }
      },
      created() {
          this.form=this.msg;
        console.log(this.msg);

        console.log(this.form);
      },
      methods:{

        // 取消按钮
        cancel() {
          this.open = false;
          this.reset();
        },

        // 地图选择子组件显示
        mapshow(){
          this.pointVisible=true;
        },

        //给父组件传值
        updateL(){
          this.$emit('updateL',true);
        },

        // 获取子组件ploygon传来的值
        myf(ms) {
          this.form.ploygon=ms;
         // console.log(this.form.ploygon);
        },


        /** 提交按钮 */
        submitForm() {

          this.$refs["form"].validate(valid => {
            if (valid) {
              if (this.form.id != null) {
                updateEnclosure(this.form).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("修改成功");
                    this.open = false;
                    //this.getList();
                    this.updateL();
                  }
                });
              } else {
                addEnclosure(this.form).then(response => {
                  if (response.code === 200) {
                    this.msgSuccess("新增成功");
                    this.open = false;
                    //this.getList();
                    this.updateL();
                  }
                });
              }
            }
          });
        },




      }



    }
</script>

<style scoped>

</style>
