<template>
  <div style="height: 100%;width: 100%;">
    <!--查询的表单区域-->
    <el-form :data="formSearch" style="width: 100%;margin-top: 10px;" :inline="true">
      <el-form-item>
        <el-input v-model="formSearch.rid" placeholder="ID搜索" style="border: 1px solid #262cab"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleIDSearch"></el-button>
      </el-form-item>
      <el-form-item label="状态搜索" style="margin-left: 100px;">
        <el-select v-model="selection" placeholder="请选择">
          <el-option label="待处理" value="pending"></el-option>
          <el-option label="正在处理" value="processing"></el-option>
          <el-option label="处理完成" value="processed"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleSTATSearch"></el-button>
      </el-form-item>
    </el-form>
    <!--表格区域-->
    <el-table :data="tableData" height="100%" style="width: 100%" v-loading="loading">
      <el-table-column prop="r_id" label="举报信编号" sortable></el-table-column>
      <el-table-column prop="u_id" label="举报人编号" sortable></el-table-column>
      <el-table-column prop="h_id" label="被举报房源编号" sortable></el-table-column>
      <el-table-column prop="report_reason" label="举报理由"></el-table-column>
      <el-table-column prop="report_time" label="举报时间" :formatter="toDescription"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleHandle(scope.row)" type="text" size="small">处理</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="更改处理状态" :visible.sync="isDialogVisible">
      <el-form>
        <el-form-item label="状态选择">
          <el-select v-model="alteration.stat" placeholder="请选择">
            <el-option label="正在处理" value="processing" :disabled="isINGDisable"></el-option>
            <el-option label="处理完成" value="processed" :disabled="isEDDisable"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="handleConfirm">确定</el-button>
        <el-button @click="isDialogVisible = false">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import api from "../apis";
import moment from 'moment'
export default {
  inject: ['reload'],
  data() {
    return {
      tableData: [],
      formSearch: {
        rid: ''
      },
      isDialogVisible: false,
      alteration: {
        rid: '',
        stat: ''
      },
      loading: false,
      selection: '',
      isINGDisable: false,
      isEDDisable: false
    };
  },
  mounted() {
    api.getAllReports()
      .then(response => {
        this.tableData = response.data;
      }).catch(error => {
        console.log(error);
      });
  },
  methods: {
    handleHandle(row) {
      if (row.status == "待处理"){
        this.isINGDisable = false;
        this.isEDDisable = true;
      }
      else if (row.status == "正在处理") {
        this.isINGDisable = true;
        this.isEDDisable = false;
      }
      else if (row.status == "处理完成") {
        this.isINGDisable = true;
        this.isEDDisable = true;
      }
      this.isDialogVisible = true;
      this.alteration.rid = row.r_id;
    },
    handleConfirm() {
      this.isDialogVisible = false;
      console.log(this.alteration);
      this.loading = true;
      api.updateReport(this.alteration)
        .then(response => {
          if (response && response.data) {
            this.loading = false;
            this.reload();
          }
        }).catch(error => {
          console.log(error);
        });
    },
    handleIDSearch() {
      this.loading = true;
      api.searchReportById(this.formSearch)
        .then(response => {
          this.loading = false;
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
    },
    handleSTATSearch() {
      this.loading = true;
      let params = {
        stat: this.selection
      };
      api.searchReportByStatus(params)
        .then(response => {
          this.loading = false;
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
    },
    toDescription(row, column) {
      let value = row.report_time;
      return moment(value).format("YYYY-MM-DD HH:mm:ss");
    }
  }
};
</script>