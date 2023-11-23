<template>
    <div class="dialog">
        <div class="title">
            <span style="font-size: 14px;">{{id}}</span>
        </div>
        <div class="message-list">
            <div class="message-list-content min_h_100">
                <div v-for="(m,index) in msgList" :key="index">
                    <chat-line-right v-if="m.fromId===$store.state.CzpUser.id" :content="m.content" :avatar-url="m.user.avatar" :type="m.type" :time="m.sendTime"/>
                    <chat-line v-else :content="m.content" :avatar-url="m.user.avatar" :type="m.type" :time="m.sendTime"/>
                </div>
            </div>
        </div>
        <div class="send-box">
            <div class="row">
                <div  class="space-margin"><label class="image-upload-btn"></label></div>
                <div  class="space-margin emoji-container">
                    <div ref="emoji">
                        <VEmojiPicker  v-show="showEmoji" @select="selectEmoji"/>
                    </div>
                    <label ref="exp" title="表情" class="emotion-btn-box" @click="showEmoji = !showEmoji"></label>
                </div>
            </div>
            <div class="input-box">
                <div class="core-style" style="height: 60px">
                    <el-input
                        id="input"
                        v-model="text"
                        type="textarea"
                        :autosize="{ minRows: 2, maxRows: 2}"
                        maxlength="500"
                        resize="none"
                        placeholder="请输入内容">
                    </el-input>
                </div>
                <div class="indicator" style="bottom: -30px;right: 100px">
                    <span>{{text.length}}</span>/<span>500</span>
                </div>
            </div>
            <div class="row right">
                <button  class="btn-box send-btn" title="enter 发送 shift + enter 换行" @click="send">发送</button>
            </div>
        </div>
    </div>
</template>

<script>
import ChatLine from "@/views/chat/ChatLine";
import ChatLineRight from "@/views/chat/ChatLineRight";
import {VEmojiPicker} from 'v-emoji-picker'
import {getRequest,postRequest} from "@/api/api";
import user from "@/views/search/User";
export default {
    name: "ChatContent",
    components:{ChatLine,ChatLineRight,VEmojiPicker},
    props: ['id'],
    data() {
        return{
            msgList: [],
            text:'',
            showEmoji: false,
        }
    },
    mounted() {
        this.loadData();
        this.$bus.$on('chat', this.chat);
        document.addEventListener('click', this.emojiListener);
    },
    beforeDestroy() {
        this.$bus.$off('chat');
    },
    destroyed() {
        document.removeEventListener('click', this.emojiListener);
    },
    methods:{
        loadData() {
            getRequest("/czp-message/message-list", {
                u1: this.$store.state.CzpUser.id,
                u2: this.$route.query.id
            }).then(res => {
                console.log(res);
                if (res.code === 200) {
                    this.msgList = res.data;
                }
            });
        },
        chat(data) {
            console.log(data);
        },
        send() {
            var param = {
                fromId: this.$store.state.CzpUser.id,
                toId: this.$route.query.id,
                content: this.text,
                type: "user",
            };
            postRequest("/czp-message/send",param).then(res=>{
            
            })
        },
        
        selectEmoji(emoji) {// 选择emoji后调用的函数
            let input = document.getElementById("input")
            let startPos = input.selectionStart
            let endPos = input.selectionEnd
            let resultText = input.value.substring(0, startPos) + emoji.data + input.value.substring(endPos)
            input.value = resultText
            input.focus()
            input.selectionStart = startPos + emoji.data.length
            input.selectionEnd = startPos + emoji.data.length
            this.text = resultText
        },
        emojiListener(e) {
            if (!(this.$refs.emoji.contains(e.target) || this.$refs.exp.contains(e.target))) this.showEmoji = false;
        },
    }
    
}
</script>

