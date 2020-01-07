<template>
  <div style="height: 100%;width: 100%;">
    <!--查询的表单区域-->
    <el-form style="width: 100%;margin-top: 10px;" :inline="true">
      <el-form-item>
        <el-input v-model="formIDSearch.lid" placeholder="ID搜索" style="border: 1px solid #262cab"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleIDSearch"></el-button>
      </el-form-item>
      <el-form-item style="margin-left: 100px;">
          <el-date-picker v-model="formTIMESearch" type="datetimerange" range-separator="至" start-placeholder="开始日期"
          end-placeholder="结束日期" align="right" style="border: 1px solid #262cab" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleTIMESearch"></el-button>
      </el-form-item>
    </el-form>
    <!--表格区域-->
    <el-table :data="tableData" height="100%" style="width: 100%" v-loading="loading">
      <el-table-column prop="id" label="日志编号" sortable></el-table-column>
      <el-table-column prop="operator_id" label="操作者" sortable></el-table-column>
      <el-table-column prop="action" label="操作"></el-table-column>
      <el-table-column prop="target_kind" label="操作对象"></el-table-column>
      <el-table-column prop="target_id" label="对象ID"></el-table-column>
      <el-table-column prop="time" label="时间" :formatter="toDescription"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  import api from '../apis'
  import moment from 'moment'
  export default {
    data() {
      return {
        tableData: [],
        formIDSearch: {
          lid: ''
        },
        formTIMESearch: [],
        loading: false
      }
    },
    mounted(){
      api.getAllLogs()
        .then((response) => {
            this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
    },
    methods: {
      handleIDSearch() {
        this.loading = true;
        api.searchLogById(this.formIDSearch)
          .then(response => {
            if (response && response.data) {
              this.loading = false;
              this.tableData = response.data;
            }
          }).catch(error => {
            console.log(error);
          });
      },
      handleTIMESearch() {
        if (this.formTIMESearch[0] == null || this.formTIMESearch[1] == null) {
          this.$message.error("请选择完整的日期时间范围。");
        }else {
          this.loading = true;
          let params = {
            start: this.formTIMESearch[0],
            end: this.formTIMESearch[1]
          };
          api.searchLogByTime(params)
            .then(response => {
              this.loading = false;
              this.tableData = response.data;
            }).catch(error => {
              console.log(error);
            });
        }
      },
      toDescription(row, column) {
        let value = row.time;
        return moment(value).format("YYYY-MM-DD HH:mm:ss");
      }
    }
  }
</script>