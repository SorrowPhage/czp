<template>
    <div style="display: flex;margin-bottom: 5px;margin-left: 10px">
        <div><el-avatar :size="30" :src="avatar"></el-avatar></div>
        <div style="margin-left: 20px;flex: 1">{{name}}</div>
<!--        <div style="width: 150px">{{birthday}}</div>-->
<!--        <div style="width: 150px">{{generationRank}}</div>-->
<!--        <div style="width: 150px">{{familyRank}}</div>-->
<!--        <div style="width: 150px">{{generation}}</div>-->
<!--        <div style="width: 150px">{{sex}}</div>-->
        <div style="margin-right: 10px">
            <el-button @click.native="chat(id)" plain type="primary">私   信</el-button>
        </div>
    </div>
</template>

<script>
export default {
    name: "GD_DeatilList",
    props: ['id', 'name', 'avatar','birthday','generationRank','familyRank','generation',"sex"],
    methods:{
        chat(id) {
            this.$router.push({
                name: "chat",
                query: {
                    id: id,
                }
            })
        },
    }
}
</script>

<style scoped>

</style>