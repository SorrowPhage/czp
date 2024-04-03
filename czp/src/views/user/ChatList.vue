<template>
    <div class="list_item"  @click="chat(id)">
        <el-avatar :src="avatar" style="margin-right: 8px;"/>
        <div class="name_box">
            <div class="name">
                {{name}}
            </div>
            <div class="last_word">
                {{des}}
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "ChatList",
    props: ['id', "avatar", "name", "des"],
    data() {
        return {
            staticAvatarUrl:  require('@/assets/img/czp.jpg') // 静态图片的URL
        }
    },
    computed: {
        avatarUrl: function () {
            return this.avatar ? this.avatar : this.staticAvatarUrl;
        }
    },
    methods:{
        chat() {
            this.$router.push({
                name: "chat",
                query:{
                    id: this.id,
                }
            })
        },
    }
}
</script>

<style scoped>

</style>