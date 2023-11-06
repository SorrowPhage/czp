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
                        @select="handleSelect"
                    >
                        <el-menu-item index="1">
                            <i class="el-icon-user-solid"></i>
                            <span slot="title">基本信息</span>
                        </el-menu-item>
                        <el-menu-item index="2">
                            <i class="el-icon-s-home"></i>
                            <span slot="title">家族信息</span>
                        </el-menu-item>
                        <el-menu-item index="3">
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
        handleSelect(key) {
            if (key === "1") {
                this.$router.push({name: 'own'})
            } else if (key === "2") {
                this.$router.push({name: 'fam'})
            }else if (key === "3") {
                this.$router.push({name: 'pc'});
            }
            
        },
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