<template>
  <div style="height: 100%;width: 100%;">
    <!--查询的表单区域-->
    <el-form style="width: 100%;margin-top: 10px;" :inline="true">
      <el-form-item>
        <el-input v-model="formSearch.hid" placeholder="ID搜索" style="border: 1px solid #262cab"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleSearch"></el-button>
      </el-form-item>
    </el-form>
    <!--表格区域-->
    <el-table :data="tableData" height="100%" style="width: 100%" v-loading="loading">
      <el-table-column prop="h_id" label="编号"></el-table-column>
      <el-table-column prop="house_address" label="地址"></el-table-column>
      <el-table-column prop="house_price" label="价格"></el-table-column>
      <el-table-column prop="house_type" label="类型"></el-table-column>
      <el-table-column prop="publisher" label="发布者"></el-table-column>
      <el-table-column prop="publish_time" label="发布时间" :formatter="toDescription"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleJump(scope.row)" type="text" size="small">详情</el-button>
          <el-button @click="handleConfirm(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
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
        hid: ''
      },
      loading: false,
      houseDetailBase: 'http://localhost:8090/v1/house?id='
    };
  },
  mounted() {
    api.getAllHouses()
      .then(response => {
        this.tableData = response.data;
      }).catch(error => {
        console.log(error);
      });
  },
  methods: {
    handleSearch() {
      this.loading = true;
      api.searchHouse(this.formSearch)
        .then(response => {
          this.loading = false;
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
    },
    handleJump(row) {
      window.open(this.houseDetailBase + row.h_id);
    },
    handleConfirm(row) {
        this.$confirm('此操作将不可恢复, 是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.handleDelete(row);
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });          
        });
    },
    handleDelete(row) {
      this.loading = true;
      let params = {
        hid: row.h_id
      };
      api.deleteHouse(params)
        .then(response => {
          if (response && response.data) {
            this.$message({
              type: 'success',
              message: '删除成功'
            });
            this.loading = false;
            this.reload();
          }
          else {
            this.$message({
              type: 'error',
              message: '删除失败'
            });
          }
        }).catch(error => {
          console.log(error);
        });
    },
    toDescription(row, column) {
      let value = row.publish_time;
        return moment(value).format("YYYY-MM-DD HH:mm:ss");
    }
  }
};
</script>