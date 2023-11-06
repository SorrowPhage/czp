<template>
    <div class="user-body">
        <div class="login-box">
            <div class="input-box">
                <h1>登录</h1>
                <div class="input-text">
                    <img src="../../assets/img/login/user.jpg" alt="">
                    <input type="text" placeholder="账号" v-model="user.id"/><br/>
                </div>
                <div class="input-text">
                    <img src="../../assets/img/login/password.jpg" alt="">
                    <input type="password" @keydown.enter="login" placeholder="密码" v-model="user.password"/><br/>
                </div>
                <div class="about-password">
                    <input type="checkbox" >记住密码
                    <router-link to="/retrieve">找回密码</router-link>
                    <br/>
                </div>
                <div class="input-btn">
                    <button @click="login">登录</button>
                </div>
                <div class="input-register">
                    还没有账号？
                    <router-link to="/register">注册</router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {postRequest} from "@/api/api";
export default {
    name: "UserLogin",
    data() {
        return {
            user: {
                id: 'sp9444708',
                password: '',
            }
        }
    },
    mounted() {
    },
    methods:{
        login() {
            postRequest("/czpUser/login",this.user).then(res=>{
                if (res.flag === true) {
                    this.$message({
                        message: res.message,
                        type: 'success',
                    })
                    //登录只存token不做其它
                    localStorage.setItem("czpToken", res.data);

                    this.$router.push({
                        name: 'userHome',
                    })
                }else if (res.flag === false) {
                    this.$message({
                        message: res.message,
                        type: 'warning',
                    })
                }
            },err => {
                console.log(err)
            })
        },
    }
}
</script>

<style scoped>
.user-body {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;

    background-image: linear-gradient(
        125deg,
        #2c3e50,
        #27ae60,
        #2980b9,
        #eb4d4b,
        #2c3e50
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

.login-box {
    display: flex;
    justify-content: center;
    align-content: space-around;
    flex-wrap: wrap;
    width: 300px;
    height: 500px;
    background-color: white;
    /*background-color: rgba(255, 255, 255, .5);*/
    border: 10px;
    padding: 20px 50px;
    border-radius: 20px;
}

.login-box h1 {
    width: 100%;
    display: flex;
    justify-content: center;
    color: black;
    font-size: 30px;
}

.input-box {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
    transform: translateY(-35px);
}

.input-text {
    width: 100%;
    display: flex;
    justify-content: center;
    
}

.about-password {
    display: flex;
    justify-content: center;
    width: 100%;
    color: gray;
}

.about-password input {
    height: 18px;
}

.about-password a {
    float: right;
    color: gray;
    text-decoration: none;
    margin-left: 50px;
}

.input-text input {
    width: 280px;
    height: 30px;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    font-size: 25px;
    border: 0;
    padding: 6px;
    border-bottom: 1px solid black;
    background-color: #ffffff00;
    color: black;
    margin-top: 20px;
    outline: none;
}

.input-text img {
    width: 30px;
    height: 30px;
    margin-top: 28px;
    padding: 5px;
}

.input-btn button {
    width: 280px;
    height: 30px;
    background-image: linear-gradient(0deg, #398bff, #3083ff);
    border-radius: 12px;
    margin-top: 40px;
    border: #27ae60;
    color: white;
}

.input-btn button:hover {
    cursor: pointer;
    background-image: linear-gradient(0deg, #227cf9, #3083ff)
}

.input-btn button:focus {
    outline: none;
}

.input-register {
    width: 100%;
    display: flex;
    justify-content: center;
    margin-top: 40px;
    color: black;
    font-size: 17px;
}

.input-register a {
    color: #3498db;
    text-decoration: none;
}
</style>