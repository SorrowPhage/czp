<template>
    <el-container class="el-container">
        <el-aside class="el-aside-container" width="200px">
            <el-row>
                <el-col style="height: 100vh; margin-top: -50px; padding-top: 50px;">
                    <el-menu
                        :unique-opened="true"
                        active-text-color="#367fff"
                        background-color="#fff"
                        class="el-menu-container"
                        text-color="#545c64"
                        :router="true"
                        :default-active="$route.path"
                    >
                        <el-menu-item index="/home/setting/own">
                            <i class="el-icon-user-solid"></i>
                            <span slot="title">基本信息</span>
                        </el-menu-item>
                        <el-menu-item index="/home/setting/pc">
                            <i class="el-icon-setting"></i>
                            <span slot="title">修改密码</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </el-row>
        </el-aside>
    
        <el-main>
            <router-view></router-view>
        </el-main>
    </el-container>
</template>

<script>
export default {
    name: "PersnoalNav",
    methods: {
    }
}
</script>

<style lang="less" scoped>
.el-container {
    height: 100%;
    
    .el-aside-container {
        height: 100%;
        
        .el-menu-container {
            height: 100%;
            //height: 100vh;
        }
    }
    
    .header-container {
        text-align: right;
        font-size: 12px;
        background-color: #545c64;
    }
}
</style>