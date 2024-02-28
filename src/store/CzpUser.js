export default {
    namespaced: true,
    actions: {

    },
    mutations: {
        userInfo(state,user) {
            state.id = user.id;
            state.avatar = user.avatar;
            state.name = user.name;
            state.permissions = user.permissions;
        },
        setAvatar(state, url) {
            state.avatar = url;
        },
        updateName(state,name) {
            state.name = name;
        },
    },
    state: {
        //账号
        id: '',
        //头像
        avatar: '',
        //用户名
        name: '',
        //权限列表
        permissions: [],
    },
    getters:{

    }
}