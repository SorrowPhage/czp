<template>
    <div style="height: 100%;background-color: white">
        <div v-if="active===0" class="step_1">
            <div class="step_1_search">
                <div style="width: 250px;">
                    <el-input v-model="group" placeholder="请输入族群号/名称"></el-input>
                </div>
                <div style="width: 50px;margin-left: 10px">
                    <el-button plain type="primary" @click="getGroup">查找</el-button>
                </div>
            </div>
            <div class="search_result">
                <select-group-card v-for="g in groupList" :id="g.id"
                                   :key="g.id" :area="g.area" :avatar="g.avatar" :create-time="g.createTime" :des="g.des"
                                   :group-name="g.groupName" :name="g.name"
                                   @click.native="selectGroup(g.id)"
                />
            </div>
        </div>
        <div v-if="active===1" style="height: calc(100ch - 50px);width: 100%;overflow: hidden">
            <div class="BaseDragResize" style="height: 100%;width: 100%;">
                <vue-draggable-resizable :key="groupId"
                                         :resizable="false"
                                         :x="0"
                                         :y="0"
                                         class="BaseDragResize-drag"
                                         h="auto"
                                         parentLimitation
                                         w="auto"
                                         @resizing="onResize"
                >
                    <TreeSelect ref="czpTree" :getSelectData="getSelectData" :json="userList"
                                @wheel.prevent="handleTableWheel($event)"></TreeSelect>
                </vue-draggable-resizable>
            </div>
        </div>
        <div v-if="active===2">
            <el-row>
                <el-col :span="24">
                    <el-result icon="success" subTitle="您的加入请求已发送给族群管理员" title="申请成功">
                        <template slot="extra">
                            <el-button size="medium" type="primary" @click="result">返回</el-button>
                        </template>
                    </el-result>
                </el-col>
            </el-row>
        </div>
    </div>
</template>

<script>
import {getRequest, postRequest} from "@/api/api";
import PeopleCard from "@/views/user/PeopleCard";
import SelectGroupCard from "@/views/group/SelectGroupCard";
import 'vue2-org-tree/dist/style.css';
import TreeSelect from "@/views/group/TreeSelect";
import 'vue-draggable-resizable/dist/VueDraggableResizable.css'

export default {
    name: "FamilyInfo",
    components: {PeopleCard, SelectGroupCard, TreeSelect},
    data() {
        return {
            active: 0,
            group: '',
            groupList: [],
            userList: [],
            groupId: '',
            
        };
    },
    mounted() {
        this.loadData()
    },
    methods: {
        loadData() {
            getRequest("/group/recommend", {id: this.$store.state.CzpUser.id}).then(res => {
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            })
        },
        getGroup() {
            getRequest("/group/search", {id: this.group}).then(res => {
                if (res.code === 200) {
                    this.groupList = res.data;
                }
            })
        },
        selectGroup(id) {
            this.groupId = id;
            //写成族谱树，让用户点击某一个节点，加入
            getRequest("/group/userList", {id: this.groupId, userId: this.$store.state.CzpUser.id}).then(res => {
                if (res.code === 200) {
                    this.userList = res.data;
                    this.active = this.active + 1;
                } else {
                    this.$message({
                        message: res.message,
                        type: "warning",
                    })
                }
            })
        },
        handleTableWheel(event) {
            let obj = this.$refs.czpTree;
            return this.tableZoom(obj, event);
        },
        tableZoom(obj, event) {
            // 一开始默认是100%
            let zoom = parseInt(obj.style.zoom, 10) || 100;
            // 滚轮滚一下wheelDelta的值增加或减少120
            zoom += event.wheelDelta / 12;
            if (zoom > 0) {
                obj.style.zoom = zoom + "%";
            }
            return false;
        },
        onResize: function (x, y, width, height) {
            this.x = x
            this.y = y
            this.width = width
            this.height = height
        },
        
        selectUser(id) {
            postRequest("/audit/apply", {
                groupId: this.groupId,
                userId: this.$store.state.CzpUser.id,
                parentId: id
            }).then(res => {
                if (res.code === 200) {
                    this.$message({
                        message: res.message,
                        type: "success",
                    })
                    this.active = this.active + 1;
                }
            })
        },
        
        getSelectData(data) {
            //判断该数据是用户还是族群，若是族群则说明该用户想插入第一节点，若是用户，则是插入到其它用户下面
            if ("groupName" in data) {//拥有groupName属性，说明点击的是族群
                postRequest("/audit/apply", {groupId: this.groupId, userId: this.$store.state.CzpUser.id}).then(res => {
                    if (res.code === 200) {
                        this.$message({
                            message: res.message,
                            type: "success",
                        })
                        this.active = this.active + 1;
                    }
                })
            } else {//反之点击的是用户
                postRequest("/audit/apply", {
                    groupId: this.groupId,
                    userId: this.$store.state.CzpUser.id,
                    parentId: data.id
                }).then(res => {
                    if (res.code === 200) {
                        this.$message({
                            message: res.message,
                            type: "success",
                        })
                        this.active = this.active + 1;
                    }
                })
            }
            
        },
        
        result() {
            this.active = 0;
        },
    }
}
</script>

<style scoped lang="scss">
.step_box {
    height: 60px;
    padding: 15px;
}

.step_1 {

}

.step_1_search {
    width: 300px;
    display: flex;
    padding: 10px;
}

.search_result {
    width: 100%;
    display: grid;
    grid-template-columns: 30% 30% auto;
}
.BaseDragResize {
    position: relative;
    width: 100%;
    height: 100%;
    border: 1px solid #2c3e50;
    box-sizing: border-box;
    background-color: white;
    
    //居中
    display: flex;
    justify-content: center;
    align-items: center;
    //background: linear-gradient(-90deg, rgba(0, 0, 0, 0.1) 1px, transparent 1px) 0% 0% / 10px 10px, linear-gradient(rgba(0, 0, 0, 0.1) 1px, transparent 1px) 0% 0% / 10px 10px;
    &-drag {
        &.vdr {
            border: none;
        }
    }
}
</style>