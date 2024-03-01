<template>
    <div class="gd_body main">
        <div class="top_box">
            <div class="title">
                <span>家族信息--按照地区分支展示</span>
            </div>
        </div>
        <div style="width: 100%">
            <el-row>
                <el-col :span="16">
                    <el-button type="primary" @click="createRootGroup">创建根级族群</el-button>
                </el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="20">
                            <el-input v-model="searchParam.groupName" placeholder="族群名称" style="right: 10px"></el-input>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary" @click="getGroupListPage">搜索</el-button>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
        </div>
        <div class="panel" >
            <el-row>
                <el-col :span="24" class="el-main-content">
                    <el-table :data="groupList" style="width: 100%;" :height="tableHeight">
                        <el-table-column prop="groupName" label="名称"></el-table-column>
                        <el-table-column prop="des" label="描述"></el-table-column>
                        <el-table-column prop="area" label="地区"></el-table-column>
                        <el-table-column prop="parentName" label="父级族群" :formatter="nullValueFormat"></el-table-column>
                        <el-table-column prop="livingPeopleTotal" label="现存人数"></el-table-column>
                        <el-table-column prop="peopleTotal" label="总数目"></el-table-column>
                        <el-table-column
                            label="操作"
                            width="200">
                            <template slot-scope="scope">
                                <el-button @click="selectGroupDetails(scope.row)" type="text" size="small">详情</el-button>
                                <el-button @click="createNewGroup(scope.row);" type="text" size="small">创建子级族群</el-button>
                                <el-button @click="deleteGroup(scope.row)" type="text" size="small" v-if="hasPerm('admin:root')">删除</el-button>
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
            width="30%"
        >
            <el-form :model="form" label-width="120px">
                <el-form-item label="族群名称" prop="name">
                    <el-input v-model="form.groupName"></el-input>
                </el-form-item>
                <el-form-item v-show="showparent" label="父级族群" prop="parentName">
                    <el-input v-model="form.parentName"></el-input>
                </el-form-item>
                <el-form-item label="地理位置" prop="area">
                    <el-input v-model="form.area"></el-input>
                </el-form-item>
                <el-form-item label="创建时间" prop="createTime">
                    <el-date-picker
                        v-model="form.createTime"
                        type="year"
                        placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="族群描述" prop="des">
                    <el-input type="textarea" v-model="form.des"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
      <el-button @click="showCreate = false">取消</el-button>
      <el-button type="primary" @click="handleSubmit">创建</el-button>
    </span>
        </el-dialog>
    </div>

</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {postRequest,windowHeight} from "@/api/api";

export default {
    name: "EthnicGroupInformation",
    data() {
        return {
            groupList: [],
            tableHeight: (windowHeight() - 320),
            pagination: {pages: 0, total: 0, pageSize: 10},
            searchParam: {
                groupName: '',
                pageNum: 1,
                pageSize:10,
            },
            showCreate: false,
            showGroupInfo: false,
            groupInfo: null,
            showparent: false,
            form: {
                groupName: '',
                area: '',
                createTime: '',
                clanElder: '',
                des: '',
                parentName: '',
                parentId: '',
            },
        };
    },
    mounted() {
        this.getGroupListPage();
    },
    methods:{
        //获取族群信息列表
        getGroupListPage() {
            showLoading()
            postRequest("/group/listpage", this.searchParam).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.groupList = res.data.list;
                    this.pagination.pages = res.data.pages;
                    this.pagination.pageSize = res.data.pageSize;
                    this.pagination.total = res.data.total;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        nullValueFormat(row) {
            return row.parentName == null ? "-" : row.parentName;
        },
        selectGroupDetails(row) {
            //跳转到详情页面
            this.$router.push({
                name: 'groupinfo',
                query: {
                    gid: row.id,
                }
            })
        },
        createNewGroup(row) {
            this.form.parentName = row.groupName;
            this.form.parentId = row.id;
            this.form.clanElder = this.$store.state.CzpUser.id;
            this.showparent = true;
            this.showCreate = true;
        },
        createRootGroup() {
            this.form.parentName = '';
            this.form.parentId = '';
            this.form.clanElder = this.$store.state.CzpUser.id;
            this.showparent = false;
            this.showCreate = true;
        },
        handleSubmit() {
            showLoading();
            postRequest("/group/create", this.form).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.$message({
                        message: "创建成功",
                        type: "success",
                    });
                    this.getGroupListPage();
                } else {
                    this.$message({
                        message: res.message,
                        type: "warning",
                    });
                }
            });
            this.showCreate = false;
        },
        deleteGroup(row) {
            showLoading();
            postRequest("/group/delete", row).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.$message({
                        message: res.message,
                        type: "success",
                    });
                    this.getGroupListPage();
                }
            });
        },
    }
}
</script>

<style scoped lang="less">
.main{
    display: flex;
    flex-wrap: wrap;
}
.top {
    height: 100px;
    background: #2b3346;
    width: 100%;
    margin-top: 5px;
}
.top_box {
    padding: 10px 0 0;
    height: 52px;
    width: 100%;
    .title{
        height: 42px;
        background-color: #fff;
        -webkit-box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
        box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
        -ms-flex-negative: 0;
        flex-shrink: 0;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        -webkit-box-pack: justify;
        -ms-flex-pack: justify;
        justify-content: space-between;
        padding: 0 16px;
        font-size: 15px;
        color: #666;
        border-radius: 4px;
    }
}
.panel {
    width: 100%;
    //height: 100%;
    margin-top: 15px;
    //background: #545c64;
    flex-grow: 1;
}
.el-main-content {
    //width: calc(100% - 40px);
    //margin: 15px 20px;
    background-color: white;
    padding: 20px;
    box-shadow: 0 2px 6px 0 rgba(0, 0, 0, .1);
}
</style>