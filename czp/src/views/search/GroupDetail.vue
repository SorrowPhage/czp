<template>
    <el-container>
        <el-main>
            <div class="gd_body">
<!--                <div style="width: 200px;height: calc(100vh - 200px);">-->
<!--                    -->
<!--                </div>-->
                <div class="user_body">
                    <el-tabs v-model="activeName" style="height: calc(100vh - 200px);">
                        <el-tab-pane label="详细信息" name="first">
                            <el-descriptions  :column="1" border>
                                <el-descriptions-item label="群编码">{{group.id}}</el-descriptions-item>
                                <el-descriptions-item label="群名称">{{group.groupName}}</el-descriptions-item>
                                <el-descriptions-item label="群描述"> 该族群创建于{{group.createTime}}:{{group.des}}</el-descriptions-item>
                                <el-descriptions-item label="父级族群">{{group.parentId}} {{group.parentName}}</el-descriptions-item>
                                <el-descriptions-item label="成员数量">{{group.num}}</el-descriptions-item>
                                <el-descriptions-item label="管理员">
                                    <el-avatar :src="group.clanElderUser.avatar" style="cursor: pointer" @click.native="chat(group.clanElderUser.id)"></el-avatar>
                                </el-descriptions-item>
                            </el-descriptions>
                        </el-tab-pane>
                        <el-tab-pane label="成员" name="second" lazy><GD_Deatil/></el-tab-pane>
                        <el-tab-pane label="族群树" name="third" lazy style="height: calc(100vh - 200px);">
                                <GD_GroupTree/>
                        </el-tab-pane>
                    </el-tabs>
                </div>
            </div>
        </el-main>
    </el-container>
</template>

<script>
import {getRequest} from "@/api/api";
import GD_Deatil from "@/views/search/GD_Deatil";
import GD_GroupTree from "@/views/search/GD_GroupTree";
export default {
    name: "GroupDetail",
    components: {GD_Deatil, GD_GroupTree},
    data() {
        return {
            group: {
                id: '',
                groupName: '',
                createTime: '',
                parentId: '',
                parentName: '',
                num: '',
                clanElder: '',
                clanElderUser: {
                    avatar: '',
                    id: '',
                    name: '',
                }
            },
            activeName: 'first'
        }
    },
    watch: {
        '$route.query.id': {
            handler() {
                this.loadData();
            },
        },
    },
    mounted() {
        this.loadData();
    },
    methods: {
        //获取族群信息
        loadData() {
            getRequest("/group/groupInfo", {id: this.$route.query.id}).then(res => {
                console.log(res);
                if (res.code === 200) {
                    this.group = res.data;
                }
            })
        },
        chat(id) {
            this.$router.push({
                name: "chat",
                query: {
                    id: id,
                }
            })
        },
        handleClick(tab, event) {
            console.log(tab, event);
        }
    }
}
</script>

<style lang="scss" scoped>
/deep/ .el-tabs__content {
    height: 100%;
}

.user_top {
    //width: 150px;
    //background-color: white;
    //display: flex;
    //justify-content: center;
    //align-items: center;
}

.user_info {
    width: 100%;
    height: 70px;
}

.info_box {
    //display: flex;
    //flex-direction: column;
    //justify-content: center;
    //align-items: center;
    
}

.user_body {
    //background-color: white;
    margin: 0 auto;
    flex: 1;
}
</style>