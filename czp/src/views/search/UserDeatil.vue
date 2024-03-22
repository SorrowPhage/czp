<template>
    <el-container>
        <el-main>
            <div class="user_top">
                <div class="avatar_box">
                    <el-avatar :src="user.avatar" :size="60"/>
                </div>
                <div class="user_info">
                    <span class="user_name">{{user.name}}</span>
                    <el-button type="primary" plain @click.native="chat(user.id)">发消息</el-button>
                </div>
            </div>
            <div class="user_body">
                <el-descriptions title="用户信息">
                    <el-descriptions-item label="账户名">{{user.id}}</el-descriptions-item>
                    <el-descriptions-item label="性别">{{user.sex}}</el-descriptions-item>
                    <el-descriptions-item label="生日">{{user.birthday}}</el-descriptions-item>
                    <el-descriptions-item label="家中排行">{{user.familyRank}}</el-descriptions-item>
                    <el-descriptions-item label="辈分">{{user.generation}}</el-descriptions-item>
                    <el-descriptions-item label="世代排行">{{user.generationRank}}</el-descriptions-item>
                </el-descriptions>
            </div>
        </el-main>
    </el-container>
</template>

<script>
import {getRequest} from "@/api/api";
export default {
    name: "UserDeatil",
    data() {
        return{
            user:{}
        }
    },
    watch: {
        '$route.query.id': {
            handler() {
                this.loadData();
            },
        },
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/czpUser/su",{id: this.$route.query.id}).then(res=>{
                if (res.code === 200) {
                    this.user = res.data;
                }
            })
        },
        chat(id) {
            this.$router.push({
                name:"chat",
                query:{
                    id: id,
                }
            })
        },
    }
}
</script>

<style scoped>
.user_top {
    display: flex;
    align-items: center;
    background-color: #f5f5f5;
    padding: 20px;
}

.avatar_box {
    margin-right: 20px;
}

.user_info {
    flex: 1;
}

.user_name {
    font-size: 20px;
    font-weight: bold;
    margin-right: 20px;
}

.user_body {
    margin-top: 20px;
    padding: 20px;
    background-color: #fff;
}
</style>