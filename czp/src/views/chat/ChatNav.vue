<template>
    <div class="chat_container">
        <div class="left_menu">
            <el-menu
                :unique-opened="true"
                active-text-color="#367fff"
                background-color="#fff"
                class="el-menu-container"
                text-color="#545c64"
                :router="true"
                :default-active="$route.path"
            >
                <el-menu-item index="/home/msg/chat">
                    <i class="el-icon-s-home"></i>
                    <span slot="title">消息</span>
                </el-menu-item>
            </el-menu>
        </div>
        <div class="right_content">
            <router-view></router-view>
        </div>
    </div>
</template>

<script>
export default {
    name: "ChatNav",
}
</script>

<style lang="less" scoped>
.chat_container {
    width: 980px;
    height: calc(100vh - 50px);
    margin: 0 auto;
    //background-color: #ffffff;
    background-color: rgba(255, 255, 255, 0.5);
    display: flex;
    .left_menu {
        box-sizing: border-box;
        width: 140px;
        height: 100%;
        background-color: rgba(255, 255, 255, 0.8);
    }
    
    .right_content {
        box-sizing: border-box;
        width: calc(100% - 140px);
    }
    
}
</style>