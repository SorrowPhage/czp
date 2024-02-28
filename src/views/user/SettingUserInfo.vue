<template>
    <div>
        <div>
            <div><el-avatar :size="90" :src="userInfo.avatar" /></div>
            <div>
                <span> {{ userInfo.name }} </span>
            </div>
        </div>
        <div>
        
        </div>
        <div></div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";

export default {
    name: "SettingUserInfo",
    data() {
        return{
            userInfo: {
                avatar: '',
                name: '',
                id: '',
                sex: '',
                birthday: '',
                familyRank: '',
                generation: '',
                generationRank: '',
                email: '',
                deathTime: '',
                phone: '',
                area: '',
                familyName: ''
            },
        }
    },
    mounted() {
        this.loadData();
    },
    methods: {
        loadData() {
            getRequest("/czpUser/userinfo", {id: this.$store.state.CzpUser.id}).then(res => {
                console.log("UserHeader", res);
                this.userInfo = res.data;
            })
        },
    },
}
</script>

<style scoped>

</style>