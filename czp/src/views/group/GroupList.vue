<template>
    <div>
        <GroupCard v-for="g in groupList" :key="g.id"
                   :id="g.id" :group-name="g.groupName" :avatar="g.avatar" :des="g.des" :name="g.name" :area="g.area" :create-time="g.createTime"
        />
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import GroupCard from "@/views/group/GroupCard";
export default {
    name: "GroupList",
    components: {GroupCard},
    data() {
        return{
            groupList: [],
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/group/list",{userId: this.$store.state.CzpUser.id}).then(res=>{
                console.log("GroupList", res);
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            })
        },
    }
}
</script>

<style scoped>

</style>