<template>
    <div class="right">
        <div class="top_box">
            <div class="title">
                <span>消息</span>
            </div>
        </div>
        <div class="body_box">
            <div class="d">
                <div class="p">
                    <div class="left">
                        <div class="title">
                            <span>近期消息</span>
                        </div>
                        <div class="list_container ps--active-y">
                                <div class="list">
                                    <UserItem v-for="u in chatList" class="infinite-list-item"  :style="u.id === $route.query.id ? ' background-color: #e4e5e6;' : 'color:#ffffff;'"
                                          :key="u.id"  :id="u.id" :avatar="u.avatar" :name="u.name" :des="u.des"
                                    />
                                </div>
                        </div>
                    </div>
                    <div class="right">
                        <div v-if="showContent" class="placeholder">
                            <div class="placeholder-img is-im"></div>
                            <div class="tip">快找小伙伴聊天吧 ( ゜- ゜)つロ</div>
                        </div>
                        <div v-else style="height: 100%">
                            <chat-content :id="$route.query.id"/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import ChatContent from "@/views/message/ChatContent";
import UserItem from "@/views/message/UserItem";
import {getRequest} from "@/api/api";
import {closeWebsocket, sendWebsocket} from "@/utils/websocket";
export default {
    name: "Chat",
    components:{UserItem,ChatContent},
    data() {
        return {
            editableTabsValue: '2',
            editableTabs: [{
                title: 'Tab 1',
                name: '1',
                content: 'Tab 1 content'
            }, {
                title: 'Tab 2',
                name: '2',
                content: 'Tab 2 content'
            }],
            tabIndex: 2,
            count: 10,
            showContent: true,
            chatList: [
                {
                    "id": "c-2-2",
                    "name": "C-2-2",
                    "sex": "男",
                    "birthday": "1980-12-21",
                    "avatar": null,
                    "password": "",
                    "status": "1",
                    "email": "470565129@qq.com",
                    "generation": "Cew",
                    "generationRank": "2",
                    "familyRank": "2",
                    "deathTime": "2004-12-01",
                    "phone": null,
                    "des": null,
                    "verCode": null,
                    "token": null
                }
            ],
        }
    },
    watch: {
        '$route.query.id': {
            immediate:true,
            handler() {
                this.show();
                this.loadChatList();
            },
        },
        // 在进来的时候判断一下列表中是否有query.id是可以达到目的的，但是动画有点别扭
        // 'chatList.length': function() {
        //     let exist = this.chatList.some(item => item.id === this.$route.query.id);
        //     if (!exist && this.showContent === false) {
        //         this.getUser();
        //     }
        // }
    },
    mounted() {
        this.loadData()
        this.requestWs();
        if (this.$route.query.id !== undefined) {
            this.showContent = false;
        } else {
            this.showContent = true;
            //加载数据
        }
        this.$bus.$on('closeChat', this.closeChat);
    },
    beforeDestroy() {
        closeWebsocket();
        this.$bus.$off('closeChat');
    },
    methods: {
        requestWs() {
            closeWebsocket();
            sendWebsocket(this.$store.state.CzpUser.id, {}, this.onmessage, this.onerror);
        },
        onmessage(data) {
            //通过data中的type来触发事件
            data = JSON.parse(data)
            if (data.type === "user") {
                //聊天信息
                this.$bus.$emit('chat', data);
            }
        },
        onerror(id) {
        
        },
        loadData() {
            getRequest("/czp-message/chat-list",{id:this.$store.state.CzpUser.id}).then(res=>{
                if (res.code === 200) {
                    this.chatList = res.data;
                }
            })
        },
        loadChatList() {
            let exist = this.chatList.some(item => item.id === this.$route.query.id);
            if (!exist && this.showContent === false) {
                this.getUser();
            }
        },
        getUser() {
            getRequest("/czpUser/su",{id: this.$route.query.id}).then(res=>{
                if (res.code === 200) {
                    this.chatList.unshift(res.data);
                }
            })
        },
        closeChat(id){
            this.chatList = this.chatList.filter((item) => {
                return item.id != id
            });
        },
        show() {
            if (this.$route.query.id !== undefined) {
                this.showContent = false;
            } else {
                this.showContent = true;
            }
        },
        load () {
            this.loading = true
            setTimeout(() => {
                this.count += 2
                this.loading = false
            }, 2000)
        },
    }
};
</script>

