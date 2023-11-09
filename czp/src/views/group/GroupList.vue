<template>
    <div style="height: 100%;width: 100%">
        <div class="breadcrumb_body">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a :href="$route.path">族群信息</a></el-breadcrumb-item>
                <el-breadcrumb-item class="now_text">族群信息</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div>
            <GroupCard v-for="g in groupList" :key="g.id"
                       :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"
            />
        </div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import GroupCard from "@/views/group/GroupCard";
import {showLoading,hideLoadingAndNotify} from "@/api/loading"
export default {
    name: "GroupList",
    components: {GroupCard},
    data() {
        return{
            groupList: [],
        }
    },
    mounted() {
        this.loadData();
        console.log(this.$route.path)
    },
    methods:{
        loadData() {
            showLoading()
            getRequest("/group/list", {userId: this.$store.state.CzpUser.id}).then(res => {
                console.log("GroupList", res);
                hideLoadingAndNotify(res);
                if (res.code === 200) {
                    this.groupList = res.data;
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
.breadcrumb_body {
    height: 30px;
    padding: 10px;
}
</style>