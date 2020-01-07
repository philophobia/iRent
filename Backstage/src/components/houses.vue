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
      <el-table-column prop="id" label="编号"></el-table-column>
      <el-table-column prop="owner_id" label="房主编号"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="status" label="状态" :formatter="toDescription"></el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleConfirm(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import api from "../apis";
export default {
  inject: ['reload'],
  data() {
    return {
      tableData: [],
      formSearch: {
        hid: ''
      },
      loading: false
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
    handleConfirm(row) {
        this.$confirm('此操作将不可恢复, 是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.handleDelete(row);
          this.$message({
            type: 'success',
            message: '删除成功'
          });
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
        hid: row.id
      };
      api.deleteHouse(params)
        .then(response => {
          if (response && response.data) {
            this.loading = false;
            this.reload();
          }
        }).catch(error => {
          console.log(error);
        });
    },
    toDescription(row, column) {
      let value = row.status;
      if (value == false) {
        return "未出租";
      }
      else {
        return "已出租";
      }
    }
  }
};
</script>