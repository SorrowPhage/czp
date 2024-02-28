<template>
    <div>
        <user-header></user-header>
        <router-view></router-view>
    </div>
</template>

<script>
import UserHeader from "@/components/UserHeader";

export default {
    name: "HomePage",
    components:{UserHeader}
};
</script>

<style scoped>

</style>