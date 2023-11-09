<template>
    <div style="margin: 5px">
        <div class="gcard">
            <div class="gcard_top">
                <div style="display: inline-block">
                    <span class="gcard_text">{{groupName}}</span>
                </div>
                <div class="gcard_btn">
                    <el-button type="primary" plain v-if="hasPerm('group::edit')">设置</el-button>
                </div>
            </div>
            <div class="gcard_body">
                <div class="body_body">
                    <span class="text_color">{{des}}</span>
                </div>
                <div style="width: 100%">
                    <create-card v-for="u in create" :key="u.id"
                                 :avatar="u.avatar" :name="u.name"
                    />
                </div>
            </div>
            <div class="gcard_foot text_color">
                <div>
                    <i class="el-icon-location-outline"></i>
                    <span>{{area}}</span>
                </div>
                <div class="foot_card">
                    <i class="el-icon-alarm-clock"></i>
                    <span>{{createTime}}</span>
                </div>
                <div class="foot_card" v-if="parentName">
                    <i class="el-icon-d-caret"></i>
                    <span>{{parentName}}</span>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import CreateCard from "@/views/group/CreateCard";
export default {
    name: "GroupCard",
    props: ["id", "groupName", "des", "area", "parentId", "parentName", "createTime"],
    components: {CreateCard},
    data() {
        return{
            create: [],
        }
    },
    mounted() {
        this.loadCreateList();
    },
    methods:{
        loadCreateList() {
            getRequest("/group/createList",{id:this.id}).then(res =>{
                console.log("GroupCard", res);
                if (res.code === 200) {
                    this.create = res.data;
                }
            })
        },
    }
}
</script>

<style scoped>
.gcard {
    /*width: 400px;*/
    background-color: white;
    padding: 15px;
    display: flex;
    flex-wrap: wrap;
}
.gcard_text {
    font-size: 20px;
    font-weight: bold;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    color: #5a8da1;
}
.name_text{
    color: #5a8da1;
}
.gcard_top {
    width: 100%;
    /*display: flex;*/
}
.gcard_btn {
    width: 100px;
    float: right;
    /*margin-right: 10px;*/
    /*display: inline-block;*/
}
.gcard_body{
    width: 100%;
    display: flex;
    margin-top: 10px;
    flex-wrap: wrap;
}
.body_body {
    width: 100%;
}
.text_color{
    color: #5a8da1;
}
.gcard_foot{
    width: 100%;
    display: flex;
    margin-top: 10px;
}
.foot_card{
    margin-left: 15px;
}
</style>