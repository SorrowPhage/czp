<template>
    <div class="gcard">
        <div class="gcard_top">
            <div style="display: inline-block">
                <span class="gcard_text">{{groupName}}</span>
            </div>
        </div>
        <div class="gcard_body">
            <div class="body_body text_color">
                {{des}}
            </div>
            <div style="height: 20px;margin-top: 5px;margin-bottom: 5px">
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
    background-image: linear-gradient(#409EFF, #7ce6f1);
    padding: 15px;
    display: flex;
    flex-wrap: wrap;
    width: 120px;
    margin-left: 5px;
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
    height: 20px;
    /*display: flex;*/
}
.gcard_btn {
    /*width: 100px;*/
    float: right;
    /*margin-right: 10px;*/
    /*display: inline-block;*/
}
.gcard_body{
    width: 100%;
    display: flex;
    margin-top: 10px;
    flex-wrap: wrap;
    height: 120px;
}
.body_body {
    width: 100%;
    height: 100px;
    overflow: auto;
}
.text_color{
    color: #5a8da1;
}
.gcard_foot{
    width: 100%;
    display: flex;
    margin-top: 10px;
    height: 20px;
}
.foot_card{
    margin-left: 15px;
}
</style>