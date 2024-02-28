export default {
    namespace: true,
    actions: {},
    mutations: {
        userInfo(state,user) {
            state.id = user.data.username;
            state.permissions = user.data.permissions;
        },
    },
    state: {
        //账号
        id: '',
        //权限列表
        permissions: [],
    },
    getters:{}
}