<template>
    <div>
        <div class="cg_panel">
            <el-form ref="form" :model="group" label-width="80px">
                <el-form-item label="族群名称">
                    <el-input v-model="group.groupName"></el-input>
                </el-form-item>
                <el-form-item label="地理位置">
                    <el-input v-model="group.area"></el-input>
                </el-form-item>
                <el-form-item label="创建时间">
                    <el-input v-model="group.createTime"></el-input>
                </el-form-item>
                <el-form-item label="族群描述">
                    <el-input type="textarea" v-model="group.des"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="createGroup">立即创建</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
import {postRequest} from "@/api/api";
export default {
    name: "CreateGroup",
    data() {
        return {
            group: {
                groupName: '',
                area: '',
                createTime: '',
                clanElder: '',
                des: '',
            },
        }
    },
    methods: {
        createGroup() {
            this.group.clanElder = this.$store.state.CzpUser.id;
            postRequest("/group/create",this.group).then(res=>{
                console.log(res);
                if (res.code === 200) {
                    this.$message({
                        message: "创建成功",
                        type: "success",
                    });
                }
            })
        }
    }
}
</script>

<style scoped>


</style>