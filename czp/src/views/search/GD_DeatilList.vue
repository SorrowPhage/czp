<template>
    <div style="display: flex;">
        <div><el-avatar :size="20" :src="avatar"></el-avatar></div>
        <div style="margin-left: 15px">{{name}}</div>
    </div>
</template>

<script>
export default {
    name: "GD_DeatilList",
    props:['id','name','avatar']
}
</script>

<style scoped>

</style>