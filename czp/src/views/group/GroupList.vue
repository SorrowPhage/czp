<template>
    <div style="height: 100%;width: 100%">
<!--        <div class="breadcrumb_body">-->
<!--            <el-breadcrumb separator="/">-->
<!--                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>-->
<!--                <el-breadcrumb-item><a :href="$route.path">族群信息</a></el-breadcrumb-item>-->
<!--                <el-breadcrumb-item class="now_text">族群信息</el-breadcrumb-item>-->
<!--            </el-breadcrumb>-->
<!--        </div>-->
        <group-info-card v-for="g in groupList" :key="g.id"
                         :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"
        />
<!--        <div style="background-color: white;display: flex;height: 100%">-->
<!--&lt;!&ndash;            <div class="mannage_box">&ndash;&gt;-->
<!--&lt;!&ndash;                <span>拥有族群</span>&ndash;&gt;-->
<!--&lt;!&ndash;                <div style="display: flex">&ndash;&gt;-->
<!--&lt;!&ndash;                    <GroupCard v-for="g in mangeList" :key="g.id"&ndash;&gt;-->
<!--&lt;!&ndash;                               :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"&ndash;&gt;-->
<!--&lt;!&ndash;                    />&ndash;&gt;-->
<!--&lt;!&ndash;                </div>&ndash;&gt;-->
<!--&lt;!&ndash;            </div>&ndash;&gt;-->
<!--            <div class="join_box">-->
<!--                <span>已加入族群</span>-->
<!--                <div style="display: flex">-->
<!--                    <GroupCard v-for="g in groupList" :key="g.id"-->
<!--                               :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"-->
<!--                    />-->
<!--                </div>-->
<!--            </div>-->
<!--        </div>-->
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
// import GroupCard from "@/views/group/GroupCard";
import {showLoading,hideLoading,hideLoadingAndNotify} from "@/api/loading"
import GroupInfoCard from "@/views/group/GroupInfoCard";
export default {
    name: "GroupList",
    components: {GroupInfoCard},
    data() {
        return{
            groupList: [],
            // mangeList: [],
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            showLoading()
            getRequest("/group/list", {userId: this.$store.state.CzpUser.id}).then(res => {
                console.log("GroupList", res);
                // hideLoadingAndNotify(res);
                hideLoading();
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
            // getRequest("/group/clan",{id: this.$store.state.CzpUser.id}).then(res=>{
            //     if (res.code === 200) {
            //         this.mangeList = res.data;
            //     }
            // })
        },
    }
}
</script>

<style scoped>
.breadcrumb_body {
    height: 30px;
    padding: 10px;
}
.mannage_box {
    width: 50%;
    padding: 30px;
}
.join_box {
    width: 50%;
    padding: 30px;
}
</style>