<style scoped lang="less">
.right {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    .top_box {
        padding: 10px 10px 0;
        height: 52px;
        .title{
            height: 42px;
            background-color: #fff;
            -webkit-box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
            box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
            -ms-flex-negative: 0;
            flex-shrink: 0;
            display: -webkit-box;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: justify;
            -ms-flex-pack: justify;
            justify-content: space-between;
            padding: 0 16px;
            font-size: 15px;
            color: #666;
            border-radius: 4px;
        }
    }
    
    .body_box {
        padding: 10px 10px;
        flex: 1;
        height: calc(100% - 66px);
        position: relative;
        -webkit-box-flex: 1;
        -ms-flex: 1;
        overflow: hidden;
        overflow-anchor: none;
        touch-action: auto;
        .d {
            height: calc(100vh - 138px);
            .p{
                font-size: 12px;
                color: #666;
                background-color: #fff;
                -webkit-box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
                box-shadow: 0 2px 4px 0 rgba(121,146,185,0.54);
                border-radius: 4px;
                height: 100%;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                position: relative;
                .left{
                    width: 240px;
                    border-right: 1px solid #e9eaec;
                    position: relative;
                    .title{
                        padding-left: 24px;
                        line-height: 35px;
                        height: 36px;
                        border-bottom: 1px solid #e9eaec;
                        -webkit-user-select: none;
                        -moz-user-select: none;
                        -ms-user-select: none;
                        user-select: none;
                        overflow: hidden;
                    }
                    .list_container{
                        height: calc(100% - 36px);
                        position: relative;
                        overflow: auto !important;
                        overflow-anchor: none;
                        -ms-overflow-style: none;
                        touch-action: auto;
                        -ms-touch-action: auto;
                        
                        
                        .ps__rail-x {
                            display: none;
                            opacity: 0;
                            transition: background-color .2s linear, opacity .2s linear;
                            -webkit-transition: background-color .2s linear, opacity .2s linear;
                            height: 15px;
                            bottom: 0px;
                            position: absolute;
                            .ps__thumb-x {
                                background-color: #aaa;
                                border-radius: 6px;
                                transition: background-color .2s linear, height .2s ease-in-out;
                                -webkit-transition: background-color .2s linear, height .2s ease-in-out;
                                height: 6px;
                                bottom: 2px;
                                position: absolute;
                            }
                        }
                        .ps__rail-y {
                            display: block;
                            background-color: transparent;
                            .ps__thumb-y {
                                background-color: #aaa;
                                border-radius: 6px;
                                transition: background-color .2s linear, width .2s ease-in-out;
                                -webkit-transition: background-color .2s linear, width .2s ease-in-out;
                                width: 6px;
                                right: 2px;
                                position: absolute;
                            }
                        }
                    }
                    .ps--active-y{
                    
                    }
                }
                .right {
                    width: calc(100% - 240px);
                    -webkit-box-flex: 1;
                    -ms-flex: 1;
                    flex: 1;
                    .placeholder{
                        width: 100%;
                        height: 100%;
                        display: -webkit-box;
                        display: -ms-flexbox;
                        display: flex;
                        -webkit-box-pack: center;
                        -ms-flex-pack: center;
                        justify-content: center;
                        -webkit-box-align: center;
                        -ms-flex-align: center;
                        align-items: center;
                        -webkit-box-orient: vertical;
                        -webkit-box-direction: normal;
                        -ms-flex-direction: column;
                        flex-direction: column;
                        overflow: hidden;
                        -webkit-user-select: none;
                        -moz-user-select: none;
                        -ms-user-select: none;
                        user-select: none;
                        .placeholder-img {
                            background: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEsAAABHCAMAAAB4UkqjAAAAh1BMVEUAAADd3d3d3d…UCIZXAAAALHRSTlMAoJDx4OYL/DcGF+6+JNi4ycSIT2k6LPeWfRzTVPXPszEQrV0hpo12cEZAZFpRNIQAAAJVSURBVFjD7dbZcpswGIbhHwwSi81q8Ib33fnu//oatRIpmliDhQ/aaZ+TKInnjVgkhb4RJMhP9Fzo8PiD+vEA+JPnqS0+1dRHBJhibAzh2HNeekxP9Z8XBSMZM6SmRBYx65QeM6XsY3rKPqanLGMWqeDmOh0b/Io9+YHi3gKtdExgLznSlyjFMGnUphIMlcgY22C4DSPBxTu4IrXDe+yI6IDWbPSqGVoHoohDyip6XZVB4hFN1TgPyUaYQ5rSWQ0nZGcC6UyJukKypa4yIXX3HLLlqCdHX++HLRfS/9bbWvvHLSLNR3N6tRXdFwdxejekiYF4fDnWYa/WqSm2PqRCSy0hrbP5RxAaW8141N17tVbZ+S1fFYbWFF2+1lqg62BoBdAsqWOLroWhxTh+M8vmWuuaxp1WaWjRSn43yl35sHTLUjxiaWlqnYE4LZoJme3v13MibqepFZRL6otV5V+/Hv+xVvi+FsvZG1qh/LQ7uFXnF/HlwcEfYlDktW2r5EARUiXWc1xRWAC8tGwxsdJ9dcRnYrddMdtrjDZyAy3kXrSJ7O89u8zEZAIKxBRnFzbonWD3ZkfCrrmzP+6979nicjAnW3NZ4OS1R6GtVBa8drTek539up3NAgMvcv51WNZQitCiFBZQaqIMSrx1XrWNoWTy/w1r+hk+xjuMSVjGGC6WZ3S1xlDriqTKxzB+Ra3I4bDHne62drqmied57QR9z0D94dHnOEmvJ/pWAMm4ovL2TTcJ5cRyMlHrbkdG5c/5r82fYqt+K/iR89m2JrP9PEZyI80PsPxVT0P3FAEAAAAASUVORK5CYII=) center/contain no-repeat;
                            width: 402px;
                            height: 304px;
                        }
                        .is-im {
                            background-image: url(//s1.hdslb.com/bfs/static/blive/blfe-message-web/static/img/no_message.def5cf54.png);
                            background-size: 402px 204px;
                            margin-bottom: 32px;
                            height: 204px;
                        }
                        .tip {
                            color: #8896b8;
                            font-size: 14px;
                            line-height: 1.5em;
                        }
                    }
                }
            }
        }
    }
}
.showuser{
    background-color: #e4e5e6;
}
</style>