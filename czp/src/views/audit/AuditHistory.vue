<template>
    <div class="gd_body">
        <div class="top_box">
            <div class="title">
                <span>审核历史</span>
            </div>
        </div>
        <div class="table_body">
            <el-table
                :data="auditList"
                :height="height"
                style="width: 100%;"
            >
                <el-table-column label="姓名" prop="name" width="120"></el-table-column>
                <el-table-column   width="120">
                    <template slot-scope="scope">
                        <el-avatar shape="square" :src="scope.row.avatar"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column label="族群" prop="groupName" width="120" ></el-table-column>
                <el-table-column label="父级" prop="parentName" width="120"></el-table-column>
                <el-table-column   width="120">
                    <template slot-scope="scope">
                        <el-avatar shape="square" :src="scope.row.parentAvatar"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column label="申请时间" prop="createTime" width="140"></el-table-column>
                <el-table-column label="审核时间" prop="updateTime" width="140"></el-table-column>
                <el-table-column
                    label="审核结果"
                >
                    <template slot-scope="scope">
                        <el-tag type="success" v-if="scope.row.status==='1'">通过</el-tag>
                        <el-tag type="danger" v-else>未通过</el-tag>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import {showLoading,hideLoadingAndNotify,hideLoading} from "@/api/loading"
export default {
    name: "AuditHistory",
    data() {
        return{
            auditList: [],
            height: window.innerHeight - 180,
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        // TODO 可以加入分页查询
        loadData() {
            showLoading()
            getRequest("/audit/history", {id: this.$store.state.CzpUser.id}).then(res => {
                console.log("AuditHistory", res);
                // hideLoadingAndNotify(res);
                hideLoading();
                if (res.code === 200) {
                    this.auditList = res.data;
                }
            }).catch(err => {
                hideLoadingAndNotify(err);
            });
        },
    }
}
</script>

<style scoped lang="scss">
.table_body {
    padding: 15px;
    background-color: white;
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
</style>