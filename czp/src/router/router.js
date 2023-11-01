import UserLogin from "@/views/login/UserLogin";
import AccessDenied from "@/views/AccessDenied";
import CzpHome from "@/views/large/CzpHome";
import Register from "@/views/login/Register";
import RetrievePassword from "@/views/login/RetrievePassword";
import UserHome from "@/views/user/UserHome";

const routers = [
    {
        path: '/',
        name: '/',
        component: UserLogin,
        meta: {title: "Czp Data"}
    },
    {
        path: '/czpHome',
        name: 'czpHome',
        component: CzpHome,
        meta: {title: "Czp Data"}
    },
    {

        path: '/login',
        name: 'login',
        component: UserLogin,
        meta: {title: "登录"}
    },
    {
        path: '/register',
        name: 'register',
        component: Register,
        meta: {title: "注册"}
    },
    {
        path: '/retrieve',
        name: 'retrieve',
        component: RetrievePassword,
        meta: {title: '找回密码'}
    },
    {
        path: '/accessDenied',
        name: 'accessDenied',
        component: AccessDenied,
        meta: {title: '访问受限'}
    },
    {
        path: '/uh',
        name: 'userHome',
        component: UserHome,
        meta: {title: '首页'}

    }
];

export default routers