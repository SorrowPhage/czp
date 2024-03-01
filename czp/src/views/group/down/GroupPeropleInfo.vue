<template>
    <div class="gd_body">
        <el-table :data="dataList" style="width: 100%;" :height="tableHeight">
            <el-table-column prop="familyRank" label="世代排行"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="generationRank" label="排行"></el-table-column>
            <el-table-column prop="status" label="状态" :formatter="nullValueFormat"></el-table-column>
            <el-table-column
                label="操作"
                width="100">
                <template slot-scope="scope">
<!--                    <el-button @click="selectGroupDetails(scope.row)" type="text" size="small">详情</el-button>-->
                    <el-button  type="text" size="small" @click="chat(scope.row.id)">聊天</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
import {getRequest, windowHeight} from "@/api/api";
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";

export default {
    name: "GroupPeropleInfo",
    data() {
        return{
            dataList: [],
            tableHeight: (windowHeight() - 320),
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            showLoading();
            getRequest("/group/ul", {id: this.$route.query.gid}).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.dataList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        nullValueFormat(row) {
            return row.status === '1' ? "存活" : "已逝";
        },
        chat(id) {
            this.$router.push({
                name:"chat",
                query:{
                    id: id,
                }
            })
        },
    }
}
</script>

<style scoped>

</style>