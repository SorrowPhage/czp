export default {
    namespaced: true,
    actions: {

    },
    mutations: {
        userInfo(state,user) {
            state.id = user.username;
            state.permissions = user.permissions;
        },
    },
    state: {
        //账号
        id: '',
        //权限列表
        permissions: [],
    },
    getters:{

    }
}