<template>
    <div style="display: inline-block;margin-top: 15px">
        <div class="side-info-box" style="margin-right: 5px;height: 100%" @click="loadInformation"><i
            class="el-icon-menu"></i></div>
        <el-drawer
            :before-close="handleClose"
            :direction="direction"
            :visible.sync="drawer"
            :with-header="false"
            append-to-body
        >
            <div>
                <div>
                    <div class="sp-info-head">
                        <el-avatar :size="60" :src="userInfo.avatar" />
                    </div>
                    <div class="sp-info-head">
                        <span> {{ userInfo.name }} </span>
                    </div>
                </div>
                <div style="margin-top: 15px;box-sizing: border-box">
                    <div>
                        <el-descriptions class="margin-top" title="" :column="1" border>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-user"></i>
                                    账户名
                                </template>
                                {{userInfo.id}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-s-custom"></i>
                                    名字
                                </template>
                                {{userInfo.name}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-odometer"></i>
                                    出生日期
                                </template>
                                {{userInfo.birthday}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-male"></i>
                                    <i class="el-icon-female"></i>
                                    性别
                                </template>
                                <el-tag size="small">{{userInfo.sex}}</el-tag>
                            </el-descriptions-item>
<!--                            <el-descriptions-item>-->
<!--                                <template slot="label">-->
<!--                                    <i class="el-icon-odometer"></i>-->
<!--                                    死亡日期-->
<!--                                </template>-->
<!--                                {{userInfo.deathTime}}-->
<!--                            </el-descriptions-item>-->
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-message"></i>
                                    邮箱Email
                                </template>
                                {{userInfo.email}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-mobile-phone"></i>
                                    手机号码
                                </template>
                                {{userInfo.phone}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    <i class="el-icon-location-outline"></i>
                                    地区
                                </template>
                                {{userInfo.area}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    排行
                                </template>
                                {{userInfo.familyRank}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    世代排行
                                </template>
                                {{userInfo.generationRank}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    世代辈分
                                </template>
                                {{userInfo.generation}}
                            </el-descriptions-item>
                            <el-descriptions-item>
                                <template slot="label">
                                    家族名称
                                </template>
                                {{userInfo.familyName}}
                            </el-descriptions-item>
                        </el-descriptions>
                    </div>
                </div>
                <div style="text-align: center;margin-top: 15px">
                    <el-button type="primary" plain @click="goPersonal()">编辑资料</el-button>
                </div>
            </div>
        </el-drawer>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";

export default {
    name: "SideInformationBar",
    data() {
        return{
            drawer: false,
            direction: 'ltr',
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
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        handleClose(done) {
            done();
        },
        loadInformation() {
            this.drawer = true;
        },
        goPersonal() {
            this.drawer = false;
            this.$router.push({
                name: "setting",
            })
        },
        loadData() {
            getRequest("/czpUser/userinfo", {id: this.$store.state.CzpUser.id}).then(res => {
                console.log("UserHeader", res);
                this.userInfo = res.data;
            })
        },
    }
}
</script>

<style scoped>
.side-info-box {
    display: inline;
    margin-left: 10px;
    font-weight: bold;
    font-family: ui-monospace, SFMono-Regular, SF Mono, Menlo, Consolas, Liberation Mono, monospace !important;
    color: #409EFF;
    font-size: 16px !important;
}
.sp-info-head {
    display: block;
    margin: 5px auto 0;
    width: 100%;
    height: 100%;
    text-align: center;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-weight: bold;
}
</style>