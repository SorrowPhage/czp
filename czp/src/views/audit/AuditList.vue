<template>
    <div class="gd_body">
        <div class="top_box">
            <div class="title">
                <span>审核</span>
            </div>
        </div>
        <div class="table_body">
            <el-row>
                <el-table
                    :data="auditList"
                    :height="tableHeight"
                    style="width: 100%;"
                >
                    <el-table-column label="姓名" prop="name" width="120"></el-table-column>
                    <el-table-column   width="120">
                        <template slot-scope="scope">
                            <el-avatar shape="square" :src="scope.row.avatar"></el-avatar>
                        </template>
                    </el-table-column>
                    <el-table-column label="加入族群" prop="groupName" width="120" ></el-table-column>
                    <el-table-column label="父级" prop="parentName" width="120"></el-table-column>
                    <el-table-column   width="120">
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
            </el-row>
        </div>
    </div>
</template>

<script>
import {getRequest, postRequest, windowHeight} from "@/api/api";

export default {
    name: "AuditList",
    data() {
        return {
            auditList: [],
            tableHeight: (windowHeight() - 250),
            height: window.innerHeight - 180,
        };
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
            row.operateId = this.$store.state.CzpUser.id;
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
            row.operateId = this.$store.state.CzpUser.id;
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