<template>
    <el-container>
        <el-main>
            <div>
                <div class="user_top">
                    <div style="width: 100%;height: 90px"></div>
                    <div class="user_info">
                        <div class="info_box">
                            <div style="width: 75px;height:75px;margin-left: 10px;display: flex;align-items: center">
                                <el-avatar :src="user.avatar" :size="60"/>
                            </div>
                            <div style="flex: 1;margin-left: 20px;margin-top: 10px">
                                <span style="font-size: 20px;font-family: 华文行楷,serif">{{user.name}}</span>
                            </div>
                            <div style="width: 200px;display: flex;align-items: center">
                                <el-button type="primary" plain @click.native="chat(user.id)">发消息</el-button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="user_body">
                    <div>
                        <div>
                            <el-descriptions title="用户信息">
                                <el-descriptions-item label="账户名">{{user.id}}</el-descriptions-item>
                                <el-descriptions-item label="性别">{{user.sex}}</el-descriptions-item>
                                <el-descriptions-item label="生日">{{user.birthday}}</el-descriptions-item>
                                <el-descriptions-item label="家中排行">{{user.familyRank}}</el-descriptions-item>
                                <el-descriptions-item label="辈分">{{user.generation}}</el-descriptions-item>
                                <el-descriptions-item label="世代排行">{{user.generationRank}}</el-descriptions-item>
                            </el-descriptions>
                        </div>
                    </div>
                </div>
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
    /*width: 80%;*/
    /*width: 980px;*/
    width: calc(100vh - 260px);
    min-width: 980px;
    height: 160px;
    margin: 0 auto;
    /*background-color: #545c64;*/
    background-color: white;
}
.user_info {
    width: 100%;
    height: 70px;
    /*background-color: white;*/
}
.info_box{
    display: flex;
}
.user_body {
    width: calc(100vh - 260px);
    min-width: 980px;
    margin: 30px auto 0;
    background: white;
}
</style>