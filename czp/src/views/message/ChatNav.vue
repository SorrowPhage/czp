<template>
    <div class="chat_body">
        <div class="chat_container">
            <div class="left_menu">
                <el-menu
                    :unique-opened="true"
                    active-text-color="#367fff"

                    class="el-menu-container"
                    text-color="#545c64"
                    :router="true"
                    :default-active="$route.path"
                >
                    <el-menu-item index="/home/msg/chat">
                        <i class="el-icon-s-comment"></i>
                        <span slot="title">消息</span>
                    </el-menu-item>
                    <el-menu-item index="/home/msg/ar">
                        <i class="el-icon-notebook-2"></i>
                        <span slot="title">申请</span>
                    </el-menu-item>
                </el-menu>
            </div>
            <div class="right_content">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ChatNav",
}
</script>

<style lang="less" scoped>
.chat_body {
    width: 100%;
    height: 100%;
    background-image: linear-gradient(125deg,
    #2c3e50,
    #27ae60,
    #2980b9,
    #eb4d4b,
    #2c3e50);
    background-size: 400%;
    animation: bganimation 15s infinite;
    position: fixed;
}
@keyframes bganimation {
    0% {
        background-position: 0 50%;
    }
    
    50% {
        background-position: 100% 50%;
    }
    
    100% {
        background-position: 0 50%;
    }
}
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
        /deep/ .el-menu {
            border: none;
            background-color: rgba(255, 255, 255, 0);
        }
    }
    
    .right_content {
        box-sizing: border-box;
        width: calc(100% - 140px);
    }
    
}
</style>