<template>
    <div class="gd_body main">
        <el-row>
            <el-col :span="1" class="head-line">
                <div><i class="el-icon-back  back-arrow"  @click="goBack"></i></div>
            </el-col>
            <el-col :span="2">
                <span class="head-line head-title">{{groupInfo.groupName}}</span>
            </el-col>
            <el-col :span="21">
                <span class="head-line head-title">{{groupInfo.area}}</span>
            </el-col>
        </el-row>
        <el-row>
            <el-col :span="24">
                <el-tabs v-model="activeName" @tab-click="handleClick">
                    <el-tab-pane label="概要" name="first">
                        <GroupInfoCard :group-info="groupInfo" />
                    </el-tab-pane>
                    <el-tab-pane label="族群树" name="second" lazy>
                        <GroupTreeDetails/>
                    </el-tab-pane>
                    <el-tab-pane label="人员信息" name="third">
                        <GroupPeropleInfo/>
                    </el-tab-pane>
                </el-tabs>
            </el-col>
        </el-row>
    </div>
</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest} from "@/api/api";
import GroupInfoCard from "@/views/group/down/pane/GroupInfoCard";
import GroupTreeDetails from "@/views/group/down/GroupTreeDetails";
import GroupPeropleInfo from "@/views/group/down/GroupPeropleInfo";
export default {
    name: "GroupInfo",
    components:{GroupInfoCard,GroupTreeDetails,GroupPeropleInfo},
    data() {
        return{
            activeName: 'first',
            groupInfo: {
                id: '',
                groupName: '',
                area: '',
                livingPeopleTotal: '',
                peopleTotal: '',
                des: '',
            },
            
            
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            showLoading();
            getRequest("/group/groupInfo",{id: this.$route.query.gid}).then(res=>{
                hideLoading();
                if (res.code === 200) {
                    this.groupInfo = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
        handleClick() {
        
        },
        goBack() {
            this.$router.back();
        },
    }
}
</script>

<style scoped lang="less">
.main{
    background: white;
}
.back-arrow{
    color: #227cf9;
    width: 26px;
    height: 26px;
    line-height: 26px;
    cursor: pointer;
    text-align: center;
}
.head-title{
    height: 26px;
}
.back-arrow:hover {
    background: #f2f2f2;
}
</style>