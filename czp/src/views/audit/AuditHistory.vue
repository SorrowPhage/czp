<template>
    <div>
<!--TODO 审计历史-->
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
export default {
    name: "AuditHistory",
    data() {
        return{
            auditList: [],
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/audit/history",{id: this.$store.state.CzpUser.id}).then(res=>{
                console.log("AuditHistory", res);
                if (res.code === 200) {
                    this.auditList = res.data;
                }
            })
        },
    }
}
</script>

<style scoped>

</style>