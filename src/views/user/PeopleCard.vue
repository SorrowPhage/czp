<template>
    <div class="pcard">
        <div class="pcard_top">
            <el-avatar :src="avatar" :size="60"/>
        </div>
        <div class="pcard_body">
            <span>{{name}}</span>
        </div>
        <div class="pcard_foot">
            <span>{{des}}</span>
        </div>
    </div>
</template>

<script>
export default {
    name: "PeopleCard",
    props:["name","avatar","des"]
}
</script>

<style scoped>
.pcard {
    width: 60px;
    background-image: linear-gradient(#409EFF, #7ce6f1);
    display: flex;
    flex-wrap: wrap;
}
.pcard_top {
    width: 100%;
    height: 100px;
    
}
.pcard_body {
    width: 100%;
    height: 30px;
}
.pcard_foot {
    width: 100%;
}
</style>