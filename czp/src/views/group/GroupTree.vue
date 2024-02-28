<template>
    <div style="width: 100%;height:calc(100vh - 50px);display: flex;flex-direction: column">
        <div class="op_lan">
            家族:
            <el-select v-model="value" placeholder="请选择" size="mini" filterable>
                <el-option
                    v-for="item in options"
                    :key="item.id"
                    :label="item.groupName"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-button type="primary" plain @click="laodGroupTreeHasSonGroupData" style="float: right;" size="mini">包含子级族群</el-button>
            <el-button type="primary" plain @click="laodGroupTreeHasOldGroupData" style="float: right;margin-right: 15px" size="mini">父级族群</el-button>
            <el-button type="primary" plain @click="loadGroupTreeData" style="float: right;margin-right: 15px" size="mini">搜索</el-button>
        </div>
        <div style="overflow: hidden;flex: 1;margin-top: 15px">
            <div class="BaseDragResize" style="height: 100%;width: 100%;">
                <vue-draggable-resizable  class="BaseDragResize-drag" w="auto" h="auto" :x="0" :y="0" :resizable="false" :key="groupId" parentLimitation
                     @resizing="onResize"
                >
                    <TreeChat :json="treeData"
                              ref="czpTree" @wheel.prevent="handleTableWheel($event)" ></TreeChat>
                </vue-draggable-resizable>
            </div>
        </div>
    </div>
</template>

<script>
import {hideLoading, hideLoadingAndNotify, showLoading} from "@/api/loading";
import {getRequest} from "@/api/api";
import 'vue2-org-tree/dist/style.css';
import TreeChat from "@/views/group/TreeChat";
import 'vue-draggable-resizable/dist/VueDraggableResizable.css'
export default {
    name: "GroupTree",
    components:{TreeChat},
    data() {
        return{
            options: [],
            value: '',
            treeData: {},
        }
    },
    computed:{
        groupId() {
            return this.treeData.id;
        },
    },
    mounted() {
        this.loadData();
    },
    methods:{
        loadData() {
            showLoading()
            //这个接口能查出所有的族群信息
            getRequest("/group/possess", {id: this.$store.state.CzpUser.id}).then(res => {
                // hideLoadingAndNotify(res);
                console.log(res)
                hideLoading()
                if (res.code === 200) {
                    this.options = res.data;
                }
            }).catch(err => {
                hideLoadingAndNotify(err);
                }
            );
        },
        loadGroupTreeData() {
            showLoading();
            getRequest("/group/tree", {id: this.value}).then(res => {
                console.log("GroupTree", res)
                // hideLoadingAndNotify(res);
                hideLoading()
                if (res.code === 200) {
                    this.treeData = res.data;
                }
            }).catch(err => {
                    hideLoadingAndNotify(err)
                // hideLoading();
                }
            );
        },
        laodGroupTreeHasOldGroupData() {
            showLoading();
            getRequest("/group/hasoldtree",{id:this.value}).then(res=>{
                hideLoading();
                if (res.code === 200) {
                    this.treeData = res.data;
                }
            })
        },
        laodGroupTreeHasSonGroupData() {
            showLoading();
            getRequest("/group/hassontree", {id: this.value}).then(res => {
                console.log("GroupTree", res)
                // hideLoadingAndNotify(res);
                hideLoading()
                if (res.code === 200) {
                    this.treeData = res.data;
                }
            }).catch(err => {
                    // hideLoadingAndNotify(err)
                    hideLoading();
                }
            );
        },
        renderContent(h, data) {
            if (data.sex === '女') {
                return (
                    <div  style="color: #f922f5;">
                        <div>
                            <span>{data.name}</span>
                        </div>
                    </div>
                );
            } else {
                return (
                    <div style="color: #227cf9;">
                        <div>
                            <span>{data.name}</span>
                        </div>
                    </div>
                );
            }
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
    }
}
</script>

<style scoped lang="scss">
.op_lan {
    background-color: #f2f2f2;
    border-left: 2px solid #367fff;
    padding: 5px;
    font-size: 14px;
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