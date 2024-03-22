// 在http.js中引入axios
import axios from 'axios'; // 引入axios
// import QS from 'qs'; // 引入qs模块，用来序列化post类型的数据，后面会提到
import {Message} from 'element-ui'
import router from "@/router";

axios.defaults.timeout = 10000 // 超时时间

// axios.defaults.baseURL = 'http://118.25.172.141:4090';
// axios.defaults.baseURL = 'http://118.25.172.141:4090';
axios.defaults.baseURL = 'http://localhost:4090';

// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
axios.defaults.headers.post['Content-Type'] = 'application/json';




// 在请求头中加token
// 添加请求拦截器
axios.interceptors.request.use((config) => {
    // config 是 axios 配置对象
    // 获取token
    let token = window.localStorage.getItem('czpToken');
    // 添加token
    if (token) {
        config.headers.token = token;
    }
    return config;
}, (error) => {
    // 请求出错
    return Promise.reject(error);
});




// http response 拦截器 添加一个响应拦截器
axios.interceptors.response.use((response) => {
    if (response.data.code === 401) {
        router.push({
            name: "login",
        });
    } else if (response.data.code===403) {
        router.push({
            name: "accessDenied",
        });
    }
    return response;
}, (error) => {
    // 响应失败
    return Promise.reject(error);
})



/**
 * get方法，对应get请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function getRequest(url, params){
    return new Promise((resolve, reject) =>{
        axios.get(url, {
            params: params
        }).then(res => {
            resolve(res.data);
        }).catch(err =>{
            reject(err.data)
        })
    });}


export function imageUpload(url,params){
    return new Promise((resolve,reject)=>{
        axios.post(url,params,{headers: {'Content-Type': 'multipart/form-data'}}).then(res => {
            resolve(res.data);
        }).catch(err =>{
            reject(err.data)
        })
    })
}


/**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 */
export function postRequest(url, params) {
    return new Promise((resolve, reject) => {
        // axios.post(url, QS.stringify(params))
        axios.post(url, params)
            .then(res => {
                resolve(res.data);
            })
            .catch(err =>{
                reject(err.data)
            })
    });
}


/**
 * 窗口高度
 * @returns {number}
 */
export function windowHeight() {
    return window.screen.availHeight;
}