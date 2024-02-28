<template>
    <div class="gd_body main">
        <div class="top_box">
            <div class="title">
                <span>家族信息--按照地区分支展示</span>
            </div>
        </div>
        <div style="width: 100%">
            <el-row>
                <el-col :span="16"><el-button type="primary">新建</el-button></el-col>
                <el-col :span="8">
                    <el-row>
                        <el-col :span="20">
                            <el-input placeholder="族群名称" style="right: 10px"></el-input>
                        </el-col>
                        <el-col :span="4">
                            <el-button type="primary">搜索</el-button>
                        </el-col>
                    </el-row>
                </el-col>
            </el-row>
        </div>
        <div class="panel" >
            <el-row>
                <el-col :span="24" class="el-main-content">
                    <el-table :data="tableData" style="width: 100%;" :height="tableHeight"
                              @selection-change="dataSelectionChange">
                        <el-table-column type="selection" width="55"></el-table-column>
                        <el-table-column prop="type" label="业务类型"></el-table-column>
                        <el-table-column fixed="right" label="操作" width="100">
                            <template slot-scope="scope">
<!--                                <el-popconfirm @confirm="isDelete(scope.row)"-->
                                <el-popconfirm @confirm="isDelete(scope.row)"
                                               confirm-button-text='确定'
                                               cancel-button-text='取消'
                                               icon="el-icon-info"
                                               icon-color="red"
                                               title="确定删除该条数据？">
                                    <el-button slot="reference" type="text" size="small">删除</el-button>
                                </el-popconfirm>
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
<!--                                   @current-change="loadData"-->
<!--                                   @size-change="(val)=>{this.searchParam.pageSize = val;this.loadData();}">-->
                    </el-pagination>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest,windowHeight} from "@/api/api";
export default {
    name: "EthnicGroupInformation",
    data() {
        return {
            groupList: [],
            tableHeight: (windowHeight() - 320),
            pagination: {pages: 0, total: 0, pageSize: 10},
            searchParam: {
                pageFlag: 1,
                pageNum: 1,
                pageSize:10,
            },
        };
    },
    mounted() {
        // this.getGroupList();
        this.getGroupListPage();
    },
    methods:{
        //获取用户所拥有的族群信息
        getGroupList() {
            showLoading()
            getRequest("/group/list", {userId: this.$store.state.CzpUser.id}).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        getGroupListPage() {
            showLoading()
            getRequest("/group/listpage", {userId: this.$store.state.CzpUser.id}).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
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