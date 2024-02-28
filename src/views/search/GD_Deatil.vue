<template>
    <div>
        <GD_DeatilList v-for="u in userList" :key="u.id" :id="u.id" :name="u.name" :avatar="u.avatar" :birthday="u.birthday"
            :generationRank="u.generationRank" :family-rank="u.familyRank" :generation="u.generation" :sex="u.sex"
        />
    </div>
</template>

<script>
import GD_DeatilList from "@/views/search/GD_DeatilList";
import {getRequest} from "@/api/api";
export default {
    name: "GD_Deatil",
    components:{GD_DeatilList},
    data() {
        return{
            userList: [],
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/group/ul",{id: this.$route.query.id}).then(res=>{
                console.log(res);
                if (res.code) {
                    this.userList = res.data;
                }
            })
        },
    }
}
</script>

<style scoped>

</style>