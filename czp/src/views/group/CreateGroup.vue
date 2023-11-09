<template>
    <div class="creater_body">
        <div class="breadcrumb_body">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a :href="$route.path">族群信息</a></el-breadcrumb-item>
                <el-breadcrumb-item class="now_text">新建族群</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="cg_panel">
                <div class="form_box">
                    <el-form ref="form" :model="group" :rules="rules" label-width="80px" size="medium" >
                        <el-form-item label="族群名称" prop="groupName">
                            <el-input v-model="group.groupName"></el-input>
                        </el-form-item>
                        <el-form-item label="地理位置" prop="area">
                            <el-input v-model="group.area"></el-input>
                        </el-form-item>
                        <el-form-item label="创建时间" prop="createTime">
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
            rules: {
                groupName: [
                    {required: true, message: "请输入族群名称", trigger: "blur"}
                ],
                area: [
                    {required: true, message: "请输入地区名称", trigger: "blur"}
                ],
                createTime: [
                    {required: true, message: "请输入族群创建时间", trigger: "blur"}
                ]
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
.creater_body {
    width: 100%;
    height: 100%;
}
.breadcrumb_body {
    height: 30px;
    padding: 10px;
}
.now_text >>> .el-breadcrumb__inner {
    color: #65CEA7;
}

.cg_panel {
    background-color: white;
    padding: 10px;
    text-align: center;
}
.form_box {
    width: 50%;
    margin: 0 auto;
}
</style>