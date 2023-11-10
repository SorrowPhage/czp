<template>
    <div style="height: 100%;background-color: white">
        <div>
            选择族群:
            <el-select v-model="value" placeholder="请选择">
                <el-option
                    v-for="item in options"
                    :key="item.id"
                    :label="item.groupName"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-button type="primary" plain @click="searchGroupList">搜索</el-button>
        </div>
        <div>
            <el-table
                :data="dataList"
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
                <el-table-column label="父级" prop="parentId" width="120"></el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
import {hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest} from "@/api/api";

export default {
    name: "GroupCreaterList",
    data() {
        return{
            options: [],
            value: '',
            dataList: [],
            height: window.innerHeight - 180,
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            showLoading()
            getRequest("/group/clan",{id: this.$store.state.CzpUser.id}).then(res=>{
                hideLoadingAndNotify(res);
                if (res.code === 200) {
                    this.options = res.data;
                }
            }).catch( err=>{
                    hideLoadingAndNotify(err)
                }
            );
        },
        searchGroupList() {
            showLoading();
            getRequest("/group/ul",{id: this.value}).then(res=>{
                hideLoadingAndNotify(res);
                if (res.code === 200) {
                    this.dataList = res.data;
                }
            }).catch( err=>{
                    hideLoadingAndNotify(err)
                }
            );
        },
    }
}
</script>

<style scoped>

</style>