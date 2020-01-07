<template>
  <div style="width: 100%;height: 100%;">
    <!--查询的表单区域-->
    <el-form style="width: 100%;margin-top: 10px;" :inline="true">
      <el-form-item>
        <el-input v-model="formSearch.uid" placeholder="ID搜索" style="border: 1px solid #262cab"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" style="background-color: #42446e;color: white;" circle @click="handleSearch"></el-button>
      </el-form-item>
    </el-form>
    <!--表格区域-->
    <el-table :data="tableData" height="100%" style="width: 100%" v-loading="loading">
      <el-table-column prop="id" label="用户编号" sortable></el-table-column>
      <el-table-column prop="nickname" label="昵称"></el-table-column>
      <el-table-column prop="telephone" label="电话号码" sortable></el-table-column>
      <el-table-column prop="permission_level" label="角色"></el-table-column>
      <el-table-column prop="account_status" label="账号状态"></el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button @click="handleFreeze(scope.row)" type="text" size="small">冻结</el-button>
          <el-button @click="handleRecover(scope.row)" type="text" size="small">恢复</el-button>
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
          uid: ''
        },
        loading: false
      };
    },
    mounted() {
      api.getAllUsers()
        .then(response => {
          this.tableData = response.data;
        }).catch(error => {
          console.log(error);
        });
    },
    methods: {
      handleFreeze(row) {
        this.loading = true;
        let params = {
          id: row.id,
          operation: 'freeze'
        };
        api.updateUser(params)
          .then(response => {
            if (response && response.data){
              this.loading = false;
              this.reload();
              }
          }).catch(error => {
            console.log(error);
          });
      },
      handleRecover(row) {
        this.loading = true;
        let params = {
          uid: row.id,
          operation: 'recover'
        };
        api.updateUser(params)
          .then(response => {
            if (response && response.data) {
              this.loading = false;
              this.reload();
            }
          }).catch(error => {
            console.log(error);
          });
      },
      handleSearch() {
        this.loading = true;
        api.searchUser(this.formSearch)
          .then(response => {
            this.loading = false;
            console.log(response);
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
          uid: row.id
        };
        api.deleteUser(params)
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
      }
    }
  }
</script>