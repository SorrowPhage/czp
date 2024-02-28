<template>
    <div class="chat-line-box">
        <div class="chat-line-avatar-box">
            <el-avatar  :size="30"  :src="avatarUrl" ></el-avatar>
        </div>
        <el-popover
            placement="top-start"
            width="200"
            trigger="click"
            :content="time">
            <div class="chat-line-content-box" slot="reference">{{content}}</div>
        </el-popover>
        <!--        <div v-show="type===1">已读</div>-->
    </div>
</template>

<script>
export default {
    name: "ChatLine",
    props: ['content','avatarUrl','type','time'],
    computed:{
    
    }
}
</script>

<style scoped>
.chat-line-box {
    z-index: 99;
    font-size: 14px;
    display: flex;
    padding: 5px;
    align-items: center;
}
.chat-line-avatar-box {
    width: 30px;
}
.chat-line-content-box {
    margin-left: 5px;
    margin-right: 35px;
    border-radius: 3px;
    background: #ffffff;
    min-height: 16px;
    padding: 0 8px 8px;
    overflow: hidden;
    position: relative;
}
</style>