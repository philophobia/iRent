<template>
  <div style="height: 100%;width: 100%;">
    <!--查询的表单区域-->
    <el-form style="width: 100%;margin-top: 10px;" :inline="true">
      <el-form-item>
        <el-input v-model="formSearch.did" placeholder="ID搜索" style="border: 1px solid #262cab"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleSearch"></el-button>
      </el-form-item>
    </el-form>
    <!--表格区域-->
    <el-table :data="tableData" height="100%" style="width: 100%" v-loading="loading">
      <el-table-column prop="id" label="编号" sortable></el-table-column>
      <el-table-column prop="tenant_id" label="买方" sortable></el-table-column>
      <el-table-column prop="landlord_id" label="卖方" sortable></el-table-column>
      <el-table-column prop="house_id" label="房源" sortable></el-table-column>
      <el-table-column prop="create_time" label="创建时间" sortable></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
    </el-table>
  </div>
</template>

<script>
  import api from '../apis'
  export default {
    inject: ['reload'],
    data() {
      return {
        tableData: [],
        formSearch: {
          did: ''
        },
        loading: false
      }
    },
    mounted(){
      api.getAllDeals()
        .then((response) => {
          this.tableData = response.data;
          }).catch(error => {
            console.log(error);
          });
    },
    methods: {
      handleSearch() {
        this.loading = true;
        api.searchDeal(this.formSearch)
          .then(response => {
            this.loading = false;
            this.tableData = response.data;
          }).catch(error => {
            console.log(error);
          });
      }
    }
  }
</script>