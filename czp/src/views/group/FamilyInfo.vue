<template>
    <div style="height: 100%;background-color: white" >
        <div class="step_box">
            <el-steps :active="active" finish-status="success" align-center>
                <el-step title="选择族群"></el-step>
                <el-step title="选择父级节点"></el-step>
                <el-step title="提交"></el-step>
            </el-steps>
        </div>
        <div>
            <div v-if="active===0" class="step_1">
                <div class="step_1_search">
                    <div style="width: 250px;">
                        <el-input placeholder="搜索族群" v-model="group"></el-input>
                    </div>
                    <div style="width: 50px;margin-left: 10px">
                        <el-button type="primary" @click="getGroup" plain>搜索</el-button>
                    </div>
                </div>
                <div class="search_result">
                    <GroupCard v-for="g in groupList" :key="g.id"
                               :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"
                               @click.native="selectGroup(g.id)"
                    />
                </div>
            </div>
            <div v-if="active===1">
                <el-button type="primary" plain @click="becomeFirst">族群树第一级</el-button>
                <people-card  eCard v-for="u in userList" :key="u.id"
                    :name="u.name" :avatar="u.avatar" :des="u.des"
                            @click.native="selectUser(u.id)"
                />
            </div>
            <div v-if="active===2">
                <span>申请成功</span>
                <el-button @click="result">返回</el-button>
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
        selectUser(id) {
            postRequest("/audit/apply",{groupId:this.groupId,userId:this.$store.state.CzpUser.id, parentId: id}).then(res=>{
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
        result() {
            this.active = 0;
        },
    }
}
</script>

<style scoped>
.step_box {
    height: 60px;
    padding: 15px;
}
.step_1{

}
.step_1_search{
    width: 300px;
    display: flex;
    padding: 10px;
}
.search_result{
    width: 100%;
}
</style>