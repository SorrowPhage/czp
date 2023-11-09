<template>
    <div style="background-color: white" >
        <el-steps :active="active" finish-status="success" align-center>
            <el-step title="选择族群"></el-step>
            <el-step title="选择父级节点"></el-step>
            <el-step title="提交"></el-step>
        </el-steps>
        <div>
            <div v-if="active===0">
                <el-input placeholder="搜索族群" v-model="group"></el-input>
                <el-button type="primary" @click="getGroup" plain>搜索</el-button>
                这里写一个接口，模糊查询族群id和名字，在下面展示相关族群信息，用户点击其中一个族群，即为要加入的族群
                <GroupCard v-for="g in groupList" :key="g.id"
                           :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"
                           @click.native="selectGroup(g.id)"
                />
            </div>
            <div v-if="active===1">
                这里的接口未，根据族权用户选择该族群或搜索该族群的一个用户，即为父级id，
                <el-button type="primary" plain @click="becomeFirst">族群树第一级</el-button>
                <PeopleCard v-for="u in userList" :key="u.id"
                    :name="u.name" :avatar="u.avatar" :des="u.des"
                />
            </div>
            <div v-if="active===2">
                申请提交成功
                <el-button>返回</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import {getRequest,postRequest} from "@/api/api";
import GroupCard from "@/views/group/GroupCard";
import PeopleCard from "@/views/user/PeopleCard";
export default {
    name: "FamilyInfo",
    components:{GroupCard,PeopleCard},
    data() {
        return {
            active: 0,
            group: '',
            groupList: [],
            userList: [],
            groupId: '',
            
        };
    },
    methods: {
        getGroup() {
            getRequest("/group/search",{id:this.group}).then(res=>{
                console.log("FamilyInfo", res);
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            })
        },
        becomeFirst() {
            postRequest("/audit/apply",{groupId:this.groupId,userId:this.$store.state.CzpUser.id}).then(res=>{
                console.log("FamilyInfo", res);
                if (res.code === 200) {
                    this.$message({
                        message: res.message,
                        type: "success",
                    })
                    this.active = this.active + 1;
                }
            })
        },
        selectGroup(id) {
            this.groupId = id;
            //TODO
            getRequest("/group/userList",{id:this.groupId,userId:this.$store.state.CzpUser.id}).then(res=>{
                console.log("FamilyInfo", res);
                if (res.code === 200) {
                    this.userList = res.data;
                    this.active = this.active + 1;
                } else {
                    this.$message({
                        message: res.message,
                        type: "warning",
                    })
                }
            })
        },
    }
}
</script>

<style scoped>

</style>