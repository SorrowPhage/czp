import UserLogin from "@/views/login/UserLogin";
import AccessDenied from "@/views/AccessDenied";
import CzpHome from "@/views/large/CzpHome";
import Register from "@/views/login/Register";
import RetrievePassword from "@/views/login/RetrievePassword";
import UserHome from "@/views/user/UserHome";
import HomePage from "@/components/HomePage";
import Personal from "@/views/user/Personal";
import PersnoalNav from "@/views/user/PersnoalNav";
import FamilyInfo from "@/views/user/FamilyInfo";
import PasswordChange from "@/views/user/PasswordChange";
import CreateGroup from "@/views/group/CreateGroup";
import GroupNav from "@/views/group/GroupNav";
import GroupList from "@/views/group/GroupList";
import AuditList from "@/views/audit/AuditList";
import GroupMangger from "@/views/group/GroupMangger";
import AuditNav from "@/views/audit/AuditNav";
import AuditHistory from "@/views/audit/AuditHistory";
const routers = [
    {
        path: '/',
        name: '/',
        component: HomePage,
        redirect: '/home/uh',
        meta: {title: "Czp Data", ver: true}
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
        path: '/home',
        name: 'home',
        component: HomePage,
        redirect: '/home/uh',
        meta: {title: '首页', ver: true},
        children: [
            {
                path: 'uh',
                name: 'userHome',
                component: UserHome,
                meta: {title: '首页', ver: true}
            },
            {
                path: "setting",
                name: "setting",
                component: PersnoalNav,
                meta: {title: "修改资料", ver: true},
                redirect: "/home/setting/own",
                children: [
                    {
                        path: 'own',
                        name: 'own',
                        component: Personal,
                        meta: {title: "修改资料", ver: true}
                    },
                    {
                        path: 'fam',
                        name: 'fam',
                        component: FamilyInfo,
                        meta: {title: "修改资料", ver: true}
                    },
                    {
                        path: "pc",
                        name: "pc",
                        component: PasswordChange,
                        meta: {title: "修改密码", ver: true}
                    }
                ]
            },
            {
                path: 'group',
                name: 'group',
                component: GroupNav,
                redirect: "/home/group/create",
                meta: {title: "家族", ver: true},
                children: [
                    {
                        path: "create",
                        name: "create",
                        component: CreateGroup,
                        meta: {title: "create", ver: true}
                    },
                    {
                        path: "list",
                        name: "list",
                        component: GroupList,
                        meta: {title: "list", ver: true}
                    },
                    {
                        path: "gb",
                        name: "gb",
                        component: GroupMangger,
                        meta: {title: "gb", ver: true}
                    }
                ]
            },

            {
                path: "audit",
                name: "audit",
                component: AuditNav,
                meta: {title: "申请", ver: true},
                redirect: "/home/audit/judge",
                children: [
                    {
                        path: "judge",
                        name: "judge",
                        component: AuditList,
                        meta: {title: "申请", ver: true}
                    },
                    {
                        path: "ah",
                        name: "ah",
                        component: AuditHistory,
                        meta: {title: "历史", ver: true}
                    }
                ]
            }
        ]
    }
];

export default routers