<template>
    <div class="group_info" >
        <div style="height: 20px;width: 100%;">
            <span class="name_text">{{groupName}}</span>
            <span class="create_time">--{{createTime}}</span>
        </div>
        <div style="height: 81px;width: 100%;" >
            <div class="des"  style="width:100%;height:100%;overflow:auto;margin-top: 5px">{{des}}</div>
        </div>
        <div style="height: 30px;margin-top: 10px">
            <div style="width: 100%;height: 100%;display: flex">
                <create-card v-for="u in create" :key="u.id"
                             :avatar="u.avatar" :name="u.name"
                />
            </div>
        </div>
        <div style="height: 20px;width: 100%;line-height: 20px">
            <i class="el-icon-location-outline"></i>
            <span class="des">{{area}}</span>
            &nbsp;
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-people" viewBox="0 0 16 16">
                <path d="M15 14s1 0 1-1-1-4-5-4-5 3-5 4 1 1 1 1h8zm-7.978-1A.261.261 0 0 1 7 12.996c.001-.264.167-1.03.76-1.72C8.312 10.629 9.282 10 11 10c1.717 0 2.687.63 3.24 1.276.593.69.758 1.457.76 1.72l-.008.002a.274.274 0 0 1-.014.002H7.022zM11 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4zm3-2a3 3 0 1 1-6 0 3 3 0 0 1 6 0zM6.936 9.28a5.88 5.88 0 0 0-1.23-.247A7.35 7.35 0 0 0 5 9c-4 0-5 3-5 4 0 .667.333 1 1 1h4.216A2.238 2.238 0 0 1 5 13c0-1.01.377-2.042 1.09-2.904.243-.294.526-.569.846-.816zM4.92 10A5.493 5.493 0 0 0 4 13H1c0-.26.164-1.03.76-1.724.545-.636 1.492-1.256 3.16-1.275zM1.5 5.5a3 3 0 1 1 6 0 3 3 0 0 1-6 0zm3-2a2 2 0 1 0 0 4 2 2 0 0 0 0-4z"/>
            </svg>
            <span> {{num}} </span>
            <create-card style="display: inline-block"
                :avatar="clan.avatar" :name="clan.name"
            />
        </div>
    </div>
</template>

<script>
import {getRequest} from "@/api/api";
import CreateCard from "@/views/group/CreateCard";
export default {
    name: "SelectGroupCard",
    components:{CreateCard},
    props: ["id", "groupName", "des", "area", "parentId", "parentName", "createTime"],
    data() {
        return{
            num: '',
            create: [],
            clan:{
                avatar:"",
                name:""
            }
        }
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            getRequest("/group/nums",{id:this.id}).then(res=>{
                if (res.code === 200) {
                    this.num = res.data;
                }
            })
    
            getRequest("/group/createList",{id:this.id}).then(res =>{
                if (res.code === 200) {
                    this.create = res.data;
                }
            })
            
            getRequest("/group/clanu",{id:this.id}).then(res =>{
                if (res.code === 200) {
                    this.clan = res.data[0];
                }
            })
            
        },
    }
}
</script>

<style scoped>
.group_info {
    width: 300px;
    height: 180px;
    margin: 10px;
    cursor: pointer;
    border: 1px solid dimgrey;
    /*background-image: linear-gradient(*/
    /*    125deg,*/
    /*    #27ae60,*/
    /*    #2980b9*/
    /*);*/
    /*background-size: 400%;*/
    /*animation: bganimation 15s infinite;*/
    /*!*position: fixed;*!*/
}
@keyframes bganimation {
    0% {
        background-position: 0 50%;
    }
    
    50% {
        background-position: 100% 50%;
    }
    
    100% {
        background-position: 0 50%;
    }
}
.name_text {
    font-size: 30px;
    font-family: "华文行楷";
}
.create_time{
    font-size: 15px;
}
.des{
    font-family: "华文行楷";
    text-indent:2em
}
</style>