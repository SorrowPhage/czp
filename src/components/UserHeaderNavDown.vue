<template>
    <div>
        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect"
                 :router="true"
                 :default-active="$route.path"
                 :unique-opened="true"
        >
            <el-menu-item index="1">个人中心</el-menu-item>
            <el-submenu index="2">
                <template slot="title">族群信息</template>
                <el-menu-item index="/home/group/create">选项1</el-menu-item>
                <el-menu-item index="/home/group/list">选项2</el-menu-item>
                <el-menu-item index="/home/group/fam">选项3</el-menu-item>
                <el-menu-item index="/home/group/gt">选项3</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
                <template slot="title">族群信息</template>
                <el-menu-item index="/home/audit/judge">选项1</el-menu-item>
                <el-menu-item index="/home/audit/ah">选项2</el-menu-item>
                <el-menu-item index="/home/audit/gb">选项3</el-menu-item>
                <el-menu-item index="/home/audit/gcl">选项3</el-menu-item>
            </el-submenu>

        </el-menu>
    </div>
</template>

<script>
export default {
    name: "UserHeaderNavDown",
    data() {
        return {
            activeIndex: '1',
            activeIndex2: '1'
        };
    },
    methods: {
        handleSelect(key) {

        }
    }
}
</script>

<style scoped>

</style>