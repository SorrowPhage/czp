<template>
    <div>
        <div class="person_top">
            <div class="person_top_img">
                <AvatarCropper :id="userInfo.id"/>
            </div>
<!--            <div class="person_top_text">-->
<!--                <div class="user_text">-->
<!--                    <div class="user_name">-->
<!--                        <span> {{ userInfo.name }} </span>-->
<!--                    </div>-->
<!--                </div>-->
<!--            </div>-->
        </div>
        <div class="person_body">
            <div style="margin: 15px;width: 100%">
                <el-form ref="form"  :label-position="labelPosition" :model="userInfo" label-width="100px" size="mini">
                    <div class="info-box">
                        <div class="left-info">
                            <el-form-item label="账号：" >
                                <span class="info-input" >{{ userInfo.id }}</span>
                            </el-form-item>
                            <el-form-item label="邮箱：">
                                <span class="info-input">{{ userInfo.email }}</span>
                            </el-form-item>
                            <el-form-item label="生日：">
                                <el-date-picker
                                    class="info-input"
                                    v-model="userInfo.birthday"
                                    placeholder="选择日期"
                                    type="date">
                                </el-date-picker>
                            </el-form-item>
                            <el-form-item label="性别：">
                                <el-select v-model="userInfo.sex" class="info-input" placeholder="类型"
                                           style="width: 100px">
                                    <el-option
                                        v-for="item in options"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                    >
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </div>
                        <div class="right-info">
                            <el-form-item label="名字：">
                                <el-input v-model="userInfo.name" class="info-input" ></el-input>
                            </el-form-item>

                            <el-form-item label="排行：">
                                <el-input v-model="userInfo.familyRank"  class="info-input"></el-input>
                            </el-form-item>
                            <el-form-item label="世代排行：">
                                <el-input v-model="userInfo.generationRank"  class="info-input"></el-input>
                            </el-form-item>
                            <el-form-item label="辈分：">
                                <el-input v-model="userInfo.generation"  class="info-input"></el-input>
                            </el-form-item>
<!--                            <el-form-item label="家族名称：">-->
<!--                                <el-input v-model="userInfo.familyName"  class="info-input"></el-input>-->
<!--                            </el-form-item>-->
                        </div>
                    </div>
                </el-form>
            </div>
            <div style="margin: 15px;text-align: center;width: 100%">
                <el-button type="primary" @click="updateUserInfo">修改</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import AvatarCropper from "@/views/user/AvatarCropper";
import {postRequest} from "@/api/api";
export default {
    name: "Personal",
    components: {AvatarCropper},
    data() {
        return {
            options: [{
                value: '男',
                label: '男'
            }, {
                value: '女',
                label: '女'
            }],
            labelPosition: 'right',
            dialogVisible: false,
            userInfo: {
                avatar: '',
                name: '',
                id: '',
                sex: '',
                birthday: '',
                familyRank: '',
                generation: '',
                generationRank: '',
                email: '',
                deathTime: '',
                phone: '',
                area: '',
                familyName: ''
            },
        };
    },
    mounted() {
        this.loadData();
    },
    methods: {
        loadData() {
            getRequest("/czpUser/userinfo", {id: this.$store.state.CzpUser.id}).then(res => {
                console.log("UserHeader", res);
                this.userInfo = res.data;
            })
        },
        format(time) {
            var data = new Date(time);
            var month =data.getMonth() < 9 ? "0" + (data.getMonth() + 1) : data.getMonth() + 1;
            var date = data.getDate() <= 9 ? "0" + data.getDate() : data.getDate();
            return data.getFullYear() + "-" + month + "-" + date;
        },
        updateUserInfo() {
            this.userInfo.birthday = this.format(this.userInfo.birthday);
            postRequest("/czpUser/update",this.userInfo).then(res =>{
                if (res.code === 200) {
                    this.$store.commit("CzpUser/updateName", res.data);
                    this.$message({
                        message: '修改成功',
                        showClose: true,
                        type: 'success',
                        center: true,
                    });
                }
            })
        },
    },
};
</script>

<style scoped>
.person_top {
    width: 1000px;
    height: 110px;
    padding-top: 20px;
    background-color: white;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    border-radius: 5px;
}

.person_top_img {
    margin-right: 24px;
    margin-left: 20px;
}

.person_top_text {
    width: 900px;
}

.person_top_btn {
    width: 100px;
}

.person_body {
    width: 1000px;
    margin-top: 15px;
    position: relative;
    left: 50%;
    transform: translateX(-50%);
    border-radius: 5px;
    background-color: white;
    display: flex;
    flex-wrap: wrap;
}

.info-box {
    overflow: auto;
    width: 100%;
}

.left-info {
    float: left;
    width: 50%;
}

.right-info {
    overflow: hidden;
    width: 50%;
}
.info-input{
    /*width: 500px;*/
}
</style>