<style scoped lang="less">
.dialog{
    display: -webkit-box;
    display: -ms-flexbox;
    display: flex;
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -ms-flex-direction: column;
    flex-direction: column;
    -webkit-box-align: stretch;
    -ms-flex-align: stretch;
    align-items: stretch;
    height: 100%;
    .title{
        height: 36px;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        display: -webkit-box;
        display: -ms-flexbox;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        align-items: center;
        border-bottom: 1px solid #e9eaec;
        position: relative;
        text-align: center;
        color: #333;
    }
    .message-list {
        -webkit-box-flex: 1;
        -ms-flex: 1;
        flex: 1;
        position: relative;
        overflow-x: hidden;
        overflow-y: scroll;
        background-color: #f4f5f7;
        .message-list-content{
            overflow: auto;
        }
        .min_h_100 {
            min-height: 100%!important;
        }
    }
    .send-box {
        height: 162px;
        border-top: 1px solid #d8d8d8;
        border-bottom-right-radius: 4px;
        background-color: #f4f5f7;
        -ms-flex-negative: 0;
        flex-shrink: 0;
        padding: 0 16px;
        position: relative;
        z-index: 2;
        .row{
            height: 48px;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: horizontal;
            -webkit-box-direction: normal;
            -ms-flex-direction: row;
            flex-direction: row;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            .space-margin {
                margin-right: 16px;
                .image-upload-btn{
                    display: block;
                    width: 22px;
                    height: 21px;
                    cursor: pointer;
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABYAAAAVCAYAAABCIB6VAAAAAXNSR0IArs4c6QAAAjFJREFUOBG1lUtrU0EUgHMfSX5AkEINSOmii4JuigihFERpoIgrsykEal6bLgQ34h8oXZUiCnlgaRZSshErhZQWAn24sEUFoSRIQQlEEZJNaRpoHn7n0gupN2mTWx04mTkz53znzJm5E8Vx1pLJ5P1mszmpKMqgOddP32q1SqqqrkcikQ3xU+UnHo8vAs0CHRPdThNfYQhL/JVEInGPaOuapk2Fw+GsHajpk0ql/I1GY43M/SpQP9G2rwoVuDCEJSVVGUhNS2ZU6YnsQx4Q1ChV+1oP45IwLY6UJsB2dpBVDnSuB1BHEwuYLNsPsH3cEdBt0gLGMI4cIL8Q2xnrOJ9rsVjskInRc5M2lE4Z28BYXf4b2FIKa2zrDLdlgFszyUHfZPUU+ezxeN4HAoET07ovcC6X0wuFwvN6vf4MwE/kI3dWo58pl8uvuKqP+wYLNJ/Pr+B4m0/2EYe8ZkIymYxWqVRm2UWGta8E+yZvxRsx8Hq9M8Vi8Snbm0YdQRrIF2TZ7Xa/rtVq84wfOp3OCT7dH4wtjQdoCv93LGwZYBQducHENfp5HqRPRHeh+5BZMpDaDZGNLxqN7jHu2oB/YPHUqDGPxh2U3y6X61YoFDpq89pNp9Mvq9XqAvCly6Dih9136XWg0l9ni3f/gsq8IxgMHtNFDaW3n0F2va/quv6WLToIMNybX3creY+BjpN1VhGzs1dfarmNWurueuGKZCrQF5TsiQEW83/xnyeZAt0U3h+pCu2ZMB0nRQAAAABJRU5ErkJggg==) no-repeat;
                    background-size: 22px;
                }
            }
            .emoji-container {
                position: relative;
                .emotion-btn-box {
                    display: block;
                    width: 24px;
                    height: 24px;
                    cursor: pointer;
                    background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABgAAAAYCAYAAADgdz34AAAB5UlEQVR4AWIYMDBz5kzH2bNnOxGhzmbOnDnWJFswa9asSYDWygCigSgO41YAJQAoWSMgAIQASQoUQJbEZAGM0aRERQAEuAFgAkIpAVXDUAzAKLBEQWptcf0+Hp6zd3fPGp+z//u+7/9/9+67Q/wWBMGoi1Or1UbgvMI99W5QrVYnEHfYxV7M9LvisIPJ1MaaCtMVpiojvpcBOAb7EajWgfcgrjTSxppD2kT0jqANrsEleKZ2wfXchmrgRRzDbUsrD9c9L4FPCBthGA753lJppJWHvKLm03Tvcl0c9OmTB149edqHdULh6r8ecXnJ0y7cgLJ9FvwvehgW7XsvL3nahAaEgtVgntoHOGOSMZex1sQRVxpLX5Cns4HJwZTqoAUWouaqmbWGuKp5NdCPFK8y5Rfr9T4N6loTR/+9Guj1QC0wQSr1e2xV05rhBNKkbsD/I4SPYCbpgMWB/8T1MHUDIj/MhBmPkGWk8T4DQ55TCEFoA82P4zXt1wCzHOu/Mo2ghyabtoGSV4kJ0pYMLfOuguXiy0ueduGAwm3CYeYw3TapzSYk+06e9pbGKX5zXRv0PSQP5UOe0QnXdXAs7CR+ONyfz4rxyLu2tgxaJjxN0EiJpjRGu5T44SCVs5oCFFIiL02/xP8BToUvO6m2Rg4AAAAASUVORK5CYII=) center/24px no-repeat;
                    
                }
                .emotion-btn-box button{
                    outline: none;
                }
                .emoji-picker {
                    z-index: 11;
                    position: absolute;
                    width: 380px;
                    height: 280px;
                    top: -290px !important;
                    left: -10px !important;
                    /deep/ #Emojis{
                        height: 200px;
                    }
                }
            }
        }
        .input-box {
            position: relative;
            .core-style {
                width: 100%;
                min-height: 22px;
                max-height: 300px;
                letter-spacing: 1px;
                -webkit-box-sizing: border-box;
                box-sizing: border-box;
                white-space: pre-wrap;
                overflow-x: hidden;
                overflow-y: auto;
                color: #222;
                background-color: transparent;
                font-size: 14px;
                vertical-align: baseline;
                outline: none;
                line-height: normal;
                border-radius: 4px;
                word-break: break-word;
                word-wrap: break-word;
                /deep/ .el-textarea__inner{
                    border: none;
                    background-color: #f4f5f7;
                    font-size: 14px;
                }
                /deep/ .el-textarea__inner:hover {
                    border: none;
                }
            }
            .indicator {
                color: #c0c0c0;
                position: absolute;
                right: 5px;
                bottom: 0;
                padding: 1px;
                font-size: 12px;
            }
        }
        .right {
            -webkit-box-pack: end;
            -ms-flex-pack: end;
            justify-content: flex-end;
            height: 46px;
            .btn-box {
                cursor: pointer;
                margin: 0;
                position: relative;
                width: 88px;
                height: 30px;
                text-align: center;
                border: 1px solid #e7e7e7;
            }
            .send-btn{
                background-color: #fff;
                border-radius: 4px;
            }
        }
    }
}

</style>