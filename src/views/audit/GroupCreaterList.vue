<template>
    <div style="height: 100%;">
        <div class="op_lan">
            家族:
            <el-select v-model="value" placeholder="请选择" size="mini">
                <el-option
                    v-for="item in options"
                    :key="item.id"
                    :label="item.groupName"
                    :value="item.id">
                </el-option>
            </el-select>
            名称:
            <el-input style="width: 150px" placeholder="请输入名称" size="mini"></el-input>
            <el-button type="primary" plain @click="searchGroupList" size="mini" style="float: right">搜索</el-button>
        </div>
        <div style="background-color: white;margin-top: 10px">
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
                <el-table-column label="世代顺序" prop="generationRank" width="120"></el-table-column>
                <el-table-column label="世代顺序" prop="generation" width="120"></el-table-column>
                <el-table-column label="状态" prop="status" width="120">
                    <template slot-scope="scope">
                        <el-switch
                            v-model="scope.row.status"
                            active-value="1"
                            disabled
                            inactive-value="0"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                        </el-switch>
                    </template>
                </el-table-column>
                <el-table-column
                    label="操作"
                >
                    <template slot-scope="scope">
<!--                        <el-button size="small" type="success" @click="agree(scope.row)">查看</el-button>-->
                        <el-button size="mini" type="success" >查看</el-button>
                        <el-button size="mini" type="success" >编辑</el-button>
<!--                        <el-button size="mini" type="danger" >删除</el-button>-->
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
import {hideLoadingAndNotify, showLoading,hideLoading} from "@/api/loading";
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
            getRequest("/group/clan", {id: this.$store.state.CzpUser.id}).then(res => {
                // hideLoadingAndNotify(res);
                hideLoading()
                if (res.code === 200) {
                    this.options = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        searchGroupList() {
            showLoading();
            getRequest("/group/ul", {id: this.value}).then(res => {
                // hideLoadingAndNotify(res);
                hideLoading();
                if (res.code === 200) {
                    this.dataList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
    }
}
</script>

<style scoped>
.op_lan {
    background-color: #f2f2f2;
    border-left: 2px solid #367fff;
    padding: 5px;
    font-size: 14px;
}
</style>