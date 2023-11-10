<template>
    <div>
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
            <el-button type="primary" plain @click="loadGroupTreeData">搜索</el-button>
        </div>
        <div>
        
        </div>
    </div>
</template>

<script>
import {hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest} from "@/api/api";

export default {
    name: "GroupTree",
    data() {
        return{
            options: [],
            value: '',
            treeData: [],
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
        loadGroupTreeData() {
            showLoading();
            getRequest("/group/tree",{id: this.value}).then(res=>{
                console.log("GroupTree",res)
                hideLoadingAndNotify();
                if (res.code === 200) {
                    this.treeData = res.data;
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