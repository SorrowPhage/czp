<template>
    <div class="gd_body main">
        <div class="top">
            asd
            xxx
        </div>
        <div class="panel">
            <group-card
            />
        </div>
    </div>
</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest} from "@/api/api";
import GroupCard from "@/views/group/down/GroupCard";
export default {
    name: "EthnicGroupInformation",
    components:{GroupCard},
    data() {
        return{
            groupList: [],
        }
    },
    mounted() {
        this.getGroupList();
    },
    methods:{
        //获取用户所拥有的族群信息
        getGroupList() {
            showLoading()
            getRequest("/group/list", {userId: this.$store.state.CzpUser.id}).then(res => {
                hideLoading();
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                }
            );
        },
    }
}
</script>

<style scoped lang="less">
.main{
    display: flex;
    flex-wrap: wrap;
}
.top {
    height: 100px;
    background: #2b3346;
    width: 100%;
    margin-top: 5px;
}
.panel {
    width: 100%;
    //height: 100%;
    margin-top: 15px;
    background: #545c64;
    flex-grow: 1;
}
</style>