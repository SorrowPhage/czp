//该文件用于创建Vuex中最为核心的store
import Vue from 'vue'
//引入Vuex
import Vuex from 'vuex'

Vue.use(Vuex)

import CzpUser from "@/store/CzpUser";

export default new Vuex.store({
    modules:{
        CzpUser,
    }
})