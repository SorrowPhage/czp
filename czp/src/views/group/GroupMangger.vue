<template>
    <div>
        <div style="height: 30px;padding: 10px;">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item><a :href="$route.path">族群信息</a></el-breadcrumb-item>
                <el-breadcrumb-item class="now_text">族群管理</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
    </div>
</template>

<script>
export default {
    name: "GroupMangger"
}
</script>

<style scoped>

</style>