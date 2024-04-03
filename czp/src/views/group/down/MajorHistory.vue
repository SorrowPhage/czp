<template>
    <div class="gd_body gd_window">
        <div class="panel" >
            <el-row>
                <el-button type="primary" @click="addMajorHistory" v-if="hasPerm('admin:root')">新增</el-button>
            </el-row>
            <el-row>
                <el-col :span="24" class="el-main-content">
                    <el-table :data="mhList" style="width: 100%;" :height="tableHeight">
                        <el-table-column prop="id" label="编码"></el-table-column>
                        <el-table-column prop="createTime" label="时间"></el-table-column>
                        <el-table-column prop="content" label="内容"></el-table-column>
                        <el-table-column prop="userId" label="记录者"></el-table-column>
                        <el-table-column prop="updateTime" label="最后修改时间"></el-table-column>
                        <el-table-column
                            label="操作"
                            width="200" v-if="hasPerm('admin:root')" >
                            <template slot-scope="scope">
<!--                                <el-button @click="selectGroupDetails(scope.row)" type="text" size="small">详情</el-button>-->
<!--                                <el-button @click="createNewGroup(scope.row);" type="text" size="small">创建子级族群</el-button>-->
<!--                                <el-button @click="deleteGroup(scope.row)" type="text" size="small" v-if="hasPerm('admin:root')">删除</el-button>-->
                                <el-button type="text" size="small" @click="updateMajorHistory(scope.row)">修改</el-button>
                                <el-button type="text" size="small" @click="deleteMajorHistory(scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="24" style="text-align: center;">
                    <el-pagination layout="total, sizes, prev, pager, next, jumper"
                                   :page-count="pagination.pages"
                                   :total="pagination.total"
                                   :page-sizes="[10,20,50]"
                                   :page-size="pagination.pageSize"
                                   :current-page.sync="searchParam.pageNum"
                    >
                    </el-pagination>
                </el-col>
            </el-row>
        </div>
        <el-dialog
            title="创建族群"
            :visible.sync="showCreate"
            width="500px"
        >
            <el-form :model="form" label-width="80px">
                <el-form-item label="事件内容" prop="des">
                    <el-input type="textarea" v-model="form.content"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
      <el-button @click="showCreate = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit">创建</el-button>
    </span>
        </el-dialog>
    
        <el-dialog
            title="创建族群"
            :visible.sync="showUpdate"
            width="500px"
        >
            <el-form :model="up" label-width="80px">
                <el-form-item label="事件内容" prop="des">
                    <el-input type="textarea" v-model="up.content"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
      <el-button @click="showUpdate = false">取消</el-button>
      <el-button type="primary" @click="update">创建</el-button>
    </span>
        </el-dialog>
    </div>
</template>

<script>
import {postRequest, windowHeight} from "@/api/api";
import {hideLoading, showLoading} from "@/api/loading";
export default {
    name: "MajorHistory",
    data() {
        return {
            tableHeight: (windowHeight() - 320),
            pagination: {pages: 0, total: 0, pageSize: 10},
            mhList: [
                {
                    id: '123',
                    groupId: '',
                    content: 'xxx搬到xxx',
                    userId: '',
                    createTime: '',
                    updateTime: ''
                }
            ],
            form: {
                content: 'xxx搬到xxx',
            },
            searchParam: {
                groupId: '',
                pageNum: 1,
                pageSize: 10,
            },
            showCreate: false,
            up: {
                id: '123',
                groupId: '',
                content: 'xxx搬到xxx',
                userId: '',
            },
            showUpdate: false,
            
        };
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            this.searchParam.groupId = this.$route.query.gid;
            postRequest("/mh/list", this.searchParam).then(res=>{
                if (res.code === 200) {
                    this.mhList = res.data.list;
                    this.pagination.pages = res.data.pages;
                    this.pagination.pageSize = res.data.pageSize;
                    this.pagination.total = res.data.total;
                }
            });
        },
        addMajorHistory() {
            this.showCreate = true;
        },
        updateMajorHistory(row) {
            this.up.content = row.content;
            this.up.id = row.id;
            this.up.groupId = row.groupId;
            this.userId = row.userId;
            this.showupdate = true;
        },
        deleteMajorHistory(row) {
            postRequest("/mh/delete", row).then(res=>{
                if (res.code === 200) {
                    this.$message({
                        message: "删除成功",
                        type: "success",
                    });
                    this.loadData();
                }
            });
        },
        handleSubmit() {
            let params={
                groupId: this.$route.query.gid,
                content: this.form.content,
                userId: this.$store.state.CzpUser.id,
            }
            showLoading();
            postRequest("/mh/create", params).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.$message({
                        message: "创建成功",
                        type: "success",
                    });
                    this.loadData();
                    this.form.content = '';
                } else {
                    this.$message({
                        message: res.message,
                        type: "warning",
                    });
                }
            });
            this.showCreate = false;
        },
        update() {
            let params = {
                id: this.up.id,
                groupId: this.$route.query.gid,
                content: this.up.content,
                userId: this.$store.state.CzpUser.id,
            };
            showLoading();
            postRequest("/mh/update", params).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.$message({
                        message: "修改成功",
                        type: "success",
                    });
                    this.loadData();
                } else {
                    this.$message({
                        message: res.message,
                        type: "warning",
                    });
                }
            });
            this.showCreate = false;
        },
    }
}
</script>

<style scoped>
/*.panel {*/
/*    width: 100%;*/
/*    height: 100%;*/
/*    margin-top: 15px;*/
/*    background: #545c64;*/
/*    flex-grow: 1;*/
/*}*/
/*.el-main-content {*/
/*    width: calc(100% - 40px);*/
/*    margin: 15px 20px;*/
/*    background-color: white;*/
/*    padding: 20px;*/
/*    box-shadow: 0 2px 6px 0 rgba(0, 0, 0, .1);*/
/*}*/
</style>