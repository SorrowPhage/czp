<template>
    <div class="list_item"  @mouseenter="showDiv=true" @mouseleave="showDiv=false">
<!--        <div class="avatar">-->
<!--        -->
<!--        </div>-->
        <el-avatar :src="avatar" style="margin-right: 8px;"/>
        <div class="name_box">
            <div class="name">
                {{name}}
            </div>
            <div class="last_word">
                {{des}}
            </div>
        </div>
        <transition  name="changeShow" appear>
            <div class="close" v-show="showDiv">
                <svg data-v-18a3b98b="" viewBox="0 0 40 40" class="css-1dtzbno"><path d="M22.83,20,38.42,4.41a2,2,0,1,0-2.83-2.83h0L20,17.17,4.41,1.58A2,2,0,0,0,1.58,4.41L17.17,20,1.58,35.59a2,2,0,0,0,2.83,2.83L20,22.83,35.59,38.42a2,2,0,1,0,2.83-2.83Z"></path></svg>
            </div>
        </transition>
<!--        <div class="close_box">-->

<!--        </div>-->
    </div>
</template>

<script>
export default {
    name: "UserItem",
    props: ['id', "avatar", "name", "des"],
    data() {
        return {
            showDiv: false,
            staticAvatarUrl:  require('@/assets/img/czp.jpg') // 静态图片的URL
        }
    },
    computed:{
        avatarUrl: function() {
            return this.avatar ? this.avatar : this.staticAvatarUrl;
        }
    }
};
</script>

<style scoped lang="less">
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