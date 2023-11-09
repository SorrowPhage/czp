import { Loading } from 'element-ui';
import {Notification} from "element-ui";

let loadingCount = 0;
let loading;

const startLoading = () => {
    loading = Loading.service({
        lock: true,
        text: '加载中……',
        background: 'rgba(0, 0, 0, 0.7)'
    });
};

const endLoading = () => {
    loading.close();
};

export const showLoading = () => {
    if (loadingCount === 0) {
        startLoading();
    }
    loadingCount += 1;
};

export const hideLoading = () => {
    if (loadingCount <= 0) {
        return;
    }
    loadingCount -= 1;
    if (loadingCount === 0) {
        endLoading();
    }
};

export const requestNotify = (res) => {
    if (res.code === 200) {
        Notification.success({title:res.code, message: res.message})
    }else if (res.code === 401) {
        Notification.warning({title:res.code, message: res.message})
    }else if (res.code === 403) {
        Notification.warning({title:res.code, message: res.message})
    }else if (res.code === 500) {
        Notification.error({title:res.code, message: res.message})
    }
};

export const hideLoadingAndNotify = (res) =>{
    if (loadingCount <= 0) {
        return;
    }
    loadingCount -= 1;
    if (loadingCount === 0) {
        endLoading();
    }
    if (res.code === 200) {
        Notification.success({title:res.code, message: res.message})
    }else if (res.code === 401) {
        Notification.warning({title:res.code, message: res.message})
    }else if (res.code === 403) {
        Notification.warning({title:res.code, message: res.message})
    }else if (res.code === 500) {
        Notification.error({title:res.code, message: res.message})
    }
}
