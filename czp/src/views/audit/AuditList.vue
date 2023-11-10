<template>
    <div>
        <div style="height: 30px;padding: 10px;">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a :href="$route.path">申请</a></el-breadcrumb-item>
                <el-breadcrumb-item class="now_text">审核</el-breadcrumb-item>
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
                <el-table-column label="加入族群" prop="groupName" width="120" ></el-table-column>
                <el-table-column label="父级" prop="parentName" width="120"></el-table-column>
                <el-table-column label="父级头像"  width="120">
                    <template slot-scope="scope">
                        <el-avatar shape="square" :src="scope.row.parentAvatar"></el-avatar>
                    </template>
                </el-table-column>
                <el-table-column label="申请时间" prop="createTime" width="140"></el-table-column>
                <el-table-column
                    label="操作"
                    >
                    <template slot-scope="scope">
                        <el-button size="small" type="success" @click="agree(scope.row)">通过</el-button>
                        <el-button size="small" type="danger" @click="disAgree(scope.row)">不通过</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
import {getRequest,postRequest} from "@/api/api";

export default {
    name: "AuditList",
    data() {
        return {
            auditList: [],
            height: window.innerHeight - 180,
        }
    },
    mounted() {
        this.loadData();
    },
    methods: {
        loadData() {
            getRequest("/audit/list", {id: this.$store.state.CzpUser.id}).then(res => {
                console.log("AuditList", res);
                if (res.code === 200) {
                    this.auditList = res.data;
                }
            })
        },
        agree(row) {
            row.status = "1";
            postRequest("/audit/result",row).then(res=>{
                console.log("Audit", res);
                if (res.code === 200) {
                    let index = this.auditList.findIndex(item => item.id === row.id);
                    this.auditList.splice(index, 1);
                }
            })
        },
        disAgree(row) {
            row.status = "-1";
            postRequest("/audit/result",row).then(res=>{
                console.log("Audit", res);
                if (res.code === 200) {
                    let index = this.auditList.findIndex(item => item.id === row.id);
                    this.auditList.splice(index, 1);
                }
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