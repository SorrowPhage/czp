<template>
    <div>
        <el-popover
            placement="top-start"
            width="300"
            trigger="hover"
        >
            <div>
                {{name}}
            </div>
            <div slot="reference">
                <el-avatar :src="avatar" :size="25"></el-avatar>
            </div>
        </el-popover>
    </div>
</template>

<script>
export default {
    name: "CreateCard",
    props: ["avatar","name"],
    
}
</script>

<style scoped>

</style>