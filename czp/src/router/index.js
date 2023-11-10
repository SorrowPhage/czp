import Vue from 'vue';
import VueRouter from 'vue-router';
import routes from './router';
import {getRequest} from "@/api/api";
import store from "@/store";
import {Message} from "element-ui";

// 解决由于router版本问题导致的重定向失败
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch((err) => err);
};

Vue.use(VueRouter);

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes,
});

router.beforeEach((to, from, next) => {
    console.log(to.meta.ver)
    //应该做判断，CzpUser中是否有数据，有的话不需要执行，并且这些数据是从redis中获取的loginUser，会出现mysql修改，redis每有修改的情况，应该从mysql中获取该数据
    if (to.meta.ver) {
        // if (store.state.CzpUser.id === '' || store.state.CzpUser.id === undefined) {
        //
        // } else {
        //     next();
        // }
        getRequest("/czpUser/token/userinfo", {}).then(res => {
            if (res.code === 200) {
                store.commit("CzpUser/userInfo", res.data);
                next();
            } else if (res.code === 401) {
                Message({
                    message: "请先登录",
                    type: 'warning',
                })
                next("/login");
            }
        });
    } else {
        next();
    }
});

router.afterEach((to) => {
    document.title = to.meta.title || 'Czp'
})

export default router;
