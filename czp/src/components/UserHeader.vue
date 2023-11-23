<template>
    <div class="header">
        <div class="nav-box">
            <div style="display: inline">
                <SideInformationBar/>
                <input v-model="search" class="sp-search-input" placeholder="Search" type="text" @keydown.enter="goSearch"/>
                <i class="el-icon-search sp-search-icon" @click="goSearch"></i>
                <div class="sp-nav" @click="goGroup">族群信息</div>
                <div class="sp-nav" @click="audit" v-if="hasPerm('audit::apply')">族群操作</div>
                <div class="sp-nav" @click="message" >消息</div>
<!--                <div class="sp-nav" @click="large">large</div>-->
            </div>
        </div>
        <div  class="menu-right">
            <ul class="notification-menu">
                <li>
                    <label ref="showUl" class="btn btn-default dropdown-toggle" @click="isTrue=!isTrue">
                        <img :src="avatar" alt="头像"/>
                        {{ name }}
                        <span class="caret"></span>
                    </label>
                    <ul v-show="isTrue" ref="myUI" class="dropdown-menu dropdown-menu-usermenu pull-right sp-userinfo">
                        <li class="sp-li-space" @click="goIndex">
                            <i class="el-icon-menu"></i> 主页
                        </li>
                        <li class="sp-li-space" @click="logout">
                            <i class="el-icon-right"></i> 退出
                        </li>
    
                    </ul>
                </li>
            </ul>
        </div>
        <el-dialog
            :visible.sync="dialogVisible"
            title="新建族群"
            width="882px"
            append-to-body
        >
                <CreateGroup/>
        </el-dialog>
    </div>
</template>

<script>
import {mapState} from "vuex"
import SideInformationBar from "@/components/SideInformationBar";
import {sessionReplaceStore} from "@/utils/session_util"
import {closeWebsocket,sendWebsocket} from "@/utils/websocket";
import CreateGroup from "@/views/group/CreateGroup";
export default {
    name: "UserHeader",
    components: {SideInformationBar,CreateGroup},
    data() {
        return{
            dialogVisible: false,
            isTrue: false,
            search: '',
            // userInfo:{
            //     avatar: '',
            //     name: '',
            // }
        }
    },
    computed: {
        ...mapState("CzpUser", ["id", "avatar", "name"])
    },
    mounted() {
        document.addEventListener('click', this.showListUI);
        // this.loadData();
        this.requestWs();
    },
    destroyed() {
        document.removeEventListener('click', this.showListUI);
    },
    beforeDestroy() {
        closeWebsocket();
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
    
        onerror() {
        
        },
        goSearch() {
            this.$router.push({
                name: 'search',
                query:{
                    q: this.search,
                }
            })
        },
        createGroup() {
            this.$router.push({
                name: "cg",
            })
        },
        goGroup() {
            this.$router.push({
                name: "group",
            })
        },
        audit() {
            this.$router.push({
                name: "audit",
            })
        },
        message() {
            this.$router.push({
                name: "msg",
            })
        },
        large() {
            this.$router.push({
                name: "czpHome",
            })
        },
        goIndex() {
            this.isTrue = false;
            this.$router.push({
                name: 'userHome',
            })
        },
        logout() {
            this.isTrue = false;
            localStorage.removeItem("czpToken");
            this.$router.push({
                name: "login"
            });
        },
        
        showListUI(e) {
            //需要使用v-show绑定
            if (!(this.$refs.myUI.contains(e.target) || this.$refs.showUl.contains(e.target))) this.isTrue = false;
        },
        loadData() {
            //加载用户信息（该组件会出现在所有页面中，它的数据放在vuex中）
            // getRequest("/czpUser/userinfo", {id: this.$store.state.CzpUser.id}).then(res => {
            //     console.log("UserHeader", res);
            //     this.userInfo = res.data[0];
            // })
            //让该组件加载时根据token获取用户信息(在router中做了)
            // getRequest("/czpUser/token/userinfo",{}).then(res =>{
            //     console.log("UserHeader",res.data)
            //     this.$store.commit("CzpUser/userInfo", res.data);
            // })
            
        },
    },
    created() {
        // 页面刷新时恢复store数据
        sessionReplaceStore('store');
        // 发生页面销毁事件前将store数据存储至sessionStoreage中
        window.addEventListener('beforeunload', () => {
            sessionStorage.setItem('store', JSON.stringify(this.$store.state))
        });
    },
    
}
</script>

