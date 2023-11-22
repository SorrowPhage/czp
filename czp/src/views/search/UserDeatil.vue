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
                                <span style="font-size: 20px;font-family: 华文行楷">{{user.name}}</span>
                            </div>
                            <div style="width: 200px;display: flex;align-items: center">
                                <el-button type="primary" plain @click.native="chat(user.id)">发消息</el-button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="user_body">
                    <div>
                        
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
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/czpUser/su",{id: this.$route.query.id}).then(res=>{
                console.log(res);
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
    width: 80%;
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
    width: 80%;
    background-color: white;
    margin: 20px auto 0;
    flex: 1;
}
</style>