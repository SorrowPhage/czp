<template>
    <div>
        <div style="height: 30px;padding: 10px;">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a :href="$route.path">申请</a></el-breadcrumb-item>
                <el-breadcrumb-item class="now_text">历史操作</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="table_body">
            <el-table
                :data="auditList"
                border
                :height="height"
                style="width: 100%;"
            >
                <el-table-column label="姓名" prop="name" width="120"></el-table-column>
                <el-table-column label="头像"  width="120">
                    <template slot-scope="scope">
                        <el-avatar shape="square" :src="scope.row.avatar"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column label="族群" prop="groupName" width="120" ></el-table-column>
                <el-table-column label="父级" prop="parentName" width="120"></el-table-column>
                <el-table-column label="父级头像"  width="120">
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
import {showLoading,hideLoadingAndNotify} from "@/api/loading"
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
            getRequest("/audit/history",{id: this.$store.state.CzpUser.id}).then(res=>{
                console.log("AuditHistory", res);
                hideLoadingAndNotify(res);
                if (res.code === 200) {
                    this.auditList = res.data;
                }
            }).catch(err=>{
                hideLoadingAndNotify(err);
            })
        },
    }
}
</script>

<style scoped>
.table_body {
    padding: 15px;
    background-color: white;
}
</style>