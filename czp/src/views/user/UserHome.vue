<template>
    <div class="gd_body_main gd_main" style="display: flex">
        <div style="width: 25%">
            诗八句
        </div>
        <div style="width: 50%;overflow-y: auto">
            一些族群事件，或者管理员可以发布和置顶一些事件
        </div>
        <div style="width: 25%">
            <ChatList v-for="u in chatList" class="infinite-list-item"
                      :key="u.id"  :id="u.id" :avatar="u.avatar" :name="u.name" :des="u.des"
            />
        </div>
    </div>
</template>

<script>
import ChatList from "@/views/user/ChatList";
import {getRequest} from "@/api/api";

export default {
    name: "UserHome",
    components: {ChatList},
    data() {
        return {
            chatList: [],
        }
    },
    mounted() {
        this.loadData()
    },
    methods: {
        loadData() {
            getRequest("/czp-message/chat-list",{id:this.$store.state.CzpUser.id}).then(res=>{
                if (res.code === 200) {
                    this.chatList = res.data;
                }
            })
        },
    }
};
</script>

<style scoped lang="scss">
.body_home {
    height: 100%;
    overflow: hidden;
    width: 100%;
    margin: 0 auto;
}
.body_home /deep/ .rel-map {
    background-image: linear-gradient(
            125deg,
            #255877,
            #287263
    );
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
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}


.bcg_color_male {
    color: #227cf9;
}

.bcg_color_female {
    color: #f922f5;
}
.g_toolbar {
    top: 60px;
    left: 16px;
    //height: 45px;
    max-width: 270px;
    position: absolute;
    z-index: 300;
    background-color: #dcdcdc33;
    color: #999;
    box-shadow: 0 0 2px #ccc;
    box-sizing: border-box;
    border-radius: 5px;
    padding: 3px;
}
/deep/ .el-select-dropdown {
    //background-color: #dcdcdc33;
    border: none;
    font-family: 华文行楷,serif;
}
///deep/ .el-select-dropdown__item.hover {
//    background-color: #545c64;
//}
/deep/ .el-input__inner {
    background-color: rgba(148, 20, 20, 0);
    border: none;
    font-family: 华文行楷,serif;
}
///deep/ .popper__arrow::after {
//    border-bottom-color: rgba(225, 24, 24, 0.9);
//}
.g_button {
    height: 40px;
    margin-top: 0;
    opacity: 1;
    text-align: center;
    padding-top: 3px;
    cursor: pointer;
    font-size: 18px;
    box-sizing: border-box;
    line-height: 21px;
    display: flex;
    align-items: center;
    border-radius: 3px;
}
.g_icon {
    width: 16px;
    height: 16px;
    vertical-align: -3px;
    fill: currentColor;
    overflow: hidden;
}
//.watercolor-font {
//    font-family: 'YourWatercolorFont', cursive;
//    font-size: 24px;
//    color: black;
//    text-align: center;
//    margin-top: 50px;
//    opacity: 0;
//    animation: fadeIn 2s forwards;
//}
//
//@keyframes fadeIn {
//    from {
//        opacity: 0;
//    }
//    to {
//        opacity: 1;
//    }
//}
//
//.watercolor-font p {
//    animation: fadeIn 2s forwards;
//    opacity: 0;
//}
//
//.watercolor-font p:nth-child(1) {
//    animation-delay: 2s;
//}
//
//.watercolor-font p:nth-child(2) {
//    animation-delay: 4s;
//}
//
//.watercolor-font p:nth-child(3) {
//    animation-delay: 6s;
//}
//
//.watercolor-font p:nth-child(4) {
//    animation-delay: 8s;
//}
.watercolor-font {
    font-family: 'YourWatercolorFont', cursive;
    font-size: 24px;
    color: black;
    text-align: center;
    margin-top: 50px;
}

.watercolor-font span {
    opacity: 0;
    animation: fadeIn 1s forwards;
    animation-delay: calc(0.1s * var(--i));
    display: inline-block;
}

@keyframes fadeIn {
    from {
        opacity: 0;
    }
    to {
        opacity: 1;
    }
}
.list_item{
    width: 100%;
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: horizontal;
    -webkit-box-direction: normal;
    -ms-flex-direction: row;
    flex-direction: row;
    padding: 19px 24px;
    position: relative;
    overflow: hidden;
    cursor: pointer;
    .avatar{
        display: block;
        width: 40px;
        height: 40px;
        border-radius: 50%;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        background-image: url(//static.hdslb.com/images/member/noface.gif);
        margin-right: 8px;
        position: relative;
    }
    .name_box{
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-orient: vertical;
        -webkit-box-direction: normal;
        -ms-flex-direction: column;
        flex-direction: column;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        .name{
            color: #333;
            font-size: 14px;
            min-height: 16px;
            line-height: 1;
            overflow: hidden;
            width: 155px;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
        .last_word{
            color: #999;
            padding: 8px 0;
            margin-bottom: -6px;
            overflow: hidden;
            width: 155px;
            white-space: nowrap;
            text-overflow: ellipsis;
        }
    }
    
    &:hover .close_box{
        display: block;  // 设置父元素hover时子元素的样式 【实现要点！！！！！】
    }
    
    .close_box {
        display: none;
        .close {
            position: absolute;
            top: 0;
            bottom: 0;
            left: 0;
            display: flex;
            -webkit-box-pack: center;
            -ms-flex-pack: center;
            justify-content: center;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            width: 24px;
            color: #999;
            .css-1dtzbno {
                fill: currentcolor;
                width: 1em;
                height: 1em;
            }
        }
    }
    
    
    .close {
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        width: 24px;
        color: #999;
        .css-1dtzbno {
            fill: currentcolor;
            width: 1em;
            height: 1em;
        }
    }
    
}
.list_item:hover{
    background-color: #e4e5e6;
}
.showuser{
    background-color: #e4e5e6;
}
.changeShow-enter-active {
    animation: changeShow 300ms;
}
.changeShow-leave-active {
    animation: changeShow 300ms reverse;
}
@keyframes changeShow {
    from {
        transform: translateX(-100%);
    }
    to {
        transform: translateX(0px);
    }
}
</style>