import UserLogin from "@/views/login/UserLogin";
import AccessDenied from "@/views/AccessDenied";
import Register from "@/views/login/Register";
import RetrievePassword from "@/views/login/RetrievePassword";
import UserHome from "@/views/user/UserHome";
import HomePage from "@/components/HomePage";
import Personal from "@/views/user/Personal";
import PersnoalNav from "@/views/user/PersnoalNav";
import FamilyInfo from "@/views/group/FamilyInfo";
import PasswordChange from "@/views/user/PasswordChange";
import CreateGroup from "@/views/group/CreateGroup";
import GroupList from "@/views/group/GroupList";
import AuditList from "@/views/audit/AuditList";
import GroupMangger from "@/views/audit/GroupMangger";
import AuditHistory from "@/views/audit/AuditHistory";
import GroupCreaterList from "@/views/audit/GroupCreaterList";
import GroupTree from "@/views/group/GroupTree";
import SearchNav from "@/views/search/SearchNav";
import user from "@/views/search/User";
import UserDeatil from "@/views/search/UserDeatil";
import group from "@/views/search/Group";
import GroupDetail from "@/views/search/GroupDetail";
import Chat from "@/views/message/Chat";
import ChatNav from "@/views/message/ChatNav";
import AuditResult from "@/views/message/AuditResult";
import EthnicGroupInformation from "@/views/group/down/EthnicGroupInformation";
import GroupInfo from "@/views/group/down/GroupInfo";
import AllGroupInfo from "@/views/group/deatil/AllGroupInfo";

const routers = [
    {
        path: '/',
        name: '/',
        component: HomePage,
        redirect: '/home/uh',
        meta: {title: "Czp Data", ver: true}
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
                        path: "pc",
                        name: "pc",
                        component: PasswordChange,
                        meta: {title: "修改密码", ver: true}
                    }
                ]
            },
            // {
            //     path: 'group',
            //     name: 'group',
            //     component: GroupNav,
            //     redirect: "/home/group/list",
            //     meta: {title: "家族", ver: true},
            //     children: [
            //
            //     ]
            // },
            //group
            {
                path: "/home/group/create",
                name: "create",
                component: CreateGroup,
                meta: {title: "create", ver: true}
            },
            {
                path: "/home/group/list",
                name: "list",
                component: GroupList,
                meta: {title: "list", ver: true}
            },
            {
                path: '/home/group/fam',
                name: 'fam',
                component: FamilyInfo,
                meta: {title: "czp", ver: true}
            },
            {
                path: "/home/group/gt",
                name: "gt",
                component: GroupTree,
                meta: {ver: true}
            },
            {
              path: "/home/ethnic",
              name: "ethnic",
              component: EthnicGroupInformation,
              meta: {title: "EthnicGroupInfo",ver: true}
            },
            {
                path: "/home/groupinfo",
                name: "groupinfo",
                component: GroupInfo,
                meta: {title:"族群信息", ver: true}
            },
            {
                path: 'agi',
                name: "agi",
                component: AllGroupInfo,
                meta: {title: "CZP", ver: true}
            },
            //=======================
            // {
            //     path: "audit",
            //     name: "audit",
            //     component: AuditNav,
            //     meta: {title: "申请", ver: true},
            //     redirect: "/home/audit/judge",
            //     children: [
            //
            //     ]
            // },
            //audit
            {
                path: "/home/audit/judge",
                name: "judge",
                component: AuditList,
                meta: {title: "申请", ver: true}
            },
            {
                path: "/home/audit/ah",
                name: "ah",
                component: AuditHistory,
                meta: {title: "历史", ver: true}
            },
            {
                path: "/home/audit/gb",
                name: "gb",
                component: GroupMangger,
                meta: {title: "gb", ver: true}
            },
            {
                path: "/home/audit/gcl",
                name: "gcl",
                component: GroupCreaterList,
                meta: {ver: true}
            },
            //=======================
            {
                name: "search",
                path: "search",
                component: SearchNav,
                redirect: "/home/search/user",
                meta: {title: "搜索", ver: true},
                children: [
                    {
                        path: "user",
                        name: 'user',
                        component: user,
                        meta: {title: "搜索", ver: true},
                    },
                    {
                        path: "sg",
                        name: "sg",
                        component: group,
                        meta: {title: "搜索", ver: true},
                    }
                ]
            },
            {
                path: "detail",
                name: "detail",
                component: UserDeatil,
                meta: {title: "用户", ver: true},
            },
            {
                path: "gd",
                name: "gd",
                component: GroupDetail,
                meta: {title: "族群", ver: true},
            },
            {
                path: 'msg',
                name: "msg",
                component: ChatNav,
                redirect: "/home/msg/chat",
                meta: {title: "消息", ver: true},
                children: [
                    {
                        path: "chat",
                        name: "chat",
                        component: Chat,
                        meta: {title: "消息", ver: true},
                    },
                    {
                        path: "ar",
                        name: "ar",
                        component: AuditResult,
                        meta: {title: "消息", ver: true},
                    }
                ]
            },
        ]
    }
];

export default routers