<style scoped>
.header {
    /*position: fixed;*/
    width: 100%;
    /*min-width: 1026px;*/
    background-color: white;
    top: 0;
    height: 50px;
    z-index: 2000;
    border-bottom: 1px solid #eef5fc;
}
.nav-box{
    height: 50px;
    margin-top: 0;
    float: left;
}
.sp-nav {
    display: inline;
    margin-left: 10px;
    font-weight: bold;
    font-family: ui-monospace, SFMono-Regular, SF Mono, Menlo, Consolas, Liberation Mono, monospace !important;
    color: #409EFF;
    font-size: 16px !important;
}

.sp-nav:hover {
    cursor: pointer;
    color: #5a8da1;
}
.notification-menu {
    list-style: none;
}

.notification-menu > li {
    display: inline-block;
    float: left;
    position: relative;
}

.menu-right {
    float: right;
    margin-right: 10px;
    margin-top: 0;
    height: 50px;
}

/*会影响导航栏*/
ul {
    margin-top: 0;
    margin-bottom: 10px;
}

ul {
    display: block;
}

a {
    text-decoration: none;
    margin-top: 0;
}

* {
    box-sizing: border-box;
}

li {
    display: list-item;
    text-align: -webkit-match-parent;
}

.btn {
    display: inline-block;
    margin-bottom: 0;
    font-weight: 400;
    text-align: center;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    white-space: nowrap;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    border-radius: 4px;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

.btn-default {
    color: #333;
    background-color: #fff;
    border-color: #ccc;
}

.dropdown-toggle {
    margin-top: 0;
    padding: 12px 10px;
    background: white;
    -moz-border-radius: 0;
    -webkit-border-radius: 0;
    border-radius: 0;
    border: none #fff;
}

.dropdown-toggle img {
    vertical-align: middle;
    margin-right: 5px;
    width: 26px;
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
}

/* 鼠标悬浮改变头像背景颜色 */
.dropdown-toggle:hover {
    background-color: #409EFF;
}

.dropdown-menu {
    display: block;
    position: absolute;
    top: 100%;
    border: 0;
    -moz-border-radius: 2px 0 2px 2px;
    -webkit-border-radius: 2px 0 2px 2px;
    border-radius: 2px 0 2px 2px;
    -moz-box-shadow: none;
    -webkit-box-shadow: none;
    box-shadow: none;
    padding: 0;
    margin: 2px 0 0;
    list-style: none;
    font-size: 18px;
}

.dropdown-menu:after {
    border-bottom: 6px solid #409EFF;
    border-left: 6px solid transparent;
    border-right: 6px solid transparent;
    content: "";
    display: inline-block;
    right: 10px;
    position: absolute;
    top: -6px;
}

.dropdown-menu-usermenu {
    background: #409EFF;
    min-width: 200px;
    color: white;
}

.pull-right {
    right: 0;
    left: auto;
    width: 100%;
}

.pull-right li:hover {
    background: white;
    cursor: pointer;
    color: black;
}

.notification-menu .dropdown-toggle .caret {
    margin-left: 5px;
}

.btn .caret {
    margin-left: 0;
}

.caret {
    display: inline-block;
    width: 0;
    height: 0;
    margin-left: 2px;
    vertical-align: middle;
    border-top: 4px solid;
    border-right: 4px solid transparent;
    border-left: 4px solid transparent;
}

.dropdown-divider {
    display: block;
    height: 0;
    margin: 8px 0;
    border-top: 1px solid #d0d7de;
}

.sp-li-space {
    margin: 4px 0;
}

.sp-userinfo {
    z-index: 9999;
}

.sp-nav {
    display: inline;
    margin-left: 10px;
    font-weight: bold;
    font-family: ui-monospace, SFMono-Regular, SF Mono, Menlo, Consolas, Liberation Mono, monospace !important;
    color: #409EFF;
    /*color: #5a8da1;*/
    font-size: 16px !important;
}

.sp-nav:hover {
    cursor: pointer;
    color: #5a8da1;
}

.sp-search-icon {
    width: 20px;
    margin-left: 5px;
    cursor: pointer;
}

.sp-login-box {
    /*background: #e0ac16;*/
}

.sp-login-btn {
    background: #409EFF;
    width: 44px;
    height: 44px;
    text-align: center;
    cursor: pointer;
    line-height: 44px;
    border-radius: 22px;
    color: white;
    margin-top: 3px;
}

.weather-box {
    width: 150px;
    position: absolute;
    right: 250px;
}
.sp-user-line-box{
    /*border-top: 1px solid dimgrey;*/
    width: 100%;
    /*height: 40%;*/
    margin-top: 5px;
    overflow: auto;
}
.sp-popover-box {
    height: 300px;
    white-space: pre-wrap;
    overflow: auto;
}
</style>