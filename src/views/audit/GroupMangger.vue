<template>
    <div>
        <div style="height: 30px;padding: 10px;">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a :href="$route.path">族群信息</a></el-breadcrumb-item>
                <el-breadcrumb-item class="now_text">族群管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <div style="display: flex">
                <GroupInfoUpdate v-for="g in mangeList" :key="g.id"
                           :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"
                                 :clan-elder="g.clanElder"
                />
            </div>
        </div>
    </div>
</template>

<script>
import {hideLoadingAndNotify, showLoading,hideLoading} from "@/api/loading";
import {getRequest} from "@/api/api";
import GroupInfoUpdate from "@/views/group/GroupInfoUpdate";
export default {
    name: "GroupMangger",
    components:{GroupInfoUpdate},
    data() {
        return {
            mangeList: [],
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
                hideLoading();
                if (res.code === 200) {
                    this.mangeList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
    }
};
</script>

<style scoped>

</style>