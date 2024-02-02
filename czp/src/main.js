import Vue from 'vue';
import App from './App.vue';
import ElementUI from 'element-ui';
import router from './router';
import 'element-ui/lib/theme-chalk/index.css';
import store from './store'
import '@/assets/css/index.css'
// 将自动注册所有组件为全局组件
import dataV from '@jiaminghi/data-view'
Vue.use(dataV)

import VueAreaLinkage from 'vue-area-linkage';
Vue.use(VueAreaLinkage)

import * as echarts from 'echarts'
Vue.prototype.$echarts = echarts
import geoJson from '@/utils/china.json'
echarts.registerMap('china', geoJson);

//拖拽·缩放·画布插件
import VueDraggableResizable from 'vue-draggable-resizable'
import 'vue-draggable-resizable/dist/VueDraggableResizable.css'
Vue.component('vue-draggable-resizable', VueDraggableResizable)

Vue.use(ElementUI, {
  size: 'small'
})
Vue.config.productionTip = false;
//注册权限控制
import {hasPermissions} from "@/utils/hasPermissions";

Vue.prototype.hasPerm = hasPermissions;

new Vue({
  store,
  router,
  render: (h) => h(App),
  beforeCreate() {
    Vue.prototype.$bus = this
  }
}).$mount('#app');
